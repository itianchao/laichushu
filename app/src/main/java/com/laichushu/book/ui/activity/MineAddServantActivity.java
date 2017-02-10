package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindServerItemList_Paramet;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.bean.netbean.FindServiceItemListModel;
import com.laichushu.book.event.RefrushMineBeServiceEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.MineAddServiceAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MineAddServantActivity extends MvpActivity2 implements View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle, tvRight;
    private RelativeLayout rlShowDetails;
    private PullLoadMoreRecyclerView mServiceRecyclerView;
    private MineAddServiceAdapter addServiceAdapter;
    private List<FindServiceItemListModel.DataBean> serviceDate = new ArrayList<>();
    private FindServiceCooperateMode model;
    private int pageNo = 1;
    private String pageSize = ConstantValue.PAGESIZE4;
    private FindServerItemList_Paramet coop_paramet = new FindServerItemList_Paramet(SharePrefManager.getUserId(), "", "", pageNo + "", pageSize);

    @Override

    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_mine_add_servant);
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        rlShowDetails = ((RelativeLayout) inflate.findViewById(R.id.rl_serviceDetails));
        mServiceRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_serviceItem);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的服务");
        tvRight.setText("添加服务");

        rlShowDetails.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        //初始化服务列表 mCollectRecyclerView
        mServiceRecyclerView.setGridLayout(1);
        mServiceRecyclerView.setFooterViewText("加载中");
        addServiceAdapter = new MineAddServiceAdapter(this, serviceDate);
        mServiceRecyclerView.setAdapter(addServiceAdapter);
        mServiceRecyclerView.setOnPullLoadMoreListener(this);
        //获取服务
        getData();
    }

    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("我的服务");
    }

    public void getData() {
        addSubscription(apiStores.getFindServerItemListDetails(coop_paramet), new ApiCallback<FindServiceItemListModel>() {
            @Override
            public void onSuccess(FindServiceItemListModel model) {
                serviceDate.clear();
                UIUtil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mServiceRecyclerView.setPullLoadMoreCompleted();
                    }
                }, 300);
                if (model.isSuccess()) {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    if (null != model.getData() && !model.getData().isEmpty()) {
                        serviceDate = model.getData();
                        addServiceAdapter.refreshAdapter(serviceDate);
                        pageNo++;
                    } else {
                        ToastUtil.showToast(model.getErrMsg());
                    }
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                } else {
                    ToastUtil.showToast(model.getErrMsg());
                    refrushErrorView();
                }


            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e(msg);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    public FindServerItemList_Paramet getCoop_paramet() {
        return coop_paramet;
    }

    public void setCoop_paramet(FindServerItemList_Paramet coop_paramet) {
        this.coop_paramet = coop_paramet;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                //添加服务
                Bundle bundle = new Bundle();
                bundle.putString("type", "service");
                UIUtil.openActivity(mActivity, HomePublishTopicActivity.class, bundle);
                break;
            case R.id.rl_serviceDetails:
                //我的资料
                Bundle showDetails = new Bundle();
                showDetails.putString("type", "serviceShow");
                UIUtil.openActivity(mActivity, MineBeServantActivity.class, showDetails);
                break;
        }
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        serviceDate.clear();
        getCoop_paramet().setPageNo(pageNo + "");
        getData();
    }

    @Override
    public void onLoadMore() {
        getCoop_paramet().setPageNo(pageNo + "");
        getData();
    }

    @Override
    public void finish() {
        EventBus.getDefault().postSticky(new RefrushMineBeServiceEvent(true));
        super.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushMineBeServiceEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            onRefresh();
        }
    }

    /**
     * 重新加载
     */
    public void refrushErrorView() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                getData();
            }
        });
    }
}
