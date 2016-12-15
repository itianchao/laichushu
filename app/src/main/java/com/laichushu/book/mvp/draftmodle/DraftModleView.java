package com.laichushu.book.mvp.draftmodle;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 草稿模式页面
 * Created by wangtong on 2016/11/18.
 */
public interface DraftModleView {
    void getDataSuccess(DraftModle model);
    void getDeleteDraftBookDataSuccess(RewardResult model, int position);
    void getChapterRenameDataSuccess(RewardResult model, int index, String rename);
    void getCreateNewDraftDataSuccess(RewardResult model);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void getDataFail3(String msg);
    void getChapterRenameDataFail(String s);
    void showLoading();
    void hideLoading();

}
