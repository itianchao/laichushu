package com.laichushu.book.mvp.mineservant;

import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.bean.netbean.FindEditorInfo_Paramet;
import com.laichushu.book.bean.netbean.FindServerInfoModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MineBeServantActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;


import okhttp3.RequestBody;

/**
 * Created by PCPC on 2016/12/29.
 */

public class MineBeServantPresener extends BasePresenter<MineBeServantView> {
    private MineBeServantActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public MineBeServantPresener(MineBeServantView view) {
        attachView(view);
        mActivity = (MineBeServantActivity) view;
    }

    //提交审核
    public void loadServantInfoData(ArrayMap<String, RequestBody> params ,RequestBody vistingFile) {
        mvpView.showDialog();
        Logger.e("获取作者作品结果");
        addSubscription(apiStores.SaveServerInfoDetails(params,vistingFile), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveServerInfoDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

    //获取服务用户头像信息
    public void loadEditorInfoData(String userID) {
        FindEditorInfo_Paramet infoParamet = new FindEditorInfo_Paramet(userID,"");
        Logger.e("用户信息");
        Logger.json(new Gson().toJson(infoParamet));
        addSubscription(apiStores.getServiceInfoDatails(infoParamet), new ApiCallback<FindServerInfoModel>() {
            @Override
            public void onSuccess(FindServerInfoModel model) {
                mvpView.getEditorInfoDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

}
