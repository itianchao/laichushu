package com.laichushu.book.mvp.sourcematerialdir;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 素材文件夹
 * Created by wangtong on 2016/11/16.
 */

public interface SourceMaterialDirView {
    void getDataSuccess(SourceMaterialDirModle model);
    void getDeleteMateialDataSuccess(RewardResult model, int index);
    void getDataFail(String msg);
    void getDeleteMateialDataFail(String msg);
    void showLoading();
    void hideLoading();

}
