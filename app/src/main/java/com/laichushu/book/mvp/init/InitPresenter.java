package com.laichushu.book.mvp.init;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.HomeAllBook_Paramet;
import com.laichushu.book.bean.netbean.HomeHot_Paramet;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.PersonalCentre_Parmet;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;

/**
 * init presenter
 * Created by wangtong on 2016/10/17.
 */
public class InitPresenter extends BasePresenter<InitView> {
    private String pageSize = ConstantValue.PAGESIZE1; ;
    private String pageNo = "1";
    private HomeAllBook_Paramet paramet;
    private String userId = ConstantValue.USERID;

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

    public void loadMineData() {
            addSubscription(apiStores.getPersonalDetails(new PersonalCentre_Parmet(SharePrefManager.getUserId())), new ApiCallback<PersonalCentreResult>() {
                @Override
                public void onSuccess(PersonalCentreResult result) {
                    mvpView.loadMineDataSuccess(result);
                }

                @Override
                public void onFailure(int code, String msg) {
                    mvpView.getDataFail("code:"+code+"\nmsg:"+msg);
                }

                @Override
                public void onFinish() {

                }
            });
    }
}
