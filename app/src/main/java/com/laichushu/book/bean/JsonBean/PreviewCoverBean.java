package com.laichushu.book.bean.JsonBean;

import com.laichushu.book.bean.netbean.BaseModel;

/**
 * 预览模版
 * Created by wangtong on 2016/11/23.
 */

public class PreviewCoverBean extends BaseModel {
    private boolean success;
    private String errMsg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
