package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.LessonAbout_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.FindClassVideoAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 相关
 * Created by wangtong on 2017/1/9.
 */

public class CourseAboutFragment extends MvpFragment2 implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private ImageView emptyIv;
    private FindClassVideoAdapter mAdapter;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData = new ArrayList<>();
    private String courseId;
    private String pageSize = ConstantValue.PAGESIZE1;
    private int pageNo = 1;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_courseabout);
        mRecyclerView = (PullLoadMoreRecyclerView)mSuccessView.findViewById(R.id.ryv_about);
        emptyIv = (ImageView)mSuccessView.findViewById(R.id.iv_empty);
        mRecyclerView.setLinearLayout();
        mAdapter = new FindClassVideoAdapter((MvpActivity2) mActivity, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        courseId = getArguments().getString("lessonId");
        loadData();
    }

    /**
     * 网络请求
     */
    private void loadData() {
        LessonAbout_Paramet paramet = new LessonAbout_Paramet(courseId,pageNo+"", pageSize);
        addSubscription(apiStores.getLessonAboutList(paramet), new ApiCallback<CourseraModle>() {
            @Override
            public void onSuccess(CourseraModle model) {
                getAboutListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                getAboutListDataFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取相关列表 成功
     * @param model
     */
    private void getAboutListDataSuccess(CourseraModle model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!model.getData().getLessonList().isEmpty()) {
                pageNo++;
                mData.addAll(model.getData().getLessonList());
                mAdapter.setmData(mData);
            }else {
                if (pageNo != 1){
                    ToastUtil.showToast("没有更多数据了");
                }else {
                    mRecyclerView.setVisibility(View.GONE);
                    emptyIv.setVisibility(View.VISIBLE);
                }
            }
        }else {
            reloadErrorBtn(model.getErrMsg());
        }
    }

    /**
     * 获取相关列表 失败
     * @param msg
     */
    private void getAboutListDataFail(String msg) {
        reloadErrorBtn(msg);
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        loadData();
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        loadData();
    }

    /**
     * 重新加载按钮
     */
    public void reloadErrorBtn(String msg){
        LoggerUtil.e(msg);
        if (pageNo == 1){
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
                @Override
                public void reLoadData() {
                    refreshPage(LoadingPager.PageState.STATE_LOADING);
                    loadData();
                }
            });
        }else {
            ToastUtil.showToast("加载失败");
        }
    }
}
