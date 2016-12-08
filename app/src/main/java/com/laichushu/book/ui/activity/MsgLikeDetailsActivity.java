package com.laichushu.book.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.MessageLikeAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.MineFragment;
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
    private MsgLikeDetailsActivity.UpdateReceiver mUpdateReceiver;
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
        registerPlayerReceiver();
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                //喜欢
                tvTitle.setText("喜欢");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_2);
                break;
            case "2":
                //打赏
                tvTitle.setText("打赏");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_6);
                break;

            case "3":
                //关注
                tvTitle.setText("关注");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_5);
                break;
            case "4":
                //私信
                tvTitle.setText("私信");
                mvpPresenter.LoadPerInfoDetailsData();
                break;
            case "5":
                //订阅
                tvTitle.setText("订阅");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_3);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_1);
                break;
            case "6":
                //活动通知
                tvTitle.setText("活动通知");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_1);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_7);
                break;
            case "7":
                //其他消息
                tvTitle.setText("其他消息");
                mvpPresenter.getParamet().setMsgType(ConstantValue.MSG_TYPE_1);
                mvpPresenter.getParamet().setSubType(ConstantValue.SUB_TYPE_8);
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
        if (!type.equals("4")) {
            mvpPresenter.LoaCommentdData();
        }

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
        if (type.equals("4")) {
            mvpPresenter.LoadPerInfoDetailsData();//请求网络获取搜索列表
        } else {
            mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
        }
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        if (type.equals("4")) {
            mvpPresenter.LoadPerInfoDetailsData();//请求网络获取搜索列表
        } else {
            mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
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

    /**
     * 获取消息列表
     *
     * @param model
     */
    @Override
    public void getPerInfoListDateSuccess(MessageCommentResult model) {
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
                ToastUtil.showToast("没有更多信息！");
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
    }

    /**
     * 获取图书详情
     *
     * @param model
     */
    @Override
    public void getPerInfoDetailsDateSuccess(PerMsgInfoReward model) {

    }

    /**
     * 发送评论详情
     * @param model
     */
    @Override
    public void getSendDataSuccess(RewardResult model) {
    }

    @Override
    public void getDelPerIdfoDataSuccess(RewardResult model) {

    }
    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConstantValue.ACTION_UPDATE_DATA_PERINFO.equals(action)) {
                //更新信息
                 type="4";
                initData();
                ToastUtil.showToast("update date!");
            }
        }
    }
    private void registerPlayerReceiver() {
        if (mUpdateReceiver == null) {
            mUpdateReceiver = new MsgLikeDetailsActivity.UpdateReceiver();

            IntentFilter filter = new IntentFilter();
            filter.addCategory(mActivity.getPackageName());

            filter.addAction(ConstantValue.ACTION_UPDATE_DATA_PERINFO);
            mActivity.registerReceiver(mUpdateReceiver, filter);
        }
    }
}
