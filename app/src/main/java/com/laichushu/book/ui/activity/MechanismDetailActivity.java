package com.laichushu.book.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.mechanismdetail.MechanisDetailModel;
import com.laichushu.book.mvp.mechanismdetail.MechanismDetailPresenter;
import com.laichushu.book.mvp.mechanismdetail.MechanismDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.BriefFragment;
import com.laichushu.book.ui.fragment.FragmentFactory;
import com.laichushu.book.ui.fragment.NoticeFragment;
import com.laichushu.book.ui.fragment.TopicListFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 机构详情页
 * Created by wangtong on 2016/11/25.
 */

public class MechanismDetailActivity extends MvpActivity2<MechanismDetailPresenter> implements MechanismDetailView, View.OnClickListener {

    private MechanismListBean.DataBean bean;
    private ImageView mechanismIv;
    private ImageView shareIv;
    private ImageView moreIv;
    private ImageView finishIv;
    private TextView titleTv;
    private TextView msgTv;
    private TextView submissionTv;
    private TextView collectionTv;
    private FrameLayout spaceFay;
    private ArrayList<AuthorWorksModle.DataBean> data = new ArrayList<>();
    private TextView mechanismTv;
    private TextView collectionCountTv;
    private String cType;
    private RadioButton firstRbn;
    private RadioButton secondRbn;
    private RadioButton thridRbn;
    private String articleId;

    @Override
    protected MechanismDetailPresenter createPresenter() {
        return new MechanismDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismdetail);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        moreIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        mechanismIv = (ImageView) mSuccessView.findViewById(R.id.iv_mechanism);//机构图片
        firstRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);//公告
        secondRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);//话题
        thridRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_03);//简介

        msgTv = (TextView) mSuccessView.findViewById(R.id.tv_msg);
        submissionTv = (TextView) mSuccessView.findViewById(R.id.tv_submission);
        collectionTv = (TextView) mSuccessView.findViewById(R.id.tv_collection);
        spaceFay = (FrameLayout) mSuccessView.findViewById(R.id.fay_space);
        mechanismTv = (TextView) mSuccessView.findViewById(R.id.tv_mechanism);//机构name
        collectionCountTv = (TextView) mSuccessView.findViewById(R.id.tv_collection_count);//收藏数

        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
        msgTv.setOnClickListener(this);
        submissionTv.setOnClickListener(this);
        collectionTv.setOnClickListener(this);
        firstRbn.setOnClickListener(this);
        secondRbn.setOnClickListener(this);
        thridRbn.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        bean = getIntent().getParcelableExtra("bean");
        articleId = getIntent().getStringExtra("articleId");
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        titleTv.setText("机构详情");//设置标题
        GlideUitl.loadImg(mActivity, R.drawable.mechanism_detail_bg, mechanismIv);//设置机构图片
        mechanismTv.setText(bean.getName());
        if (bean.isCollect()) {
            collectionTv.setText("已收藏");
        } else {
            collectionTv.setText("收藏");
        }
        collectionCountTv.setText("收藏:" + bean.getCollectCount() + "人");
        position = 0;
        onClick(firstRbn);
    }

    @Override
    public void getDataSuccess(MechanisDetailModel model) {

    }

    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        hideLoading();
        if (model.isSuccess()) {
            data.clear();
            if (model.getData() != null) {
                data = model.getData();
                if (!data.isEmpty()) {
//                    mvpPresenter.openSelectBookDialog(data, bean.getId());
                } else {
                    ToastUtil.showToast("您还没有作品");
                }
            }
        } else {
            ToastUtil.showToast("获取作品列表失败");
        }
    }

    @Override
    public void articleVote(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("投稿成功，15日内通知审核结果");
        } else {
            if (model.getErrMsg().contains("已经投稿")){
                ToastUtil.showToast("投稿失败，此出版社已经投稿了");
            }else {
                ToastUtil.showToast("投稿失败");
            }
        }
    }

    @Override
    public void collectSaveData(RewardResult model, boolean collect) {
        collectionTv.setEnabled(true);
        if (model.isSuccess()) {
            bean.setCollect(collect);
            if (bean.isCollect()) {
                collectionTv.setText("已收藏");
            } else {
                collectionTv.setText("收藏");
            }
        } else {
            ToastUtil.showToast("收藏失败");
        }
    }

    @Override
    public void getSendMsgToPartyDataSuccess(RewardResult model) {
        hideLoading();
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
        } else {
            ToastUtil.showToast("发送失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        ToastUtil.showToast("获取作品列表失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail3(String msg) {
        hideLoading();
        ToastUtil.showToast("投稿失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail4(String msg) {
        collectionTv.setEnabled(true);
        hideLoading();
        ToastUtil.showToast("收藏失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail5(String msg) {
        hideLoading();
        ToastUtil.showToast("发送失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    int position = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_other:
                break;
            case R.id.iv_title_another:
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.tv_msg:
                mvpPresenter.sendMsgToPartyDialog(bean.getId());
                break;
            case R.id.rbn_01:
                if (position != 1) {
                    onTabSelected(new NoticeFragment());
                    position = 1;
                }
                break;
            case R.id.rbn_02:
                if (position != 2) {
                    onTabSelected(new TopicListFragment());
                    position = 2;
                }
                break;
            case R.id.rbn_03:
                if (position != 3) {
                    onTabSelected(new BriefFragment());
                    position = 3;
                }
                break;
            case R.id.tv_submission:
                mvpPresenter.openSelectBookDialog(articleId, bean.getId());
                break;
            case R.id.tv_collection:
                if (collectionTv.getText().equals("收藏")) {
                    cType = "0";
                } else {
                    cType = "1";
                }
                collectionTv.setEnabled(false);
                mvpPresenter.collectSave(bean.getId(),cType, "4");
                break;
        }
    }

    public MechanismListBean.DataBean getBean() {
        return bean;
    }

    public void setBean(MechanismListBean.DataBean bean) {
        this.bean = bean;
    }

    /**
     * 替换fragment
     */
    public void onTabSelected(Fragment mFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = mFragment;
        ft.replace(R.id.fay_space, fragment);
        ft.commit();
    }
}
