package com.laichushu.book.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.BookDetailsResult;
import com.laichushu.book.mvp.home.HomeHotModel;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by PCPC on 2016/12/9.
 */

public class ModelUtils {
    /**
     * 把modelA对象的属性值赋值给bClass对象的属性。
     *
     * @param modelA
     * @param bClass
     * @return
     */

    public static <A, B> B modelA2B(A modelA, Class<B> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            B instanceB = gson.fromJson(gsonA, bClass);

            Log.d(TAG, "modelA2B A=" + modelA.getClass() + " B=" + bClass + " 转换后=" + instanceB);
            return instanceB;
        } catch (Exception e) {
            Log.e(TAG, "modelA2B Exception=" + modelA.getClass() + " " + bClass + " " + e.getMessage());
            return null;
        }
    }

    public static <B> B modelA22B(Object modelA, Class<B> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            B instanceB = gson.fromJson(gsonA, bClass);

            Log.d(TAG, "modelA2B A=" + modelA.getClass() + " B=" + bClass + " 转换后=" + instanceB);
            return instanceB;
        } catch (Exception e) {
            Log.e(TAG, "modelA2B Exception=" + modelA.getClass() + " " + bClass + " " + e.getMessage());
            return null;
        }
    }

    public static HomeHotModel.DataBean bean2HotBean(BookDetailsModle result) {
        HomeHotModel.DataBean dataBean = new HomeHotModel.DataBean();
        dataBean.setActivityName(result.getData().getArticleName());
        dataBean.setActivityId(result.getData().getArticleId());
        dataBean.setActivityName(result.getData().getArticleName());
        dataBean.setActivityId(result.getData().getArticleId());
        dataBean.setAuthorId(result.getData().getAuthorId());
        dataBean.setAuthorName(result.getData().getAuthorName());
        dataBean.setStatus(result.getData().getStatus());
        dataBean.setFreezeStatus(result.getData().getFreezeStatus());
        dataBean.setWordNum(result.getData().getWordNum());
        dataBean.setSubscribeNum(result.getData().getSubscribeNum());
        dataBean.setBrowseNum(result.getData().getBrowseNum());
        dataBean.setCommentNum(result.getData().getCommentNum());
        dataBean.setAwardNum(result.getData().getAwardNum());
        dataBean.setLevel(result.getData().getLevel());
        dataBean.setScore(result.getData().getScore());
        dataBean.setAwardMoney(result.getData().getAwardMoney());
        dataBean.setCreateDate(result.getData().getCreateDate());
        dataBean.setIsPurchase(result.getData().isIsPurchase());
        dataBean.setIsSubscribe(result.getData().isIsSubscribe());
        dataBean.setCollect(result.getData().isIsCollect());
        dataBean.setCoverUrl(result.getData().getCoverUrl());
        dataBean.setCoverName(result.getData().getCoverName());
        dataBean.setTopCategoryId(result.getData().getTopCategoryId());
        dataBean.setTopCategoryName(result.getData().getTopCategoryName());
        dataBean.setIntroduce(result.getData().getIntroduce());
        dataBean.setFreezeStatus(result.getData().getFreezeStatus());
        dataBean.setScore(result.getData().getScore());
        dataBean.setUpdateDate(result.getData().getUpdateDate());
        return dataBean;
    }
}
