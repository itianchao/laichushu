package com.laichushu.book.mvp.find.eidt.findeditpage;

import com.laichushu.book.bean.netbean.FindEditorListModel;

/**
 * Created by PCPC on 2016/12/23.
 */


public interface FindEditPageView {

    void getEditorListDataSuccess(FindEditorListModel model,String orderBy);
    void showDialog();

    void getDataFail(String msg);

    void dismissDialog();
}
