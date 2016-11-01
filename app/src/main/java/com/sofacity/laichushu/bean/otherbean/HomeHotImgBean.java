package com.sofacity.laichushu.bean.otherbean;

import com.sofacity.laichushu.mvp.home.HomeHotModel;

/**
 * 首页轮最热轮播图
 * Created by wangtong on 2016/10/18.
 */
public class HomeHotImgBean {

    public String fristImg;
    public String fristTitle;
    public String fristName;
    private HomeHotModel.DataBean fristBean;

    public String secondImg;
    public String secondTitle;
    public String secondName;
    private HomeHotModel.DataBean secondBean;

    public String thirdImg;
    public String thirdTitle;
    public String thirdName;
    private HomeHotModel.DataBean thirdtBean;


    public String getFristImg() {
        return fristImg;
    }

    public void setFristImg(String fristImg) {
        this.fristImg = fristImg;
    }

    public String getFristTitle() {
        return fristTitle;
    }

    public void setFristTitle(String fristTitle) {
        this.fristTitle = fristTitle;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getSecondImg() {
        return secondImg;
    }

    public void setSecondImg(String secondImg) {
        this.secondImg = secondImg;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdImg() {
        return thirdImg;
    }

    public void setThirdImg(String thirdImg) {
        this.thirdImg = thirdImg;
    }

    public String getThirdTitle() {
        return thirdTitle;
    }

    public void setThirdTitle(String thirdTitle) {
        this.thirdTitle = thirdTitle;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setFristBean(HomeHotModel.DataBean fristBean) {
        this.fristBean = fristBean;
    }

    public HomeHotModel.DataBean getFristBean() {
        return fristBean;
    }

    public void setSecondBean(HomeHotModel.DataBean secondBean) {
        this.secondBean = secondBean;
    }

    public HomeHotModel.DataBean getSecondBean() {
        return secondBean;
    }

    public void setThirdtBean(HomeHotModel.DataBean thirdtBean) {
        this.thirdtBean = thirdtBean;
    }

    public HomeHotModel.DataBean getThirdtBean() {
        return thirdtBean;
    }
}
