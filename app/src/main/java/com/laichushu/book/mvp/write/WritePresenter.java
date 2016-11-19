package com.laichushu.book.mvp.write;

import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 写书 presenter
 * Created by wangtong on 2016/11/16.
 */
public class WritePresenter extends BasePresenter<WriteView> {

    private String pageNo = "1";

    //初始化构造
    public WritePresenter(WriteView view) {
        attachView(view);
    }

    public void getArticleBookList() {
        LoggerUtil.e("获取创作列表");
        ArticleBookList_Paramet paramet = new ArticleBookList_Paramet(ConstantValue.USERID, pageNo, ConstantValue.PAGESIZE1);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getArticleBookList(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
