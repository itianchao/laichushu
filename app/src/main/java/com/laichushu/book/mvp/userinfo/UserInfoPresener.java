package com.laichushu.book.mvp.userinfo;

import com.laichushu.book.bean.netbean.HomeUserInfor_paramet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;

/**
 * Created by PCPC on 2016/11/30.
 */

public class UserInfoPresener extends BasePresenter<UserInfoView> {
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public UserInfoPresener(UserInfoView view) {
        attachView(view);
    }

    public HomeUserInfor_paramet getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HomeUserInfor_paramet headInfo) {
        this.headInfo = headInfo;
    }

    //初始化用户界面用户信息
    HomeUserInfor_paramet headInfo = new HomeUserInfor_paramet(SharePrefManager.getUserId(), "");

    public void getUserHeadDate(String userId) {
        headInfo.setUserId(userId);
        LoggerUtil.toJson(headInfo);
        addSubscription(apiStores.getUserInforDetails(headInfo), new ApiCallback<HomeUserResult>() {

            @Override
            public void onSuccess(HomeUserResult model) {
                mvpView.getUserHeadDateSuccess(model);
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
