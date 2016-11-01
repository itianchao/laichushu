package com.sofacity.laichushu.ui.fragment;

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

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.home.HomePresenter;
import com.sofacity.laichushu.mvp.home.HomeView;
import com.sofacity.laichushu.ui.activity.HomeSearchActivity;
import com.sofacity.laichushu.ui.adapter.HomeRecyclerAdapter;
import com.sofacity.laichushu.ui.adapter.HomeTitleViewPagerAdapter;
import com.sofacity.laichushu.ui.base.MvpFragment;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList mData = new ArrayList();
    private ArrayList<HomeHotModel.DataBean> mHotData = new ArrayList<>();
    private HomeTitleViewPagerAdapter adapter;
    private HomeRecyclerAdapter mAdapter;
    private int item;
    private Handler mRefreshWidgetHandler = new Handler();
    private Runnable refreshThread = new Runnable() {

        public void run() {
            homeVp.setCurrentItem(++item);
            mRefreshWidgetHandler.postDelayed(refreshThread,5000);
        }
    };
    private LinearLayout searchLyt;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = UIUtil.inflate(R.layout.fragment_home);
        homeVp = (ViewPager) mRootView.findViewById(R.id.vp_home_title);
        pointIv = (ImageView) mRootView.findViewById(R.id.iv_red_point);
        lineLyt = (LinearLayout) mRootView.findViewById(R.id.ll_container);
        searchLyt = (LinearLayout) mRootView.findViewById(R.id.lay_search);
        mRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_home);
        lineLyt.setOnClickListener(this);
        searchLyt.setOnClickListener(this);
        return mRootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecycler();
        this.setUserVisibleHint(true);
    }

    /**
     * PullLoadMoreRecyclerView 的初始化
     */
    private void initRecycler() {
        mHotData.clear();
        mData.clear();
        mRecyclerView.setLinearLayout();
        mRecyclerView.setFooterViewText("加载中");
        mRecyclerView.setOnPullLoadMoreListener(this);
    }

    /**
     * 标题轮播图
     */
    private void titleViewPager() {
        adapter = new HomeTitleViewPagerAdapter(mTitleData,mActivity);
        homeVp.setAdapter(adapter);
        int remainder = Integer.MAX_VALUE / 2 % mTitleData.size();
        item = Integer.MAX_VALUE/2 - remainder;
        homeVp.setCurrentItem(item);
        homeVp.setOnPageChangeListener(this);
        pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                range = lineLyt.getChildAt(1).getLeft() - lineLyt.getChildAt(0).getLeft();
            }
        });
        for (int i = 0; i < mTitleData.size(); i++) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(R.drawable.shape_point_hollow);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i>0){
                params.leftMargin = UIUtil.px2dip(10);
            }
            imageView.setLayoutParams(params);
            lineLyt.addView(imageView);
        }
    }

    @Override
    public void getDataSuccess(HomeModel model) {
        hideLoading();
        if (model.isSuccess()){
            mTitleData = (ArrayList<HomeModel.DataBean>) model.getData();
            titleViewPager();
            mvpPresenter.loadHomeHotData();//请求网络获取热门
        }else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    public void getHotDataSuccess(HomeHotModel model) {
        hideLoading();
        if (model.isSuccess()){
            mHotData = (ArrayList<HomeHotModel.DataBean>) model.getData();
            mAdapter = new HomeRecyclerAdapter(mData, mActivity, mHotData,mvpPresenter);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void getDataFail(String msg) {
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

    @Override
    public void initData() {
        mData.clear();
        mAdapter.setmData(mData);
    }

    /**
     * 滑动监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = (int) ((position%mTitleData.size()+positionOffset)*range);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
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
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        },2000);
    }
    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 2000);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lay_search:
                UIUtil.openActivity(getActivity(), HomeSearchActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mRefreshWidgetHandler.postDelayed(refreshThread,5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mRefreshWidgetHandler.removeCallbacks(refreshThread);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            mvpPresenter.loadHomeCarouseData();//请求网络获取轮播图
        }
    }
}
