package com.sofacity.laichushu.mvp.directories;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.MaterialContent_Paramet;
import com.sofacity.laichushu.bean.netbean.MaterialList_Paramet;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.DirectoriesActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 目录 presenter
 * Created by wangtong on 2016/10/12.
 */
public class DirectoriesPresenter extends BasePresenter<DirectoriesView> {
    private DirectoriesActivity mActivity;

    //初始化构造
    public DirectoriesPresenter(DirectoriesView view) {
        attachView(view);
        mActivity = (DirectoriesActivity) view;
    }
    public void loadMaterialListData(String articleId){
        MaterialList_Paramet paramet = new MaterialList_Paramet(articleId);
        Logger.e("获取素材列表");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getMaterialList(paramet), new ApiCallback<MaterialListModel>() {
            @Override
            public void onSuccess(MaterialListModel model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    public void loadMaterialContentData(String parentId){
        MaterialContent_Paramet paramet = new MaterialContent_Paramet(parentId);
        Logger.e("获取素材列表");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getMaterialContent(paramet), new ApiCallback<MaterialContentModel>() {
            @Override
            public void onSuccess(MaterialContentModel model) {
                mvpView.getMaterialContentData(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
