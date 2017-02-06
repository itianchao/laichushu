package com.laichushu.book.bean.JsonBean;

import com.laichushu.book.bean.netbean.BaseModel;

/**
 * 打赏结果
 * Created by wangtong on 2016/11/8.
 */

public class RewardResult extends BaseModel {
    private boolean success;
    private String errMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
