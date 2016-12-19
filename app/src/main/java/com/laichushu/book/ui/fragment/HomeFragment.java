package com.laichushu.book.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshBookDetaileCommentEvent;
import com.laichushu.book.event.RefurshHomeEvent;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.home.HomePresenter;
import com.laichushu.book.mvp.home.HomeView;
import com.laichushu.book.ui.activity.CategoryActivity;
import com.laichushu.book.ui.activity.HomeSearchActivity;
import com.laichushu.book.ui.activity.MainActivity;
import com.laichushu.book.ui.adapter.HomeRecyclerAdapter;
import com.laichushu.book.ui.adapter.HomeTitleViewPagerAdapter;
import com.laichushu.book.ui.base.MvpFragment;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 首页
 * Created by wangtong on 2016/10/17.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView, ViewPager.OnPageChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {

    private ImageView pointIv;
    private ViewPager homeVp;
    private LinearLayout lineLyt;
    private int range;
    private PullLoadMoreRecyclerView mRecyclerView;
    ArrayList<HomeModel.DataBean> mTitleData = new ArrayList<>();
    private ArrayList<HomeHotModel.DataBean> mData = new ArrayList<>();
    private ArrayList<HomeHotModel.DataBean> mAllData = new ArrayList<>();
    private ArrayList<HomeHotModel.DataBean> mHotData = new ArrayList<>();
    private HomeTitleViewPagerAdapter adapter;
    private HomeRecyclerAdapter mAdapter;
    private int item;
    private Handler mRefreshWidgetHandler = new Handler();
    private Runnable refreshThread = new Runnable() {
        public void run() {
            homeVp.setCurrentItem(++item);
            mRefreshWidgetHandler.postDelayed(refreshThread, 5000);
        }
    };
    private LinearLayout searchLyt;
    private String pageNo = "2";//全部不加在
    private String type = "1";//类型
    private String pageNo2 = "1";//活动加载1次
    private ImageView categoryIv;
    private HomeHotModel model;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        HomeModel homeModel = bundle.getParcelable("homeModel");
        HomeHotModel homeHotModel = bundle.getParcelable("homeHotModel");
        HomeHotModel homeAllModel = bundle.getParcelable("homeAllModel");
        if (mTitleData.size() == 0) mTitleData = homeModel.getData();
        if (mHotData.size() == 0) mHotData = homeHotModel.getData();
        if (mData.size() == 0) mData = homeAllModel.getData();
    }

    @Override
    protected HomePresenter createPresenter() {
        EventBus.getDefault().register(this);
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = UIUtil.inflate(R.layout.fragment_home);
        homeVp = (ViewPager) mRootView.findViewById(R.id.vp_home_title);
        pointIv = (ImageView) mRootView.findViewById(R.id.iv_red_point);
        categoryIv = (ImageView) mRootView.findViewById(R.id.iv_category);
        lineLyt = (LinearLayout) mRootView.findViewById(R.id.ll_container);
        searchLyt = (LinearLayout) mRootView.findViewById(R.id.lay_search);
        mRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_home);
        searchLyt.setOnClickListener(this);
        categoryIv.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecycler();
        titleViewPager();
    }

    /**
     * PullLoadMoreRecyclerView 的初始化
     */
    private void initRecycler() {
        mRecyclerView.setLinearLayout();
        mRecyclerView.setFooterViewText("加载中");
        mAdapter = new HomeRecyclerAdapter(mData, (MainActivity) getActivity(), mHotData, mvpPresenter, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
    }

    /**
     * 标题轮播图
     */
    private void titleViewPager() {
        adapter = new HomeTitleViewPagerAdapter(mTitleData, mActivity, mvpPresenter);
        homeVp.setAdapter(adapter);
        int remainder = Integer.MAX_VALUE / 2 % (mTitleData.size() == 0 ? 1 : mTitleData.size());
        item = Integer.MAX_VALUE / 2 - remainder;
        homeVp.setCurrentItem(item);
        homeVp.setOnPageChangeListener(this);
        pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (lineLyt.getChildCount() > 1) {
                    range = lineLyt.getChildAt(1).getLeft() - lineLyt.getChildAt(0).getLeft();
                }
            }
        });
        for (int i = 0; i < mTitleData.size(); i++) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(R.drawable.shape_point_hollow);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = UIUtil.px2dip(10);
            }
            imageView.setLayoutParams(params);
            lineLyt.addView(imageView);
        }
    }

    @Override
    public void getDataSuccess(HomeModel model) {
        mRecyclerView.setPullLoadMoreCompleted();
        hideLoading();
        if (model.isSuccess()) {
            mTitleData = model.getData();
            titleViewPager();
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getHotDataSuccess(HomeHotModel model) {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        hideLoading();
        if (model.isSuccess()) {
            this.model = model;
            mHotData = model.getData();
            mAdapter = new HomeRecyclerAdapter(mData, (MainActivity) getActivity(), mHotData, mvpPresenter, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 获取图书列表成功
     * @param model
     */
    @Override
    public void getAllData(HomeHotModel model) {
        mAdapter.getActivityRbn().setEnabled(true);
        hideLoading();
        mAllData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            mAllData = model.getData();
            if (mAllData.size() != 0) {
                pageNo = Integer.parseInt(pageNo) + 1 + "";
                mData.addAll(mAllData);
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 获取活动列表接成功
     *
     * @param model
     */
    @Override
    public void getActivityData(HomeHotModel model) {
        mAdapter.getAllRbn().setEnabled(true);
        hideLoading();
        mAllData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            mAllData = model.getData();
            if (mAllData.size() != 0) {
                pageNo2 = Integer.parseInt(pageNo2) + 1 + "";
                mData.addAll(mAllData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        mAdapter.getAllRbn().setEnabled(true);
        mAdapter.getActivityRbn().setEnabled(true);
        mRecyclerView.setPullLoadMoreCompleted();
        Logger.e(msg);
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
     * 滑动监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = (int) ((position % (mTitleData.size() == 0 ? 1 : mTitleData.size()) + positionOffset) * range);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = move;
        pointIv.setLayoutParams(params);
        item = position;
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mHotData.clear();
        mData.clear();
        int state = mvpPresenter.getState();//选择状态
        if (state == 1) {
            pageNo = "1";
            mvpPresenter.getParamet().setPageNo(pageNo);
            mvpPresenter.loadHomeHotData();//请求网络获取热门
            mvpPresenter.loadHomeAllData(type);//请求网络获取全部活动等列表
        } else if (state == 2) {
            pageNo2 = "1";
            mvpPresenter.getActivityListParamet().setPageNo(pageNo2);
            mvpPresenter.loadHomeHotData();//请求网络获取热门
            mvpPresenter.loadActivityData();//请求活动
        }
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        int state = mvpPresenter.getState();
        if (state == 1) {
            mvpPresenter.getParamet().setPageNo(pageNo);
            mvpPresenter.loadHomeAllData(type);
        } else if (state == 2) {
            mvpPresenter.getActivityListParamet().setPageNo(pageNo2);
            mvpPresenter.loadActivityData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_search:
                UIUtil.openActivity(getActivity(), HomeSearchActivity.class);
                break;
            case R.id.iv_category:
                UIUtil.openActivity(getActivity(), CategoryActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mRefreshWidgetHandler.postDelayed(refreshThread, 5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mRefreshWidgetHandler.removeCallbacks(refreshThread);
    }

    public void setType(String type) {
        this.type = type;
    }

    public HomeHotModel getModel() {
        return model;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 刷新 活动
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshHomeEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            for (int position = 0; position < mData.size(); position++) {
                if (mData.get(position).getArticleId().equals(event.getBean().getArticleId())) {
                    mData.set(position, event.getBean());
                    mAdapter.setmData(mData);
                    break;
                }
            }
        }
    }

    /**
     * 刷新 全部热门
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshBookDetaileCommentEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        int position = event.getPosition();
        mData.set(position, event.getBean());
        for (int i = 0; i < mData.size(); i++) {
            if (mHotData.get(i).getArticleId().equals(event.getBean().getArticleId())) {
                mHotData.set(i, event.getBean());
                break;
            }
        }
        mAdapter.setmData(mData);
        mAdapter.setmHotData(mData);
    }
}
