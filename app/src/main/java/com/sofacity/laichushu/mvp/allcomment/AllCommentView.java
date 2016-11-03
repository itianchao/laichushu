package com.sofacity.laichushu.mvp.allcomment;

/**
 * 首页页面
 * Created by wangtong on 2016/10/12.
 */
public interface AllCommentView {
    void getDataSuccess(AllCommentMoudle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
