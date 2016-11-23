package com.laichushu.book.mvp.write;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.ArticleVote_Paramet;
import com.laichushu.book.bean.netbean.DeleteNewBook_Paramet;
import com.laichushu.book.bean.netbean.PublishNewBook_Paramet;
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

    /**
     * 删除
     * @param articleId
     */
    public void deleteBook(String articleId, final int position) {
        mvpView.showLoading();
        LoggerUtil.e("删除新书");
        DeleteNewBook_Paramet paramet = new DeleteNewBook_Paramet(articleId,ConstantValue.USERID);
        addSubscription(apiStores.deleteNewBook(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.deleteNewBook(model,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    /**
     * 投稿
     * @param articleId
     * @param pressId
     */
    public void voteBook(String articleId, String pressId) {
        mvpView.showLoading();
        LoggerUtil.e("投稿");
        ArticleVote_Paramet paramet = new ArticleVote_Paramet(articleId,ConstantValue.USERID,pressId);
        addSubscription(apiStores.articleVote(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.articleVote(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail3("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    /**
     * 发表
     * @param articleId
     */
    public void publishNewBook(String articleId) {
        mvpView.showLoading();
        LoggerUtil.e("发表");
        PublishNewBook_Paramet paramet = new PublishNewBook_Paramet(articleId);
        addSubscription(apiStores.publishNewBook(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.publishNewBook(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail4("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
