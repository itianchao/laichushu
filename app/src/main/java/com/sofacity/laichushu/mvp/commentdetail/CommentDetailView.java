package com.sofacity.laichushu.mvp.commentdetail;

import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;

/**
 * 评价详情页面
 * Created by wangtong on 2016/10/12.
 */
public interface CommentDetailView {
    void getDataSuccess(CommentDetailModle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
