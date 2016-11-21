package com.laichushu.book.mvp.bookcast;

import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.CollectList_Paramet;
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

    public ArticleBookList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(ArticleBookList_Paramet paramet) {
        this.paramet = paramet;
    }

    private ArticleBookList_Paramet paramet = new ArticleBookList_Paramet(userId, pageNo, pageSize);
    private int state = 1;

    //初始化构造
    public BookcastPresener(BookcastView view) {
        attachView(view);
        mActivity = (MyBookCastActivity) view;
    }

    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getArticleBookList(paramet), new ApiCallback<HomeHotModel>() {
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


    //1-图书2-服务者3-课程4-机构
    private String type = "1";

    public CollectList_Paramet getCollectionParamet() {
        return collectParamet;
    }

    public void setCollectionParamet(CollectList_Paramet collectParamet) {
        this.collectParamet = collectParamet;
    }

    private CollectList_Paramet collectParamet = new CollectList_Paramet(pageSize, pageNo, userId, type);

    public void LoadCollectionData() {
       LoggerUtil.toJson(collectParamet );
        addSubscription(apiStores.getCollectList(collectParamet), new ApiCallback<BookCastModle>() {
            @Override
            public void onSuccess(BookCastModle model) {
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
