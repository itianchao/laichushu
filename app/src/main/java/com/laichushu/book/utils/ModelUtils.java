package com.laichushu.book.utils;

import android.text.TextUtils;
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
        if (!TextUtils.isEmpty(result.getData().getArticleData().getArticleName())) {
            dataBean.setActivityName(result.getData().getArticleData().getArticleName());
        }
        if (!TextUtils.isEmpty(result.getData().getArticleData().getName())) {
            dataBean.setActivityName(result.getData().getArticleData().getName());
        }
        dataBean.setActivityId(result.getData().getArticleData().getArticleId());
        dataBean.setArticleId(result.getData().getArticleData().getArticleId());
        dataBean.setActivityName(result.getData().getArticleData().getArticleName());
        dataBean.setActivityId(result.getData().getArticleData().getArticleId());
        dataBean.setAuthorId(result.getData().getArticleData().getAuthorId());
        dataBean.setAuthorName(result.getData().getArticleData().getAuthorName());
        dataBean.setStatus(result.getData().getArticleData().getStatus());
        dataBean.setFreezeStatus(result.getData().getArticleData().getFreezeStatus());
        dataBean.setWordNum(result.getData().getArticleData().getWordNum());
        dataBean.setSubscribeNum(result.getData().getArticleData().getSubscribeNum());
        dataBean.setBrowseNum(result.getData().getArticleData().getBrowseNum());
        dataBean.setCommentNum(result.getData().getArticleData().getCommentNum());
        dataBean.setAwardNum(result.getData().getArticleData().getAwardNum());
        dataBean.setLevel(result.getData().getArticleData().getLevel());
        dataBean.setScore(result.getData().getArticleData().getScore());
        dataBean.setAwardMoney(result.getData().getArticleData().getAwardMoney());
        dataBean.setIsPurchase(result.getData().getArticleData().isIsPurchase());
        dataBean.setIsSubscribe(result.getData().getArticleData().isIsSubscribe());
        dataBean.setCollect(result.getData().getArticleData().isIsCollect());
        dataBean.setCoverUrl(result.getData().getArticleData().getCoverUrl());
        dataBean.setCoverName(result.getData().getArticleData().getCoverName());
        dataBean.setTopCategoryId(result.getData().getArticleData().getTopCategoryId());
        dataBean.setTopCategoryName(result.getData().getArticleData().getTopCategoryName());
        dataBean.setIntroduce(result.getData().getArticleData().getIntroduce());
        dataBean.setFreezeStatus(result.getData().getArticleData().getFreezeStatus());
        dataBean.setScore(result.getData().getArticleData().getScore());
        dataBean.setUpdateDate(result.getData().getArticleData().getUpdateDate());
        return dataBean;
    }
}
