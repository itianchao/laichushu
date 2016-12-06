package com.laichushu.book.mvp.messagecomment;

import android.text.TextUtils;
import android.widget.EditText;

import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddPerDetails_Paramet;
import com.laichushu.book.bean.netbean.BookDetails_Paramet;
import com.laichushu.book.bean.netbean.DelPerInfo_Paramet;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.MessageComment_Paramet;
import com.laichushu.book.bean.netbean.PerInformationDetails_Paramet;
import com.laichushu.book.bean.netbean.PerInformation_Paramet;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.bean.netbean.SendMsgDetails_Paramet;
import com.laichushu.book.bean.netbean.TopicDetailCommentSave_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MessageCommentDetailsActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.pickerview.lib.Province;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentPresenter extends BasePresenter<MessageCommentView> {
    private MessageCommentDetailsActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private String msgType;
    private String subType;

    //初始化构造
    public MessageCommentPresenter(MessageCommentView view) {
        attachView(view);
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public MessageComment_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MessageComment_Paramet paramet) {
        this.paramet = paramet;
    }

    public MessageCommentDetailsActivity getmActivity() {
        return mActivity;
    }

    public void setmActivity(MessageCommentDetailsActivity mActivity) {
        this.mActivity = mActivity;
    }

    private MessageComment_Paramet paramet = new MessageComment_Paramet(pageSize, pageNo, "", userId, msgType, subType);

    //获取评论
    public void LoaCommentdData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.messageCommentDetails(paramet), new ApiCallback<MessageCommentResult>() {
            @Override
            public void onSuccess(MessageCommentResult model) {
                mvpView.getMsgCommentDateSuccess(model);
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


    public BookDetails_Paramet getBookParamet() {
        return bookParamet;
    }

    public void setBookParamet(BookDetails_Paramet bookParamet) {
        this.bookParamet = bookParamet;
    }

    //获取图书详情
    private BookDetails_Paramet bookParamet = new BookDetails_Paramet(userId, "");

    public void LoadBookDetailsData(String id, final int position) {
        bookParamet.setId(id);
        LoggerUtil.toJson(bookParamet);
        addSubscription(apiStores.getBookDetailsById(bookParamet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getBookDetailsDateSuccess(model, position);
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

    public SendMsgDetails_Paramet getMsgParamet() {
        return msgParamet;
    }

    public void setMsgParamet(SendMsgDetails_Paramet msgParamet) {
        this.msgParamet = msgParamet;
    }

    //回复消息
    private SendMsgDetails_Paramet msgParamet = new SendMsgDetails_Paramet(userId, "", "", "", "");

    public void LoadSendMsgDetailsData(String receiveId, String content, String msgType, String subType) {
        msgParamet.setReceiveId(receiveId);
        msgParamet.setContent(content);
        msgParamet.setMsgType(msgType);
        msgParamet.setSubType(subType);
        LoggerUtil.toJson(msgParamet);
        addSubscription(apiStores.msgSendMsgDetails(msgParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.sendMsgDetailsDateSuccess(model);
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

    public PerInformation_Paramet getInfoParamet() {
        return infoParamet;
    }

    public void setInfoParamet(PerInformation_Paramet infoParamet) {
        this.infoParamet = infoParamet;
    }

    //私信列表
    private PerInformation_Paramet infoParamet = new PerInformation_Paramet(userId, pageNo, pageSize);

    public void LoadPerInfoDetailsData() {

        LoggerUtil.toJson(infoParamet);
        addSubscription(apiStores.getPerInformation(infoParamet), new ApiCallback<MessageCommentResult>() {
            @Override
            public void onSuccess(MessageCommentResult model) {
                mvpView.getPerInfoListDateSuccess(model);
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

    public PerInformationDetails_Paramet getInfoDetailsParamet() {
        return infoDetailsParamet;
    }

    public void setInfoDetailsParamet(PerInformationDetails_Paramet infoDetailsParamet) {
        this.infoDetailsParamet = infoDetailsParamet;
    }

    //私信详情
    private PerInformationDetails_Paramet infoDetailsParamet = new PerInformationDetails_Paramet(userId, "", "", pageNo, pageSize);

    public void LoadPerDetailsData(String senderId, String msgId) {
        infoDetailsParamet.setSenderId(senderId);
        infoDetailsParamet.setMsgId(msgId);
        LoggerUtil.toJson(infoDetailsParamet);
        addSubscription(apiStores.getPerInformationDetails(infoDetailsParamet), new ApiCallback<PerMsgInfoReward>() {
            @Override
            public void onSuccess(PerMsgInfoReward model) {
                mvpView.getPerInfoDetailsDateSuccess(model);
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

    /**
     * 发送评论
     *
     * @param sendmsgEv
     * @param
     */
    public void topicDetailCommentSave(EditText sendmsgEv, String msgId) {
        String msg = sendmsgEv.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            sendmsgEv.startAnimation(ShakeAnim.shakeAnimation(3));
            return;
        }
        LoggerUtil.e("发送评论");
        AddPerDetails_Paramet paramet = new AddPerDetails_Paramet(userId, msgId, msg);
        addSubscription(apiStores.getPerDetails(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSendDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }


    public void loadDelPerInfoDetails(String senderId, String msgId) {
        DelPerInfo_Paramet delParamet = new DelPerInfo_Paramet(senderId, msgId, userId);
        LoggerUtil.e("发送评论");
        AddPerDetails_Paramet paramet = new AddPerDetails_Paramet(userId, msgId, msgId);
        addSubscription(apiStores.getDelPerInfoDetails(delParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDelPerIdfoDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }

}
