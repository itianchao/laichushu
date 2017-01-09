package com.laichushu.book.ui.activity;

import android.graphics.drawable.Drawable;
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
import com.laichushu.book.event.RefurshWriteFragment;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.find.mechanism.mechanismdetail.MechanisDetailModel;
import com.laichushu.book.mvp.find.mechanism.mechanismdetail.MechanismDetailPresenter;
import com.laichushu.book.mvp.find.mechanism.mechanismdetail.MechanismDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.BriefFragment;
import com.laichushu.book.ui.fragment.NoticeFragment;
import com.laichushu.book.ui.fragment.TopicListFragment;
import com.laichushu.book.ui.share.onekeyshare.OnekeyShare;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;

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

        shareIv.setBackgroundResource(R.drawable.icon_share2x);
        moreIv.setBackgroundResource(R.drawable.icon_sort2x);
        shareIv.setVisibility(View.VISIBLE);
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
//        判断当前是否是机构管理员
        if (null != bean.getAdmin() && SharePrefManager.getUserId().equals(bean.getAdmin())) {
            moreIv.setVisibility(View.VISIBLE);
        } else {
            moreIv.setVisibility(View.INVISIBLE);
        }
        titleTv.setText("机构详情");//设置标题
        GlideUitl.loadImg(mActivity, R.drawable.mechanism_detail_bg, mechanismIv);//设置机构图片
        mechanismTv.setText(bean.getName());
        if (bean.isIsCollect()) {
            collectionTv.setText("已收藏");
            Drawable drawable = getResources().getDrawable(R.drawable.icon_likedwhite2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            collectionTv.setCompoundDrawables(drawable, null, null, null);
        } else {
            collectionTv.setText("收藏");
            Drawable drawable = getResources().getDrawable(R.drawable.icon_likewhite2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            collectionTv.setCompoundDrawables(drawable, null, null, null);
        }
        collectionCountTv.setText("收藏:" + bean.getCollectCount() + "人");
        position = 0;
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        onClick(firstRbn);
    }

    @Override
    public void getDataSuccess(MechanisDetailModel model) {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        hideLoading();
        if (model.isSuccess()) {
            data.clear();
            if (model.getData() != null) {
                data = model.getData();
                if (!data.isEmpty()) {
                    mvpPresenter.openSelectBookDialog(data, bean.getId());
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
            // TODO: 2016/12/19 刷新 写作页面
            EventBus.getDefault().postSticky(new RefurshWriteFragment(true));
        } else {
            if (model.getErrMsg().contains("已经投稿")) {
                ToastUtil.showToast("投稿失败，此出版社已经投稿了");
            } else {
                ToastUtil.showToast("投稿失败");
            }
        }
    }

    @Override
    public void collectSaveData(RewardResult model, boolean collect) {
        collectionTv.setEnabled(true);
        if (model.isSuccess()) {
            bean.setIsCollect(collect);
            if (bean.isIsCollect()) {
                collectionTv.setText("已收藏");
                Drawable drawable = getResources().getDrawable(R.drawable.icon_likedwhite2x);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                collectionTv.setCompoundDrawables(drawable, null, null, null);
            } else {
                collectionTv.setText("收藏");
                Drawable drawable = getResources().getDrawable(R.drawable.icon_likewhite2x);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                collectionTv.setCompoundDrawables(drawable, null, null, null);
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
                //公告管理
                mvpPresenter.showManageDialog(mActivity, moreIv, bean.getId(), bean);

                break;
            case R.id.iv_title_another:
                //分享
                showShare();
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.tv_msg:
                mvpPresenter.sendMsgToPartyDialog(bean.getAdmin());
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
                if (null != articleId) {
                    mvpPresenter.openSelectBookDialog(articleId, bean.getId());
                } else {
                    mvpPresenter.loadAuthorWorksData();
                }

                break;
            case R.id.tv_collection:
                if (bean.isIsCollect()) {
                    cType = "1";
                } else {
                    cType = "0";
                }
                mvpPresenter.collectSave(bean.getId(), cType, ConstantValue.MECHANISM_TYPE);
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

    //share
    private void showShare() {
        OnekeyShare oks = null;
        if (oks == null)
            oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("来出书");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("https://www.baidu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getResources().getString(R.string.app_introduce_details) + "https://www.sina.com");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("https://www.sina.com");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.sina.com");

// 启动分享GUI
        oks.show(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止shareSDK
        ShareSDK.stopSDK(mActivity);
    }
}
