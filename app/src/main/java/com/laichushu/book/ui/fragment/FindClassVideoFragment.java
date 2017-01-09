package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.coursera.video.FindClassVideoPresenter;
import com.laichushu.book.mvp.find.coursera.video.FindClassVideoView;
import com.laichushu.book.ui.adapter.FindClassVideoAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 视频
 * Created by wangtong on 2017/1/4.
 */

public class FindClassVideoFragment extends MvpFragment2<FindClassVideoPresenter> implements FindClassVideoView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private TextView rankingTv;
    private TextView categroyTv;
    private FindClassVideoAdapter mAdapter;
    private ArrayList mData = new ArrayList<>();
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
        mRecyclerView.setLinearLayout();
        mAdapter = new FindClassVideoAdapter((MvpActivity2) mActivity,mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {

    }
}
