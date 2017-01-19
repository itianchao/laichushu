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
import com.laichushu.book.bean.netbean.FindServerInfoModel;
import com.laichushu.book.bean.netbean.FindServiceItemListModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.find.service.findservicemainpage.FindServiceMainPagePresenter;
import com.laichushu.book.mvp.find.service.findservicemainpage.FindServiceMainPageView;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicdetailModel;
import com.laichushu.book.ui.adapter.ServiceCommentListAdapter;
import com.laichushu.book.ui.adapter.ServiceFindCaseAdapter;
import com.laichushu.book.ui.adapter.ServiceFindServiceAdapter;
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
 * 发现--服务主页
 * Created by PCPC on 2016/12/29.
 */

public class FindServerMainPageActivity extends MvpActivity2<FindServiceMainPagePresenter> implements FindServiceMainPageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {
    private ImageView ivBack, ivHeadImg, ivCollect, ivShare;
    private TextView tvTitle, tvRealName, tvIntroduction, tvTeamNum;
    private PullLoadMoreRecyclerView mCaseRecyclerView, mCommentRecyclerView, mServiceRecyclerView;
    private LinearLayout llFindMsg, llTeamwork, llCommentList;
    private String userId = null;
    private RatingBar ratingBar, numRb;
    //案列
    private ServiceFindCaseAdapter caseAdapter;
    private List<HomeHotModel.DataBean> caseDate = new ArrayList<>();
    //服务
    private List<FindServiceItemListModel.DataBean> serviceDate = new ArrayList<>();
    private ServiceFindServiceAdapter serviceAdapter;
    //合作
    private List<AuthorWorksModle.DataBean> mArticleData = new ArrayList<>();
    private RadioGroup radioGroup;
    private ScrollView scrollBrief;
    private ServiceCommentListAdapter commAdapter;
    private ArrayList<CommentDetailModle.DataBean> commDate = new ArrayList<>();
    private int PAGE_NO = 1;
    private EditText commentEt;

    private String type = null;

    private FindServerInfoModel.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FindServiceMainPagePresenter createPresenter() {
        return new FindServiceMainPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_service_mainpage);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        ivHeadImg = ((ImageView) inflate.findViewById(R.id.iv_userHeadImg));
        ivCollect = ((ImageView) inflate.findViewById(R.id.iv_title_another));
        ivShare = ((ImageView) inflate.findViewById(R.id.iv_title_other));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRealName = ((TextView) inflate.findViewById(R.id.tv_userRealName));
        tvTeamNum = ((TextView) inflate.findViewById(R.id.tv_teamworkNum));
        llFindMsg = (LinearLayout) inflate.findViewById(R.id.ll_findMsg);
        commentEt = (EditText) inflate.findViewById(R.id.et_comment);
        llTeamwork = (LinearLayout) inflate.findViewById(R.id.ll_teamwork);
        mCaseRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_case);
        mCommentRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_commentList);
        mServiceRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_myService);
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
        ivCollect.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.activity_share);

        ivBack.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        llFindMsg.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        llTeamwork.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        commentEt.setOnEditorActionListener(this);
        //查询案列
        mCaseRecyclerView.setGridLayout(1);
        mCaseRecyclerView.setFooterViewText("加载中");
        caseAdapter = new ServiceFindCaseAdapter(this, caseDate, mvpPresenter);
        mCaseRecyclerView.setAdapter(caseAdapter);
        mCaseRecyclerView.setOnPullLoadMoreListener(this);

        //我的服务
        mServiceRecyclerView.setGridLayout(1);
        mServiceRecyclerView.setFooterViewText("加载中");
        serviceAdapter = new ServiceFindServiceAdapter(this, serviceDate);
        mServiceRecyclerView.setAdapter(serviceAdapter);
        mServiceRecyclerView.setOnPullLoadMoreListener(this);
        //查询评论
        mCommentRecyclerView.setGridLayout(1);
        mCommentRecyclerView.setFooterViewText("加载中");
        commAdapter = new ServiceCommentListAdapter(this, commDate, mvpPresenter);
        mCommentRecyclerView.setAdapter(commAdapter);
        mCommentRecyclerView.setOnPullLoadMoreListener(this);

        if (TextUtils.isEmpty(userId)) {
            ToastUtil.showToast("加载失败");
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            mvpPresenter.loadServerInfoData(userId);
        }

        scrollBrief.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_title_other:
                //分享
                String linkUrl = Base64Utils.getStringUrl(userId, ConstantValue.SHARE_TYPR_SERVICE);
                ShareUtil.showShare(mActivity, linkUrl, linkUrl, dataBean.getPhoto(), dataBean.getServiceIntroduce(), dataBean.getNickName());
                break;
            case R.id.iv_title_another:
                //收藏
                if (null == dataBean) {
                    ToastUtil.showToast("参数错误！");
                    return;
                }
                if (dataBean.isIsCollect()) {
                    mvpPresenter.loadCollectSaveDate(userId, ConstantValue.COLLECTSERVICE_TYPE, "1");

                } else {
                    mvpPresenter.loadCollectSaveDate(userId, ConstantValue.COLLECTSERVICE_TYPE, "0");
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
        switch (checkedId) {
            case R.id.rb_introduce:
                //简介
                scrollBrief.setVisibility(View.VISIBLE);
                llCommentList.setVisibility(View.GONE);
                mServiceRecyclerView.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.rb_case:
                //案列
                type = "1";
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.GONE);
                mServiceRecyclerView.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.VISIBLE);
                mvpPresenter.loadFindArticleByCaseIdData(userId);

                break;
            case R.id.rb_myService:
                // 我的服务
                type = "3";
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.GONE);
                mServiceRecyclerView.setVisibility(View.VISIBLE);
                mvpPresenter.loadServiceItemListDate(userId);

                break;
            case R.id.rb_evaluate:
                //评价
                type = "2";
                scrollBrief.setVisibility(View.GONE);
                llCommentList.setVisibility(View.VISIBLE);
                mServiceRecyclerView.setVisibility(View.GONE);
                mCaseRecyclerView.setVisibility(View.GONE);
                mvpPresenter.loadEditorCommentListData(userId);
                break;
        }
    }

    @Override
    public void getEditorInfoDataSuccess(FindServerInfoModel model) {
        if (model.isSuccess() && model.getData() != null) {
            dataBean = model.getData();
            GlideUitl.loadRandImg(mActivity, model.getData().getPhoto(), ivHeadImg);
            tvRealName.setText(model.getData().getName());
            if (model.getData().getServiceType() != 0) {
                switch (model.getData().getServiceType()) {
                    case 1:
                        ratingBar.setRating(5);
                        break;
                    case 2:
                        ratingBar.setRating(4);
                        break;
                    case 3:
                        ratingBar.setRating(3);
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
            if (null != model.getData() && model.getData().size() != 0) {
                PAGE_NO++;
                commDate = model.getData();
            } else {
                ToastUtil.showToast(mActivity.getString(R.string.errMsg_empty));
            }
            commAdapter.refreshAdapter(commDate);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        dismissDialog();
        if (model.isSuccess()) {
            mArticleData.clear();
            mArticleData.addAll(model.getData());
            if (!model.getData().isEmpty()) {
                mvpPresenter.openTeamworkDialog(mArticleData, userId);
            } else {
                ToastUtil.showToast("您还没有作品");
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
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCaseRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size() != 0) {
                PAGE_NO++;
                caseDate.clear();
                caseDate = model.getData();
                caseAdapter.refreshAdapter(caseDate);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    /**
     * 服务
     *
     * @param model
     */
    @Override
    public void getFindServerItemListDetails(FindServiceItemListModel model) {
        dismissDialog();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mServiceRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size() != 0) {
                PAGE_NO++;
                serviceDate.clear();
                serviceDate = model.getData();
                serviceAdapter.refreshAdapter(serviceDate);
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
                dataBean.setIsCollect(true);
                ivCollect.setImageResource(R.drawable.icon_likedwhite2x);
            } else {
                ToastUtil.showToast("取消收藏！");
                dataBean.setIsCollect(false);
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
                mvpPresenter.loadSendServiceCommentData(userId, conent, (int) numRb.getRating() + "");
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
        } else if (type.equals("3")) {
            mvpPresenter.getCaseIdParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadServiceItemListDate(userId);
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
        } else if (type.equals("3")) {
            mvpPresenter.getCaseIdParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadServiceItemListDate(userId);
        }
    }

    @Override
    public void getDataFail(String msg,int flg) {
        LoggerUtil.e(msg);
        ErrorReloadData(flg);
    }

    @Override
    public void getDataFail5(String msg,int flg) {
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
        }
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                if (flg == 1) {
                    mvpPresenter.loadServerInfoData(userId);
                } else if (flg == 2 | flg == 3 | flg == 4 | flg == 5 | flg == 6 | flg == 7 | flg == 8 | flg == 9| flg == 10) {
                    ToastUtil.showToast(mActivity.getString(R.string.errMsg_data));
                } else {
                    ToastUtil.showToast(mActivity.getString(R.string.errMsg_network));
                }
            }
        });
    }
}

