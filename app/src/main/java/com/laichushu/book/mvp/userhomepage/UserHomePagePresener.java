package com.laichushu.book.mvp.userhomepage;

import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusBe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusMe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusState_Paramet;
import com.laichushu.book.bean.netbean.HomeUserInfor_paramet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
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


    public HomeUserInfor_paramet getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HomeUserInfor_paramet headInfo) {
        this.headInfo = headInfo;
    }

    //初始化头像信息
    HomeUserInfor_paramet headInfo = new HomeUserInfor_paramet(SharePrefManager.getUserId(), "");

    public void getUserHeadDate(String id) {
        headInfo.setUserId(id);
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

    public HomeUserDy_parmet getParamet() {
        return paramet;
    }

    public void setParamet(HomeUserDy_parmet paramet) {
        this.paramet = paramet;
    }

    //获取用户动态详情
    private HomeUserDy_parmet paramet = new HomeUserDy_parmet("", pageSize, pageNo,userId);

    public void getUserDynmicDate(String id) {
        paramet.setUserId(id);
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

    public ArticleBookList_Paramet getBookList() {
        return bookList;
    }

    public void setBookList(ArticleBookList_Paramet bookList) {
        this.bookList = bookList;
    }

    //获取用户作品列表 userId==targetId
    ArticleBookList_Paramet bookList = new ArticleBookList_Paramet("", pageNo, pageSize, "");

    public void getUserBookListDate(String userId) {
        bookList.setUserId(userId);
        addSubscription(apiStores.getArticleBookList(bookList), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getUserHomeBookListSuccess(model);
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


    public HomeUserFocusBe_parmet getFocusMe_parmet() {
        return focusMe_parmet;
    }

    public void setFocusMe_parmet(HomeUserFocusBe_parmet focusMe_parmet) {
        this.focusMe_parmet = focusMe_parmet;
    }

    //用户 他关注的
    HomeUserFocusBe_parmet focusMe_parmet = new HomeUserFocusBe_parmet(SharePrefManager.getUserId(), pageNo, pageSize, "");

    public void getUserHeFocusDate(String id) {
        focusMe_parmet.setUserId(id);
        addSubscription(apiStores.getHomeUserFocusBeDetails(focusMe_parmet), new ApiCallback<HomePersonFocusResult>() {

            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getUserHomeFocusHeSuccess(model);
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


    public HomeUserFocusMe_parmet getFocusBe_parmet() {
        return focusBe_parmet;
    }

    public void setFocusBe_parmet(HomeUserFocusMe_parmet focusBe_parmet) {
        this.focusBe_parmet = focusBe_parmet;
    }

    //用户界面 关注他的
    HomeUserFocusMe_parmet focusBe_parmet = new HomeUserFocusMe_parmet(SharePrefManager.getUserId(), pageNo, pageSize, "");

    public void getUserFocusHeDate(String userId) {
        focusBe_parmet.setTargetId(userId);
        addSubscription(apiStores.getHomeUserFocusMeDetails(focusBe_parmet), new ApiCallback<HomePersonFocusResult>() {

            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getUserHomeHeFocusSuccess(model);
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