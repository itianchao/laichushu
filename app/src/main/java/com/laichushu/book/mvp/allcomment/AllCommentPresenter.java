package com.laichushu.book.mvp.allcomment;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.TopicDetailCommentList_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.Comment2_Paramet;
import com.laichushu.book.bean.netbean.SaveComment_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.AllCommentActivity;

/**
 * 全部评论
 * Created by wangtong on 2016/11/3.
 */
public class AllCommentPresenter extends BasePresenter<AllCommentView> {
    private AllCommentActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private String sourceType = ConstantValue.COMMENTBOOK_TYPE;
    private TopicDetailCommentList_Paramet paramet = new TopicDetailCommentList_Paramet("",sourceType,pageNo,pageSize,userId);

    public TopicDetailCommentList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(TopicDetailCommentList_Paramet paramet) {
        this.paramet = paramet;
    }

    //初始化构造
    public AllCommentPresenter(AllCommentView view) {
        attachView(view);
        mActivity = (AllCommentActivity) view;
    }
    public void loadAllCommentData(String sourceId){
        getParamet().setArticleId(sourceId);
        Logger.e("全部评论");
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
        Logger.e("发送评论");
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
    /**
     * 点赞 取消赞
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, final String type){
        mvpView.showLoading();
        String sourceType = "1";
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId,sourceId,sourceType,type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model,type);
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
