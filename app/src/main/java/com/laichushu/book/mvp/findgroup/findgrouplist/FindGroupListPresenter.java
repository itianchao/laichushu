package com.laichushu.book.mvp.findgroup.findgrouplist;

import com.laichushu.book.bean.netbean.JoinGroupList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupListActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 发现 - 小组列表
 * Created by wangtong on 2016/12/29.
 */

public class FindGroupListPresenter extends BasePresenter<FindGroupListView> {
    private FindGroupListActivity mActivity;
    private String userId = ConstantValue.USERID;

    public FindGroupListPresenter(FindGroupListView view) {
        attachView(view);
        mActivity = (FindGroupListActivity) view;
    }
    /**
     * 获取我加入的小组列表
     */
    public void loadMyJoinGroupList(){
        LoggerUtil.e("获取加入小组列表");
        JoinGroupList_Paramet paramet = new JoinGroupList_Paramet(userId);
        addSubscription(apiStores.getJoinGroupList(paramet), new ApiCallback<GroupListModle>() {
            @Override
            public void onSuccess(GroupListModle model) {
                mvpView.getGroupListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getGroupListDataFail("我加入的小组列表:"+code+"|"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取我创建的小组列表
     */
    public void getMyCreateGroupList() {
        LoggerUtil.e("获取我创建的小组列表");
        JoinGroupList_Paramet paramet = new JoinGroupList_Paramet(userId);
        addSubscription(apiStores.getMyCreateGroupList(paramet), new ApiCallback<GroupListModle>() {
            @Override
            public void onSuccess(GroupListModle model) {
                mvpView.getGroupListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getGroupListDataFail("我创建的小组列表:"+code+"|"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
