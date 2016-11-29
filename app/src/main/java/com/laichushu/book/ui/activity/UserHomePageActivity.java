package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.userhomepage.UserHomePagePresener;
import com.laichushu.book.mvp.userhomepage.UserHomePageView;
import com.laichushu.book.ui.adapter.HomePageFocusMeAdapter;
import com.laichushu.book.ui.adapter.UserDynamicAdapter;
import com.laichushu.book.ui.adapter.UserFocusHeAdapter;
import com.laichushu.book.ui.adapter.UserHeFoucsAdapter;
import com.laichushu.book.ui.adapter.UserWorksListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户主页详情
 * 2016年11月25日17:04:06
 */
public class UserHomePageActivity extends MvpActivity2<UserHomePagePresener> implements UserHomePageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack, ivHead, ivGrade, ivGradeDetails;
    private TextView tvTitle, nickName, tvRealName, tvAuthorGrade;
    private HomePersonFocusResult.DataBean dataBean;
    private Button btnFocus;
    private PullLoadMoreRecyclerView mDyRecyclerView, mWorksRecyclerView, mHeFocusRecyclerView, mFocusHeRecyclerView;
    private UserDynamicAdapter dyAdapter;
    private UserWorksListAdapter worksAdapter;
    private UserHeFoucsAdapter beAdapter;//关注他的
    private UserFocusHeAdapter heAdapter;//他关注的
    private RadioGroup radioGroup;
    private List<HomeUseDyrResult.DataBean> dyData = new ArrayList<>();
    private List<HomeHotModel.DataBean> worksData = new ArrayList<>();
    private List<HomePersonFocusResult.DataBean> focusBeData = new ArrayList<>();
    private List<HomePersonFocusResult.DataBean> focusMeData = new ArrayList<>();
    private int PAGE_NO = 1, type = 1;
    private boolean dibbleDy = false, dibbleWorks = false, dibbleheFo = false, dibbleFoHe = false;
    private List<View> pulls = new ArrayList<>();
    private String userId;

    /**
     * 1 关注我的 2 我关注的
     */
    private int flg;

    @Override
    protected UserHomePagePresener createPresenter() {
        return new UserHomePagePresener(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_user_home_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        nickName = ((TextView) inflate.findViewById(R.id.tv_userNickName));
        tvRealName = ((TextView) inflate.findViewById(R.id.tv_userRealName));
        tvAuthorGrade = ((TextView) inflate.findViewById(R.id.tv_userAuthorGrade));
        ivGrade = ((ImageView) inflate.findViewById(R.id.iv_userGrade));
        ivHead = ((ImageView) inflate.findViewById(R.id.iv_userHeadImg));
        ivGradeDetails = ((ImageView) inflate.findViewById(R.id.iv_userGradeDetails));
        btnFocus = ((Button) inflate.findViewById(R.id.btn_userFocus));

        radioGroup = (RadioGroup) inflate.findViewById(R.id.rg_userList);
        mDyRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_userDynamic);
        mWorksRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_userWorks);
        mHeFocusRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_userHeFocus);
        mFocusHeRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_userFocusHe);

        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        pulls.clear();
        pulls.add(mDyRecyclerView);
        pulls.add(mWorksRecyclerView);
        pulls.add(mHeFocusRecyclerView);
        pulls.add(mFocusHeRecyclerView);

        tvTitle.setText("用户主页");
        tvTitle.setVisibility(View.VISIBLE);

        ivBack.setOnClickListener(this);
        ivGradeDetails.setOnClickListener(this);
        btnFocus.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        //初始化mRecyclerView 关注
        mDyRecyclerView.setGridLayout(1);
        mDyRecyclerView.setFooterViewText("加载中");
        dyAdapter = new UserDynamicAdapter(this, dyData);
        mDyRecyclerView.setAdapter(dyAdapter);
        mDyRecyclerView.setOnPullLoadMoreListener(this);
        //初始化作品
        //初始化mRecyclerView 他的作品
        mWorksRecyclerView.setGridLayout(3);
        mWorksRecyclerView.setFooterViewText("加载中");
        worksAdapter = new UserWorksListAdapter(this, worksData);
        mWorksRecyclerView.setAdapter(worksAdapter);
        mWorksRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 关注他的
        mHeFocusRecyclerView.setGridLayout(1);
        mHeFocusRecyclerView.setFooterViewText("加载中");
        beAdapter = new UserHeFoucsAdapter(this, focusBeData, mvpPresenter);
        mHeFocusRecyclerView.setAdapter(beAdapter);
        mHeFocusRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 他关注的
        mFocusHeRecyclerView.setGridLayout(1);
        mFocusHeRecyclerView.setFooterViewText("加载中");
        heAdapter = new UserFocusHeAdapter(this, focusMeData, mvpPresenter);
        mFocusHeRecyclerView.setAdapter(heAdapter);
        mFocusHeRecyclerView.setOnPullLoadMoreListener(this);
        // 初始化头像+动态
        dataBean = (HomePersonFocusResult.DataBean) getIntent().getSerializableExtra("bean");
        flg = getIntent().getIntExtra("type", 0);
        if (flg == 1) {
            mvpPresenter.getUserHeadDate(dataBean.getSourceUserId());
            mvpPresenter.getUserDynmicDate(dataBean.getSourceUserId());
        } else if (flg == 2) {
            mvpPresenter.getUserHeadDate(dataBean.getTargetUserId());
            mvpPresenter.getUserDynmicDate(dataBean.getTargetUserId());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_userGradeDetails:
                //等级说明
                UIUtil.openActivity(this, HomePageGradeDetailsActivity.class);
                break;
            case R.id.btn_userFocus:
                //关注

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PAGE_NO = 1;
        switch (checkedId) {
            case R.id.rb_userDynamic:
                // 动态
                selectLine(0);
                dibbleWorks = false;
                dibbleheFo = false;
                dibbleFoHe = false;
                type = 1;
                if (!dibbleDy) {
                    dyData.clear();
                    mvpPresenter.getUserDynmicDate(dataBean.getSourceUserId());
                }
                dibbleDy = true;
                break;
            case R.id.rb_userWorks:
                // 作品
                selectLine(1);
                dibbleDy = false;
                dibbleheFo = false;
                dibbleFoHe = false;
                type = 2;
                if (!dibbleWorks) {
                    worksData.clear();
                    mvpPresenter.getUserBookListDate(userId);
                }
                dibbleWorks = true;
                break;
            case R.id.rb_userHeFocus:
                // 他关注的
                selectLine(2);
                dibbleDy = false;
                dibbleWorks = false;
                dibbleFoHe = false;
                type = 3;
                if (!dibbleheFo) {
                    focusMeData.clear();
                    mvpPresenter.getUserHeFocusDate(userId);
                }
                dibbleheFo = true;
                break;
            case R.id.rb_userFocusHe:
                // 关注他的
                selectLine(3);
                dibbleWorks = false;
                dibbleDy = false;
                dibbleheFo = false;
                type = 4;
                if (!dibbleFoHe) {
                    focusBeData.clear();
                    mvpPresenter.getUserFocusHeDate(userId);
                }
                dibbleFoHe = true;
                break;
        }
    }

    @Override
    public void getUserHeadDateSuccess(HomeUserResult result) {
        if (result.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            GlideUitl.loadRandImg(mActivity, result.getPhoto(), ivHead);
            nickName.setText(result.getNickName());
            tvRealName.setText(result.getNickName());
            userId = result.getUserId();
            if (result.isBeFocused()) {
                btnFocus.setText("已关注");
            } else {
                btnFocus.setText("关注");
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }

    }

    @Override
    public void getUserHomeDyDateSuccess(HomeUseDyrResult result) {
        dyData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDyRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
            ToastUtil.showToast("HomeUseDyrResult");
            dyData = result.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!dyData.isEmpty()) {
                dyAdapter.refreshAdapter(dyData);
                PAGE_NO++;
            } else {

            }
        } else {
        }
    }

    @Override
    public void getUserHomeBookListSuccess(HomeHotModel result) {
        worksData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWorksRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
            worksData = result.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!worksData.isEmpty()) {
                worksAdapter.refreshAdapter(worksData);
                PAGE_NO++;
            } else {

            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    /**
     * 他关注的
     *
     * @param result
     */
    @Override
    public void getUserHomeFocusHeSuccess(HomePersonFocusResult result) {
        focusMeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeFocusRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
            ToastUtil.showToast("HomeUseDyrResult");
            focusMeData = result.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!focusMeData.isEmpty()) {
                PAGE_NO++;
            } else {
                ToastUtil.showToast(result.getErrMsg());
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            ToastUtil.showToast(result.getErrMsg());
        }
        heAdapter.refreshAdapter(focusMeData);
    }

    /**
     * 关注他的
     *
     * @param result
     */
    @Override
    public void getUserHomeHeFocusSuccess(HomePersonFocusResult result) {
        focusBeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFocusHeRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
            ToastUtil.showToast("HomeUseDyrResult");
            focusBeData = result.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!focusBeData.isEmpty()) {
                PAGE_NO++;
            } else {
                ToastUtil.showToast(result.getErrMsg());
            }
        } else {
            beAdapter.refreshAdapter(focusBeData);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            ToastUtil.showToast(result.getErrMsg());
        }
        beAdapter.refreshAdapter(focusBeData);
    }

    /**
     * 关注他的
     *
     * @param model
     * @param flg
     */
    @Override
    public void getFocusBeStatus(HomeFocusResult model, boolean flg) {
        if (model.isSuccess()) {
            if (flg) {
                ToastUtil.showToast("关注成功！");
            } else {
                ToastUtil.showToast("取消关注成功！");
            }
        } else {
            ToastUtil.showToast("关注失败！");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getFocusMeStatus(HomeFocusResult model, boolean flg) {
        if (model.isSuccess()) {
            if (flg) {
                ToastUtil.showToast("关注成功！");
            } else {
                ToastUtil.showToast("取消关注成功！");
            }

        } else {
            ToastUtil.showToast("关注失败！");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getDataFail(String errorMsg) {
        Logger.e(errorMsg);
    }


    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        if (type == 1) {
            dyData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserDynmicDate(dataBean.getSourceUserId());//动态
        } else if (type == 2) {
            worksData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserBookListDate(dataBean.getSourceUserId());//作品
        } else if (type == 3) {
            focusBeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(dataBean.getSourceUserId());//他关注的
        } else if (type == 4) {
            focusMeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(dataBean.getSourceUserId());//关注他的
        }
    }

    @Override
    public void onLoadMore() {
        if (type == 1) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserDynmicDate(dataBean.getSourceUserId());//请求网络获取搜索列表
        } else if (type == 2) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserBookListDate(dataBean.getSourceUserId());//请求网络获取搜索列表
        } else if (type == 3) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(dataBean.getSourceUserId());//请求网络获取搜索列表
        } else if (type == 4) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(dataBean.getSourceUserId());//请求网络获取搜索列表
        }
    }

    /**
     * 隐藏下华兰
     *
     * @param position
     */
    public void selectLine(int position) {
        for (int i = 0; i < 4; i++) {
            if (i != position) {
                pulls.get(i).setVisibility(View.GONE);
            } else {
                pulls.get(i).setVisibility(View.VISIBLE);
            }

        }
    }
}
