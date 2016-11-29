package com.laichushu.book.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.mvp.messagecomment.MessageCommentView;
import com.laichushu.book.ui.activity.MessageCommentDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.adapter.SubMissionAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 消息
 * Created by wangtong on 2016/10/17.
 */
public class MsgFragment extends MvpFragment2<MessageCommentPresenter> implements MessageCommentView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private TextView tvTitle;
    private RelativeLayout rlComment;
    private PullLoadMoreRecyclerView mRecyclerView;
    private SubMissionAdapter subAdapter;
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
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("消息");

        rlComment.setOnClickListener(this);

        mvpPresenter.setMsgType("2");
        mvpPresenter.LoaCommentdData();
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void getMsgCommentDateSuccess(MessageCommentResult model) {

    }

    @Override
    public void getDataFail(String msg) {

    }
}
