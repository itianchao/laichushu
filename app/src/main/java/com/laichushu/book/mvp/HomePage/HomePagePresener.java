package com.laichushu.book.mvp.homePage;

import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePageFocusBeResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusBe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusMe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusState_Paramet;
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
    private HomeUserFocusMe_parmet paramet2 = new HomeUserFocusMe_parmet(userId, pageNo, pageSize);

    public void LoadFocusMeData() {

        LoggerUtil.toJson(paramet2);
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

    public HomeUserFocusBe_parmet getParamet3() {
        return paramet3;
    }

    public void setParamet3(HomeUserFocusBe_parmet paramet3) {
        this.paramet3 = paramet3;
    }

    //我关注的
    private HomeUserFocusBe_parmet paramet3 = new HomeUserFocusBe_parmet(userId, pageNo, pageSize);

    public void LoadFocusBeData() {

        LoggerUtil.toJson(paramet3);
        addSubscription(apiStores.getHomeUserFocusBeDetails(paramet3), new ApiCallback<HomePersonFocusResult>() {
            @Override
            public void onSuccess(HomePersonFocusResult model) {
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        targetId = targetId;
    }

    private String targetId;

    public HomeUserFocusState_Paramet getStatus() {
        return status;
    }

    public void setStatus(HomeUserFocusState_Paramet status) {
        this.status = status;
    }

    private HomeUserFocusState_Paramet status = new HomeUserFocusState_Paramet(userId,targetId,false);
//更新关注我的状态
    public void loadFocusBeStatus(final boolean flg){
        LoggerUtil.toJson(paramet3);
                    addSubscription(apiStores.getHomeUserFocusBeStatus(status), new ApiCallback<HomeFocusResult>() {
                        @Override
                        public void onSuccess(HomeFocusResult model) {
                            mvpView.getFocusBeStatus(model,flg);
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
    //更新我关注的状态
    public void loadFocusMeStatus(final boolean isFocus){
        LoggerUtil.toJson(paramet3);
        addSubscription(apiStores.getHomeUserFocusMeStatus(status), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model,isFocus);
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
