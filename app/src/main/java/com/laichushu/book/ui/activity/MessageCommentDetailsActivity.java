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
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.msg.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.msg.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.MessageCommentAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ModelUtils;
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
        MessageCommentPresenter messageCommentPresenter = new MessageCommentPresenter(this);
        messageCommentPresenter.setmActivity(this);
        return messageCommentPresenter;
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
        msgAdapter = new MessageCommentAdapter(this, commData, mvpPresenter);
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
        PAGE_NO = 1;
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
            if (null != model.getData() && !model.getData().isEmpty()) {
                commData = model.getData();
                PAGE_NO++;
            } else {
                ToastUtil.showToast(R.string.errMsg_empty);
            }
            msgAdapter.refreshAdapter(commData);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ErrorReloadData(1);
        }
    }

    @Override
    public void getBookDetailsDateSuccess(HomeHotModel model, int position) {

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
        if (model.isSuccess()) {
            //跳转图书详情页
            Bundle bundle = new Bundle();
            HomeHotModel.DataBean dataBean = ModelUtils.bean2HotBean(model);
            bundle.putParcelable("bean", dataBean);
            bundle.putString("pageMsg", "消息喜欢");
            UIUtil.openActivity(this, BookDetailActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 删除评论
     *
     * @param model
     * @param position
     */
    @Override
    public void messageDeleteCommentSuccess(RewardResult model, int position) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            commData.remove(position);
            msgAdapter.refreshAdapter(commData);
        } else {
            ToastUtil.showToast("删除失败");
        }
    }

    @Override
    public void getDataFail(String msg, int flg) {
        LoggerUtil.e(msg);
        if (msg.equals("删除评论")) {
            ToastUtil.showToast("删除失败");
        }
        ErrorReloadData(flg);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    public void ErrorReloadData(final int flg) {
        if (flg == 1) {
//            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ToastUtil.showToast(R.string.errMsg_data);
        }
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                if (flg == 1) {
                    refreshPage(LoadingPager.PageState.STATE_LOADING);
                    mvpPresenter.LoaCommentdData();
                } else if (flg == 2 | flg == 3 | flg == 4 | flg == 5 | flg == 6 | flg == 7 | flg == 8) {
                    ToastUtil.showToast(R.string.errMsg_data);
                }
            }
        });
    }
}
