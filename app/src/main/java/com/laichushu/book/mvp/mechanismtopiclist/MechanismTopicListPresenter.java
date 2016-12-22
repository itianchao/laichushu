package com.laichushu.book.mvp.mechanismtopiclist;

import com.laichushu.book.bean.netbean.MechanismTopicList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 机构话题列表 presenter
 * Created by wangtong on 2016/11/26.
 */
public class MechanismTopicListPresenter extends BasePresenter<MechanismTopicListView> {
    private MechanismTopicList_Paramet paramet = new MechanismTopicList_Paramet("","","","1", ConstantValue.PAGESIZE1);

    public MechanismTopicList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MechanismTopicList_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 初始化构造
     */
    public MechanismTopicListPresenter(MechanismTopicListView view) {
        attachView(view);
    }

    /**
     * 获取机构话题列
     */
    public void loadMechanismTopicListData(String id) {
        LoggerUtil.e("获取机构话题列表");
        getParamet().setId(id);
        addSubscription(apiStores.getMechanismTopicList(paramet), new ApiCallback<MechanismTopicListModel>() {
            @Override
            public void onSuccess(MechanismTopicListModel modle) {
                mvpView.getDataSuccess(modle);
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
}