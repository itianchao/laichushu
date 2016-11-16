package com.sofacity.laichushu.mvp.homecategory;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.CategoryActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 分类
 * Created by wangtong on 2016/11/10.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> {
    private CategoryActivity mActivity;

    public CategoryPresenter(CategoryView view) {
        attachView(view);
        mActivity = (CategoryActivity) view;
    }

    public void loadCategoryData(){
        Logger.e("分类请求");
        addSubscription(apiStores.getCategoryList(), new ApiCallback<CategoryModle>() {
            @Override
            public void onSuccess(CategoryModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code: "+code + " \n msg: "+msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
