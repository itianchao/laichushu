package com.laichushu.book.mvp.sourcematerial;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 素材view
 * Created by wangtong on 2016/11/16.
 */

public interface SourceMaterialView {
    void getDataSuccess(SourceMaterialModle model);
    void getDeleteMateialDataSuccess(RewardResult model,int index);
    void getMaterialRenameDataSuccess(RewardResult model, int index,String name);
    void getDataFail(String msg);
    void getDeleteMateialDataFail(String msg);
    void getMaterialRenameDataFail(String msg);
    void showLoading();
    void hideLoading();

}
