package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.LessonList_Paramet;
import com.laichushu.book.mvp.find.coursera.live.FindClassLivePresenter;
import com.laichushu.book.mvp.find.coursera.live.FindClassLiveView;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.ui.adapter.FindClassLiveAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 直播 列表
 * Created by wangtong on 2017/1/4.
 */

public class FindClassLiveFragment extends MvpFragment2<FindClassLivePresenter> implements FindClassLiveView, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private FindClassLiveAdapter mAdapter;
    public int pageNo = 1;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData = new ArrayList<>();
    private ArrayList<CourseraModle.DataBean.LessonCategoryListBean> mCategoryList = new ArrayList<>();
    private ImageView emptyIv;

    /**
     * @return 控制器
     */
    @Override
    protected FindClassLivePresenter createPresenter() {
        return new FindClassLivePresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_live);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_video);//容器
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);//空页面
        mRecyclerView.setLinearLayout();
        mAdapter = new FindClassLiveAdapter((MvpActivity2) mActivity, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        emptyIv.setOnClickListener(this);
        mvpPresenter.loadVideoList(false);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mData.clear();
        mvpPresenter.loadVideoList(true);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.loadVideoList(false);
    }

    /**
     * 获取视频列表接口 成功
     *
     * @param modle
     */
    @Override
    public void getVideoListDataSuccess(CourseraModle modle) {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (modle.getData().getLessonList().isEmpty()) {//空
                LessonList_Paramet paramet = mvpPresenter.getParamet();
                if (paramet.getPageNo().equals("1")) {
                    emptyIv.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    ToastUtil.showToast("没有更多数据啦！");
                }
            } else {//不是空
                pageNo++;
                mvpPresenter.getParamet().setPageNo(pageNo + "");
                mCategoryList = modle.getData().getLessonCategoryList();
                mData.addAll(modle.getData().getLessonList());
                mAdapter.setmData(mData);
                emptyIv.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        } else {
            if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                reloadErrorBtn();
            } else {
                ToastUtil.showToast("加载失败");
            }
        }
    }

    /**
     * 获取视频列表接口 失败
     *
     * @param msg
     */
    @Override
    public void getVideoListDataFail(String msg) {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        if (mvpPresenter.getParamet().getPageNo().equals("1")) {
            reloadErrorBtn();
        } else {
            ToastUtil.showToast("加载失败");
        }
        LoggerUtil.e(msg);
    }

    /**
     * 重新加载按钮
     */
    public void reloadErrorBtn() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadVideoList(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_empty:
                onRefresh();
                break;
        }
    }
}
