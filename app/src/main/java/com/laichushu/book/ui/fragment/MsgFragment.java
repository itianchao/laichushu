package com.laichushu.book.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.activity.MessageCommentDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.adapter.HomePageDynamicAdapter;
import com.laichushu.book.ui.adapter.SubMissionAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 * Created by wangtong on 2016/10/17.
 */
public class MsgFragment extends MvpFragment2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private TextView tvTitle;
    private RelativeLayout rlComment,rlLike;
    private PullLoadMoreRecyclerView mRecyclerView;
    private SubMissionAdapter subAdapter;
    private List<MessageCommentResult.DataBean> subData = new ArrayList<>();
    private RadioGroup radioGroup;
    private LinearLayout llContainer,llSys;
    private int PAGE_NO = 1;
    private boolean dibbleSub;

    @Override
    protected MessageCommentPresenter createPresenter() {
        return new MessageCommentPresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_msg);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        rlComment = (RelativeLayout) mRootView.findViewById(R.id.rl_comment);
        mRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_subMission);
        radioGroup = (RadioGroup) mRootView.findViewById(R.id.rg_msgList);
        llContainer = (LinearLayout) mRootView.findViewById(R.id.ll_container);
        llSys = (LinearLayout) mRootView.findViewById(R.id.ll_sysMsg);
        rlLike = (RelativeLayout) mRootView.findViewById(R.id.rl_like);
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("消息");

        rlComment.setOnClickListener(this);
        llSys.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        //初始化mRecyclerView 投稿
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        subAdapter = new SubMissionAdapter(mActivity, subData);
        mRecyclerView.setAdapter(subAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);

        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_comment:
                //作品管理
                Bundle homePage = new Bundle();
                UIUtil.openActivity(mActivity, MessageCommentDetailsActivity.class, homePage);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_perMsg:
                //首页
                dibbleSub=false;
                llSys.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                llContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_submissionMsg:
               //投稿
                llContainer.setVisibility(View.GONE);
                llSys.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mvpPresenter.setMsgType("2");
                if(!dibbleSub){
                    mvpPresenter.LoaCommentdData();
                }
                dibbleSub=true;
                break;
            case R.id.rb_sysMsg:
                //系统消息
                dibbleSub=false;
                llContainer.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                llSys.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_like:
                //喜欢
//UIUtil.openActivity(mActivity,);
                break;
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        subData.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoaCommentdData();//请求网络获取搜索列表

    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
    }

    @Override
    public void getMsgCommentDateSuccess(MessageCommentResult model) {
        subData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            ToastUtil.showToast("HomeUseFocusMerResult");
            subData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!subData.isEmpty()) {
                PAGE_NO++;
            } else {

            }
        } else {

        }
        subAdapter.refreshAdapter(subData);
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }


}
