package com.sofacity.laichushu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.home.HomePresenter;
import com.sofacity.laichushu.mvp.home.HomeView;
import com.sofacity.laichushu.ui.adapter.HomeRecyclerAdapter;
import com.sofacity.laichushu.ui.adapter.HomeTitleViewPagerAdapter;
import com.sofacity.laichushu.ui.base.MvpFragment;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 首页
 * Created by wangtong on 2016/10/17.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView, ViewPager.OnPageChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ImageView pointIv;
    private ViewPager homeVp;
    private LinearLayout lineLyt;
    private int range;
    private PullLoadMoreRecyclerView mRecyclerView;
    ArrayList<String> mTitleData = new ArrayList<>();
    private ArrayList mData = new ArrayList();
    private ArrayList mHotData = new ArrayList();
    private HomeTitleViewPagerAdapter adapter;
    private HomeRecyclerAdapter mAdapter;

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
        mRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_home);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleViewPager();
        initRecycler();
    }

    /**
     * PullLoadMoreRecyclerView 的初始化
     */
    private void initRecycler() {
        mHotData.clear();
        mData.clear();
        for (int i = 0; i < 9; i++) {
            mHotData.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg");
        }
        for (int i = 0; i < 9; i++) {
            mData.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg");
        }
        mRecyclerView.setLinearLayout();
        mAdapter = new HomeRecyclerAdapter(mData, mActivity, mHotData,mvpPresenter);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setFooterViewText("加载中");
        mRecyclerView.setOnPullLoadMoreListener(this);
    }

    /**
     * 标题轮播图
     */
    private void titleViewPager() {
        mTitleData.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg");
        mTitleData.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg");
        adapter = new HomeTitleViewPagerAdapter(mTitleData,mActivity);
        homeVp.setAdapter(adapter);
        homeVp.setOnPageChangeListener(this);
        pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                range = lineLyt.getChildAt(1).getLeft() - lineLyt.getChildAt(0).getLeft();
            }
        });
        for (int i = 0; i < mTitleData.size(); i++) {
            if (lineLyt.getChildCount()>1){
                return;
            }
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

    }

    @Override
    public void getDataFail(String msg) {

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
        for (int i = 0; i < 12; i++) {
            mData.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg");
        }
        mAdapter.setmData(mData);
    }

    /**
     * 滑动监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = (int) ((position+positionOffset)*range);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = move;
        pointIv.setLayoutParams(params);
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
        },2000);
    }
}
