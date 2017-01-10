package com.laichushu.book.mvp.write.directories;

/**
 * 目录
 * Created by wangtong on 2016/11/7.
 */
public interface DirectoriesView {
    void getDataSuccess(MaterialListModel model);
    void getMaterialContentData(MaterialContentModel model);
    void getBookListData(BookMoudle model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();

}
