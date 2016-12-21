package com.laichushu.book.mvp.home;

import android.os.Bundle;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.HomeTitleBean;
import com.laichushu.book.bean.netbean.ActivityById_Paramet;
import com.laichushu.book.bean.netbean.ActivityList_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorksByBookId_Paramet;
import com.laichushu.book.bean.netbean.HomeAllBook_Paramet;
import com.laichushu.book.bean.netbean.HomeHot_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.ui.activity.CampaignActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.HomeFragment;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

/**
 * 首页 presenter
 * Created by wangtong on 2016/10/17.
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String pageNo2 = "1";
    private String userId = ConstantValue.USERID;
    private HomeAllBook_Paramet paramet = paramet = new HomeAllBook_Paramet("1",pageSize,pageNo,userId);
    private ActivityList_Paramet activityListParamet = new ActivityList_Paramet(pageNo2,pageSize,userId);;
    private int state = 1;
    private HomeFragment homeFragment;

    public HomePresenter(HomeView view) {
        attachView(view);
        homeFragment = (HomeFragment) view;
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
        mvpView.showLoading();
        getParamet().setSortWay(type);
        mvpView.showLoading();
        Logger.json(new Gson().toJson(paramet));
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
    public void loadActivityData(){
        mvpView.showLoading();

        addSubscription(apiStores.activityList(activityListParamet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getActivityData(model);
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

    /**
     * 根据Id获取图书
     * @param articleId 图书Id
     * @param position
     */
    public void getBookById(String articleId, int position){
        mvpView.showLoading();
        AuthorWorksByBookId_Paramet paramet = new AuthorWorksByBookId_Paramet(userId,articleId);
        addSubscription(apiStores.getBookById(paramet), new ApiCallback<HomeTitleBean>() {
            @Override
            public void onSuccess(HomeTitleBean model) {
                if (model.isSuccess()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", model.getData());
                    bundle.putString("pageMsg", "首页");
                    UIUtil.openActivity(homeFragment.getActivity(), BookDetailActivity.class, bundle);
                }else {
                    ToastUtil.showToast(model.getErrMsg());
                }
                mvpView.hideLoading();
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.hideLoading();
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 根据Id获取活动
     * @param activityId 活动Id
     * @param index 轮播图位置
     */
    public void getActivityById(String activityId, final int index){
        mvpView.showLoading();
        ActivityById_Paramet paramet = new ActivityById_Paramet(activityId,userId);
        addSubscription(apiStores.getActivityById(paramet), new ApiCallback<HomeTitleBean>() {
            @Override
            public void onSuccess(HomeTitleBean model) {
                if (model.isSuccess()) {
                    //跳转活动详情页
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", model.getData());
                    bundle.putInt("position", -1);
                    bundle.putString("type", "");
                    UIUtil.openActivity(homeFragment.getActivity(), CampaignActivity.class, bundle);
                }else {

                }
                mvpView.hideLoading();
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.hideLoading();
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

    public ActivityList_Paramet getActivityListParamet() {
        return activityListParamet;
    }

    public void setActivityListParamet(ActivityList_Paramet activityListParamet) {
        this.activityListParamet = activityListParamet;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
