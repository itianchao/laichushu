package com.laichushu.book.mvp.find.mechanism.mechanismsearch;

import com.laichushu.book.bean.JsonBean.MechanismListBean;

/**
 * 机构页面
 * Created by wangtong on 2016/10/12.
 */
public interface MechanismSearchView {
    void getDataSuccess(MechanismListBean model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
