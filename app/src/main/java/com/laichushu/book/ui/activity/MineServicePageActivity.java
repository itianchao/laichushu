package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mine.mineservice.MineServicePresenter;
import com.laichushu.book.mvp.mine.mineservice.MineServiceView;
import com.laichushu.book.ui.adapter.MineServiceCollectAdapter;
import com.laichushu.book.ui.adapter.MineServiceCooperateAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的--服务
 */
public class MineServicePageActivity extends MvpActivity2<MineServicePresenter> implements MineServiceView, View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ImageView ivBack;
    private TextView tvTitle, tvRight;
    private PullLoadMoreRecyclerView mCooprerateRecyclerView, mCollectRecyclerView;
    private RadioGroup radioGroup;
    private int PAGE_NO = 1;
    //收藏
    private List<FindServiceInfoModel.DataBean> collData = new ArrayList<>();
    private MineServiceCollectAdapter collAdapter;
    //合作
    private MineServiceCooperateAdapter coopAdapter;
    private List<FindServiceCooperateMode.DataBean> coopData = new ArrayList<>();
    private FindServiceCooperateMode model;
    /**
     * 当前是否连续点击
     */
    private boolean scanDibble = false, collDibble = false;
    /**
     * type 1: 合作  2：收藏
     */
    private int type = 1;

    @Override
    protected MineServicePresenter createPresenter() {
        return new MineServicePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_mine_service_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        mCooprerateRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_cooperate);
        mCollectRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_collection);
        radioGroup = ((RadioGroup) inflate.findViewById(R.id.rg_serviceList));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的服务");
        tvRight.setText("成为服务者");
        if (equals(ConstantValue.READER) || SharePrefManager.getType().equals(ConstantValue.SERVICER) || SharePrefManager.getType().equals(ConstantValue.AUTHOR)) {
            tvRight.setVisibility(View.VISIBLE);
        } else {
            tvRight.setVisibility(View.GONE);
        }

        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        //初始化合作 mCollectRecyclerView
        mCooprerateRecyclerView.setGridLayout(1);
        mCooprerateRecyclerView.setFooterViewText("加载中");
        coopAdapter = new MineServiceCooperateAdapter(this, coopData, mvpPresenter);
        mCooprerateRecyclerView.setAdapter(coopAdapter);
        mCooprerateRecyclerView.setOnPullLoadMoreListener(this);
        //初始化收藏  mCollectRecyclerView
        mCollectRecyclerView.setGridLayout(1);
        mCollectRecyclerView.setFooterViewText("加载中");
        collAdapter = new MineServiceCollectAdapter(this, collData, mvpPresenter);
        mCollectRecyclerView.setAdapter(collAdapter);
        mCollectRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.LoadCooperateData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                //成为服务者
                switch (model.getAuditStatus()) {
                    case 1:
                        ToastUtil.showToast("审核通过");
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("model",this.model);
                        UIUtil.openActivity(mActivity, MineAddServantActivity.class);
                        break;
                    case 2:
                        ToastUtil.showToast("审批被拒绝");
                        break;
                    case 3:
                        //跳转添加服务页面
                        ToastUtil.showToast("审核中");
                        break;
                    default:
                        //成为服务者
                        UIUtil.openActivity(mActivity, MineBeServantActivity.class);
                        break;
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PAGE_NO = 1;
        switch (checkedId) {
            case R.id.rb_cooperate:
                //点击合作
                mCooprerateRecyclerView.setVisibility(View.VISIBLE);
                mCollectRecyclerView.setVisibility(View.GONE);
                collDibble = false;
                type = 1;
                if (!scanDibble) {
                    coopData.clear();
                    mvpPresenter.LoadCooperateData();
                }
                scanDibble = true;
                break;
            case R.id.rb_collection:
                //点击收藏
                mCollectRecyclerView.setVisibility(View.VISIBLE);
                mCooprerateRecyclerView.setVisibility(View.GONE);
                scanDibble = false;
                type = 2;
                if (!collDibble) {
                    collData.clear();
                    mvpPresenter.LoadCollectionData();
                }
                collDibble = true;
                break;
        }
    }


    @Override
    public void getCollectionDataSuccess(FindServiceInfoModel model) {
        collData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCollectRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            collData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!collData.isEmpty()) {
                collAdapter.refreshAdapter(collData);
                PAGE_NO++;
            } else {

            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getCooperateDataSuccess(FindServiceCooperateMode model) {
        coopData.clear();
        this.model = model;
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCooprerateRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            coopData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!collData.isEmpty()) {
                coopAdapter.refreshAdapter(coopData);
                PAGE_NO++;
            } else {

            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;

        if (type == 1) {
            coopData.clear();
            mvpPresenter.getCoop_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCooperateData();//请求网络获取搜索列表
        } else if (type == 2) {
            collData.clear();
            mvpPresenter.getColl_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
        }
    }

    @Override
    public void onLoadMore() {
        if (type == 1) {
            mvpPresenter.getCoop_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCooperateData();//请求网络获取搜索列表
        } else if (type == 2) {
            mvpPresenter.getColl_paramet().setPageNo(PAGE_NO + "");
            mvpPresenter.LoadCollectionData();//请求网络获取搜索列表
        }
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.toJson(msg);
    }

}
