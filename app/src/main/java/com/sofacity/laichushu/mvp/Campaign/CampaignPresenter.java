package com.sofacity.laichushu.mvp.Campaign;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.ActivityResult_Paramet;
import com.sofacity.laichushu.bean.netbean.AuthorWorks_Paramet;
import com.sofacity.laichushu.bean.netbean.JoinActivity_Paramet;
import com.sofacity.laichushu.mvp.bookdetail.AuthorDetailModle;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.CampaignActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * 活动 presenter
 * Created by wangtong on 2016/11/4.
 */
public class CampaignPresenter extends BasePresenter<CampaignView> {
    private CampaignActivity mActivity;
    private String userId = SharePrefManager.getUserId();

    public CampaignPresenter(CampaignView view) {
        attachView(view);
        this.mActivity = (CampaignActivity)view;
    }

    public void loadActivityResultData(String activityId) {
        mvpView.showLoading();
        ActivityResult_Paramet paramet = new ActivityResult_Paramet(activityId);
        Logger.e("获取活动结果");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getActivityResult(paramet), new ApiCallback<CampaignModel>() {
            @Override
            public void onSuccess(CampaignModel model) {
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
    public void loadJoinActivityData(String activityId, String articleId, final String type) {
        mvpView.showLoading();
        JoinActivity_Paramet paramet = new JoinActivity_Paramet(activityId,articleId,userId,type);
        Logger.e("参加活动");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.joinActivity(paramet), new ApiCallback<CampaignJoinModel>() {
            @Override
            public void onSuccess(CampaignJoinModel model) {
                mvpView.getJoinDataSuccess(model,type);
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
    public void loadAuthorWorksData(){
        mvpView.showLoading();
        AuthorWorks_Paramet paramet = new AuthorWorks_Paramet(userId);
        Logger.e("获取作者作品结果");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getAuthorWorks(paramet), new ApiCallback<AuthorWorksModle>() {
            @Override
            public void onSuccess(AuthorWorksModle model) {
                mvpView.getAuthorWorksDataSuccess(model);
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
