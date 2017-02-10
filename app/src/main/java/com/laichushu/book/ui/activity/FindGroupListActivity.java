package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.group.grouplist.FindGroupListPresenter;
import com.laichushu.book.mvp.find.group.grouplist.FindGroupListView;
import com.laichushu.book.mvp.find.group.GroupListModle;
import com.laichushu.book.ui.adapter.FindGroupListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 小组列表
 * Created by wangtong on 2016/12/29.
 */

public class FindGroupListActivity extends MvpActivity2<FindGroupListPresenter> implements FindGroupListView {

    private PullLoadMoreRecyclerView groupRyv;
    private ImageView finishIv;
    private TextView titleTv;
    private FindGroupListAdapter mGroupListAdapter;
    private ArrayList<GroupListModle.DataBean> mGroupListdata = new ArrayList<>();
    private int type;

    @Override
    protected FindGroupListPresenter createPresenter() {
        return new FindGroupListPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgrouplist);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        groupRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_group);
        groupRyv.setLinearLayout();
        groupRyv.setPullRefreshEnable(false);
        groupRyv.setPushRefreshEnable(false);
        mGroupListAdapter = new FindGroupListAdapter(mGroupListdata, this);
        groupRyv.setAdapter(mGroupListAdapter);
        finishIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return mSuccessView;
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", -1);
        switch (type) {
            case 0://我加入的小组
                mvpPresenter.loadMyJoinGroupList();
                titleTv.setText("我加入的小组");
                break;
            case 1://我创建的小组
                mvpPresenter.getMyCreateGroupList();
                titleTv.setText("我创建的小组");
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        if (type == 0) {
            mPage.tvTitle.setText("我加入的小组");
        } else if (type == 1) {
            mPage.tvTitle.setText("我创建的小组");
        }

    }

    /**
     * 获取小组列表接口 成功
     *
     * @param modle
     */
    @Override
    public void getGroupListDataSuccess(GroupListModle modle) {
        if (modle.isSuccess()) {
            mGroupListdata = modle.getData();
            if (mGroupListdata.isEmpty()) {
                refreshPage(LoadingPager.PageState.STATE_EMPTY);
            } else {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                mGroupListAdapter.setmData(mGroupListdata);
            }
        } else {
            LoggerUtil.e(modle.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            errorReload();
        }
    }

    /**
     * 获取小组列表接口 失败
     *
     * @param msg
     */
    @Override
    public void getGroupListDataFail(String msg) {
        LoggerUtil.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        errorReload();
    }

    public void errorReload() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                switch (type) {
                    case 0://我加入的小组
                        mvpPresenter.loadMyJoinGroupList();
                        break;
                    case 1://我创建的小组
                        mvpPresenter.getMyCreateGroupList();
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 4) {//更新小组人数
            Bundle bundle = data.getExtras();
            int argsMember = bundle.getInt("argsMember");
            int index = bundle.getInt("index");
            GroupListModle.DataBean bean = mGroupListdata.get(index);
            bean.setJoinNum(bean.getJoinNum() + argsMember);
        }
    }
}