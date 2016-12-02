package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.MessageLikeAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MsgLikeDetailsActivity extends MvpActivity2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mRecyclerView;
    private MessageLikeAdapter msgAdapter;
    private List<MessageCommentResult.DataBean> likeData = new ArrayList<>();
    private int PAGE_NO = 1;
    //1 : 喜欢  2. 打赏 3 关注 4私信 5 订阅
    private String type;

    @Override
    protected MessageCommentPresenter createPresenter() {
        return new MessageCommentPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_msg_like_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_likeMsg);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                //喜欢
                tvTitle.setText("喜欢");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_2);
                break;
            case "2":
                //打赏
                tvTitle.setText("打赏");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_6);
                break;

            case "3":
                //关注
                tvTitle.setText("关注");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_5);
                break;
            case "4":
                //私信
                tvTitle.setText("私信");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_7);
                break;
            case "5":
                //订阅
                tvTitle.setText("订阅");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_1);
                break;
            case "6":
                //订阅
                tvTitle.setText("活动通知");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_1);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_8);
                break;
            case "7":
                //订阅
                tvTitle.setText("其他消息");
                mvpPresenter.setMsgType(ConstantValue.MSG_TYPE_1);
                mvpPresenter.setSubType(ConstantValue.SUB_TYPE_9);
                break;
        }


        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);

        //初始化mRecyclerView
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        msgAdapter = new MessageLikeAdapter(this, likeData, type, mvpPresenter);
        mRecyclerView.setAdapter(msgAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);

        //请求数据
        likeData.clear();
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
    public void getMsgCommentDateSuccess(MessageCommentResult model) {
        LoggerUtil.toJson(model);
        likeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            likeData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!likeData.isEmpty()) {
                msgAdapter.refreshAdapter(likeData);
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
    public void getDataFail(String msg) {
        Logger.e(msg);
    }


    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        likeData.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
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
}
