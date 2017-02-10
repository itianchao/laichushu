package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.mvp.find.mechanism.mechanismcollect.MechanismCollectPresenter;
import com.laichushu.book.mvp.find.mechanism.mechanismcollect.MechanismCollectView;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.adapter.MechanismCollectAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 我的机构收藏页面
 */
public class MechanismCollectActivity extends MvpActivity2<MechanismCollectPresenter> implements MechanismCollectView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private MechanismCollectAdapter mAdapter;
    private PullLoadMoreRecyclerView mRecyclerView;
    private ArrayList<HomeHotModel.DataBean> collData = new ArrayList<>();
    private int PAGE_NO = 1;
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    protected MechanismCollectPresenter createPresenter() {
        return new MechanismCollectPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanism_collect);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_mechanism);
        finishIv.setOnClickListener(this);
        titleTv.setText("我收藏的机构");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        articleId = getIntent().getStringExtra("articleId");
        //初始化mRecyclerView Scan
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        mAdapter = new MechanismCollectAdapter(this, collData, mvpPresenter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.LoadCollectionData();
    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("我收藏的机构");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                break;
        }
    }

    @Override
    public void getCollectionDataSuccess(HomeHotModel model) {
        collData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            collData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!collData.isEmpty()) {
                mAdapter.setmData(collData);
                PAGE_NO++;
            } else {
                ToastUtil.showToast("没有更多数据");
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refrushErrorView();
        }
    }

    /**
     * 获取机构列表
     *
     * @param model
     */
    @Override
    public void getDataSuccess(MechanismListBean model, String partyId) {
        dismissDialog();
        if (model.isSuccess()) {
            if (null != model) {
                for (int i = 0; i < model.getData().size(); i++) {
                    if (model.getData().get(i).getId().equals(partyId)) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", model.getData().get(i));
                        bundle.putString("articleId", articleId);
                        UIUtil.openActivity(mActivity, MechanismDetailActivity.class, bundle);
                    }
                }
            } else {

            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        collData.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }

    /**
     * 重新加载
     */
    public void refrushErrorView() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.LoadCollectionData();
            }
        });
    }
}
