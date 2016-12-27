package com.laichushu.book.mvp.findgroup.groupmain;

import com.laichushu.book.bean.netbean.JoinGroupList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 小组主页控制器
 * Created by wangtong on 2016/12/27.
 */

public class GroupMainPresenter extends BasePresenter<GroupMainView> {
    private String userId = ConstantValue.USERID;

    public GroupMainPresenter(GroupMainView view) {
        attachView(view);
    }

    /**
     * 获取加入小组列表
     */
    public void loadGroupList(){
        LoggerUtil.e("获取加入小组列表");
        JoinGroupList_Paramet paramet = new JoinGroupList_Paramet(userId);
        addSubscription(apiStores.getJoinGroupList(paramet), new ApiCallback<GroupListModle>() {
            @Override
            public void onSuccess(GroupListModle model) {
                mvpView.getGroupListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getGroupListDataFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获最新构话题列
     */
    public void loadMechanismTopicListData(String id) {
        LoggerUtil.e("获取最新话题列表");
//        getParamet().setPartyId(id);
//        addSubscription(apiStores.getMechanismTopicList(paramet), new ApiCallback<MechanismTopicListModel>() {
//            @Override
//            public void onSuccess(MechanismTopicListModel modle) {
//                mvpView.getDataSuccess(modle);
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }
}
