package com.laichushu.book.mvp.messagecomment;

import com.laichushu.book.bean.netbean.MessageCommentResult;

/**
 * Created by PCPC on 2016/11/28.
 */

public interface MessageCommentView {
    void getMsgCommentDateSuccess(MessageCommentResult model);
    void getDataFail(String msg);
}
