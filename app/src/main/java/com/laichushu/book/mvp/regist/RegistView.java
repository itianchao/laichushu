package com.laichushu.book.mvp.regist;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public interface RegistView {
    void getDataSuccess(RegistModel model);
    void getDataSuccess(RewardResult model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
