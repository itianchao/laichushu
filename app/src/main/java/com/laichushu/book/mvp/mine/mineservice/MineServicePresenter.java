package com.laichushu.book.mvp.mine.mineservice;

import com.laichushu.book.bean.netbean.FindMyServerList_Paramet;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/12/30.
 */

public class MineServicePresenter extends BasePresenter<MineServiceView> {
    private MineServicePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public MineServicePresenter(MineServiceView view) {
        attachView(view);
        mActivity = (MineServicePageActivity) view;
    }

    public MyArticBooklist_paramet getColl_paramet() {
        return coll_paramet;
    }

    public void setColl_paramet(MyArticBooklist_paramet coll_paramet) {
        this.coll_paramet = coll_paramet;
    }

    //服务收藏
    private MyArticBooklist_paramet coll_paramet = new MyArticBooklist_paramet(userId, pageNo, pageSize, ConstantValue.COLLECTSERVICE_TYPE, ConstantValue.COLLECTSERVICE_TYPE);
    public void LoadCollectionData() {
        mvpView.showDialog();
        LoggerUtil.toJson(coll_paramet);
        addSubscription(apiStores.getCollectServiceDateList(coll_paramet), new ApiCallback<FindServiceInfoModel>() {
            @Override
            public void onSuccess(FindServiceInfoModel model) {
                mvpView.getCollectionDataSuccess(model);
            }
            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,2);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
    public FindMyServerList_Paramet getCoop_paramet() {
        return coop_paramet;
    }

    public void setCoop_paramet(FindMyServerList_Paramet coop_paramet) {
        this.coop_paramet = coop_paramet;
    }

    //服务合作FindMyServerList_Paramet
    private FindMyServerList_Paramet coop_paramet = new FindMyServerList_Paramet(userId, "", pageNo, pageSize);

    public void LoadCooperateData() {
        LoggerUtil.toJson(coop_paramet);
        addSubscription(apiStores.getFindMyServerListDetails(coop_paramet), new ApiCallback<FindServiceCooperateMode>() {
            @Override
            public void onSuccess(FindServiceCooperateMode model) {
                mvpView.getCooperateDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,1);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
