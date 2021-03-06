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
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.find.eidt.findeditmainpage.FindEditMainPagePresenter;
import com.laichushu.book.mvp.find.eidt.findeditmainpage.FindEditMainPageView;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicdetailModel;
import com.laichushu.book.ui.adapter.EditCommentListAdapter;
import com.laichushu.book.ui.adapter.EditorFindCaseAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017年2月9日11:19:01
 * 编辑主页
 */
public class FindEditMainPageActivity extends MvpActivity2<FindEditMainPagePresenter> implements FindEditMainPageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {
    private ImageView ivBack, ivHeadImg, ivCollect, ivGrade, ivShare;
    private TextView tvTitle, tvRealName, tvIntroduction, tvTeamNum, tvGrade, tvEmptyTips;
    private PullLoadMoreRecyclerView mCaseRecyclerView, mCommentRecyclerView;
    private LinearLayout llFindMsg, llTeamwork, llCommentList;
    private String userId = null;
    private RatingBar numRb;
    //案列
    private EditorFindCaseAdapter caseAdapter;
    private List<HomeHotModel.DataBean> caseDate = new ArrayList<>();

    //合作
    private List<AuthorWorksModle.DataBean> mArticleData = new ArrayList<>();

    private RadioGroup radioGroup;
    private ScrollView scrollBrief;
    private EditCommentListAdapter commAdapter;
    private ArrayList<CommentDetailModle.DataBean> commDate = new ArrayList<>();
    private int PAGE_NO = 1;
    private EditText commentEt;

    private String type = null;
    private FindEditorInfoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FindEditMainPagePresenter createPresenter() {
        return new FindEditMainPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_edit_mypage);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        ivHeadImg = ((ImageView) inflate.findViewById(R.id.iv_userHeadImg));
        ivGrade = ((ImageView) inflate.findViewById(R.id.iv_userGrade));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        ivCollect = ((ImageView) inflate.findViewById(R.id.iv_title_another));
        ivShare = ((ImageView) inflate.findViewById(R.id.iv_title_other));
        tvRealName = ((TextView) inflate.findViewById(R.id.tv_userRealName));
        tvTeamNum = ((TextView) inflate.findViewById(R.id.tv_teamworkNum));
        llFindMsg = (LinearLayout) inflate.findViewById(R.id.ll_findMsg);
        commentEt = (EditText) inflate.findViewById(R.id.et_comment);
        llTeamwork = (LinearLayout) inflate.findViewById(R.id.ll_teamwork);
        mCaseRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_case);
        mCommentRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_commentList);
        tvGrade = (TextView) inflate.findViewById(R.id.iv_userGradeContent);
        numRb = (RatingBar) inflate.findViewById(R.id.ratbar_num);
        radioGroup = (RadioGroup) inflate.findViewById(R.id.rg_editorList);
        tvIntroduction = (TextView) inflate.findViewById(R.id.tv_brief);
        scrollBrief = (ScrollView) inflate.findViewById(R.id.scroll_brief);
        llCommentList = (LinearLayout) inflate.findViewById(R.id.ll_commentList);
        tvEmptyTips = (TextView) inflate.findViewById(R.id.tv_empTips);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("个人主页");
        userId = getIntent().getStringExtra("userId");
        ivCollect.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.activity_share);

        ivBack.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        llFindMsg.setOnClickListener(this);
        llTeamwork.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        commentEt.setOnEditorActionListener(this);
        //查询案列
        mCaseRecyclerView.setGridLayout(1);
        mCaseRecyclerView.setFooterViewText("加载中");
        caseAdapter = new EditorFindCaseAdapter(this, caseDate, mvpPresenter);
        mCaseRecyclerView.setAdapter(caseAdapter);
        mCaseRecyclerView.setOnPullLoadMoreListener(this);
        //查询评论
        mCommentRecyclerView.setGridLayout(1);
        mCommentRecyclerView.setFooterViewText("加载中");
        commAdapter = new EditCommentListAdapter(this, commDate, mvpPresenter);
        mCommentRecyclerView.setAdapter(commAdapter);
        mCommentRecyclerView.setOnPullLoadMoreListener(this);

        if (TextUtils.isEmpty(userId)) {
            ToastUtil.showToast("加载失败");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                }
            }, 30);
        } else {
            mvpPresenter.loadEditorInfoData(userId);
        }

        scrollBrief.setVisibility(View.VISIBLE);
    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("个人主页");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_title_other:
                //分享
                String shareContent = "#来出书为您推荐出书人"+model.getData().getName()+"#";
                String linkUrl = Base64Utils.getStringUrl(userId, ConstantValue.SHARE_TYPR_EDITOR);
                ShareUtil.showShare(mActivity, linkUrl, shareContent, model.getData().getPhoto(), model.getData().getServiceIntroduce(), model.getData().getNickName());
                break;
            case R.id.iv_title_another:
                //收藏
                if (null == model) {
                    ToastUtil.showToast("参数错误！");
                    return;
                }
                if (this.model.getData().isIsCollect()) {
                    mvpPresenter.loadCollectSaveDate(userId, ConstantValue.COLLECTEDITOR_TYPE, "1");
                } else {
                    mvpPresenter.loadCollectSaveDate(userId, ConstantValue.COLLECTEDITOR_TYPE, "0");
                }
                break;
            case R.id.ll_findMsg:
                //私信
                mvpPresenter.openSendPerMsgDialog(userId);
                break;
            case R.id.ll_teamwork:
                //合作  TODO 获取图书列表--》选择图书---》合作
                mvpPresenter.loadAuthorWorksData();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PAGE_NO = 1;
        tvEmptyTips.setVisibility(View.GONE);
        switch (checkedId) {
            case R.id.rb_introduce:
                //简介
                scrollBrief.setVisibility(View.VISIBLE);
                llCommentList.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.rb_case:
                //案列
                type = "1";
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.VISIBLE);
                mvpPresenter.loadFindArticleByCaseIdData(userId);

                break;
            case R.id.rb_evaluate:
                //评价
                type = "2";
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
            this.model = model;
            GlideUitl.loadRandImg(mActivity, model.getData().getPhoto(), ivHeadImg);
            tvRealName.setText(model.getData().getName());
            if (model.getData().getScore() != 0) {
                switch ((int) model.getData().getScore()) {
                    case 1:
                        tvGrade.setText("金牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_gold_medal2x, ivGrade);
                        break;
                    case 2:
                        tvGrade.setText("银牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_silver_medal2x, ivGrade);
                        break;
                    case 3:
                        tvGrade.setText("铜牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_copper_medal2x, ivGrade);
                        break;
                }
            }
            tvTeamNum.setText("已有" + model.getData().getCooperateNum() + "人合作");
            if (model.getData().isIsCollect()) {
                ivCollect.setImageResource(R.drawable.icon_likedwhite2x);
            } else {
                ivCollect.setImageResource(R.drawable.icon_likewhite2x);
            }
            //简介
            if (!TextUtils.isEmpty(model.getData().getServiceIntroduce()))
                tvIntroduction.setText(model.getData().getServiceIntroduce());

        } else

        {
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
            if (null != model.getData() && model.getData().size() > 0) {
                PAGE_NO++;
                commDate = model.getData();
            } else {
                ToastUtil.showToast(mActivity.getString(R.string.errMsg_empty_comment));
            }
            commAdapter.refreshAdapter(commDate);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            ErrorReloadData(1);
        }

    }

    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        dismissDialog();
        if (model.isSuccess()) {
            mArticleData.clear();
            if (!model.getData().isEmpty()) {
                mArticleData.addAll(model.getData());
                mvpPresenter.openTeamworkDialog(mArticleData, userId);
            } else {
                ToastUtil.showToast(R.string.errMsg_empty_workList);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getArticleVoteDataSuccess(RewardResult model) {
        dismissDialog();
        if (model.isSuccess()) {
            ToastUtil.showToast("合作成功！");
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getSendDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            commentEt.setText(null);
            onRefresh();
        } else {
            String errorMsg = model.getErrMsg();
            if (errorMsg.contains("该用户已经评分了")) {
                ToastUtil.showToast(errorMsg);
            } else {
                ToastUtil.showToast(model.getErrMsg());
            }
        }
    }

    @Override
    public void getEditorFindArticleDataSuccess(HomeHotModel model) {
        dismissDialog();
        caseDate.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCaseRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size() != 0) {
                caseDate = model.getData();
                caseAdapter.refreshAdapter(caseDate);
                PAGE_NO++;
            } else {
                if (PAGE_NO == 1) {
                    tvEmptyTips.setVisibility(View.VISIBLE);
                    tvEmptyTips.setText(R.string.errMsg_empty_workList);
                }
            }
        } else {

            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getSaveCollectSuccess(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {
                ToastUtil.showToast("收藏成功！");
                this.model.getData().setIsCollect(true);
                ivCollect.setImageResource(R.drawable.icon_likedwhite2x);
            } else {
                ToastUtil.showToast("取消收藏！");
                this.model.getData().setIsCollect(false);
                ivCollect.setImageResource(R.drawable.icon_likewhite2x);
            }

        } else {
            if (type.equals("0")) {
                ToastUtil.showToast("收藏失败！");
            } else {
                ToastUtil.showToast("取消收藏失败！");
            }

            LoggerUtil.toJson(model);
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
        if (type.equals("2")) {
            mvpPresenter.getCommentList_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadEditorCommentListData(userId);
        } else if (type.equals("1")) {
            mvpPresenter.getCaseIdParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadFindArticleByCaseIdData(userId);
        }

    }

    @Override
    public void onLoadMore() {
        if (type.equals("2")) {
            mvpPresenter.getCommentList_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadEditorCommentListData(userId);
        } else if (type.equals("1")) {
            mvpPresenter.getCaseIdParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadFindArticleByCaseIdData(userId);
        }
    }


    @Override
    public void getDataFail(String msg, int flg) {
        LoggerUtil.e(msg);
        dismissDialog();
        ErrorReloadData(flg);
    }

    @Override
    public void getDataFail5(String msg, int flg) {
        dismissDialog();
        LoggerUtil.e(msg);
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
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
                @Override
                public void reLoadData() {
                    if (flg == 1) {
                        refreshPage(LoadingPager.PageState.STATE_LOADING);
                        mvpPresenter.loadEditorInfoData(userId);
                    }
                }
            });
        } else {
            ToastUtil.showToast(mActivity.getString(R.string.errMsg_data_exception));
        }


    }
}
