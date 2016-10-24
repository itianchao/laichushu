package com.sofacity.laichushu.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.widget.GlideCircleTransform;

/**
 * 图片框架工具类
 * Created by wangtong on 2016/10/18.
 */
public class GlideUitl {
    /**
     * 快速加载图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadImg(Context mContext, String path, ImageView mImageView) {
        if (!TextUtils.isEmpty(path)){
            Glide.with(mContext).load(path).centerCrop().into(mImageView);
        }else {
            Glide.with(mContext).load(R.drawable.img_default).centerCrop().into(mImageView);
        }
    }
    public static void loadImg(Context mContext, int path, ImageView mImageView) {
        if (path != 0){
            Glide.with(mContext).load(path).centerCrop().into(mImageView);
        }else {
            Glide.with(mContext).load(R.drawable.img_default).centerCrop().into(mImageView);
        }
    }
    /**
     * 指定大小加载图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param width      宽
     * @param height     高
     * @param mImageView 控件
     */
    public static void loadImg(Context mContext, String path, int width, int height, ImageView mImageView) {
        if (!TextUtils.isEmpty(path)){
            Glide.with(mContext).load(path).override(width, height).centerCrop().into(mImageView);

        }else {
            Glide.with(mContext).load(R.drawable.img_default).override(width, height).centerCrop().into(mImageView);
        }
    }
    /**
     * 快速加载圆形图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadRandImg(Context mContext, String path, ImageView mImageView) {
        if (!TextUtils.isEmpty(path)){
            Glide.with(mContext).load(path).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
        }else {
            Glide.with(mContext).load(R.drawable.img_default).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
        }
    }
}
