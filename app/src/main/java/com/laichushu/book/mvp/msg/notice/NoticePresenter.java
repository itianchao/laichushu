package com.laichushu.book.mvp.msg.notice;

import com.laichushu.book.bean.netbean.NoticesList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 公告 presenter
 * Created by wangtong on 2016/11/26.
 */
public class NoticePresenter extends BasePresenter<NoticeView> {

    private String pageSize = ConstantValue.PAGESIZE1;
    private NoticesList_Paramet paramet = new NoticesList_Paramet("", "1", pageSize);

    public NoticesList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(NoticesList_Paramet paramet) {
        this.paramet = paramet;
    }

    //初始化构造
    public NoticePresenter(NoticeView view) {
        attachView(view);
    }

    /**
     * 获取公告列表
     */
    public void loadNoticeListData(String id) {
        LoggerUtil.e("获取公告列表");
        paramet.setId(id);
        addSubscription(apiStores.getNoticesList(paramet), new ApiCallback<NoticeModle>() {
            @Override
            public void onSuccess(NoticeModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\n" + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}