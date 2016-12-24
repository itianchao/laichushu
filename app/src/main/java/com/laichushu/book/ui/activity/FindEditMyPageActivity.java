package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findeditmypage.FindEditMyPagePresenter;
import com.laichushu.book.mvp.findeditmypage.FindEditMyPageView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class FindEditMyPageActivity extends MvpActivity2<FindEditMyPagePresenter> implements FindEditMyPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mVideoRecyclerView, mFileRecyclerView, mMineRecyclerView;
    private LinearLayout llFindMsg, llTeamwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FindEditMyPagePresenter createPresenter() {
        return new FindEditMyPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_edit_mypage);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        llFindMsg = (LinearLayout) inflate.findViewById(R.id.ll_findMsg);
        llTeamwork = (LinearLayout) inflate.findViewById(R.id.ll_teamwork);
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_video);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("个人主页");

        ivBack.setOnClickListener(this);
        llFindMsg.setOnClickListener(this);
        llTeamwork.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.ll_findMsg:
                //私信
                mvpPresenter.openSendPerMsgDialog();
                break;
            case R.id.ll_teamwork:
                //合作
                mvpPresenter.openTeamworkDialog();
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
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }
}
