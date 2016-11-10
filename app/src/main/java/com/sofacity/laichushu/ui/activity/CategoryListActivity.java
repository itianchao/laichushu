package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.netbean.HomeCategroyListBook_Paramet;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.adapter.CaregoryListAdapter;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 首页分类列表
 * Created by wt on 2016/11/10.
 */

public class CategoryListActivity extends BaseActivity implements View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private String pageSize = "10";
    private int pageNo = 1;
    private String userId = SharePrefManager.getUserId();
    private HomeCategroyListBook_Paramet paramet = new HomeCategroyListBook_Paramet("", pageSize, pageNo + "", userId);
    private PullLoadMoreRecyclerView categoryRyv;
    private TextView titleTv;
    private ImageView finishIv;
    private ArrayList<HomeHotModel.DataBean> mData = new ArrayList<>();
    private ArrayList<HomeHotModel.DataBean> mAllData = new ArrayList<>();
    private CaregoryListAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_categorylist);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        categoryRyv = (PullLoadMoreRecyclerView) findViewById(R.id.ryv_category);

        finishIv.setOnClickListener(this);
        categoryRyv.setLinearLayout();
        categoryRyv.setOnPullLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        String categoryId = getIntent().getStringExtra("categoryId");
        String title = getIntent().getStringExtra("title");
        titleTv.setText(title);
        getParamet().setCategroyId(categoryId);
        mAdapter = new CaregoryListAdapter(this, mAllData);
        categoryRyv.setAdapter(mAdapter);
        onRefresh();
    }

    /**
     * 请求网络
     */
    public void loadDataForNet() {
        showProgressDialog();
        addSubscription(apiStores.gethomeCategroyData(paramet), subscriber);
    }

    private ApiCallback subscriber = new ApiCallback<HomeHotModel>() {

        @Override
        public void onSuccess(HomeHotModel model) {
            loadResult(model);
        }

        @Override
        public void onFailure(int code, String msg) {
            dismissProgressDialog();
            Logger.e("code: " + code + "\nmsg " + msg);
        }

        @Override
        public void onFinish() {
            dismissProgressDialog();
        }
    };

    /**
     * 处理结果
     *
     * @param model
     */
    private void loadResult(HomeHotModel model) {
        dismissProgressDialog();
        mData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                categoryRyv.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            mData = model.getData();
            if (!mData.isEmpty()) {
                mAllData.addAll(mData);
                mAdapter.setmAllData(mAllData);
                mAdapter.notifyDataSetChanged();
            }
            pageNo = ++pageNo;
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    public HomeCategroyListBook_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(HomeCategroyListBook_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
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
        mAllData.clear();
        pageNo = 1;
        getParamet().setPageNo(pageNo + "");
        loadDataForNet();
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        getParamet().setPageNo(pageNo + "");
        loadDataForNet();
    }
}
