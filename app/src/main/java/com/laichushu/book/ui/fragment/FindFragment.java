package com.laichushu.book.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindCourseCommResult;
import com.laichushu.book.mvp.find.FindPresenter;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.mvp.find.group.GroupListModle;
import com.laichushu.book.mvp.home.homelist.HomeModel;
import com.laichushu.book.mvp.find.FindView;
import com.laichushu.book.ui.activity.FindEditPageActivity;
import com.laichushu.book.ui.activity.FindCoursePageActivity;
import com.laichushu.book.ui.activity.FindGroupMainActivity;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.ui.activity.MechanismListActivity;
import com.laichushu.book.ui.adapter.ClassRecycleAdapter;
import com.laichushu.book.ui.adapter.FindTitleViewPagerAdapter;
import com.laichushu.book.ui.adapter.GroupRecomAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

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
    private PullLoadMoreRecyclerView mLessonRecyclerView;
    //标签列表
    private int img[] = {R.drawable.home_course2x, R.drawable.home_group2x, R.drawable.home_server2x, R.drawable.home_agency2x, R.drawable.home_editor2x};
    private String title[] = {"课程", "小组", "服务", "机构", "编辑"};
    //课程
    private ClassRecycleAdapter classAdapter;
    private LinearLayout llContainer;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mLessonDate = new ArrayList<>();
    //小组
    private GroupRecomAdapter courseAdapter;
    private ArrayList<GroupListModle.DataBean> mCourseDate = new ArrayList<>();
    private boolean frist = false;
    private boolean second = false;
    /**
     * handler
     */
    private MyHandler mhandler = new MyHandler(this);

    static class MyHandler extends Handler {
        private WeakReference<FindFragment> weakReference;

        MyHandler(FindFragment mFragment) {
            weakReference = new WeakReference<>(mFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FindFragment mFragment = weakReference.get();
            if (mFragment != null) {
                if (mFragment.frist && mFragment.second) {
                    mFragment.refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                }
            }
        }
    }

    private AutoScrollHandler mRefreshWidgetHandler = new AutoScrollHandler();

    private class AutoScrollHandler extends Handler {
        boolean pause = false;

        @Override
        public void handleMessage(Message msg) {
            if (!pause) {
                findVp.setCurrentItem(findVp.getCurrentItem() + 1);
            }
            sendEmptyMessageDelayed(msg.what, 3000);
        }

        void startLoop() {
            pause = false;
            removeCallbacksAndMessages(null);
            sendEmptyMessageDelayed(1, 3000);
        }

        void stopLoop() {
            removeCallbacksAndMessages(null);
        }
    }

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
        mLessonRecyclerView = (PullLoadMoreRecyclerView) mRootView.findViewById(R.id.ryv_find_lesson);
        return mRootView;
    }

    @Override
    public void initData() {
        super.initData();
        titleViewPager();
        //标签列表
        View itemView;
        ImageView imageView;
        TextView textView;
        LinearLayout llItem;
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                UIUtil.getScreenWidth() / img.length,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llContainer.removeAllViews();
        for (int i = 0; i < img.length; i++) {
            itemView = UIUtil.inflate(R.layout.item_tab_course, null);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_itemHead);
            imageView = (ImageView) itemView.findViewById(R.id.iv_stripIcon);
            textView = (TextView) itemView.findViewById(R.id.tv_stripContent);
            GlideUitl.loadRandImg(mActivity, img[i], imageView);
            textView.setText(title[i]);
            llItem.setLayoutParams(linearParams);
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
                            Bundle mechBundle = new Bundle();
                            UIUtil.openActivity(mActivity, MechanismListActivity.class, mechBundle);
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
        mLessonRecyclerView.setLinearLayout();
        mLessonRecyclerView.setFooterViewText("加载中");
        classAdapter = new ClassRecycleAdapter(getActivity(), mLessonDate, mCourseDate, mvpPresenter);
        mLessonRecyclerView.setAdapter(classAdapter);
        mLessonRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.loadFindLessonListCommData();
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
        mRefreshWidgetHandler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mRefreshWidgetHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 滑动监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position % (mTitleData.size() == 0 ? 1 : mTitleData.size()) == mTitleData.size() - 1) {
            return;
        }
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
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mRefreshWidgetHandler.pause = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                mRefreshWidgetHandler.pause = false;
                break;
        }
    }

    /**
     * 精选课程
     *
     * @param model
     */
    @Override
    public void getLessonListDataSuccess(CourseraModle model) {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLessonRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        hideLoading();
        if (model.isSuccess()) {
            mLessonDate = model.getData().getLessonList();
            classAdapter.refreshAdapter(mLessonDate, mCourseDate);
            frist = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            if(frist)
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ErrorReloadData();
        }
    }

    /**
     * 推荐小组
     *
     * @param model
     */
    @Override
    public void getCourseDataSuccess(FindCourseCommResult model) {
        hideLoading();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLessonRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            mCourseDate = model.getData();
            classAdapter.refreshAdapter(mLessonDate, mCourseDate);
            second = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            if(second)
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
                params.leftMargin = UIUtil.px2dip(15);
            }
            imageView.setLayoutParams(params);
            lineLyt.addView(imageView);
        }
    }

    @Override
    public void onRefresh() {
        mvpPresenter.loadFindLessonListCommData();
        mvpPresenter.loadFindCourseCommData();
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.loadFindLessonListCommData();
        mvpPresenter.loadFindCourseCommData();
    }

    /**
     * 失败后 重新请求按钮
     */
    public void ErrorReloadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                if (!frist) mvpPresenter.loadFindLessonListCommData();
                if (!second) mvpPresenter.loadFindCourseCommData();
            }
        });
    }
}
