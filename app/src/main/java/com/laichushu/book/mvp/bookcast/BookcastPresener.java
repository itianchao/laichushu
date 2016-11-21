package com.laichushu.book.mvp.bookcast;

import com.laichushu.book.bean.netbean.ActivityList_Paramet;
import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.HomeAllBook_Paramet;
import com.laichushu.book.bean.netbean.HomeSearch_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.home.HomeView;
import com.laichushu.book.mvp.homesearch.HomeSearchView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.HomeSearchActivity;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.ui.base.BasePresenter;

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

    private ArticleBookList_Paramet  paramet = new ArticleBookList_Paramet(userId, pageSize, pageNo);
    private int state = 1;
    //初始化构造
    public BookcastPresener(BookcastView view) {
        attachView(view);
        mActivity = (MyBookCastActivity) view;
    }
    public void LoadData(){
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
}
