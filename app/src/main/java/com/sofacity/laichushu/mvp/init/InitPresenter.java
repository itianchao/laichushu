package com.sofacity.laichushu.mvp.init;

import com.sofacity.laichushu.bean.netbean.HomeAllBook_Paramet;
import com.sofacity.laichushu.bean.netbean.HomeHot_Paramet;
import com.sofacity.laichushu.mvp.home.HomeAllModel;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.home.HomeView;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * init presenter
 * Created by wangtong on 2016/10/17.
 */
public class InitPresenter extends BasePresenter<InitView> {
    private String pageSize = "10";
    private String pageNo = "1";
    private HomeAllBook_Paramet paramet;
    private String userId = SharePrefManager.getUserId();

    public InitPresenter(InitView view) {
        attachView(view);
    }
    public void loadHomeCarouseData() {
        mvpView.showLoading();
        addSubscription(apiStores.homeCarouselData(),
                new ApiCallback<HomeModel>() {
                    @Override
                    public void onSuccess(HomeModel model) {
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

    public void loadHomeHotData() {
        mvpView.showLoading();
        addSubscription(apiStores.homeHotData(new HomeHot_Paramet(userId)), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getHotDataSuccess(model);
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
    public void loadHomeAllData(String type) {
        paramet = new HomeAllBook_Paramet(type,pageSize,pageNo,userId);
        mvpView.showLoading();
        addSubscription(apiStores.homeAllData(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getAllData(model);
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

    public HomeAllBook_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(HomeAllBook_Paramet paramet) {
        this.paramet = paramet;
    }
}
