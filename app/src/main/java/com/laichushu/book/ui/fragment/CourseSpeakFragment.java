package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.SpeakpdfDetail_Paramet;
import com.laichushu.book.bean.otherbean.SpeakListModle;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.CourseraSpeakAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 讲义
 * Created by wangtong on 2017/1/9.
 */

public class CourseSpeakFragment extends MvpFragment2 implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private CourseraSpeakAdapter mAdapter;
    private ArrayList<SpeakListModle.DataBean.HandOutsListBean> mData;
    private int pageNo = 1;
    private String pageSize = ConstantValue.PAGESIZE;
    private ImageView emptyIv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_coursespeak);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_speak);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setPullRefreshEnable(false);
//        mRecyclerView.setPushRefreshEnable(t);
        mAdapter = new CourseraSpeakAdapter(mActivity, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        String lessonId = getArguments().getString("lessonId");
        loadSpeakListData(lessonId);
    }

    private void loadSpeakListData(String lessonId) {
        SpeakpdfDetail_Paramet paramet = new SpeakpdfDetail_Paramet(lessonId, pageNo + "", pageSize);
        addSubscription(apiStores.getSpeakpdfList(paramet), new ApiCallback<SpeakListModle>() {
            @Override
            public void onSuccess(SpeakListModle model) {
                UIUtil.postPullLoadMoreCompleted(mRecyclerView);
                if (model.isSuccess()) {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    if (model.getData().getHandOutsList().isEmpty()) {
                        emptyIv.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        mData = model.getData().getHandOutsList();
                        emptyIv.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mAdapter.setmData(mData);
                    }
                } else {
                    reloadErrorBtn();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                reloadErrorBtn();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        refreshPage(LoadingPager.PageState.STATE_LOADING);
        mData.clear();
        initData();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onLoadMore() {

    }

    /**
     * 重新加载
     */
    public void reloadErrorBtn() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                onRefresh();
            }
        });
    }
}
