package com.laichushu.book.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laichushu.book.ui.widget.GlideCircleTransform;
import com.laichushu.book.R;
import com.laichushu.book.ui.widget.RoundedCornersTransformation;

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
        if (!TextUtils.isEmpty(path)) {
            Glide.with(mContext).load(path).asBitmap().fitCenter().error(R.drawable.img_default).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.img_default).asBitmap().fitCenter().into(mImageView);
        }
    }

    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    public static void loadImg(Context mContext, int path, ImageView mImageView) {
        if (path != 0) {
            Glide.with(mContext).load(path).fitCenter().error(R.drawable.img_default).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.img_default).fitCenter().into(mImageView);
        }
    }

    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    public static void loadImg2(Context mContext, int path, ImageView mImageView) {
        if (path != 0) {
            Glide.with(mContext).load(path).fitCenter().error(R.drawable.img_default).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.img_default).fitCenter().into(mImageView);
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
        if (!TextUtils.isEmpty(path)) {


            Glide.with(mContext).load(path).override(width, height).centerCrop().into(mImageView);

        } else {
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
        if (!TextUtils.isEmpty(path)) {
            Glide.with(mContext).load(path).asBitmap().fitCenter().transform(new GlideCircleTransform(mContext)).error(R.drawable.icon_percentre_defhead2x).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.icon_percentre_defhead2x).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
        }
    }

    public static void loadRandImgLocal(Context mContext, int path, ImageView mImageView) {
        if (0 != path) {
            Glide.with(mContext).load(path).asBitmap().fitCenter().transform(new GlideCircleTransform(mContext)).error(R.drawable.icon_percentre_defhead2x).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.icon_percentre_defhead2x).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
        }
    }

    /**
     * 加载失败默认图片
     *
     * @param mContext
     * @param path
     * @param mImageView
     * @param loadErrorImage
     */
    public static void loadRandImg(Context mContext, String path, ImageView mImageView, int loadErrorImage) {
        if (!TextUtils.isEmpty(path)) {
            Glide.with(mContext).load(path).asBitmap().fitCenter().transform(new GlideCircleTransform(mContext)).error(loadErrorImage).into(mImageView);
        } else {
            Glide.with(mContext).load(loadErrorImage).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
        }
    }

    /**
     * 加载失败默认图片
     *
     * @param mContext
     * @param path
     * @param mImageView
     * @param loadErrorImage
     */
    public static void loadCornersImg(Context mContext, int path,int radius, ImageView mImageView, int loadErrorImage) {
        if (0 != path) {
            //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
            Glide.with(mContext).load(path).bitmapTransform(new RoundedCornersTransformation(mContext, radius, 0, RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(mImageView);
        } else {
            Glide.with(mContext).load(loadErrorImage).bitmapTransform(new RoundedCornersTransformation(mContext, radius, 0, RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(mImageView);
        }
    }

    /**
     * 加载失败默认图片
     *
     * @param mContext
     * @param path
     * @param mImageView
     * @param
     */
    public static void loadCornersImg(Context mContext,int radius, String path, ImageView mImageView) {
        if (!TextUtils.isEmpty(path)) {
            //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
            Glide.with(mContext).load(path).bitmapTransform(new RoundedCornersTransformation(mContext, radius, 0, RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.icon_percentre_defhead2x).bitmapTransform(new RoundedCornersTransformation(mContext, radius, 0, RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(mImageView);
        }
    }
}
