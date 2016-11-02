package com.sofacity.laichushu.mvp.HomeSearch;

import com.sofacity.laichushu.bean.netbean.HomeSearch_Paramet;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.HomeSearchActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchPresenter extends BasePresenter<HomeSearchView> {
    private HomeSearchActivity mActivity;
    private String pageSize = "10";
    private String pageNo = "1";
    private HomeSearch_Paramet paramet = new HomeSearch_Paramet("",pageSize,pageNo);
    //初始化构造
    public HomeSearchPresenter(HomeSearchView view) {
        attachView(view);
        mActivity = (HomeSearchActivity) view;
    }
    public void LoadData(String name){
        getParamet().setComplexName(name);
        addSubscription(apiStores.homeSearch(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
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

    public HomeSearch_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(HomeSearch_Paramet paramet) {
        this.paramet = paramet;
    }
}
