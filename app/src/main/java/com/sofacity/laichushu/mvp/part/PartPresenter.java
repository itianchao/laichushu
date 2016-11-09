package com.sofacity.laichushu.mvp.part;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.BookList_Paramet;
import com.sofacity.laichushu.bean.netbean.MaterialContent_Paramet;
import com.sofacity.laichushu.bean.netbean.MaterialList_Paramet;
import com.sofacity.laichushu.mvp.directories.BookMoudle;
import com.sofacity.laichushu.mvp.directories.DirectoriesView;
import com.sofacity.laichushu.mvp.directories.MaterialContentModel;
import com.sofacity.laichushu.mvp.directories.MaterialListModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.DirectoriesActivity;
import com.sofacity.laichushu.ui.activity.PartActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 节 presenter
 * Created by wangtong on 2016/10/12.
 */
public class PartPresenter extends BasePresenter<PartView> {
    private PartActivity mActivity;
    private String pageNo = "1";
    private String pageSize = "2000";
    BookList_Paramet patamet = new BookList_Paramet("",pageNo,pageSize);
    //初始化构造
    public PartPresenter(PartView view) {
        attachView(view);
        mActivity = (PartActivity) view;
    }
}
