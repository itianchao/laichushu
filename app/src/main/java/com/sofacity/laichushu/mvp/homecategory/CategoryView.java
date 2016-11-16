package com.sofacity.laichushu.mvp.homecategory;

/**
 * 分类
 * Created by wangtong on 2016/11/10.
 */

public interface CategoryView {
    void getDataSuccess(CategoryModle model);
    void getDataFail(String msg);
}
