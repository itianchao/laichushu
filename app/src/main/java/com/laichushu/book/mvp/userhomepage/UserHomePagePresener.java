package com.laichushu.book.mvp.userhomepage;

import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserInfor_paramet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;

/**
 * Created by PCPC on 2016/11/25.
 */

public class UserHomePagePresener extends BasePresenter<UserHomePageView> {
    private UserHomePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public UserHomePagePresener(UserHomePageView view) {
        attachView(view);
        mActivity = (UserHomePageActivity) view;
    }


    //初始化头像信息
    HomeUserInfor_paramet headInfo = new HomeUserInfor_paramet("", SharePrefManager.getUserId());

    public void getUserHeadDate(String targetId) {
        headInfo.setTagetId(targetId);
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

    //获取用户动态详情
    private HomeUserDy_parmet paramet = new HomeUserDy_parmet(userId, pageSize, pageNo);

    public void getUserDynmicDate() {
        addSubscription(apiStores.getUserHomeDyDetails(paramet), new ApiCallback<HomeUseDyrResult>() {
            @Override
            public void onSuccess(HomeUseDyrResult model) {
                mvpView.getUserHomeDyDateSuccess(model);
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
//获取作品列表

}