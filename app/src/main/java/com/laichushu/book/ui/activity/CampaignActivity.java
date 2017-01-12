package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CampaignDetailsModel;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.campaign.CampaignJoinModel;
import com.laichushu.book.mvp.home.campaign.CampaignModel;
import com.laichushu.book.mvp.home.campaign.CampaignPresenter;
import com.laichushu.book.mvp.home.campaign.CampaignView;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.adapter.JoinActivityAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 活动详情
 * Created by wangtong on 2016/10/24.
 */
public class CampaignActivity extends MvpActivity2<CampaignPresenter> implements CampaignView, View.OnClickListener {

    private TextView joinTv;
    private TextView numTv;
    private TextView endTimeTv;
    private TextView startTimeTv;
    private TextView activityNameTv;
    private ImageView activityImgIv;
    private TextView detailsTv;
    private LinearLayout parentLay,parentLayContainer;
    private ImageView stateIv;
    private ImageView comentIv;
    private HomeHotModel.DataBean bean;
    private ArrayList<CampaignModel.DataBean> mData = new ArrayList<>();
    private ArrayList<CampaignDetailsModel.DataBean> mDetailsData = new ArrayList<>();
    private ArrayList<AuthorWorksModle.DataBean> mArticleData = new ArrayList<>();
    private int position;
    private MessageCommentResult.DataBean dataBeen;
    private View mSuccessView;
    private String activityId;
    private CampaignModel.DataBean dataBean;

    @Override
    protected View createSuccessView() {
        mSuccessView = UIUtil.inflate(R.layout.activity_campaign);
        initTitleBar("活动详情");
        findViewById();
        return mSuccessView;
    }


    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        comentIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        titleTv.setText(title);
        shareIv.setImageResource(R.drawable.activity_share);
        comentIv.setImageResource(R.drawable.activity_comment);
        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        comentIv.setOnClickListener(this);
    }

    /**
     * activityImgIv    大图
     * activityNameTv   活动名称
     * startTimeTv      开始时间
     * endTimeTv        结束时间
     * numTv            参加人数
     * joinTv           参加活动按钮
     * detailsTv        活动详情介绍
     * parentLay        活动结果的容器
     */
    private void findViewById() {
        activityImgIv = (ImageView) mSuccessView.findViewById(R.id.iv_activity_img);
        stateIv = (ImageView) mSuccessView.findViewById(R.id.iv_activity_state);
        activityNameTv = (TextView) mSuccessView.findViewById(R.id.tv_activity_name);
        startTimeTv = (TextView) mSuccessView.findViewById(R.id.tv_activity_start);
        endTimeTv = (TextView) mSuccessView.findViewById(R.id.tv_activity_end);
        numTv = (TextView) mSuccessView.findViewById(R.id.tv_activity_num);
        joinTv = (TextView) mSuccessView.findViewById(R.id.tv_join);
        detailsTv = (TextView) mSuccessView.findViewById(R.id.tv_details);
        parentLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_result);
        parentLayContainer = (LinearLayout) mSuccessView.findViewById(R.id.lay_container);
        joinTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        bean = getIntent().getParcelableExtra("bean");
        activityId = bean.getActivityId();
        position = getIntent().getIntExtra("position", 0);
        mvpPresenter.getActivityById(activityId);
        refurshErrorViewOnClick();
    }

    /**
     * 点击事件
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other://分享
                //分享
                String linkUrl= Base64Utils.getStringUrl(activityId, ConstantValue.SHARE_TYPR_ACTIVITY);
                ShareUtil.showShare(mActivity, linkUrl,linkUrl,bean.getCoverUrl(),bean.getIntroduce(),bean.getName());
                break;
            case R.id.iv_title_another://评论
                //打开参加活动的图书
                openSendPerMsgDialog();
                break;
            case R.id.tv_join://参加活动
                if (joinTv.getText().equals("参加活动")) {
                    mvpPresenter.loadAuthorWorksData();
                }
                break;
        }
    }

    /**
     * 参加活动 对话框
     */
    private void openAlertDialog() {
        for (int i = 0; i < mArticleData.size(); i++) {
            AuthorWorksModle.DataBean bean = mArticleData.get(i);
            if (i == 0) {
                bean.setIscheck(true);
            } else {
                bean.setIscheck(false);
            }
        }
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        final View customerView = UIUtil.inflate(R.layout.dialog_join);
        ListView joinLv = (ListView) customerView.findViewById(R.id.lv_join);
        final JoinActivityAdapter joinAdapter = new JoinActivityAdapter(mArticleData, 0,null);
        joinLv.setAdapter(joinAdapter);

        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e(mArticleData.get(joinAdapter.getPosition()).getArticleName());
                mvpPresenter.loadJoinActivityData(bean.getActivityId(), mArticleData.get(joinAdapter.getPosition()).getArticleId(), "0");
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, this)                // 添加自定义View
                .show();
    }


    /**
     * 参加活动
     *
     * @param model
     * @param type
     */
    @Override
    public void getJoinDataSuccess(CampaignJoinModel model, String type) {
        joinTv.setEnabled(true);
        if (model.isSuccess()) {
            if (type.equals("0")) {
                joinTv.setText("已参加");
                bean.setApplyAmount(bean.getApplyAmount() + 1);
                numTv.setText("报名人数：" + bean.getApplyAmount() + "人");
            } else {
                joinTv.setText("参加活动");
                bean.setApplyAmount(bean.getApplyAmount() - 1);
                numTv.setText("报名人数：" + bean.getApplyAmount() + "人");
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 获取作者作品信息
     *
     * @param model
     */
    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        hideLoading();
        if (model.isSuccess()) {
            mArticleData.addAll(model.getData());
            if (!model.getData().isEmpty()) {
//                openSelectBookDialog(mArticleData, dataBeen.getArticleId());
                openAlertDialog();
            } else {
                ToastUtil.showToast("您还没有作品");
            }
        } else {
            ToastUtil.showToast("获取作品失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    @Override
    public void getDataFail3(String msg) {

    }

    @Override
    public void articleVote(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("投稿成功，15日内通知审核结果");
        } else {
            if (model.getErrMsg().contains("已经投稿")) {
                ToastUtil.showToast("投稿失败，此出版社已经投稿了");
            } else {
                ToastUtil.showToast("投稿失败");
            }
        }

    }

    @Override
    public void getAddPerInfoSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功！");
        } else {
            ToastUtil.showToast("发送失败！");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 活动详情接口成功
     *
     * @param modle
     */
    @Override
    public void getgetActivityByIdDataSuccess(CampaignModel modle) {
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            dataBean = modle.getData();
            setData(dataBean);//活动详情
            if (dataBean.getResult() != null) {
                getResultData(dataBean.getResult());//活动结果
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }


    /**
     * 活动详情接口失败
     *
     * @param msg
     */
    @Override
    public void getDataFail2(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    protected CampaignPresenter createPresenter() {
        return new CampaignPresenter(this);
    }

    /**
     * 投稿对话框
     *
     * @param mArticleData
     * @param pressId
     */
    public void openSelectBookDialog(final ArrayList<AuthorWorksModle.DataBean> mArticleData, final String pressId) {
        for (int i = 0; i < mArticleData.size(); i++) {
            AuthorWorksModle.DataBean bean = mArticleData.get(i);
            if (i == 0) {
                bean.setIscheck(true);
            } else {
                bean.setIscheck(false);
            }
        }
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_join);
        ListView joinLv = (ListView) customerView.findViewById(R.id.lv_join);
        final JoinActivityAdapter joinAdapter = new JoinActivityAdapter(mArticleData, 0,null);
        joinLv.setAdapter(joinAdapter);

        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/25 投稿
                mvpPresenter.voteBook(mArticleData.get(joinAdapter.getPosition()).getArticleId(), pressId);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();

    }

    /**
     * 发送私信对话框
     */
    public void openSendPerMsgDialog() {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_send_per_msg);
        final EditText edMsg = (EditText) customerView.findViewById(R.id.et_dialogMsg);

        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/25 投稿
                mvpPresenter.loadAddPerInfoDate(bean.getActivityId(), edMsg);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle("发送私信")                                  // 为null时不显示title
                .withDialogColor("#94C3B7")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();

    }

    /**
     * 设置数据
     *
     * @param dataBean
     */
    private void setData(CampaignModel.DataBean dataBean) {
        if (dataBean != null) {
            GlideUitl.loadImg(this, dataBean.getImgUrl(), activityImgIv);
            if (dataBean.isParticipate()) {
                joinTv.setText("已参加");
            } else {
                joinTv.setText("参加活动");
            }
            switch (dataBean.getStatus()) {
                case "2":
                    GlideUitl.loadImg(mActivity, R.drawable.activity_start, stateIv);
                    joinTv.setVisibility(View.VISIBLE);
                    parentLay.setVisibility(View.INVISIBLE);
                    comentIv.setVisibility(View.VISIBLE);
                    break;
                case "4":
                    GlideUitl.loadImg(mActivity, R.drawable.activity_end, stateIv);
                    joinTv.setVisibility(View.INVISIBLE);
                    comentIv.setVisibility(View.INVISIBLE);
                    parentLay.setVisibility(View.VISIBLE);
                    break;
            }
            activityNameTv.setText(dataBean.getActivityName());
            startTimeTv.setText("开始时间：" + dataBean.getBeginTime());
            endTimeTv.setText("结束时间：" + dataBean.getEndTime());
            numTv.setText("报名人数：" + dataBean.getApplyAmount() + "人");
            detailsTv.setText(dataBean.getDetail());
        }
    }

    /**
     * 比赛结束获取排名
     *
     * @param resultData 结果集合
     */
    public void getResultData(ArrayList<CampaignModel.DataBean.ResultBean> resultData) {
        parentLayContainer.removeAllViews();
        for (int i = 1; i <= resultData.size(); i++) {
            View itemView = UIUtil.inflate(R.layout.item_home_activity_details);
            TextView bonusTv = (TextView) itemView.findViewById(R.id.tv_bonus);
            ImageView headIv = (ImageView) itemView.findViewById(R.id.iv_head);
            TextView usernameIv = (TextView) itemView.findViewById(R.id.iv_username);
            TextView booknameIv = (TextView) itemView.findViewById(R.id.iv_bookname);
            bonusTv.setText(i + "");
            CampaignModel.DataBean.ResultBean bean = resultData.get(i - 1);
            GlideUitl.loadRandImg(this, bean.getPhoto(), headIv);
            usernameIv.setText(bean.getNickName());
            booknameIv.setText(bean.getArticleName());
            final String authorId = resultData.get(i - 1).getAuthorId();
            headIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转用户主页
                    if (!TextUtils.isEmpty(authorId)) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", authorId);
                        if (SharePrefManager.getUserId().equals(authorId)) {
                            UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                        }
                    }
                }
            });
            parentLayContainer.addView(itemView);
        }
    }

    /**
     * 失败页面按钮重新加载
     */
    public void refurshErrorViewOnClick() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.getActivityById(activityId);
            }
        });
    }
}
