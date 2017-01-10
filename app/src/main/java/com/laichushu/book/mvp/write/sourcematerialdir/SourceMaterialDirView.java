package com.laichushu.book.mvp.write.sourcematerialdir;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 素材文件夹
 * Created by wangtong on 2016/11/16.
 */

public interface SourceMaterialDirView {
    void getDataSuccess(SourceMaterialDirModle model);
    void getDeleteMateialDataSuccess(RewardResult model, int index);
    void getMaterialRenameDataSuccess(RewardResult model, int index, String rename);
    void getCreateSourceMaterialDir(RewardResult model);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void getUpdateMerPermission(RewardResult model,String permission);
    void getDeleteMateialDataFail(String msg);
    void getMaterialRenameDataFail(String s);
    void showLoading();
    void hideLoading();

}
