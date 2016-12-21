package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.RefrushAunnManagerEvent;
import com.laichushu.book.mvp.annmanage.AnnManagerPresenter;
import com.laichushu.book.mvp.annmanage.AnnManagerView;
import com.laichushu.book.mvp.notice.NoticeModle;
import com.laichushu.book.ui.adapter.AnnManageAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 公告管理界面
 * 2016年12月21日13:31:40
 */
public class AnnounMangeActivity extends MvpActivity2<AnnManagerPresenter> implements AnnManagerView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivSendAnn;
    private PullLoadMoreRecyclerView mRecyclerView;
    private AnnManageAdapter adapter;
    private List<NoticeModle.DataBean> annDate = new ArrayList<>();
    private int PAGE_NO = 0;
    private String partyId = null;

    @Override
    protected AnnManagerPresenter createPresenter() {
        return new AnnManagerPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_announ_mange);
        EventBus.getDefault().register(this);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        ivSendAnn = ((ImageView) inflate.findViewById(R.id.iv_title_other));
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_ann_manager);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("公告管理");
        partyId = getIntent().getStringExtra("partyId");
        ivSendAnn.setBackgroundResource(R.drawable.my_reset2x);

        ivSendAnn.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        ivSendAnn.setOnClickListener(this);

        //初始化mRecyclerView
        mRecyclerView.setGridLayout(1);
        mRecyclerView.setFooterViewText("加载中");
        mRecyclerView.setOnPullLoadMoreListener(this);
        adapter = new AnnManageAdapter(this, annDate, mvpPresenter);
        mRecyclerView.setAdapter(adapter);

        if (null != partyId)
            mvpPresenter.loadAnnManageDate(partyId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_title_other:
                Bundle bundle = new Bundle();
                bundle.putString("partyId", partyId);
                bundle.putString("type", "partyManage");
                UIUtil.openActivity(mActivity, HomePublishTopicActivity.class, bundle);
                break;
        }
    }


    @Override
    public void getAnnanageDataSuccess(NoticeModle model) {
        annDate.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        ;
        if (model.isSuccess()) {
            annDate = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!annDate.isEmpty()) {
                PAGE_NO++;
            } else {

            }
            adapter.refreshAdapter(annDate);
        } else {
            adapter.refreshAdapter(annDate);
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
    }

    @Override
    public void getDeleteAnnDateSuccess(RewardResult modle) {
        if (modle.isSuccess()) {
            ToastUtil.showToast("删除成功！");
            //刷新类表数据
            onRefresh();
        } else {
            ToastUtil.showToast("删除失败！");
            LoggerUtil.e(modle.getErrMsg());
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    @Override
    public void shwoDialog() {
        showProgressDialog();
    }

    @Override
    public void dissmissDialog() {
        dismissProgressDialog();
    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        annDate.clear();
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadAnnManageDate(partyId);//请求网络获取搜索列表
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadAnnManageDate(partyId);//请求网络获取搜索列表
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushAunnManagerEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
