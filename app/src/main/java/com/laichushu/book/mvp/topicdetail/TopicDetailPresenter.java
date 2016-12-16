package com.laichushu.book.mvp.topicdetail;

import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddActPerMsgInfo_Paramet;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
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
    private String sourceType = ConstantValue.TOPICCOMMENT_TYPE;

    //初始化构造
    public TopicDetailPresenter(TopicDetailView view) {
        attachView(view);
        mActivity = (TopicDetilActivity) view;
    }

    public TopicDetailCommentList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(TopicDetailCommentList_Paramet paramet) {
        this.paramet = paramet;
    }

    TopicDetailCommentList_Paramet paramet = new TopicDetailCommentList_Paramet("", "","", pageNo, pageSize, userId);
    public void loadCommentData(String topicId,String sourceType) {
        getParamet().setSourceId(topicId);
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


    /**
     * 点赞 取消赞
     *
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, String sourceType, final String type) {
        mvpView.showLoading();
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId, sourceId, sourceType, type);
        Logger.e("点赞 取消赞");
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
     * 活动页面发送私信
     *
     * @param sendmsgEv
     * @param accepterId
     */
    public void topicDetailCommentSave(EditText sendmsgEv, String accepterId,String sourceType) {
        LoggerUtil.e("活动页面发送私信");
        String msg = sendmsgEv.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            sendmsgEv.startAnimation(ShakeAnim.shakeAnimation(3));
            return;
        }
        mvpView.showLoading();
        TopicDetailCommentSave_Paramet paramet = new TopicDetailCommentSave_Paramet(accepterId, userId,msg,sourceType);
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

    //话题收藏
    public void loadCollectSaveDate(String sourceId, String sourceType, final String type) {
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        LoggerUtil.toJson(collectSave);
        addSubscription(apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveCollectSuccess(model, type);
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