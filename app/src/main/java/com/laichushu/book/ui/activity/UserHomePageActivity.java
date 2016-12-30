package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.event.RefrushHomePageEvent;
import com.laichushu.book.event.RefrushUserPageEvent;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.userhomepage.UserHomePagePresener;
import com.laichushu.book.mvp.userhomepage.UserHomePageView;
import com.laichushu.book.ui.adapter.UserDynamicAdapter;
import com.laichushu.book.ui.adapter.UserFocusHeAdapter;
import com.laichushu.book.ui.adapter.UserHeFoucsAdapter;
import com.laichushu.book.ui.adapter.UserWorksListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ModelUtils;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户主页详情
 * 2016年11月25日17:04:06
 */
public class UserHomePageActivity extends MvpActivity2<UserHomePagePresener> implements UserHomePageView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack, ivAnthor, ivOther, ivHead, ivGrade, ivGradeDetails, ivGradeDetail;
    private TextView tvTitle, nickName, tvAuthorGrade;
    private HomePersonFocusResult.DataBean dataBean;
    private TextView btnFocus;
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
    private HomeUserResult userBean;
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
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_middleLeft));
        ivAnthor = ((ImageView) inflate.findViewById(R.id.iv_title_another));
        ivOther = ((ImageView) inflate.findViewById(R.id.iv_title_other));
        nickName = ((TextView) inflate.findViewById(R.id.tv_userNickName));
        tvAuthorGrade = ((TextView) inflate.findViewById(R.id.tv_userRealName));
        ivGrade = ((ImageView) inflate.findViewById(R.id.iv_userGrade));
        ivHead = ((ImageView) inflate.findViewById(R.id.iv_userHeadImg));
        ivGradeDetails = ((ImageView) inflate.findViewById(R.id.iv_userGradeDetails));
        ivGradeDetail = ((ImageView) inflate.findViewById(R.id.iv_userGradeDetail));
        btnFocus = ((TextView) inflate.findViewById(R.id.btn_userFocus));

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
        ivAnthor.setVisibility(View.VISIBLE);
        ivOther.setVisibility(View.VISIBLE);
        GlideUitl.loadImg(mActivity, R.drawable.icon_book_comment, ivAnthor);
        GlideUitl.loadImg(mActivity, R.drawable.icon_share, ivOther);
        GlideUitl.loadImg(mActivity, R.drawable.icon_geade_details2x, ivGradeDetails);

        ivBack.setOnClickListener(this);
        ivGradeDetail.setOnClickListener(this);
        btnFocus.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        ivAnthor.setOnClickListener(this);

        //初始化mRecyclerView 动态
        mDyRecyclerView.setGridLayout(1);
        mDyRecyclerView.setFooterViewText("加载中");
        dyAdapter = new UserDynamicAdapter(this, dyData, mvpPresenter);
        mDyRecyclerView.setAdapter(dyAdapter);
        mDyRecyclerView.setOnPullLoadMoreListener(this);
        //初始化作品
        //初始化mRecyclerView 他的作品
        mWorksRecyclerView.setGridLayout(3);
        mWorksRecyclerView.setFooterViewText("加载中");
        worksAdapter = new UserWorksListAdapter(this, worksData, mvpPresenter);
        mWorksRecyclerView.setAdapter(worksAdapter);
        mWorksRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 关注他的
        mHeFocusRecyclerView.setGridLayout(1);
        mHeFocusRecyclerView.setFooterViewText("加载中");
        beAdapter = new UserHeFoucsAdapter(this, focusBeData, mvpPresenter, 1);
        mHeFocusRecyclerView.setAdapter(beAdapter);
        mHeFocusRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView 他关注的
        mFocusHeRecyclerView.setGridLayout(1);
        mFocusHeRecyclerView.setFooterViewText("加载中");
        heAdapter = new UserFocusHeAdapter(this, focusMeData, mvpPresenter, 2);
        mFocusHeRecyclerView.setAdapter(heAdapter);
        mFocusHeRecyclerView.setOnPullLoadMoreListener(this);
        // 初始化头像+动态
        dataBean = (HomePersonFocusResult.DataBean) getIntent().getSerializableExtra("bean");
        flg = getIntent().getIntExtra("type", 0);
        String UserID = getIntent().getStringExtra("userId");
        if (flg == 1) {
            this.userId = dataBean.getSourceUserId();
        } else if (flg == 2) {
            this.userId = dataBean.getTargetUserId();
        } else if (!TextUtils.isEmpty(UserID)) {
            this.userId = UserID;
        }
        mvpPresenter.getUserHeadDate(userId);
        mvpPresenter.getUserDynmicDate(userId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_userGradeDetail:
                //等级说明
                UIUtil.openActivity(this, HomePageGradeDetailsActivity.class);
                break;
            case R.id.btn_userFocus:
                //关注
                if (!userBean.isBeFocused()) {
                    mvpPresenter.loadAddFocus(userId, true, null, 0, 0);
                } else {
                    mvpPresenter.loadDelFocus(userId, false, null, 0, 0);
                }
                break;
            case R.id.iv_title_another:
                //打开私信弹框
                openSendPerMsgDialog();
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
                    mvpPresenter.getUserDynmicDate(userId);
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
                    showProgressDialog();
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
                    showProgressDialog();
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
                    showProgressDialog();
                    mvpPresenter.getUserFocusHeDate(userId);
                }
                dibbleFoHe = true;
                break;
        }
    }

    @Override
    public void getUserHeadDateSuccess(HomeUserResult result) {
        if (result.isSuccess()) {
            userBean = result;
            GlideUitl.loadRandImg(mActivity, result.getPhoto(), ivHead);
            nickName.setText(result.getNickName());
            if (null != result.getLevelType()) {
                switch (result.getLevelType()) {
                    case "1":
                        tvAuthorGrade.setText("金牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_gold_medal2x, ivGrade);
                        break;
                    case "2":
                        tvAuthorGrade.setText("银牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_silver_medal2x, ivGrade);
                        break;
                    case "3":
                        tvAuthorGrade.setText("铜牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_copper_medal2x, ivGrade);
                        break;
                }
            } else {
                ivGrade.setVisibility(View.GONE);
                tvAuthorGrade.setText("暂无等级");
            }

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
            dyData = result.getData();

            if (!dyData.isEmpty()) {
                dyAdapter.refreshAdapter(dyData);
                PAGE_NO++;
            } else {
                ToastUtil.showToast(result.getErrMsg());
            }
        } else {
            ToastUtil.showToast("没有更多数据");
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getUserHomeBookListSuccess(HomeHotModel result) {
        dismissProgressDialog();
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
        dismissProgressDialog();
        focusMeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeFocusRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
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
        dismissProgressDialog();
        focusBeData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFocusHeRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (result.isSuccess()) {
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
     * 添加关注
     *
     * @param model
     * @param flg
     */
    @Override
    public void getFocusBeStatus(HomeFocusResult model, boolean flg, HomePersonFocusResult.DataBean dataBean, int position, int types) {
        if (model.isSuccess()) {
            HomePersonFocusResult.DataBean bean = dataBean;
            if (types != 0) {
                if (flg) {
                    bean.setStatus(true);
                    ToastUtil.showToast("关注成功！");
                } else {
                    bean.setStatus(false);
                    ToastUtil.showToast("关注失败！");
                }
                if (types == 1) {
                    focusBeData.set(position, bean);
                    beAdapter.setDataBeen(focusBeData);
                } else if (types == 2) {
                    focusMeData.set(position, bean);
                    heAdapter.setDataBeen(focusMeData);
                }
            } else {
                if (flg) {
                    btnFocus.setText("已关注");
                    userBean.setBeFocused(true);
                    ToastUtil.showToast("关注成功！");
                } else {
                    btnFocus.setText("关注");
                    userBean.setBeFocused(false);
                    ToastUtil.showToast("取消关注！");
                }
            }

        } else {
            if (flg) {
                ToastUtil.showToast("关注失败！");
            } else {
                ToastUtil.showToast("取消关注失败！");
            }

            LoggerUtil.toJson(model);
            btnFocus.setClickable(true);
        }


    }

    /**
     * 取消关注
     *
     * @param model
     * @param flg
     * @param dataBean
     * @param position
     * @param types
     */
    @Override
    public void getFocusMeStatus(HomeFocusResult model, boolean flg, HomePersonFocusResult.DataBean dataBean, int position, int types) {
        if (model.isSuccess()) {
            HomePersonFocusResult.DataBean bean = dataBean;
            if (types != 0) {
                if (flg) {
                    bean.setStatus(true);
                    ToastUtil.showToast("关注成功！");
                } else {
                    bean.setStatus(false);
                    ToastUtil.showToast("取消关注成功！");
                }
                if (types == 1) {
                    focusBeData.set(position, bean);
                    beAdapter.setDataBeen(focusBeData);
                } else if (types == 2) {
                    focusMeData.set(position, bean);
                    heAdapter.setDataBeen(focusMeData);
                }
            } else {
                if (!flg) {
                    btnFocus.setText("关注");
                    userBean.setBeFocused(false);
                    ToastUtil.showToast("取消关注！");
                } else {
                    btnFocus.setText("已关注");
                    userBean.setBeFocused(true);
                    ToastUtil.showToast("关注成功！");
                }
            }
        } else {
            if (flg) {
                ToastUtil.showToast("关注失败！");
            } else {
                ToastUtil.showToast("取消关注失败！");
            }
            LoggerUtil.toJson(model);
            btnFocus.setClickable(true);
        }


    }

    /**
     * 收藏
     *
     * @param model
     * @param type
     */
    @Override
    public void getSaveCollectSuccess(RewardResult model, String type, HomeUseDyrResult.DataBean dataBean, int position) {
        if (model.isSuccess()) {
            HomeUseDyrResult.DataBean bean = dataBean;
            if (type.equals("0")) {
                bean.setCollect(true);
                bean.setCollectNum(bean.getCollectNum() + 1);
                ToastUtil.showToast("收藏成功！");
            } else {
                bean.setCollect(false);
                bean.setCollectNum(bean.getCollectNum() - 1);
                ToastUtil.showToast("取消收藏！");
            }
            dyData.set(position, bean);
            dyAdapter.setDataBeen(dyData);

        } else {
            if (type.equals("0")) {
                ToastUtil.showToast("收藏失败！");
            } else {
                ToastUtil.showToast("取消收藏失败！");
            }

            LoggerUtil.toJson(model);
        }
    }

    /**
     * 发送私信结果
     *
     * @param model
     */
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
    public void getBookDetailsByIdDataSuccess(BookDetailsModle model) {
        //跳转图书详情页
        dismissProgressDialog();
        if (model.isSuccess()) {
            Bundle bundle = new Bundle();
            HomeHotModel.DataBean dataBean = ModelUtils.bean2HotBean(model);
            bundle.putParcelable("bean", dataBean);
            bundle.putString("pageMsg", "浏览收藏详情");
            UIUtil.openActivity(this, BookDetailActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String errorMsg) {
        Logger.e(errorMsg);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }


    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        if (type == 1) {
            dyData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserDynmicDate(userId);//动态
        } else if (type == 2) {
            worksData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserBookListDate(userId);//作品
        } else if (type == 3) {
            focusBeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(userId);//他关注的
        } else if (type == 4) {
            focusMeData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(userId);//关注他的
        }
    }

    @Override
    public void onLoadMore() {
        if (type == 1) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserDynmicDate(userId);//请求网络获取搜索列表
        } else if (type == 2) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserBookListDate(userId);//请求网络获取搜索列表
        } else if (type == 3) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(userId);//请求网络获取搜索列表
        } else if (type == 4) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.getUserHeFocusDate(userId);//请求网络获取搜索列表
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
                mvpPresenter.loadAddPerInfoDate(userId, edMsg);
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
        EventBus.getDefault().postSticky(new RefrushHomePageEvent(true));
        super.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushUserPageEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }
}
