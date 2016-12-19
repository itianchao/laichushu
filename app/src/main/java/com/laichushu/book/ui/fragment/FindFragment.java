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
import com.laichushu.book.mvp.FindPresenter;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.write.FindView;
import com.laichushu.book.ui.adapter.FindTitleViewPagerAdapter;
import com.laichushu.book.ui.adapter.HomeTitleViewPagerAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 发现
 * Created by wangtong on 2016/10/17.
 */
public class FindFragment extends MvpFragment<FindPresenter> implements FindView, View.OnClickListener, ViewPager.OnPageChangeListener {
    private ImageView pointIv;
    private ViewPager findVp;
    private FindTitleViewPagerAdapter titleAdapter;
    private ArrayList<HomeModel.DataBean> mTitleData = new ArrayList<>();
    private int item;
    private int range;
    private LinearLayout lineLyt;
    private Handler mRefreshWidgetHandler = new Handler();
    private Runnable refreshThread = new Runnable() {

        public void run() {
            findVp.setCurrentItem(++item);
            mRefreshWidgetHandler.postDelayed(refreshThread, 5000);
        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化数据
        Bundle bundle = getArguments();
        HomeModel homeModel = bundle.getParcelable("homeModel");
        mTitleData = homeModel.getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = UIUtil.inflate(R.layout.fragment_find);
        findVp = (ViewPager) mRootView.findViewById(R.id.vp_home_title);
        pointIv = (ImageView) mRootView.findViewById(R.id.iv_red_point);
        lineLyt = (LinearLayout) mRootView.findViewById(R.id.ll_container);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleViewPager();
    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 标题轮播图
     */
    private void titleViewPager() {
        titleAdapter = new FindTitleViewPagerAdapter(mTitleData, mActivity,mvpPresenter);
        findVp.setAdapter(titleAdapter);
        int remainder = Integer.MAX_VALUE / 2 %(mTitleData.size()==0?1:mTitleData.size());
        item = Integer.MAX_VALUE / 2 - remainder;
        findVp.setCurrentItem(item);
        findVp.setOnPageChangeListener(this);
        pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (lineLyt.getChildCount()>1){
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
    public void onStart() {
        super.onStart();
        mRefreshWidgetHandler.postDelayed(refreshThread, 5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mRefreshWidgetHandler.removeCallbacks(refreshThread);
    }

    /**
     * 滑动监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = (int) ((position % (mTitleData.size()==0?1:mTitleData.size()) + positionOffset) * range);
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
}
