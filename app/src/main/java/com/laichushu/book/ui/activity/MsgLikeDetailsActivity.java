package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.event.RefrushPerInfoEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.msg.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.msg.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.MessageLikeAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ModelUtils;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息界面
 */
public class MsgLikeDetailsActivity extends MvpActivity2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mRecyclerView;
    private MessageLikeAdapter msgAdapter;
    private List<MessageCommentResult.DataBean> likeData = new ArrayList<>();
    private int PAGE_NO = 1;
    //1 : 喜欢  2. 打赏 3 关注 4私信 5 订阅
    private String type;
    private Gson gson;
    private GsonBuilder builder;

    @Override
    protected MessageCommentPresenter createPresenter() {
        return new MessageCommentPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_msg_like_details);
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_likeMsg);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        builder = new GsonBuilder();
        gson = builder.create();
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
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }


    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        likeData.clear();
        if (type.equals("4")) {
            mvpPresenter.getInfoParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadPerInfoDetailsData();//请求网络获取搜索列表
        } else {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
        }
    }

    @Override
    public void onLoadMore() {

        if (type.equals("4")) {
            mvpPresenter.getInfoParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadPerInfoDetailsData();//请求网络获取搜索列表
        } else {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoaCommentdData();//请求网络获取搜索列表
        }
    }

    @Override
    public void getBookDetailsDateSuccess(HomeHotModel model, int position) {

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
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (!model.getData().isEmpty()) {
                likeData.clear();
                likeData = model.getData();
                msgAdapter.refreshAdapter(likeData);
                PAGE_NO++;
            } else {
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
     *
     * @param model
     */
    @Override
    public void getSendDataSuccess(RewardResult model) {
    }

    @Override
    public void getDelPerIdfoDataSuccess(RewardResult model) {

    }

    /**
     * 跳转图书详情
     *
     * @param model
     */
    @Override
    public void getBookDetailsByIdDataSuccess(BookDetailsModle model) {
        if (model.isSuccess()) {
            //跳转图书详情页
            Bundle bundle = new Bundle();
//        String bd = gson.toJson(model, BookDetailsModle.class);
//        HomeHotModel.DataBean homeHotModel = gson.fromJson(bd, new TypeToken<HomeHotModel.DataBean>() {}.getType());
            HomeHotModel.DataBean dataBean = ModelUtils.bean2HotBean(model);
            bundle.putParcelable("bean", dataBean);
            bundle.putString("pageMsg", "消息喜欢");
            UIUtil.openActivity(this, BookDetailActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }


    }

    @Override
    public void messageDeleteCommentSuccess(RewardResult model, int position) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            likeData.remove(position);
            msgAdapter.refreshAdapter(likeData);
        } else {
            ToastUtil.showToast("删除失败");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushPerInfoEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            type = "3";
            initData();
        }
    }

}
