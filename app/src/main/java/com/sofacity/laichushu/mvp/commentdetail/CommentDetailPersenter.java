package com.sofacity.laichushu.mvp.commentdetail;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.netbean.ReCommentList_Paramet;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.CommentDetailActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * 评价详情
 * Created by wangtong on 2016/11/4.
 */
public class CommentDetailPersenter extends BasePresenter<CommentDetailView> {

    private CommentDetailActivity mActivity;
    private String pageSize = "10";
    private String pageNo = "1";
    private String userId = SharePrefManager.getUserId();
    private ReCommentList_Paramet paramet = new ReCommentList_Paramet("", userId, pageNo, pageSize);

    public CommentDetailPersenter(CommentDetailView view) {
        attachView(view);
        this.mActivity = (CommentDetailActivity) view;
    }

    public void loadCommentData(String commentId) {
        getParamet().setCommentId(commentId);
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
}
