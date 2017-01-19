package com.laichushu.book.mvp.write;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.SignStateResult;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;

/**
 * 写作
 * Created by wangtong on 2016/11/16.
 */

public interface WriteView {
    void getDataSuccess(HomeHotModel model);
    void getSignEditorDataSuccess(RewardResult model, String articleId);
    void deleteNewBook(RewardResult model, int position);
    void articleVote(RewardResult model);
    void getSignStateDeteSuccess(SignStateResult model,String articleId);
    void updateBookPermission(RewardResult model,String permission, int position);
    void publishNewBook(RewardResult model,int index,String type);
    void getDataFail(String msg);
    void getDataFail2(String msg);
    void getDataFail3(String msg);
    void getDataFail4(String msg);
    void showLoading();
    void hideLoading();

}
