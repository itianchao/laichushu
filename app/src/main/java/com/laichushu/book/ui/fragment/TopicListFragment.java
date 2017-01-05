package com.laichushu.book.ui.fragment;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListPresenter;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListView;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.ui.adapter.MechanismTopicListAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 话题列表
 * Created by wangtong on 2016/11/26.
 */

public class TopicListFragment extends MvpFragment2<MechanismTopicListPresenter> implements MechanismTopicListView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView topicRyc;
    private String id;//机构Id
    private int pageNo = 1;
    private MechanismTopicListAdapter mAdapter;
    private ArrayList<MechanismTopicListModel.DataBean> mData = new ArrayList<>();

    @Override
    protected MechanismTopicListPresenter createPresenter() {
        return new MechanismTopicListPresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_mechanism_topic);
        topicRyc = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_topic);
        topicRyc.setLinearLayout();
        topicRyc.setOnPullLoadMoreListener(this);
        topicRyc.setFooterViewText("加载中");
        mAdapter = new MechanismTopicListAdapter(mData, mActivity,1);
        topicRyc.setAdapter(mAdapter);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        id = ((MechanismDetailActivity) getActivity()).getBean().getId();
        if (mData.isEmpty()) {
            mvpPresenter.loadMechanismTopicListData(id);
        }else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.loadMechanismTopicListData(id);
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.loadMechanismTopicListData(id);
    }

    @Override
    public void getDataSuccess(MechanismTopicListModel model) {
        topicRyc.setPullLoadMoreCompleted();
        if (model.isSuccess()) {
            if (pageNo == 1) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
            if (model.getData() != null && !model.getData().isEmpty()) {
                mData.addAll(model.getData());
                pageNo++;
                mAdapter.setmData(mData);
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            refrushData();
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        refrushData();
        LoggerUtil.e(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    public void refrushData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
            }
        });
    }
}
