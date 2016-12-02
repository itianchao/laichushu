package com.laichushu.book.mvp.topicdetail;

import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.bean.netbean.TopicDetailCommentList_Paramet;
import com.laichushu.book.bean.netbean.TopicDetailCommentSave_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.TopicDetilActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
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
    private TopicDetailCommentList_Paramet paramet = new TopicDetailCommentList_Paramet("", "", pageNo, pageSize);

    //初始化构造
    public TopicDetailPresenter(TopicDetailView view) {
        attachView(view);
        mActivity = (TopicDetilActivity) view;
    }

    //获取全部评论
    public void loadCommentData(String sourceId, String sourceType) {
        getParamet().setSourceId(sourceId);
        getParamet().setSourceType(sourceType);
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

    public TopicDetilActivity getmActivity() {
        return mActivity;
    }

    public void setmActivity(TopicDetilActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 2016年12月2日14:12:12
     * 话题点赞 取消赞
     *
     * @param sourceId
     * @param type
     */
    public void loadLikeSaveData(String sourceId, String sourceType, final String type) {
        mvpView.showLoading();
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId,sourceId,sourceType, type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveTopicDyLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model, type);
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
     * 发送评论
     *
     * @param sendmsgEv
     * @param
     */
    public void topicDetailCommentSave(EditText sendmsgEv, String sourceId, String sourceType) {
        String msg = sendmsgEv.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            sendmsgEv.startAnimation(ShakeAnim.shakeAnimation(3));
            return;
        }
        LoggerUtil.e("发送评论");
        mvpView.showLoading();
        TopicDetailCommentSave_Paramet paramet = new TopicDetailCommentSave_Paramet(sourceId, userId, msg, sourceType);
        addSubscription(apiStores.topicDetailCommentSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSendDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}