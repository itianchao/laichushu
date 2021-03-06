package com.laichushu.book.mvp.write.creatnewdraft;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 创建草稿
 * Created by wangtong on 2016/11/17.
 */

public interface CreateNewDraftView {

    void getDataSuccess(RewardResult modle);

    void getCommitPhotoDataSuccess(CreateNewDraftModle modle);

    void getDataFail(String msg);

    void getDataFail2(String msg);

    void showLoading();

    void hideLoading();

}
