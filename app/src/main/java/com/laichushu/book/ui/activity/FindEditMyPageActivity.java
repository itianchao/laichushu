package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.findeditmypage.FindEditMyPagePresenter;
import com.laichushu.book.mvp.findeditmypage.FindEditMyPageView;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;
import com.laichushu.book.ui.adapter.EditCommentListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

public class FindEditMyPageActivity extends MvpActivity2<FindEditMyPagePresenter> implements FindEditMyPageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {
    private ImageView ivBack, ivHeadImg;
    private TextView tvTitle, tvRealName, tvIntroduction;
    private PullLoadMoreRecyclerView mCaseRecyclerView, mCommentRecyclerView;
    private LinearLayout llFindMsg, llTeamwork, llCommentList;
    private String userId = null;
    private RatingBar ratingBar, numRb;
    ;
    private RadioGroup radioGroup;
    private ScrollView scrollBrief;
    private EditCommentListAdapter commAdapter;
    private ArrayList<CommentDetailModle.DataBean> commDate = new ArrayList<>();
    private int PAGE_NO = 1;
    private EditText commentEt;

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
        ivHeadImg = ((ImageView) inflate.findViewById(R.id.iv_userHeadImg));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRealName = ((TextView) inflate.findViewById(R.id.tv_userRealName));
        llFindMsg = (LinearLayout) inflate.findViewById(R.id.ll_findMsg);
        commentEt = (EditText) inflate.findViewById(R.id.et_comment);
        llTeamwork = (LinearLayout) inflate.findViewById(R.id.ll_teamwork);
        mCaseRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_case);
        mCommentRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_commentList);
        ratingBar = (RatingBar) inflate.findViewById(R.id.ratbar_detail_num);
        numRb = (RatingBar) inflate.findViewById(R.id.ratbar_num);
        radioGroup = (RadioGroup) inflate.findViewById(R.id.rg_editorList);
        tvIntroduction = (TextView) inflate.findViewById(R.id.tv_brief);
        scrollBrief = (ScrollView) inflate.findViewById(R.id.scroll_brief);
        llCommentList = (LinearLayout) inflate.findViewById(R.id.ll_commentList);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("个人主页");
        userId = getIntent().getStringExtra("userId");

        ivBack.setOnClickListener(this);
        llFindMsg.setOnClickListener(this);
        llTeamwork.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        commentEt.setOnEditorActionListener(this);

        mCommentRecyclerView.setGridLayout(1);
        mCommentRecyclerView.setFooterViewText("加载中");
        commAdapter = new EditCommentListAdapter(this, commDate, mvpPresenter);
        mCommentRecyclerView.setAdapter(commAdapter);
        mCommentRecyclerView.setOnPullLoadMoreListener(this);

        if (TextUtils.isEmpty(userId)) {
            ToastUtil.showToast("加载失败");
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            mvpPresenter.loadEditorInfoData(userId);
        }

        scrollBrief.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.ll_findMsg:
                //私信
                mvpPresenter.openSendPerMsgDialog(userId);
                break;
            case R.id.ll_teamwork:
                //合作
                mvpPresenter.openTeamworkDialog();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_introduce:
                //简介
                scrollBrief.setVisibility(View.VISIBLE);
                llCommentList.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.rb_case:
                //案列
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.VISIBLE);


                break;
            case R.id.rb_evaluate:
                //评价
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.VISIBLE);
                mCaseRecyclerView.setVisibility(View.GONE);
                mvpPresenter.loadEditorCommentListData(userId);
                break;
        }
    }

    @Override
    public void getEditorInfoDataSuccess(FindEditorInfoModel model) {
        if (model.isSuccess() && model.getData() != null) {
            GlideUitl.loadRandImg(mActivity, model.getData().getPhoto(), ivHeadImg);
            tvRealName.setText(model.getData().getName());
            if (!TextUtils.isEmpty(model.getData().getLevel())) {
                ratingBar.setNumStars(Integer.parseInt(model.getData().getLevel()));
            }
            //简介
            if (!TextUtils.isEmpty(model.getData().getIntroduction()))
                tvIntroduction.setText(model.getData().getIntroduction());
            //评价

        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }


    @Override
    public void SaveScoreLikeData(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//点赞
                Logger.e("点赞");
            } else {//取消赞
                Logger.e("取消赞");
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getEditorCommentListDataSuccess(TopicdetailModel model) {
        dismissDialog();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCommentRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size() != 0) {
                PAGE_NO++;
                commDate = model.getData();
                commAdapter.refreshAdapter(commDate);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getSendDataSuccess(SendCommentMoudle model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            commentEt.setText(null);
            onRefresh();
        } else {
            String errorMsg = model.getErrMsg();
            if (errorMsg.contains("该用户已经评分了")) {
                ToastUtil.showToast(errorMsg);
            } else {
                ToastUtil.showToast("发送失败");
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String conent = commentEt.getText().toString();
            if (!TextUtils.isEmpty(conent)) {
                mvpPresenter.loadSendCommentData(userId, conent, (int) numRb.getRating() + "");
                commentEt.setText("");
                UIUtil.hideKeyboard(commentEt);
            } else {
                ToastUtil.showToast("请输入评论内容");
            }
        }
        return false;
    }

    @Override
    public void getSendMsgToPartyDataSuccess(RewardResult model) {
        dismissDialog();
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
        } else {
            ToastUtil.showToast("发送失败");
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        mvpPresenter.getCommentList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadEditorCommentListData(userId);
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getCommentList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadEditorCommentListData(userId);
    }


    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail5(String msg) {
        LoggerUtil.e(msg);
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
