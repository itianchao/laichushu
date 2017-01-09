package com.laichushu.book.mvp.find.mechanism.mechanismsearch;

import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.MechanismSearchList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MechanismSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 机构 presenter
 * Created by wangtong on 2016/10/12.
 */
public class MechanismSearchPresenter extends BasePresenter<MechanismSearchView> {
    private MechanismSearchActivity mActivity;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private String userId = ConstantValue.USERID;
    private MechanismSearchList_Paramet paramet = new MechanismSearchList_Paramet("", pageNo, pageSize, userId);

    //初始化构造
    public MechanismSearchPresenter(MechanismSearchView view) {
        attachView(view);
        mActivity = (MechanismSearchActivity) view;
    }


    /**
     * 请求搜索机构接口
     *
     * @param name 关键字
     */
    public void loadSearchResultData(String name) {
        LoggerUtil.e("搜索机构");
        getParamet().setName(name);
        LoggerUtil.toJson(paramet);
        mvpView.showLoading();
        addSubscription(apiStores.getSearchMechanismList(paramet), new ApiCallback<MechanismListBean>() {
            @Override
            public void onSuccess(MechanismListBean model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    public MechanismSearchList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MechanismSearchList_Paramet paramet) {
        this.paramet = paramet;
    }
}
