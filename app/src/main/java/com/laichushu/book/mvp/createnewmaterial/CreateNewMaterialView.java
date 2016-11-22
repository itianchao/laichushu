package com.laichushu.book.mvp.createnewmaterial;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 创建素材页面
 * Created by wangtong on 2016/10/12.
 */
public interface CreateNewMaterialView {
    void getCreateSourceMaterialDir(RewardResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
