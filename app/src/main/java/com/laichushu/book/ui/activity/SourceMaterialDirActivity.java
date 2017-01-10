package com.laichushu.book.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.RefrushMerPermissionEvent;
import com.laichushu.book.mvp.write.sourcematerialdir.SourceMaterialDirModle;
import com.laichushu.book.mvp.write.sourcematerialdir.SourceMaterialDirPresenter;
import com.laichushu.book.mvp.write.sourcematerialdir.SourceMaterialDirView;
import com.laichushu.book.ui.adapter.MaterialListDirAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 素材文件夹页面
 * Created by wangtong on 2016/11/22.
 */

public class SourceMaterialDirActivity extends MvpActivity2<SourceMaterialDirPresenter> implements SourceMaterialDirView, View.OnClickListener {

    private TextView addTv;
    private TextView addJur;
    private ImageView finishIv;
    private TextView titleTv;
    private PullLoadMoreRecyclerView mateialRyv;
    private TextView titleRightTv;
    private LinearLayout newDraftLay;
    private View page;
    private MaterialListDirAdapter mAdapter;
    private String articleId;
    private ArrayList<SourceMaterialDirModle.DataBean> mData = new ArrayList<>();
    private boolean isLoad = true;
    private String materialPermission;
    private int index;

    @Override
    protected SourceMaterialDirPresenter createPresenter() {
        return new SourceMaterialDirPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_sourcemateialdir);
        articleId = getIntent().getStringExtra("articleId");
        materialPermission = getIntent().getStringExtra("materialPermission");
        index = getIntent().getIntExtra("index",-1);
        addTv = (TextView) mSuccessView.findViewById(R.id.tv_add);
        addJur = (TextView) mSuccessView.findViewById(R.id.tv_title_right2);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        titleRightTv = (TextView) mSuccessView.findViewById(R.id.tv_title_right);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        newDraftLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_add_newbook);
        mateialRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_sourcemateial);
        page = mSuccessView.findViewById(R.id.layout_page_add);
        page.setVisibility(View.VISIBLE);
        mateialRyv.setLinearLayout();
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("管理");
        titleRightTv.setTextColor(Color.WHITE);
        titleTv.setText("素材模式");
        addTv.setText("添加素材文件夹");
        addJur.setText("权限");
        addJur.setVisibility(View.VISIBLE);

        finishIv.setOnClickListener(this);
        newDraftLay.setOnClickListener(this);
        titleRightTv.setOnClickListener(this);
        addJur.setOnClickListener(this);
//        mateialRyv.setOnPullLoadMoreListener(this);
        mateialRyv.setPullRefreshEnable(false);//不需要下拉刷新
        mateialRyv.setPushRefreshEnable(false);//不需要上拉刷新
        mateialRyv.setFooterViewText("加载中");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        if (isLoad) {//只执行一次
            mvpPresenter.getSourceMaterialList(articleId);
        } else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
        mAdapter = new MaterialListDirAdapter(this, mData, mvpPresenter);
        mateialRyv.setAdapter(mAdapter);
        isLoad = false;

    }

    @Override
    public void getDataSuccess(SourceMaterialDirModle model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (model.getData() != null) {
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            refursh();
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    @Override
    public void getDeleteMateialDataSuccess(RewardResult model, int index) {
        hideLoading();
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            mData.remove(index);
            mAdapter.setmData(mData);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getMaterialRenameDataSuccess(RewardResult model, int index, String rename) {
        hideLoading();
        if (model.isSuccess()) {
            ToastUtil.showToast("重命名成功");
            SourceMaterialDirModle.DataBean dataBean = mData.get(index);
            dataBean.setName(rename);
            mAdapter.setmData(mData);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getCreateSourceMaterialDir(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("创建成功");
            mData.clear();
            refreshPage(LoadingPager.PageState.STATE_LOADING);
            mvpPresenter.getSourceMaterialList(articleId);
        } else {
            ToastUtil.showToast("创建失败");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getDataFail(String msg) {
        refursh();
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        ToastUtil.showToast(msg);
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        LoggerUtil.e(msg);
        ToastUtil.showToast("创建素材文件夹失败");
    }

    /**
     * 修改素材权限结果
     *
     * @param model
     */
    @Override
    public void getUpdateMerPermission(RewardResult model,String permission) {
        if (model.isSuccess()) {
            materialPermission = permission;
            EventBus.getDefault().postSticky(new RefrushMerPermissionEvent(materialPermission,index));
            ToastUtil.showToast("修改权限成功");
        } else {
            ToastUtil.showToast("修改权限失败");
        }
    }

    @Override
    public void getDeleteMateialDataFail(String msg) {
        hideLoading();
        ToastUtil.showToast("删除失败");
        LoggerUtil.toJson(msg);
    }

    @Override
    public void getMaterialRenameDataFail(String msg) {
        hideLoading();
        ToastUtil.showToast("重命名失败");
        LoggerUtil.toJson(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.lay_add_newbook://创建素材
//                Bundle bundle = new Bundle();
//                bundle.putString("articleId", articleId);
//                UIUtil.openActivity(this, CreateMaterialDirActivity.class, bundle);
                mvpPresenter.openNewDialog(articleId);
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
            case R.id.tv_title_right2:
                //权限
                mvpPresenter.openPermissionAlertDialog(mActivity,articleId,materialPermission);
                break;
        }
    }
//
//    @Override
//    public void onRefresh() {
//
//    }
//
//    @Override
//    public void onLoadMore() {
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void refursh() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                mData.clear();
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.getSourceMaterialList(articleId);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }
}