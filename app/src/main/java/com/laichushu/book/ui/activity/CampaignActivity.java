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
import com.laichushu.book.event.RefurshBookDetaileCommentEvent;
import com.laichushu.book.bean.netbean.CampaignDetailsModel;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.campaign.CampaignJoinModel;
import com.laichushu.book.mvp.campaign.CampaignModel;
import com.laichushu.book.mvp.campaign.CampaignPresenter;
import com.laichushu.book.mvp.campaign.CampaignView;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.adapter.JoinActivityAdapter;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 活动详情
 * Created by wangtong on 2016/10/24.
 */
public class CampaignActivity extends MvpActivity<CampaignPresenter> implements CampaignView, View.OnClickListener {

    private TextView joinTv;
    private TextView numTv;
    private TextView endTimeTv;
    private TextView startTimeTv;
    private TextView activityNameTv;
    private ImageView activityImgIv;
    private TextView detailsTv;
    private LinearLayout parentLay;
    private ImageView stateIv;
    private ImageView comentIv;
    private HomeHotModel.DataBean bean;
    private ArrayList<CampaignModel.DataBean> mData = new ArrayList<>();
    private ArrayList<CampaignDetailsModel.DataBean> mDetailsData = new ArrayList<>();
    private ArrayList<AuthorWorksModle.DataBean> mArticleData = new ArrayList<>();
    private int position;
    private String articId;
    private MessageCommentResult.DataBean dataBeen;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_campaign);
        initTitleBar("活动详情");
        findViewById();
    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        ImageView shareIv = (ImageView) findViewById(R.id.iv_title_other);
        comentIv = (ImageView) findViewById(R.id.iv_title_another);
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
        activityImgIv = (ImageView) findViewById(R.id.iv_activity_img);
        stateIv = (ImageView) findViewById(R.id.iv_activity_state);
        activityNameTv = (TextView) findViewById(R.id.tv_activity_name);
        startTimeTv = (TextView) findViewById(R.id.tv_activity_start);
        endTimeTv = (TextView) findViewById(R.id.tv_activity_end);
        numTv = (TextView) findViewById(R.id.tv_activity_num);
        joinTv = (TextView) findViewById(R.id.tv_join);
        detailsTv = (TextView) findViewById(R.id.tv_details);
        parentLay = (LinearLayout) findViewById(R.id.lay_result);
    }

    @Override
    protected void initData() {
        bean = getIntent().getParcelableExtra("bean");
        position = getIntent().getIntExtra("position", 0);
        mvpPresenter.loadAuthorWorksData();
        mvpPresenter.loadActivityResultData(bean.getActivityId());
        GlideUitl.loadImg(this, bean.getImgUrl(), activityImgIv);
        joinTv.setOnClickListener(this);
        if (bean.isParticipate()) {
            joinTv.setText("已参加");
        } else {
            joinTv.setText("参加活动");
        }
        switch (bean.getStatus()) {
            case "1":
//                GlideUitl.loadImg(mActivity, R.drawable.activity_start, stateIv);
//                joinTv.setVisibility(View.INVISIBLE);
                break;
            case "2":
                GlideUitl.loadImg(mActivity, R.drawable.activity_start, stateIv);
                joinTv.setVisibility(View.VISIBLE);
                parentLay.setVisibility(View.INVISIBLE);
                comentIv.setVisibility(View.VISIBLE);
                break;
            case "3":
//                GlideUitl.loadImg(mActivity, R.drawable.activity_start, stateIv);
//                joinTv.setVisibility(View.VISIBLE);
//                parentLay.setVisibility(View.INVISIBLE);
//                comentIv.setVisibility(View.VISIBLE);
                break;
            case "4":
                GlideUitl.loadImg(mActivity, R.drawable.activity_end, stateIv);
                joinTv.setVisibility(View.INVISIBLE);
                comentIv.setVisibility(View.INVISIBLE);
                parentLay.setVisibility(View.VISIBLE);
                break;
        }
        activityNameTv.setText(bean.getActivityName());
        startTimeTv.setText("开始时间：" + bean.getBeginTime());
        endTimeTv.setText("结束时间：" + bean.getEndTime());
        numTv.setText("报名人数：" + bean.getApplyAmount() + "人");
        detailsTv.setText(bean.getDetail());
    }
//    }

    /**
     * 活动详情列表   活动通知------》详情    已经弃用
     *
     * @param model
     */
    @Override
    public void getDetailsDataSuccess(CampaignDetailsModel model) {
        if (model.isSuccess()) {
            mDetailsData.addAll(model.getData());
            //初始化头部信息
            GlideUitl.loadImg(this, model.getImgUrl(), activityImgIv);
            joinTv.setText("已结束");
            GlideUitl.loadImg(mActivity, R.drawable.activity_end, stateIv);
            joinTv.setVisibility(View.INVISIBLE);
            activityNameTv.setText(model.getName());
            startTimeTv.setText("开始时间：" + model.getStartTime());
            endTimeTv.setText("结束时间：" + model.getEndTime());
            numTv.setText("报名人数：" + model.getApplyAmount() + "人");
            detailsTv.setText(model.getDetail());
            //初始化尾部信息
            for (int i = 1; i <= mDetailsData.size(); i++) {
                View itemView = UIUtil.inflate(R.layout.item_home_activity_details);
                TextView bonusTv = (TextView) itemView.findViewById(R.id.tv_bonus);
                ImageView headIv = (ImageView) itemView.findViewById(R.id.iv_head);
                TextView usernameIv = (TextView) itemView.findViewById(R.id.iv_username);
                TextView booknameIv = (TextView) itemView.findViewById(R.id.iv_bookname);
                bonusTv.setText(i + "");
                CampaignDetailsModel.DataBean bean = mDetailsData.get(i - 1);
                GlideUitl.loadRandImg(this, bean.getPhoto(), headIv);
                usernameIv.setText(bean.getNickName());
                booknameIv.setText(bean.getArticleName());
                articId = mDetailsData.get(i).getArticleId();
                headIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        if (!TextUtils.isEmpty(articId)) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userId", articId);
                            if (SharePrefManager.getUserId().equals(articId)) {
                                UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                            } else {
                                UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                            }
                        }

                    }
                });

                parentLay.addView(itemView);
            }
        } else {
            ToastUtil.showToast("加载数据失败！");
        }
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
                break;
            case R.id.iv_title_another://评论
                //打开参加活动的图书
//                mvpPresenter.loadAuthorWorksData();
                openSendPerMsgDialog();
                break;
            case R.id.tv_join://参加活动
                if (mArticleData.size() == 0) {
                    ToastUtil.showToast("您还没有作品");
                } else {
                    if (joinTv.getText().equals("参加活动") | joinTv.getText().equals("已结束")) {
                        openAlertDialog();
                    } else {
//                        mvpPresenter.loadJoinActivityData(bean.getActivityId(), "", "1");
//                        joinTv.setEnabled(false);
                    }
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
        final JoinActivityAdapter joinAdapter = new JoinActivityAdapter(mArticleData, 0);
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
     * 比赛结束获取排名
     *
     * @param model
     */
    @Override
    public void getDataSuccess(CampaignModel model) {
        if (model.isSuccess()) {
            mData.addAll(model.getData());
            for (int i = 1; i <= mData.size(); i++) {
                View itemView = UIUtil.inflate(R.layout.item_home_activity_details);
                TextView bonusTv = (TextView) itemView.findViewById(R.id.tv_bonus);
                ImageView headIv = (ImageView) itemView.findViewById(R.id.iv_head);
                TextView usernameIv = (TextView) itemView.findViewById(R.id.iv_username);
                TextView booknameIv = (TextView) itemView.findViewById(R.id.iv_bookname);
                bonusTv.setText(i + "");
                CampaignModel.DataBean bean = mData.get(i - 1);
                GlideUitl.loadRandImg(this, bean.getPhoto(), headIv);
                usernameIv.setText(bean.getNickName());
                booknameIv.setText(bean.getArticleName());
                articId = mData.get(i).getArticleId();
                headIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        if (!TextUtils.isEmpty(articId)) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userId", articId);
                            if (SharePrefManager.getUserId().equals(articId)) {
                                UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                            } else {
                                UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                            }
                        }
                    }
                });
                parentLay.addView(itemView);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
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
                openSelectBookDialog(mArticleData, dataBeen.getArticleId());
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
        final JoinActivityAdapter joinAdapter = new JoinActivityAdapter(mArticleData, 0);
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

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().postSticky(new RefurshBookDetaileCommentEvent(bean,position));
    }
}
