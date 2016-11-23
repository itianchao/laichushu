package com.laichushu.book.mvp.draftmodle;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 草稿模式页面
 * Created by wangtong on 2016/11/18.
 */
public interface DraftModleView {
    void getDataSuccess(DraftModle model);
    void getDeleteDraftBookDataSuccess(RewardResult model, int position);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void showLoading();
    void hideLoading();

}
