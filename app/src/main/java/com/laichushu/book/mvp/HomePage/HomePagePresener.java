package com.laichushu.book.mvp.HomePage;

import com.laichushu.book.bean.netbean.HomePageFocusBeResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusMe_parmet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomePagePresener extends BasePresenter<HomePageView> {
    private PersonalHomePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    public HomeUserDy_parmet getParamet() {
        return paramet;
    }

    public void setParamet(HomeUserDy_parmet paramet) {
        this.paramet = paramet;
    }

    private HomeUserDy_parmet paramet = new HomeUserDy_parmet(userId, pageSize, pageNo);

    public HomeUserDy_parmet getParamet2() {
        return paramet;
    }


    //初始化构造
    public HomePagePresener(HomePageView view) {
        attachView(view);
        mActivity = (PersonalHomePageActivity) view;
    }

    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getHomeUserDyDetails(paramet), new ApiCallback<HomeUseDyrResult>() {
            @Override
            public void onSuccess(HomeUseDyrResult model) {
                mvpView.getDyDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void setParamet2(HomeUserFocusMe_parmet paramet2) {
        this.paramet2 = paramet2;
    }

    //我关注的
    private HomeUserFocusMe_parmet paramet2 = new HomeUserFocusMe_parmet(userId, pageNo,pageSize);

    public void LoadFocusMeData() {

        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getHomeUserFocusMeDetails(paramet2), new ApiCallback<HomePersonFocusResult>() {
            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getFocusMeDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void LoadFocusBeData() {

        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getHomeUserFocusMeDetails(paramet2), new ApiCallback<HomePageFocusBeResult>() {
            @Override
            public void onSuccess(HomePageFocusBeResult model) {
                mvpView.getFocusBeDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
