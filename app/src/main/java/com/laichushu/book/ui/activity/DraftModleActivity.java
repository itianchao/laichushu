package com.laichushu.book.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.RefurshDraftEvent;
import com.laichushu.book.mvp.draftmodle.DraftModle;
import com.laichushu.book.mvp.draftmodle.DraftModlePresenter;
import com.laichushu.book.mvp.draftmodle.DraftModleView;
import com.laichushu.book.ui.adapter.DraftListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 草稿模式
 * Created by wangtong on 2016/11/18.
 */

public class DraftModleActivity extends MvpActivity2<DraftModlePresenter> implements View.OnClickListener, DraftModleView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private TextView addTv;
    private ImageView finishIv;
    private TextView titleTv;
    private PullLoadMoreRecyclerView draftRyv;
    private TextView titleRightTv;
    private LinearLayout newDraftLay;
    private View page;
    private DraftListAdapter mAdapter;
    private int pageNo = 1;
    private String articleId;
    private ArrayList<DraftModle.DataBean> mData = new ArrayList<>();
    private boolean isLoad = true;

    @Override
    protected DraftModlePresenter createPresenter() {
        return new DraftModlePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        articleId = getIntent().getStringExtra("articleId");
        View mSuccseeView = UIUtil.inflate(R.layout.activity_creatdraft);
        addTv = (TextView) mSuccseeView.findViewById(R.id.tv_add);
        titleTv = (TextView) mSuccseeView.findViewById(R.id.tv_title);
        titleRightTv = (TextView) mSuccseeView.findViewById(R.id.tv_title_right);
        finishIv = (ImageView) mSuccseeView.findViewById(R.id.iv_title_finish);
        newDraftLay = (LinearLayout) mSuccseeView.findViewById(R.id.lay_add_newbook);
        draftRyv = (PullLoadMoreRecyclerView) mSuccseeView.findViewById(R.id.ryv_draft);
        page = mSuccseeView.findViewById(R.id.layout_page_add);
        page.setVisibility(View.VISIBLE);
        draftRyv.setLinearLayout();
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("管理");
        titleRightTv.setTextColor(Color.WHITE);
        titleTv.setText("草稿模式");
        addTv.setText("添加草稿");
        finishIv.setOnClickListener(this);
        newDraftLay.setOnClickListener(this);
        titleRightTv.setOnClickListener(this);
        draftRyv.setOnPullLoadMoreListener(this);
        draftRyv.setPullRefreshEnable(false);//不需要下拉刷新
        draftRyv.setFooterViewText("loading");
        return mSuccseeView;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        if (isLoad) {//只执行一次
            mvpPresenter.getDraftList(articleId);
        } else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
        mAdapter = new DraftListAdapter(this, mData, mvpPresenter);
        draftRyv.setAdapter(mAdapter);
        isLoad = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.lay_add_newbook://创建草稿
                Bundle bundle = new Bundle();
                bundle.putString("articleId", articleId);
                bundle.putString("type", "1");//1创建、2修改
                UIUtil.openActivity(this, CreatNewDraftActivity.class, bundle);
                break;
            case R.id.tv_title_right:
                if (titleRightTv.getText().toString().equals("管理")) {
                    titleRightTv.setText("完成");
                    page.setVisibility(View.GONE);
                    mAdapter.setGone(true);
                } else {
                    titleRightTv.setText("管理");
                    page.setVisibility(View.VISIBLE);
                    mAdapter.setGone(false);
                }
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void getDataSuccess(DraftModle model) {
        if (model.isSuccess()) {
            if (model.getData() != null && !model.getData().isEmpty()) {
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                pageNo++;
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDeleteDraftBookDataSuccess(RewardResult model, int position) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            mData.remove(position);
            mAdapter.setmData(mData);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    public void getDataFail2(String msg) {
        Logger.e(msg);
        ToastUtil.showToast("删除失败");
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
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.getDraftList(articleId);//请求网络获取搜索列表
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshDraftEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            pageNo = 1;
            mvpPresenter.getParamet().setPageNo(pageNo + "");
            mvpPresenter.getDraftList(articleId);//请求网络获取搜索列表
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
