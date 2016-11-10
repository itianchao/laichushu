package com.sofacity.laichushu.ui.activity;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.homecategory.CategoryModle;
import com.sofacity.laichushu.mvp.homecategory.CategoryPresenter;
import com.sofacity.laichushu.mvp.homecategory.CategoryView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;

import java.util.ArrayList;

/**
 * 首页分类
 * Created by wangtong on 2016/11/10.
 */

public class CategoryActivity extends MvpActivity<CategoryPresenter> implements CategoryView {

    private ArrayList<CategoryModle.DataBean> mParentData;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_category);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getDataSuccess(CategoryModle model) {
        if (model.isSuccess()) {
            mParentData = model.getData();
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
        ToastUtil.showToast("网络失败");
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
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(this);
    }
}
