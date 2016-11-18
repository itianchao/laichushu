package com.laichushu.book.mvp.createnewbook;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.homecategory.CategoryModle;

/**
 * 创建新书
 * Created by wangtong on 2016/11/17.
 */

public interface CreateNewBookView {
    void getCategoryDataSuccess(CategoryModle modle);

    void getDataSuccess(CreateNewBookModle modle);

    void commitNewBook(RewardResult modle);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();

    void getDataFail2(String s);
}
