package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomeInfo_paramet;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.event.RefreshHomePageEvent;
import com.laichushu.book.mvp.homepage.HomePagePresener;
import com.laichushu.book.mvp.homepage.HomePageView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.HomePageDynamicAdapter;
import com.laichushu.book.ui.adapter.HomePageFocusBeAdapter;
import com.laichushu.book.ui.adapter.HomePageFocusMeAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PersonalHomePageActivity extends MvpActivity2<HomePagePresener> implements HomePageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack, ivEdit, iv_headImg, ivPerGrade,ivGreadDetails, ivAnother;
    private TextView tvTitle, tvNickName, tvRealName, tvAuthorAgree;
    private PullLoadMoreRecyclerView mDyRecyclerView, mFocuMeRecyclerView, mFocuRecyclerView;
    private RadioGroup rgHomeList;
    private RadioButton rbDy;
    private List<HomeUseDyrResult.DataBean> dyData = new ArrayList<>();
    private List<HomePersonFocusResult.DataBean> focusMeData = new ArrayList<>();
    private List<HomePersonFocusResult.DataBean> focusBeData = new ArrayList<>();
    private HomePageDynamicAdapter dyAdapter;
    private HomePageFocusMeAdapter fmAdapter;//关注我的
    private HomePageFocusBeAdapter fbAdapter;//我关注的
    private int PAGE_NO = 1, type = 1;
    private boolean dibbleDy = false, dibbleFoMe = false, dibbleFo = false;
    private List<View> pulls = new ArrayList<>();

    @Override
    protected HomePagePresener createPresenter() {
        return new HomePagePresener(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_personal_home_page);
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        ivEdit = ((ImageView) inflate.findViewById(R.id.iv_title_another));
        ivPerGrade = ((ImageView) inflate.findViewById(R.id.iv_perGrade));
        ivGreadDetails = ((ImageView) inflate.findViewById(R.id.iv_perGradeDetails));
        iv_headImg = ((ImageView) inflate.findViewById(R.id.iv_PerHeadImg));
        ivAnother = (ImageView) inflate.findViewById(R.id.iv_title_another);
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvNickName = ((TextView) inflate.findViewById(R.id.tv_PerNickName));
        tvRealName = ((TextView) inflate.findViewById(R.id.tv_perRealName));
        tvAuthorAgree = ((TextView) inflate.findViewById(R.id.tv_perAuthorAgree));
        rbDy = ((RadioButton) inflate.findViewById(R.id.rb_dynamic));

        rgHomeList = ((RadioGroup) inflate.findViewById(R.id.rg_homeList));
        mDyRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_dynamic);
        mFocuMeRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_focusMe);
        mFocuRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_focus);
        return inflate;
    }


    @Override
    protected void initData() {
        super.initData();

        pulls.clear();
        pulls.add(mDyRecyclerView);
        pulls.add(mFocuMeRecyclerView);
        pulls.add(mFocuRecyclerView);

        tvTitle.setText("个人主页");
        tvTitle.setVisibility(View.VISIBLE);
        GlideUitl.loadImg(mActivity,R.drawable.my_reset2x,ivAnother);
        ivAnother.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        ivGreadDetails.setOnClickListener(this);
        ivAnother.setOnClickListener(this);
        rgHomeList.setOnCheckedChangeListener(this);
        initHeadInfo();
        //初始化mRecyclerView 动态
        mDyRecyclerView.setGridLayout(1);
        mDyRecyclerView.setFooterViewText("加载中");
        dyAdapter = new HomePageDynamicAdapter(this, dyData);
        mDyRecyclerView.setAdapter(dyAdapter);
        mDyRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 关注我的
        mFocuMeRecyclerView.setGridLayout(1);
        mFocuMeRecyclerView.setFooterViewText("加载中");
        fmAdapter = new HomePageFocusMeAdapter(this, focusMeData, mvpPresenter);
        mFocuMeRecyclerView.setAdapter(fmAdapter);
        mFocuMeRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 我关注的
        mFocuRecyclerView.setGridLayout(1);
        mFocuRecyclerView.setFooterViewText("加载中");
        fbAdapter = new HomePageFocusBeAdapter(this, focusBeData, mvpPresenter);
        mFocuRecyclerView.setAdapter(fbAdapter);
        mFocuRecyclerView.setOnPullLoadMoreListener(this);
        //初始化动态
        mvpPresenter.LoadData();
        selectLine(0);
    }

    /**
     * 个人信息
     */
    private void initHeadInfo() {
        HomeInfo_paramet paramet = new HomeInfo_paramet(SharePrefManager.getUserId(), SharePrefManager.getUserId());
        addSubscription(apiStores.getHomeUserInforDetails(paramet), new ApiCallback<HomeUserResult>() {
            @Override
            public void onSuccess(HomeUserResult result) {
                if (result.isSuccess()) {
                    //初始化个人信息
                    GlideUitl.loadRandImg(mActivity, result.getPhoto(), iv_headImg,R.drawable.icon_percentre_defhead2x);
                    tvNickName.setText(result.getNickName());
                    tvRealName.setText("");
                    if(!TextUtils.isEmpty(result.getLevelType())){
                        switch (result.getLevelType()){
                            case "1":
                                tvAuthorAgree.setText("金牌作家");
                                GlideUitl.loadImg(mActivity,R.drawable.icon_gold_medal2x,ivPerGrade);
                                break;
                            case "2":
                                tvAuthorAgree.setText("银牌作家");
                                GlideUitl.loadImg(mActivity,R.drawable.icon_silver_medal2x,ivPerGrade);
                                break;
                            case "3":
                                tvAuthorAgree.setText("铜牌作家");
                                GlideUitl.loadImg(mActivity,R.drawable.icon_copper_medal2x,ivPerGrade);
                                break;
                        }
                    }


                } else {
                    refreshPage(LoadingPager.PageState.STATE_ERROR);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                refreshPage(LoadingPager.PageState.STATE_ERROR);
            }

            @Override
            public void onFinish() {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_title_another:
                Bundle bunTopic = new Bundle();
                UIUtil.openActivity(this, HomePublishTopicActivity.class, bunTopic);
                break;
            case R.id.iv_title_other:
                //编辑

                break;
            case R.id.iv_perGradeDetails:
                //作者等级说明
                Bundle bundle = new Bundle();
                UIUtil.openActivity(this, HomePageGradeDetailsActivity.class, bundle);
                break;
        }
    }

    @Override
    public void getDyDataSuccess(HomeUseDyrResult model) {
        dyData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDyRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            dyData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!dyData.isEmpty()) {
                dyAdapter.refreshAdapter(dyData);
                PAGE_NO++;
            } else {

            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    /**
     * 关注我的
     * @param model
     */
    @Override
    public void getFocusMeDataSuccess(HomePersonFocusResult model) {
        dismissProgressDialog();
        dyData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFocuMeRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            focusMeData = model.getData();
            if (!focusMeData.isEmpty()) {
                PAGE_NO++;
            } else {

            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        fmAdapter.refreshAdapter(focusMeData);
    }

    /**
     *
     *我关注的
     * @param model
     */
    @Override
    public void getFocusBeDataSuccess(HomePersonFocusResult model) {
        dismissProgressDialog();
        focusBeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFocuRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            focusBeData = model.getData();
            if (!focusBeData.isEmpty()) {
                PAGE_NO++;
            } else {
            }
        } else {
            fbAdapter.refreshAdapter(focusBeData);
            ToastUtil.showToast(model.getErrMsg());
        }
        fbAdapter.refreshAdapter(focusBeData);
    }

    /**
     * @param modle
     * @param flg   添加关注
     */
    @Override
    public void getFocusBeStatus(HomeFocusResult modle, boolean flg) {
        if (modle.isSuccess()) {
            if (flg) {
                ToastUtil.showToast("关注成功！");
            } else {
                ToastUtil.showToast("取消关注成功！");
            }
        } else {
            ToastUtil.showToast("关注失败！");
            LoggerUtil.toJson(modle);
        }
    }

    /**
     * @param modle
     * @param flg   取消关注
     */
    @Override
    public void getFocusMeStatus(HomeFocusResult modle, boolean flg) {
        if (modle.isSuccess()) {
            if (flg) {
                ToastUtil.showToast("关注成功！");
            } else {
                ToastUtil.showToast("取消关注成功！");
            }

        } else {
            ToastUtil.showToast("关注失败！");
            LoggerUtil.toJson(modle);
        }
    }


    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PAGE_NO = 1;
        switch (checkedId) {
            case R.id.rb_dynamic:
                // 动态
                selectLine(0);
                dibbleFo = false;
                dibbleFoMe = false;
                type = 1;
                if (!dibbleDy) {
                    dyData.clear();
                    mvpPresenter.LoadData();
                }
                dibbleDy = true;
                break;
            case R.id.rb_focusMe:
                //关注我的
                selectLine(1);
                dibbleDy = false;
                dibbleFo = false;
                type = 2;
                if (!dibbleFoMe) {
                    focusMeData.clear();
                    showProgressDialog();
                    mvpPresenter.LoadFocusMeData();
                }
                dibbleFoMe = true;
                break;
            case R.id.rb_focus:
                //我关注的
                selectLine(2);
                dibbleDy = false;
                dibbleFoMe = false;
                type = 3;
                if (!dibbleFo) {
                    focusBeData.clear();
                    showProgressDialog();
                    mvpPresenter.LoadFocusBeData();
                }
                dibbleFo = true;
                break;
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        if (type == 1) {
            dyData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadData();//请求网络获取搜索列表
        } else if (type == 2) {
            focusMeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadFocusMeData();//请求网络获取搜索列表
        } else if (type == 3) {
            focusBeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadFocusBeData();//请求网络获取搜索列表
        }
    }

    @Override
    public void onLoadMore() {
        if (type == 1) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadData();//请求网络获取搜索列表
        } else if (type == 2) {
            focusMeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadFocusMeData();//请求网络获取搜索列表
        } else if (type == 3) {
            focusBeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadFocusBeData();//请求网络获取搜索列表
        }
    }

    /**
     * 隐藏下华兰
     *
     * @param position
     */
    public void selectLine(int position) {
        for (int i = 0; i < 3; i++) {
            if (i != position) {
                pulls.get(i).setVisibility(View.GONE);
            } else {
                pulls.get(i).setVisibility(View.VISIBLE);
            }

        }
        if(position==0){
            rbDy.setChecked(true);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshHomePageEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void reLoadDatas() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                switch (type) {
                    case 1:
                        mvpPresenter.LoadData();
                        break;
                    case 2:
                        mvpPresenter.LoadFocusMeData();
                        break;
                    case 3:
                        mvpPresenter.LoadFocusBeData();
                        break;
                    default:
                        initHeadInfo();
                        break;
                }
            }
        });
    }
}
