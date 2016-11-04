package com.sofacity.laichushu.mvp.allcomment;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.Comment_Paramet;
import com.sofacity.laichushu.bean.netbean.SaveComment_Paramet;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.AllCommentActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * 全部评论
 * Created by wangtong on 2016/11/3.
 */
public class AllCommentPresenter extends BasePresenter<AllCommentView> {
    private AllCommentActivity mActivity;
    private String pageSize = "10";
    private String pageNo = "1";
    private String userId = SharePrefManager.getUserId();
    private Comment_Paramet paramet = new Comment_Paramet("",pageSize,pageNo,userId);

    public Comment_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(Comment_Paramet paramet) {
        this.paramet = paramet;
    }

    //初始化构造
    public AllCommentPresenter(AllCommentView view) {
        attachView(view);
        mActivity = (AllCommentActivity) view;
    }
    public void loadAllCommentData(String articleId){
        getParamet().setArticleId(articleId);
        Logger.e("获取全部评论");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.articleComment(paramet), new ApiCallback<ArticleCommentModle>() {
            @Override
            public void onSuccess(ArticleCommentModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    public void loadSendCommentData(String sourceId,String content,String starLevel){
        Logger.e("获取全部评论");
        Logger.json(new Gson().toJson(paramet));
        SaveComment_Paramet paramet = new SaveComment_Paramet(sourceId,userId,content,starLevel);
        addSubscription(apiStores.saveComment(paramet), new ApiCallback<SendCommentMoudle>() {
            @Override
            public void onSuccess(SendCommentMoudle model) {
                mvpView.getSendDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
