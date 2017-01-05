package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.event.RefrushWalletEvent;
import com.laichushu.book.mvp.wallet.WalletPresener;
import com.laichushu.book.mvp.wallet.WalletView;
import com.laichushu.book.ui.adapter.MyWalletRecordAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的钱包
 */
public class MyWalletDetailsActivity extends MvpActivity2<WalletPresener> implements WalletView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle, tvBalanceShow;
    private Button btnRecharge, btnWithdrawals;
    private PullLoadMoreRecyclerView mRecordRecyclerView;
    private List<WalletBalanceReward.DataBean> recordData = new ArrayList<>();
    private MyWalletRecordAdapter recordAdapter;
    private int PAGE_NO = 1;
    private WalletBalanceReward bean;

    @Override
    protected WalletPresener createPresenter() {

        return new WalletPresener(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_my_wallet_details);
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvBalanceShow = ((TextView) inflate.findViewById(R.id.tv_balanceShow));
        btnRecharge = ((Button) inflate.findViewById(R.id.btn_Recharge));
        btnWithdrawals = ((Button) inflate.findViewById(R.id.btn_withdrawals));
        mRecordRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_transRecord);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的钱包");
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
        btnWithdrawals.setOnClickListener(this);

        //初始化mRecyclerView 关注
        mRecordRecyclerView.setGridLayout(1);
        mRecordRecyclerView.setFooterViewText("加载中");
        recordAdapter = new MyWalletRecordAdapter(this, recordData);
        mRecordRecyclerView.setAdapter(recordAdapter);
        mRecordRecyclerView.setOnPullLoadMoreListener(this);
        //初始化钱包首页信息
        mvpPresenter.LoadWalletRecordData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.btn_Recharge:
                //充值
                Bundle recharge = new Bundle();
                recharge.putParcelable("bean", bean);
                UIUtil.openActivity(this, RechargeDetailsActivity.class, recharge);
                break;
            case R.id.btn_withdrawals:
                //提现
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", bean);
                UIUtil.openActivity(this, WithdrawalsDetails.class, bundle);
                break;
        }
    }

    @Override
    public void getWalletRecordDateSuccess(WalletBalanceReward model) {
        recordData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecordRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            bean = model;
            recordData = model.getData();
            tvBalanceShow.setText(model.getBalance() + "");
            if (!recordData.isEmpty()) {
                recordAdapter.refreshAdapter(recordData);
                PAGE_NO++;
            } else {

            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                ToastUtil.showToast(model.getErrMsg());
                reLoadData();
        }

    }

    @Override
    public void getWithdrawalsApplayDateSuccess(RewardResult model) {

    }

    @Override
    public void getRechargePayDateSuccess(RewardResult model) {

    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        reLoadData();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        recordData.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadWalletRecordData();//请求网络获取搜索列表
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.LoadWalletRecordData();//请求网络获取搜索列表
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushWalletEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }

    public void reLoadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                mvpPresenter.LoadWalletRecordData();
            }
        });
    }
}
