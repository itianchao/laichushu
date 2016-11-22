package com.laichushu.book.mvp.createnewmaterial;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftModle;

/**
 * 创建素材页面
 * Created by wangtong on 2016/11/22.
 */
public interface CreateNewMaterialView {

    void getCreateSourceMaterial(RewardResult model);
    void getCommitPhotoDataSuccess(CreateNewDraftModle model);
    void getEditMaterialDataSuccess(RewardResult model);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void showLoading();
    void hideLoading();

}
