
package com.laichushu.book.bean.JsonBean;

import com.laichushu.book.mvp.home.HomeHotModel;

/**
 * 首页轮播图 搜索结果
 * Created by wangtong on 2016/12/17.
 */

public class HomeTitleBean {
    private boolean success;

    private String errMsg;
    /**
     * data : {"activityId":"24","activityName":"出版机构来合作吧","applyAmount":8,"beginTime":"2016-12-10","creator":"1","detail":"坚持\u201c先授权，后传播\u201d的理念，在电子图书内容领域占有明显优势，取得国内近三百家出版机构的合法授权，拥有数字内容二十余万种。","endTime":"2016-12-31","imgUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLphGABV-6AAAx0RvBjB8311.jpg","isParticipate":false,"status":"4"}
     */

    private HomeHotModel.DataBean data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HomeHotModel.DataBean getData() {
        return data;
    }

    public void setData(HomeHotModel.DataBean data) {
        this.data = data;
    }
}

