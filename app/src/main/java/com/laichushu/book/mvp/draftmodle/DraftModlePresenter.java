package com.laichushu.book.mvp.draftmodle;

import com.laichushu.book.bean.netbean.DraftList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.orhanobut.logger.Logger;

/**
 * 草稿模式 presenter
 * Created by wangtong on 2016/11/18.
 */
public class DraftModlePresenter extends BasePresenter<DraftModleView> {
    private DraftModleActivity mActivity;
    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE3;
    private String articleId = "";

    private DraftList_Paramet paramet = new DraftList_Paramet(articleId, pageNo, pageSize, userId);

    //初始化构造
    public DraftModlePresenter(DraftModleView view) {
        attachView(view);
        mActivity = (DraftModleActivity) view;
    }

    /**
     * 获取草稿列表
     */
    public void getDraftList(String articleId) {
        Logger.e("获取草稿列表");
        paramet.setArticleId(articleId);
        addSubscription(apiStores.getDraftList(paramet), new ApiCallback<DraftModle>() {
            @Override
            public void onSuccess(DraftModle model) {
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

    public DraftList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(DraftList_Paramet paramet) {
        this.paramet = paramet;
    }
}
