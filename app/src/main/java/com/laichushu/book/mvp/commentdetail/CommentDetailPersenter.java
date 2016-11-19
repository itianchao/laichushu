package com.laichushu.book.mvp.commentdetail;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ReCommentList_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CommentDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.orhanobut.logger.Logger;

/**
 * 评价详情
 * Created by wangtong on 2016/11/4.
 */
public class CommentDetailPersenter extends BasePresenter<CommentDetailView> {

    private CommentDetailActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private ReCommentList_Paramet paramet = new ReCommentList_Paramet("", userId, pageNo, pageSize);

    public CommentDetailPersenter(CommentDetailView view) {
        attachView(view);
        this.mActivity = (CommentDetailActivity) view;
    }

    public void loadCommentData(String commentId) {
        getParamet().setScoreId(commentId);
        Logger.e("获取全部评论");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.CommentList(paramet), new ApiCallback<CommentDetailModle>() {
            @Override
            public void onSuccess(CommentDetailModle model) {
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

    public ReCommentList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(ReCommentList_Paramet paramet) {
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
