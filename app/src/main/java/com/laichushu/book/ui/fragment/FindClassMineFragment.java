package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.laichushu.book.R;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.mvp.find.coursera.mine.FindClassMinePresenter;
import com.laichushu.book.mvp.find.coursera.mine.FindClassMineView;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.ui.adapter.FindClassVideoAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 我的
 * Created by wangtong on 2017/1/4.
 */

public class FindClassMineFragment extends MvpFragment2<FindClassMinePresenter> implements FindClassMineView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private RadioButton collectionRbn;
    private RadioButton historyRbn;
    private RadioButton downloadRbn;
    private int position = -1;
    private int pageNo1 = 1;
    private int pageNo2 = 1;
    private int pageNo3 = 1;
    private FindClassVideoAdapter mAdapter;
    private PullLoadMoreRecyclerView mRecyclerView;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData1 = new ArrayList<>();
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData2 = new ArrayList<>();
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData3 = new ArrayList<>();
    private ImageView emptyIv;
    private boolean isLoadMore;

    @Override
    protected FindClassMinePresenter createPresenter() {
        return new FindClassMinePresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_findclassmine);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);//空页面
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_mine);//容器
        collectionRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);//收藏
        historyRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);//历史
        downloadRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_03);//下载
        collectionRbn.setOnClickListener(this);
        historyRbn.setOnClickListener(this);
        downloadRbn.setOnClickListener(this);
        mAdapter = new FindClassVideoAdapter((MvpActivity2) mActivity,mData1);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    public void initData() {
        onClick(collectionRbn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbn_01:
                if (position != 1) {
                    position = 1;
                    historyRbn.setEnabled(false);
                    downloadRbn.setEnabled(false);
                    loadData(position);
                }
                break;
            case R.id.rbn_02:
                if (position != 2) {
                    position = 2;
                    collectionRbn.setEnabled(false);
                    downloadRbn.setEnabled(false);
                    loadData(position);
                }
                break;
            case R.id.rbn_03:
                if (position != 3) {
                    collectionRbn.setEnabled(false);
                    historyRbn.setEnabled(false);
                    position = 3;
                    loadData(position);
                }
                break;
        }
    }

    /**
     * 点击tab 切换数据
     * @param position
     */
    private void loadData(int position) {
        switch(position){
            case 1:
                if (mData1.isEmpty()){
                    mvpPresenter.loadVideoList(position+"",false);
                }else {
                    doMore();
                    mAdapter.setmData(mData1);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    emptyIv.setVisibility(View.GONE);
                }
                break;
            case 2:
                if (mData2.isEmpty()){
                    mvpPresenter.loadVideoList(position+"",false);
                }else {
                    doMore();
                    mAdapter.setmData(mData2);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    emptyIv.setVisibility(View.GONE);
                }
                break;
            case 3:
                if (mData3.isEmpty()){
                    mvpPresenter.loadVideoList(position+"",false);
                }else {
                    doMore();
                    mAdapter.setmData(mData3);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    emptyIv.setVisibility(View.GONE);
                }
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        switch(position){
            case 1:
                pageNo1 = 1;
                mvpPresenter.getParamet().setPageNo(pageNo1+"");
                mData1.clear();
                break;
            case 2:
                pageNo2 = 1;
                mvpPresenter.getParamet().setPageNo(pageNo2+"");
                mData2.clear();
                break;
            case 3:
                pageNo3 = 1;
                mvpPresenter.getParamet().setPageNo(pageNo3+"");
                mData3.clear();
                break;
        }
        mvpPresenter.loadVideoList(position+"",true);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        switch(position){
            case 1:
                pageNo1++;
                mvpPresenter.getParamet().setPageNo(pageNo1+"");
                break;
            case 2:
                pageNo2++;
                mvpPresenter.getParamet().setPageNo(pageNo2+"");
                break;
            case 3:
                pageNo3++;
                mvpPresenter.getParamet().setPageNo(pageNo3+"");
                break;
        }
        isLoadMore = true;
        mvpPresenter.loadVideoList(position+"",true);
    }

    /**
     * 获取我的列表 成功
     * @param modle
     */
    @Override
    public void getMineListDataSuccess(CourseraModle modle) {
        doMore();
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!modle.getData().getLessonList().isEmpty()){
                mRecyclerView.setVisibility(View.VISIBLE);
                emptyIv.setVisibility(View.GONE);
                switch(position){
                    case 1:
                        mData1.addAll(modle.getData().getLessonList());
                        mAdapter.setmData(mData1);
                        break;
                    case 2:
                        mData2.addAll(modle.getData().getLessonList());
                        mAdapter.setmData(mData2);
                        break;
                    case 3:
                        mData3.addAll(modle.getData().getLessonList());
                        mAdapter.setmData(mData3);
                        break;
                }

            }else {
                if (mvpPresenter.getParamet().getPageNo().equals("1")){
                    mRecyclerView.setVisibility(View.GONE);
                    emptyIv.setVisibility(View.VISIBLE);
                }else {
                    if (isLoadMore){
                        ToastUtil.showToast("没有更多数据了！");
                        isLoadMore = false;
                    }
                    mRecyclerView.setVisibility(View.VISIBLE);
                    emptyIv.setVisibility(View.GONE);
                    switch(position){
                        case 1:
                            mAdapter.setmData(mData1);
                            break;
                        case 2:
                            mAdapter.setmData(mData2);
                            break;
                        case 3:
                            mAdapter.setmData(mData3);
                            break;
                    }
                }
            }

        }else {
            isLoadMore = false;
            if (mvpPresenter.getParamet().getPageNo().equals("1")){
                reloadErrorBtn();
            }else {
                ToastUtil.showToast("加载失败");
            }
            LoggerUtil.e(modle.getErrMsg());
        }
    }

    /**
     * 获取我的列表 失败
     * @param msg
     */
    @Override
    public void getMineListDataFail(String msg) {
        doMore();
        if (mvpPresenter.getParamet().getPageNo().equals("1")){
            reloadErrorBtn();
        }else {
            ToastUtil.showToast("加载失败");
        }
        LoggerUtil.e(msg);
    }

    /**
     * 处理按钮
     */
    private void doMore() {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        collectionRbn.setEnabled(true);
        historyRbn.setEnabled(true);
        downloadRbn.setEnabled(true);
    }

    /**
     * 重新加载按钮
     */
    public void reloadErrorBtn(){
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadVideoList(position+"",false);
            }
        });
    }
}
