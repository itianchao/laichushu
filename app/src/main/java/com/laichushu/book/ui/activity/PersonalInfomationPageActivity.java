package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.event.RefrushMineEvent;
import com.laichushu.book.event.RefrushPerInfoEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.adapter.PersonalInfomationAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfomationPageActivity extends MvpActivity2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle, tvRight;
    private LinearLayout llMsg;
    private EditText edInput;
    private TextView ivSendMsg;
    private PullLoadMoreRecyclerView mRecyclerView;
    private PersonalInfomationAdapter perAdapter;
    private List<PerMsgInfoReward.DataBean> msgData = new ArrayList<>();
    private int PAGE_NO = 1;
    private MessageCommentResult.DataBean dataBean;
    private String sendId, msgId;

    @Override
    protected MessageCommentPresenter createPresenter() {
        return new MessageCommentPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_personal_infomation_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        edInput = (EditText) inflate.findViewById(R.id.et_sendMsg);
        ivSendMsg = (TextView) inflate.findViewById(R.id.iv_sendMsg);
        llMsg = ((LinearLayout) inflate.findViewById(R.id.ll_sendMsg));
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_perInfoDetails);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        dataBean = (MessageCommentResult.DataBean) getIntent().getSerializableExtra("perInfoDetails");
        tvTitle.setText(dataBean.getSenderName());
        tvTitle.setVisibility(View.VISIBLE);
        tvRight.setText("删除");
        tvRight.setVisibility(View.VISIBLE);

        ivBack.setOnClickListener(this);
        ivSendMsg.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        //初始化mRecyclerView
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        perAdapter = new PersonalInfomationAdapter(this, msgData);
        mRecyclerView.setAdapter(perAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        sendId = dataBean.getSenderId();
        msgId = dataBean.getMsgId();
        mvpPresenter.LoadPerDetailsData(sendId, msgId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_sendMsg:
                mvpPresenter.topicDetailCommentSave(edInput, dataBean.getMsgId());
                break;
            case R.id.tv_title_right:
                mvpPresenter.loadDelPerInfoDetails(sendId, msgId);
                break;
        }
    }

    @Override
    public void onLoadMore() {
        PAGE_NO = 1;
        msgData.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadPerDetailsData(sendId, msgId);
        //请求网络获取搜索列表

    }

    @Override
    public void onRefresh() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadPerDetailsData(sendId, msgId);
        ;//请求网络获取搜索列表
    }

    /**
     * 获取私信详情
     *
     * @param model
     */
    @Override
    public void getPerInfoDetailsDateSuccess(PerMsgInfoReward model) {
        LoggerUtil.toJson(model);
        msgData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            msgData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!msgData.isEmpty()) {
                perAdapter.refreshAdapter(msgData);
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
    public void finish() {
        super.finish();
        EventBus.getDefault().postSticky(new RefrushPerInfoEvent(true));
    }

    /**
     * 私信回复
     *
     * @param model
     */
    @Override
    public void getSendDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            edInput.setText("");
            onLoadMore();
        } else {
            ToastUtil.showToast("发送失败");
        }
    }

    /**
     * 删除私信详情
     *
     * @param model
     */
    @Override
    public void getDelPerIdfoDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功！");
            mActivity.finish();
            //发广播
            Intent intent = new Intent(ConstantValue.ACTION_UPDATE_DATA_PERINFO);
            intent.addCategory(mActivity.getPackageName());
            mActivity.sendBroadcast(intent);
        } else {
            ToastUtil.showToast("删除失败");
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void getBookDetailsByIdDataSuccess(BookDetailsModle model) {

    }

    //--------------------------------------

    @Override
    public void getMsgCommentDateSuccess(MessageCommentResult model) {

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
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }


}
