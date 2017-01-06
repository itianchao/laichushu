package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.RadioButton;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findclass.mine.FindClassMinePresenter;
import com.laichushu.book.mvp.findclass.mine.FindClassMineView;
import com.laichushu.book.ui.adapter.FindClassVideoAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.base.MvpFragment2;
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
    private FindClassVideoAdapter mAdapter;
    private PullLoadMoreRecyclerView mRecyclerView;
    private ArrayList mData = new ArrayList<>();
    @Override
    protected FindClassMinePresenter createPresenter() {
        return new FindClassMinePresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_findclassmine);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_mine);//容器
        collectionRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);//收藏
        historyRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);//历史
        downloadRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_03);//下载
        collectionRbn.setOnClickListener(this);
        historyRbn.setOnClickListener(this);
        downloadRbn.setOnClickListener(this);
        mAdapter = new FindClassVideoAdapter((MvpActivity2) mActivity,mData);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        onClick(collectionRbn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbn_01:
                if (position != 0) {
                    position = 0;
                }
                break;
            case R.id.rbn_02:
                if (position != 1) {
                    position = 1;
                }
                break;
            case R.id.rbn_03:
                if (position != 2) {
                    position = 2;
                }
                break;
        }
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
