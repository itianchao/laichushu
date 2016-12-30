package com.laichushu.book.mvp.topicmanage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
import com.laichushu.book.bean.netbean.DeleteTopic_Paramet;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.TopicManageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/21.
 */

public class TopicManagePresenter extends BasePresenter<TopicManageView> {
    private TopicManageActivity mActicity;
    private String PageSize = ConstantValue.PAGESIZE4, PageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public TopicManagePresenter(TopicManageView view) {
        attachView(view);
        mActicity = (TopicManageActivity) view;
    }

    /**
     * 删除机构话题信息
     *
     * @param id 机构id
     */
    public void loadDeleteTopicManage(String id) {
        DeleteTopic_Paramet deleteParamet = new DeleteTopic_Paramet(id);
        LoggerUtil.toJson(deleteParamet);
        addSubscription(apiStores.deleteTopicManageDetails(deleteParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteTopicDateSuccess(model);
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

    public HomeUserDy_parmet getTopicList_paramet() {
        return topicList_paramet;
    }

    public void setTopicList_paramet(HomeUserDy_parmet topicList_paramet) {
        this.topicList_paramet = topicList_paramet;
    }

    /**
     * 获取机构话题列
     *
     * @param id
     */
    HomeUserDy_parmet topicList_paramet = new HomeUserDy_parmet(userId, "", PageSize, PageNo, "");

    public void loadMechanismTopicListData(String partyId) {
        LoggerUtil.e("获取机构话题列表");
        getTopicList_paramet().setUserId(userId);
        getTopicList_paramet().setPartyId(partyId);
        addSubscription(apiStores.getHomeUserDyDetails(topicList_paramet), new ApiCallback<HomeUseDyrResult>() {
            @Override
            public void onSuccess(HomeUseDyrResult modle) {
                mvpView.getTopicListDateSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 话题收藏
     *
     * @param sourceId
     * @param sourceType
     * @param type
     */
    public void loadCollectSaveDate(String sourceId, String sourceType, final String type, final List<HomeUseDyrResult.DataBean> dataBeen, final int position) {
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        LoggerUtil.toJson(collectSave);
        mvpView.showDialog();
        addSubscription(apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveCollectSuccess(model, type, dataBeen, position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dissmissDialog();
            }
        });
    }
}
