package com.laichushu.book.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindCourseCommResult;
import com.laichushu.book.mvp.findfragment.FindPresenter;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.findfragment.FindView;
import com.laichushu.book.ui.activity.FindEditMyPageActivity;
import com.laichushu.book.ui.activity.FindEditPageActivity;
import com.laichushu.book.ui.activity.FindCoursePageActivity;
import com.laichushu.book.ui.activity.FindGroupMainActivity;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.adapter.ClassRecycleAdapter;
import com.laichushu.book.ui.adapter.FindTitleViewPagerAdapter;
import com.laichushu.book.ui.adapter.GroupRecomAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现
 * Created by wangtong on 2016/10/17.
 */
public class FindFragment extends MvpFragment2<FindPresenter> implements FindView, View.OnClickListener, ViewPager.OnPageChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView pointIv;
    private ViewPager findVp;
    private FindTitleViewPagerAdapter titleAdapter;
    private ArrayList<HomeModel.DataBean> mTitleData = new ArrayList<>();
    private int item;
    private int range;
    private LinearLayout lineLyt;
    private Handler mRefreshWidgetHandler = new Handler();
    private PullLoadMoreRecyclerView mRecyclerView, mCourseRecyclerView;
    private Runnable refreshThread = new Runnable() {

        public void run() {
            findVp.setCurrentItem(++item);
            mRefreshWidgetHandler.postDelayed(refreshThread, 5000);
        }
    };
    //标签列表
    private int img[] = {R.drawable.icon_draft2x, R.drawable.icon_material2x, R.drawable.icon_delete2x, R.drawable.icon_publishl2x, R.drawable.icon_submission2x};
    private String title[] = {"课程", "小组", "服务", "机构", "编辑"};
    //课程
    private ClassRecycleAdapter classAdapter;
    private LinearLayout llContainer;
    //小组
    private GroupRecomAdapter courseAdapter;
    private ArrayList<FindCourseCommResult.DataBean> mCourseDate = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (mTitleData.size() == 0) {
                HomeModel homeModel = bundle.getParcelable("homeModel");
                mTitleData = homeModel.getData();
            }
        }
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_find);
        findVp = (ViewPager) mRootView.findViewById(R.id.vp_find_title);
        pointIv = (ImageView) mRootView.findViewById(R.id.iv_red_point);
        lineLyt = (LinearLayout) mRootView.findViewById(R.id.ll_container_find);
        llContainer = (LinearLayout) mRootView.findViewById(R.id.ll_tab);
        mRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_find);
        mCourseRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_find_course);
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        titleViewPager();
        //标签列表
        View itemView;
        ImageView imageView;
        TextView textView;
        llContainer.removeAllViews();
        for (int i = 0; i < img.length; i++) {
            itemView = UIUtil.inflate(R.layout.item_tab_course, null);
            imageView = (ImageView) itemView.findViewById(R.id.iv_stripIcon);
            textView = (TextView) itemView.findViewById(R.id.tv_stripContent);
            imageView.setImageResource(img[i]);
            textView.setText(title[i]);
            llContainer.addView(itemView);
            final int finalJ = i;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalJ) {
                        case 0:
                            //课程主页
                            UIUtil.openActivity(mActivity, FindCoursePageActivity.class);
                            break;
                        case 1:
                            //小组主页
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("recommend", mCourseDate);
                            UIUtil.openActivity(mActivity, FindGroupMainActivity.class, bundle);
                            break;
                        case 2:
                            //服务主页
                            UIUtil.openActivity(mActivity, FindServicePageActivity.class);
                            break;
                        case 3:
                            //机构主页
//测试
                            UIUtil.openActivity(mActivity, FindEditMyPageActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("articleId", dataBean.getArticleId());
//                            UIUtil.openActivity(mActivity, MechanismListActivity.class, bundle);
                            break;
                        case 4:
                            //编辑主页
                            UIUtil.openActivity(mActivity, FindEditPageActivity.class);
                            break;
                    }
                }
            });

        }
        //初始化精选课程
        mRecyclerView.setGridLayout(2);
        mRecyclerView.setPullRefreshEnable(false);
        mRecyclerView.setFooterViewText("加载中");
        classAdapter = new ClassRecycleAdapter(getActivity(), mCourseDate, mvpPresenter);
        mRecyclerView.setAdapter(classAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        //初始化小组推荐
        mCourseRecyclerView.setFooterViewText("加载中");
        mCourseRecyclerView.setGridLayout(4);
        mCourseRecyclerView.setPullRefreshEnable(false);
        courseAdapter = new GroupRecomAdapter(getActivity(), mCourseDate, mvpPresenter);
        mCourseRecyclerView.setAdapter(courseAdapter);
        mCourseRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.loadFindCourseCommData();
    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter(this);
    }

    @Override
    public void onClick(View v) {

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
     * 推荐小组
     *
     * @param model
     */
    @Override
    public void getCourseDataSuccess(FindCourseCommResult model) {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCourseRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        hideLoading();
        if (model.isSuccess()) {
            mCourseDate = model.getData();
            courseAdapter.refreshAdapter(mCourseDate);
            classAdapter.refreshAdapter(mCourseDate);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ErrorReloadData();
        }
    }

    @Override
    public void getDataFail(String msg) {
        ErrorReloadData();
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
     * 标题轮播图
     */
    private void titleViewPager() {
        titleAdapter = new FindTitleViewPagerAdapter(mTitleData, mActivity, mvpPresenter);
        findVp.setAdapter(titleAdapter);
        int remainder = Integer.MAX_VALUE / 2 % (mTitleData.size() == 0 ? 1 : mTitleData.size());
        item = Integer.MAX_VALUE / 2 - remainder;
        findVp.setCurrentItem(item);
        findVp.setOnPageChangeListener(this);
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    /**
     * 失败后 重新请求按钮
     */
    public void ErrorReloadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadFindCourseCommData();
            }
        });
    }
}
