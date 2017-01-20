package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.mvp.mine.bookcast.BookcastPresener;
import com.laichushu.book.mvp.mine.bookcast.BookcastView;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.adapter.BookCastCollAdapter;
import com.laichushu.book.ui.adapter.MyBookCastAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ModelUtils;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的书架
 */
public class MyBookCastActivity extends MvpActivity2<BookcastPresener> implements BookcastView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle, tvManage;
    private PullLoadMoreRecyclerView mRecyclerView, mCollRecyclerView;
    private RadioGroup radioGroup;
    private RadioButton rbScan, rbCollection;
    private int PAGE_NO = 1;
    private List<HomeHotModel.DataBean> scanData = new ArrayList<>();
    private List<HomeHotModel.DataBean> collData = new ArrayList<>();
    private MyBookCastAdapter scanAdapter;
    private BookCastCollAdapter collAdapter;
    private boolean isShowDelete;
    /**
     * 当前是否连续点击
     */
    private boolean scanDibble = false, collDibble = false;
    /**
     * type 1: 浏览  2：收藏
     */
    private int type = 1;

    @Override
    protected BookcastPresener createPresenter() {
        return new BookcastPresener(this);
    }

    @Override
    protected View createSuccessView() {
        View bookCastView = UIUtil.inflate(R.layout.activity_my_book_cast);
        ivBack = ((ImageView) bookCastView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) bookCastView.findViewById(R.id.tv_title));
        tvManage = ((TextView) bookCastView.findViewById(R.id.tv_title_right));
        mRecyclerView = (PullLoadMoreRecyclerView) bookCastView.findViewById(R.id.ryv_bookCast);
        mCollRecyclerView = (PullLoadMoreRecyclerView) bookCastView.findViewById(R.id.ryv_bookCastColl);
        radioGroup = ((RadioGroup) bookCastView.findViewById(R.id.rg_bookList));
        rbScan = ((RadioButton) bookCastView.findViewById(R.id.rb_scan));
        rbCollection = ((RadioButton) bookCastView.findViewById(R.id.rb_collection));

        return bookCastView;
    }


    @Override
    protected void initData() {
        rbScan.setChecked(true);
        tvTitle.setText("我的书架");
        tvManage.setText("管理");
        tvManage.setVisibility(View.VISIBLE);
        tvManage.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        //初始化mRecyclerView Scan
        mRecyclerView.setGridLayout(3);
        mRecyclerView.setFooterViewText("加载中");
        scanAdapter = new MyBookCastAdapter(this, scanData, mvpPresenter, isShowDelete);
        mRecyclerView.setAdapter(scanAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        //初始化mRecyclerView Coll
        mCollRecyclerView.setGridLayout(3);
        mCollRecyclerView.setFooterViewText("加载中");
        collAdapter = new BookCastCollAdapter(this, collData, mvpPresenter, isShowDelete);
        mCollRecyclerView.setAdapter(collAdapter);
        mCollRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.loadBrowserListData("1");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                if (type == 1) {
                    if (null != scanData && scanData.size() > 0) {
                        if (scanAdapter.isShow()) {
                            scanAdapter.setShow(false);
                        } else {
                            scanAdapter.setShow(true);
                        }
                        scanAdapter.refreshAdapter(scanData);
                    }
                } else {
                    if (null != collData && collData.size() > 0) {
                        if (collAdapter.isShow()) {
                            collAdapter.setShow(false);
                        } else {
                            collAdapter.setShow(true);
                        }
                        collAdapter.refreshAdapter(collData);
                    }
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PAGE_NO = 1;
        scanAdapter.setShow(false);
        collAdapter.setShow(false);
        switch (checkedId) {
            case R.id.rb_scan:
                //点击浏览
                mCollRecyclerView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                collDibble = false;
                type = 1;
                if (!scanDibble) {
                    if (scanData.size() > 0)
                        scanData.clear();
                    mvpPresenter.loadBrowserListData("1");
                }
                scanDibble = true;
                break;
            case R.id.rb_collection:
                //点击收藏
                mCollRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                scanDibble = false;
                type = 2;
                if (!collDibble) {
                    if (collData.size() > 0)
                        collData.clear();
                    mvpPresenter.LoadCollectionData();
                }
                collDibble = true;
                break;
        }
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;

        if (type == 1) {
            if (scanData.size() > 0)
                scanData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadBrowserListData("1");//请求网络获取搜索列表
        } else if (type == 2) {
            if (collData.size() > 0)
                collData.clear();
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
        }


    }

    @Override
    public void onLoadMore() {
        if (type == 1) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.loadBrowserListData("1");//请求网络获取搜索列表
        } else if (type == 2) {
            mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
        }

    }

    @Override
    public void getDataSuccess(HomeHotModel model) {
        scanData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (null != model.getData() && !model.getData().isEmpty()) {
                scanData = model.getData();
                scanAdapter.refreshAdapter(scanData);
                PAGE_NO++;
            } else {
                ToastUtil.showToast(mActivity.getResources().getString(R.string.errMsg_empty));
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            if (PAGE_NO == 1)
                ErrorReloadData(1);
        }
    }

    @Override
    public void getCollectionDataSuccess(HomeHotModel model) {
        collData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCollRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (null != model.getData() && !model.getData().isEmpty()) {
                collData = model.getData();
                collAdapter.refreshAdapter(collData);
                PAGE_NO++;
            } else {
                ToastUtil.showToast(mActivity.getResources().getString(R.string.errMsg_empty));
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getBookDetailsByIdDataSuccess(BookDetailsModle model) {
        //跳转图书详情页
        dismissProgressDialog();
        if (model.isSuccess()) {
            Bundle bundle = new Bundle();
            HomeHotModel.DataBean dataBean = ModelUtils.bean2HotBean(model);
            bundle.putParcelable("bean", dataBean);
            bundle.putString("pageMsg", "浏览收藏详情");
            UIUtil.openActivity(this, BookDetailActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }

    }

    @Override
    public void getDeleteBookByIdDataSuccess(RewardResult modle, int position) {
        if (modle.isSuccess()) {
            ToastUtil.showToast("删除成功");
            //TODO 刷新页面
            if (type == 1) {
                scanAdapter.deleteDataRefresh(position);
            } else {
                collAdapter.deleteDataRefresh(position);
            }

        } else {
            ToastUtil.showToast("删除失败");
        }
    }

    @Override
    public void getDataFail(String msg, int flg) {
        Logger.e(msg);
        dismissDialog();
        ErrorReloadData(flg);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    public void ErrorReloadData(final int flg) {
        if (flg == 1) {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
                @Override
                public void reLoadData() {
                    if (flg == 1) {
                        refreshPage(LoadingPager.PageState.STATE_LOADING);
                        mvpPresenter.loadBrowserListData("1");
                    }
                }
            });
        } else {
            ToastUtil.showToast(R.string.errMsg_data_exception);
        }

    }
}
