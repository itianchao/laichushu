package com.laichushu.book.mvp.entry.regist;

import com.laichushu.book.bean.netbean.BaseModel;

/**
 * 注册数据模型
 * Created by wangtong on 2016/10/12.
 */
public class RegistModel extends BaseModel {

    /**
     * success : 请求成功
     * errMsg : 错误信息
     */

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
