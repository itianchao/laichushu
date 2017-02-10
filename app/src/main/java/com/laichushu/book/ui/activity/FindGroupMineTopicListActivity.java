package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.group.topiclist.FindGroupMineTopicPresenter;
import com.laichushu.book.mvp.find.group.topiclist.FindGroupMineTopicView;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.ui.adapter.MechanismTopicListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 小组 - 我（发表、回复、收藏）的话题
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMineTopicListActivity extends MvpActivity2<FindGroupMineTopicPresenter> implements FindGroupMineTopicView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private TextView titleTv;
    private ImageView backIv, emptyIv;
    private PullLoadMoreRecyclerView mRecyclerView;
    private ArrayList<MechanismTopicListModel.DataBean> mData = new ArrayList<>();
    private String title;
    private String type;
    private int pageNo = 1;
    private MechanismTopicListAdapter mAdapter;

    /**
     * @return 出事花presenter
     */
    @Override
    protected FindGroupMineTopicPresenter createPresenter() {
        return new FindGroupMineTopicPresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupminetopiclist);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        backIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_list);
        return mSuccessView;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        switch (title) {
            case "我发表的话题":
                type = "1";
                break;
            case "我回复的话题":
                type = "2";
                break;
            case "我收藏的话题":
                type = "3";
                break;
        }
        titleTv.setText(title);
        backIv.setOnClickListener(this);
        mRecyclerView.setLinearLayout();
        mAdapter = new MechanismTopicListAdapter(mData, this,3);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        mvpPresenter.loadData(type);
    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText(title);
    }
    /**
     * 点击事件
     *
     * @param v 被点按钮
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadData(type);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.loadData(type);
    }

    /**
     * 获取话题列表数据 成功
     *
     * @param modle 数据模型
     */
    @Override
    public void getDataSuccess(MechanismTopicListModel modle) {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (modle.getData() != null && modle.getData().isEmpty()) {
                if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                    emptyIv.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }else {
                    ToastUtil.showToast("没有更多数据");
                }
                return;
            }
            pageNo++;
            mvpPresenter.getParamet().setPageNo(pageNo+"");
            mData.addAll(modle.getData());
            mAdapter.setmData(mData);
        } else {
            if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                loadErrorData();
                refreshPage(LoadingPager.PageState.STATE_ERROR);
            } else {
                ToastUtil.showToast("加载失败");
            }
        }
    }

    /**
     * 获取话题列表数据 失败
     *
     * @param msg 错误信息
     */
    @Override
    public void getDataFail(String msg) {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        LoggerUtil.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        if (mvpPresenter.getParamet().getPageNo().equals("1")) {
            loadErrorData();
        } else {
            ToastUtil.showToast("加载失败");
        }
    }

    public void loadErrorData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadData(type);
            }
        });
    }
}