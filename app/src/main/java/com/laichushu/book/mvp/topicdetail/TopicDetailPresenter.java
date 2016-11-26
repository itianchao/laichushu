package com.laichushu.book.mvp.topicdetail;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ReCommentList_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.bean.netbean.TopicDetailCommentList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.TopicDetilActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

/**
 * 登录 presenter
 * Created by wangtong on 2016/10/12.
 */
public class TopicDetailPresenter extends BasePresenter<TopicDetailView> {
    private TopicDetilActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private TopicDetailCommentList_Paramet paramet = new TopicDetailCommentList_Paramet( userId, pageNo, pageSize);

    //初始化构造
    public TopicDetailPresenter(TopicDetailView view) {
        attachView(view);
        mActivity = (TopicDetilActivity) view;
    }
    public void loadCommentData(String topicId) {
        getParamet().setTopicId(topicId);
        Logger.e("获取全部评论");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.topicDetailCommentList(paramet), new ApiCallback<TopicdetailModel>() {
            @Override
            public void onSuccess(TopicdetailModel model) {
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

    public TopicDetailCommentList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(TopicDetailCommentList_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 点赞 取消赞
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, final String type){
        mvpView.showLoading();
        ScoreLike_Paramet paramet = new ScoreLike_Paramet(sourceId,userId,type);
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