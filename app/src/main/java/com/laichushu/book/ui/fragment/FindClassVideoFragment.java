package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.LessonList_Paramet;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.mvp.find.coursera.video.FindClassVideoPresenter;
import com.laichushu.book.mvp.find.coursera.video.FindClassVideoView;
import com.laichushu.book.ui.adapter.FindClassVideoAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 视频
 * Created by wangtong on 2017/1/4.
 */

public class FindClassVideoFragment extends MvpFragment2<FindClassVideoPresenter> implements FindClassVideoView, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private TextView rankingTv;
    private TextView categroyTv;
    private FindClassVideoAdapter mAdapter;
    public int pageNo = 1;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData = new ArrayList<>();
    private ArrayList<CourseraModle.DataBean.LessonCategoryListBean> mCategoryList = new ArrayList<>();
    private ImageView emptyIv;

    /**
     * @return 控制器
     */
    @Override
    protected FindClassVideoPresenter createPresenter() {
        return new FindClassVideoPresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_video);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_video);//容器
        rankingTv = (TextView) mSuccessView.findViewById(R.id.tv_ranking);//排行
        categroyTv = (TextView) mSuccessView.findViewById(R.id.tv_categroy);//分类
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);//空页面
        mRecyclerView.setLinearLayout();
        mAdapter = new FindClassVideoAdapter((MvpActivity2) mActivity, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        rankingTv.setOnClickListener(this);
        categroyTv.setOnClickListener(this);
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
            if (mvpPresenter.getParamet().getPageNo().equals("1")){
                reloadErrorBtn();
            }else {
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
        if (mvpPresenter.getParamet().getPageNo().equals("1")){
            reloadErrorBtn();
        }else {
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

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ranking:
                ArrayList<String> mlist1 = new ArrayList<>();
                mlist1.add("默认排序");
                mlist1.add("按点击量排序");
                mlist1.add("按下载量排序");
                mlist1.add("按收藏量排序");
                showPopWindow(v, mlist1, 0);
                break;
            case R.id.tv_categroy:
                ArrayList<String> mlist2 = mvpPresenter.fromArrayList(mCategoryList);
                showPopWindow(v, mlist2, 1);
                break;
            case R.id.iv_empty:
                onRefresh();
                break;
        }
    }

    /**
     * 分类排行 下拉框
     *
     * @param v
     * @param mlist
     * @param type
     */
    public void showPopWindow(View v, final ArrayList<String> mlist, final int type) {

        TypePopWindow popWindow = new TypePopWindow(mActivity, mlist);
        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {
                if ((type == 0)) {//排序
                    mData.clear();
                    mvpPresenter.loadVideoList(position);
                    rankingTv.setText(mlist.get(position));

                } else {//分类
                    mData.clear();
                    String lessonCategoryId = mvpPresenter.findListByName(mlist.get(position), mCategoryList);
                    mvpPresenter.loadVideoList(lessonCategoryId);
                    categroyTv.setText(mlist.get(position));
                }
            }
        });
        popWindow.setWidth(v.getWidth());
        popWindow.setHeight(UIUtil.dip2px(40) * mlist.size());
        popWindow.showAsDropDown(v);
    }
}
