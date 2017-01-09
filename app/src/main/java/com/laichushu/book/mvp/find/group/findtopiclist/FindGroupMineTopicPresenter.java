package com.laichushu.book.mvp.find.group.findtopiclist;

import com.laichushu.book.bean.netbean.MyPublishTopicList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupMineTopicListActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

import rx.Observable;
/**
 * 发现 - 小组 - 我（发表、回复、收藏）的话题
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMineTopicPresenter extends BasePresenter<FindGroupMineTopicView> {

    private FindGroupMineTopicListActivity mActivity;
    private String userId = ConstantValue.USERID;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String type = ConstantValue.SEARCH_TYPE_GROUP;
    private MyPublishTopicList_Paramet paramet = new MyPublishTopicList_Paramet(userId,type,pageNo,pageSize);
    public FindGroupMineTopicPresenter(FindGroupMineTopicView view) {
        attachView(view);
        mActivity = (FindGroupMineTopicListActivity) view;
    }

    /**
     * 请求话题列表
     */
    public void loadData(String type) {
        LoggerUtil.e("请求话题列表");
        Observable<MechanismTopicListModel> observable = null;
        switch(type){
            case "1":
                observable = apiStores.getMyPublishTopicList(paramet);
                break;
            case "2"://我回复的
                observable = apiStores.getMyReplyTopicList(paramet);
                break;
            case "3"://我收藏的
                observable = apiStores.getMyCollectTopicList(paramet);
                break;
        }
        if (observable!=null){
            addSubscription(observable, new ApiCallback<MechanismTopicListModel>() {
                @Override
                public void onSuccess(MechanismTopicListModel model) {
                    mvpView.getDataSuccess(model);
                }

                @Override
                public void onFailure(int code, String msg) {
                    mvpView.getDataFail(code+"|"+msg);
                }

                @Override
                public void onFinish() {

                }
            });
        }
    }

    public MyPublishTopicList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MyPublishTopicList_Paramet paramet) {
        this.paramet = paramet;
    }
}
