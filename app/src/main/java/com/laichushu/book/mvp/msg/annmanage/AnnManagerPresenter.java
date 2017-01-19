package com.laichushu.book.mvp.msg.annmanage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.NoticesList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.msg.notice.NoticeModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.AnnounMangeActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/12/20.
 */

public class AnnManagerPresenter extends BasePresenter<AnnManagerView> {
    private AnnounMangeActivity mActivity;
    private String PageSize = "6", PageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public AnnManagerPresenter(AnnManagerView view) {
        attachView(view);
        mActivity = (AnnounMangeActivity) view;
    }
    public String getPageNo() {
        return PageNo;
    }

    public void setPageNo(String pageNo) {
        PageNo = pageNo;
    }

    public NoticesList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(NoticesList_Paramet paramet) {
        this.paramet = paramet;
    }

    NoticesList_Paramet paramet = new NoticesList_Paramet("", PageNo, PageSize);

    //查询公告列表
    public void loadAnnManageDate(String id) {
        getParamet().setId(id);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getNoticesList(paramet), new ApiCallback<NoticeModle>() {
            @Override
            public void onSuccess(NoticeModle model) {
                mvpView.getAnnanageDataSuccess(model);
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

    //删除单条公告
    public void loadDelAnnManageDate(String partyId) {
        mvpView.shwoDialog();
        NoticesList_Paramet deleteParamet = new NoticesList_Paramet(partyId, "", "");
        LoggerUtil.toJson(deleteParamet);
        addSubscription(apiStores.dleteNoticesItem(deleteParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteAnnDateSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,2);
            }

            @Override
            public void onFinish() {
                mvpView.dissmissDialog();
            }
        });
    }
}
