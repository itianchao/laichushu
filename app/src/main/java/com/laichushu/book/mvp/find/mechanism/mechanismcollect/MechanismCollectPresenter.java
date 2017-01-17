package com.laichushu.book.mvp.find.mechanism.mechanismcollect;

import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.MechanismList_Paramet;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MechanismCollectActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2017/1/16.
 */

public class MechanismCollectPresenter extends BasePresenter<MechanismCollectView> {
    private MechanismCollectActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    //1、读者组 2、专业社 3、教育社 4、大众社
    private String type = ConstantValue.MECHANISM_TYPE;
    private MyArticBooklist_paramet paramet = new MyArticBooklist_paramet(userId, pageNo, pageSize, "1", ConstantValue.MECHANISM_TYPE);
    private MechanismList_Paramet paramet2 = new MechanismList_Paramet(type, pageNo + "", pageSize, userId);

    //初始化构造
    public MechanismCollectPresenter(MechanismCollectView view) {
        attachView(view);
        mActivity = (MechanismCollectActivity) view;
    }

    public MyArticBooklist_paramet getParamet() {
        return paramet;
    }

    public void setParamet(MyArticBooklist_paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 我收藏的机构
     */
    public void LoadCollectionData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getCollectList(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getCollectionDataSuccess(model);
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

    public MechanismList_Paramet getParamet2() {
        return paramet2;
    }

    public void setParamet2(MechanismList_Paramet paramet2) {
        this.paramet2 = paramet2;
    }

    /**
     * 请求获取机构列表
     */
    public void loadMechanismListData(String type, final String partyId) {
        paramet2.setType(type);
        LoggerUtil.e("获取机构列表");
        mvpView.showDialog();
        addSubscription(apiStores.getMechanismList(paramet2), new ApiCallback<MechanismListBean>() {
            @Override
            public void onSuccess(MechanismListBean model) {
                mvpView.getDataSuccess(model, partyId);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}
