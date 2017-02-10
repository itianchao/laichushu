package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.event.RefrushTopicManageEvent;
import com.laichushu.book.mvp.msg.topic.topicmanage.TopicManagePresenter;
import com.laichushu.book.mvp.msg.topic.topicmanage.TopicManageView;
import com.laichushu.book.ui.adapter.TopicManageAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构：话题管理
 * 2016年12月21日13:31:13
 */
public class TopicManageActivity extends MvpActivity2<TopicManagePresenter> implements TopicManageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView moreIv;
    private ImageView finishIv;
    private TextView titleTv;
    private PullLoadMoreRecyclerView mRecyclerView;
    private TopicManageAdapter topicAdapter;
    private List<HomeUseDyrResult.DataBean> topListDate = new ArrayList<>();
    private int PAGE_NO = 1;
    private String partyId = null;

    @Override
    protected TopicManagePresenter createPresenter() {
        return new TopicManagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_topic_manage);
        EventBus.getDefault().register(this);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        moreIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_topic_manager);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        moreIv.setVisibility(View.VISIBLE);
        titleTv.setText("话题管理");
        partyId = getIntent().getStringExtra("partyId");

        moreIv.setBackgroundResource(R.drawable.my_reset2x);
        finishIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);

        //初始化mRecyclerView
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        mRecyclerView.setOnPullLoadMoreListener(this);
        topicAdapter = new TopicManageAdapter(this, topListDate, mvpPresenter);
        mRecyclerView.setAdapter(topicAdapter);

        if (!TextUtils.isEmpty(partyId)) {
            mvpPresenter.loadMechanismTopicListData(partyId);
        }

    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("话题管理");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other:
                //发表机构话题
                Bundle bundle = new Bundle();
                bundle.putString("partyId", partyId);
                bundle.putString("type", "topicManage");
                UIUtil.openActivity(mActivity, HomePublishTopicActivity.class, bundle);
                break;
        }
    }

    @Override
    public void getDeleteTopicDateSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功！");
            onRefresh();
        } else {
            ToastUtil.showToast(model.getErrMsg());
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getTopicListDateSuccess(HomeUseDyrResult model) {
        topListDate.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            topListDate = model.getData();
            topicAdapter.refreshAdapter(topListDate);
            if (!topListDate.isEmpty()) {
                PAGE_NO++;
            } else {
                ToastUtil.showToast(model.getErrMsg());
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            refrushErrorView();
        }
    }

    @Override
    public void getSaveCollectSuccess(RewardResult model, String type,List<HomeUseDyrResult.DataBean> dataBeen,int position) {
        if (model.isSuccess()) {
                HomeUseDyrResult.DataBean bean = dataBeen.get(position);
            if (type.equals("0")) {
                bean.setCollect(true);
                bean.setCollectNum(bean.getCollectNum()+1);
                ToastUtil.showToast("收藏成功！");
            } else {
                bean.setCollect(false);
                bean.setCollectNum(bean.getCollectNum()-1);
                ToastUtil.showToast("取消收藏！");
            }
            topListDate.set(position, bean);
            topicAdapter.setDataBeen(topListDate);

        } else {
            if (type.equals("0")) {
                ToastUtil.showToast("收藏失败！");
            }else{
                ToastUtil.showToast("取消收藏失败！");
            }

            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO=1;
        topListDate.clear();
        mvpPresenter.getTopicList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadMechanismTopicListData(partyId);//请求网络获取搜索列表
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getTopicList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadMechanismTopicListData(partyId);//请求网络获取搜索列表
    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg.toString());
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dissmissDialog() {
        dismissProgressDialog();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushTopicManageEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                mvpPresenter.loadMechanismTopicListData(partyId);
            }
        });
    }
}
