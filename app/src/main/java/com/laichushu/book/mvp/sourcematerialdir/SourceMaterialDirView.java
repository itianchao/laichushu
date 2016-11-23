package com.laichushu.book.mvp.sourcematerialdir;

/**
 * 素材文件夹
 * Created by wangtong on 2016/11/16.
 */

public interface SourceMaterialDirView {
    void getDataSuccess(SourceMaterialDirModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
