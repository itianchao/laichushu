package com.laichushu.book.mvp.createnewmaterialdir;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 创建素材页面
 * Created by wangtong on 2016/11/22.
 */
public interface CreateNewMaterialDirView {
    void getCreateSourceMaterialDir(RewardResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
