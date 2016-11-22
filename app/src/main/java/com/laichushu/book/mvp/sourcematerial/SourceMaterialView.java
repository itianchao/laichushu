package com.laichushu.book.mvp.sourcematerial;

/**
 * 素材view
 * Created by wangtong on 2016/11/16.
 */

public interface SourceMaterialView {
    void getDataSuccess(SourceMaterialModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
