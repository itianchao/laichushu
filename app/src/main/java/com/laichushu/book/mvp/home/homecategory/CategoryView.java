package com.laichushu.book.mvp.home.homecategory;

/**
 * 分类
 * Created by wangtong on 2016/11/10.
 */

public interface CategoryView {
    void getDataSuccess(CategoryModle model);
    void getDataFail(String msg);
}
