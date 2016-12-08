package com.laichushu.book.mvp.homecategory;

import com.laichushu.book.bean.netbean.HomeCategory_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CategoryActivity;

/**
 * 分类
 * Created by wangtong on 2016/11/10.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> {
    private CategoryActivity mActivity;
    private String userId = ConstantValue.USERID;

    public CategoryPresenter(CategoryView view) {
        attachView(view);
        mActivity = (CategoryActivity) view;
    }

    public void loadCategoryData(){
        Logger.e("分类请求");
        HomeCategory_Paramet paramet = new HomeCategory_Paramet(userId);
        addSubscription(apiStores.getCategoryList(paramet), new ApiCallback<CategoryModle>() {
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
