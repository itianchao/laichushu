package com.laichushu.book.mvp.bookcast;

import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.CollectList_Paramet;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.bean.netbean.MyHomeModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/11/21.
 */

public class BookcastPresener extends BasePresenter<BookcastView> {
    private MyBookCastActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    public MyArticBooklist_paramet getParamet() {
        return paramet;
    }

    public void setParamet(MyArticBooklist_paramet paramet) {
        this.paramet = paramet;
    }

    private MyArticBooklist_paramet paramet = new MyArticBooklist_paramet(userId, pageNo, pageSize,"");

    public MyArticBooklist_paramet getParamet2() {
        return paramet2;
    }

    public void setParamet2(MyArticBooklist_paramet paramet2) {
        this.paramet2 = paramet2;
    }

    private MyArticBooklist_paramet paramet2 = new MyArticBooklist_paramet(userId, pageNo, pageSize,"1");
    private int state = 1;
    private int type=1;
    //初始化构造
    public BookcastPresener(BookcastView view) {
        attachView(view);
        mActivity = (MyBookCastActivity) view;
    }

    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getArticleBookListScan(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getDataSuccess(model);
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
    public void LoadCollectionData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getCollectList(paramet2), new ApiCallback<HomeHotModel>() {
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



}
