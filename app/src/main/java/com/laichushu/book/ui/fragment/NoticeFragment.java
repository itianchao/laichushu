package com.laichushu.book.ui.fragment;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.mvp.notice.NoticeModle;
import com.laichushu.book.mvp.notice.NoticePresenter;
import com.laichushu.book.mvp.notice.NoticeView;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.ui.adapter.NoticeAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 公告
 * Created by wangtong on 2016/11/26.
 */

public class NoticeFragment extends MvpFragment2<NoticePresenter> implements NoticeView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView noticemRyv;
    private ArrayList<NoticeModle.DataBean> mData = new ArrayList<>();
    private int pageNo = 1;
    private String id;
    private NoticeAdapter mAdapter;

    @Override
    protected NoticePresenter createPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_notice);
        noticemRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_notice);
        noticemRyv.setLinearLayout();
        noticemRyv.setOnPullLoadMoreListener(this);
        mAdapter = new NoticeAdapter(mActivity, mData);
        noticemRyv.setAdapter(mAdapter);
        noticemRyv.setFooterViewText("加载中");
        return mSuccessView;
    }

    @Override
    protected void initData() {

        id = ((MechanismDetailActivity) getActivity()).getBean().getId();
        if (mData.isEmpty()) {
            mvpPresenter.loadNoticeListData(id);
        }else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
    }

    @Override
    public void getDataSuccess(NoticeModle model) {
        noticemRyv.setPullLoadMoreCompleted();
        if (model.isSuccess()) {
            if (pageNo ==1){
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
            if (model.getData()!=null &&model.getData().size()>0){
                mData.addAll(model.getData());

                pageNo++;

                mAdapter.notifyDataSetChanged();
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            refreshData();
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        refreshData();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    public void refreshData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {

            }
        });
    }

    /**
     * 下拉
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadNoticeListData(id);
    }

    /**
     * 上拉
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadNoticeListData(id);
    }
}
