package com.laichushu.book.mvp.messagecomment;

import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.MessageComment_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MessageCommentDetailsActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentPresenter extends BasePresenter<MessageCommentView> {
    private MessageCommentDetailsActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private String msgType = "3";
    private String subType = "5";

    //初始化构造
    public MessageCommentPresenter(MessageCommentView view) {
        attachView(view);
        mActivity = (MessageCommentDetailsActivity) view;
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
}
