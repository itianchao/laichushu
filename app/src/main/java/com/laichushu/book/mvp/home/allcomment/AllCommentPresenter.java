package com.laichushu.book.mvp.home.allcomment;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.CourseScoreList_Paramet;
import com.laichushu.book.bean.netbean.CourseScoreSave_Paramet;
import com.laichushu.book.bean.netbean.TopicDetailCommentList_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.SaveComment_Paramet;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.retrofit.ApiCallback;

/**
 * 全部评论
 * Created by wangtong on 2016/11/3.
 */
public class AllCommentPresenter extends BasePresenter<AllCommentView> {

    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String pageNo2 = "1";
    private String userId = ConstantValue.USERID;
    private String sourceType = ConstantValue.COMMENTBOOK_TYPE;
    private String sourceType2 = ConstantValue.COLLECTSERVICE_TYPE;//4 课程
    private TopicDetailCommentList_Paramet paramet = new TopicDetailCommentList_Paramet("","",sourceType,pageNo,pageSize,userId);
    private CourseScoreList_Paramet paramet2 = new CourseScoreList_Paramet("",sourceType2,pageNo2,pageSize,userId);

    public TopicDetailCommentList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(TopicDetailCommentList_Paramet paramet) {
        this.paramet = paramet;
    }

    public CourseScoreList_Paramet getParamet2() {
        return paramet2;
    }

    public void setParamet2(CourseScoreList_Paramet paramet2) {
        this.paramet2 = paramet2;
    }

    //初始化构造
    public AllCommentPresenter(AllCommentView view) {
        attachView(view);
    }

    /**
     * 图书全部评论
     * @param sourceId
     */
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

    /**
     * 课程全部评论
     * @param sourceId
     */
    public void loadAllCommentData(int sourceId){
        getParamet2().setSourceId(sourceId+"");
        Logger.e("全部评论");
        Logger.json(new Gson().toJson(paramet2));
        addSubscription(apiStores.getCourseScoreList(paramet2), new ApiCallback<ArticleCommentModle>() {
            @Override
            public void onSuccess(ArticleCommentModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("全部评论"+"code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    /**
     * 发送图书评分
     * @param sourceId
     * @param content
     * @param starLevel
     */
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
                mvpView.getDataFail("发送评论"+"code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    /**
    * 发送课程评分
    * @param sourceId
    * @param content
    * @param starLevel
    */
    public void loadSendCommentData(int sourceId,String content,String starLevel){
        Logger.e("发送评论");
        CourseScoreSave_Paramet paramet = new CourseScoreSave_Paramet(sourceId+"",sourceType2,starLevel,content,userId);
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.courseScoreSave(paramet), new ApiCallback<SendCommentMoudle>() {
            @Override
            public void onSuccess(SendCommentMoudle model) {
                mvpView.getSendDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("发送评论"+"code+" + code + "/msg:" + msg);
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
     * @param position
     */
    public void saveScoreLikeData(String sourceId, final String type, final int position){
        mvpView.showLoading();
        String sourceType = "1";
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId,sourceId,sourceType,type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.hideLoading();
                mvpView.SaveScoreLikeData(model,type,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.hideLoading();
                mvpView.getDataFail("点赞"+"code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

}
