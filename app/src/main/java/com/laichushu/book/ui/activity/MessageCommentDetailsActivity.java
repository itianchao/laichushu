package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.MessageCommentAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageCommentDetailsActivity extends MvpActivity2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mRecyclerView;
    private MessageCommentAdapter msgAdapter;
    private List<MessageCommentResult.DataBean> commData = new ArrayList<>();
    private int PAGE_NO = 1;

    @Override
    protected MessageCommentPresenter createPresenter() {

        return new MessageCommentPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_message_comment_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_comment);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("评论");
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);

        //初始化mRecyclerView 评论
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        msgAdapter = new MessageCommentAdapter(this, commData,mvpPresenter);
        mRecyclerView.setAdapter(msgAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_3);
        mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_4);
        mvpPresenter.LoaCommentdData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO=1;
        commData.clear();
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
        commData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            commData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!commData.isEmpty()) {
                msgAdapter.refreshAdapter(commData);
                PAGE_NO++;
            } else {

            }
        } else {
            if (model.getData().size() == 0) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                ToastUtil.showToast("没有更多信息！");
            }
        }
    }

    @Override
    public void getBookDetailsDateSuccess(HomeHotModel model, int position) {
        //跳转图书详情页
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", model.getData().get(0));
        UIUtil.openActivity(this, BookDetailActivity.class, bundle);
    }

    @Override
    public void sendMsgDetailsDateSuccess(RewardResult model) {

    }

    @Override
    public void getPerInfoListDateSuccess(MessageCommentResult model) {

    }

    @Override
    public void getPerInfoDetailsDateSuccess(PerMsgInfoReward model) {

    }

    @Override
    public void getSendDataSuccess(RewardResult model) {

    }

    @Override
    public void getDelPerIdfoDataSuccess(RewardResult model) {

    }

    @Override
    public void getBookDetailsByIdDataSuccess(BookDetailsModle model) {

    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }


}
