package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sofacity.laichushu.mvp.home.HomeModel;

import java.util.ArrayList;

/**
 * 首页标题viewpager 的控制器
 * Created by wangtong on 2016/10/17.
 */
public class HomeTitleViewPagerAdapter extends PagerAdapter {

    private ArrayList<HomeModel.DataBean> imageList;
    private Activity mActivity;

    public HomeTitleViewPagerAdapter(ArrayList<HomeModel.DataBean> imageList, Activity mActivity) {
        this.imageList = imageList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return imageList==null?0:Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView v = new ImageView(mActivity);
        HomeModel.DataBean dataBean = imageList.get(position % imageList.size());
        Glide.with(mActivity).load(dataBean.getUrl()).centerCrop().into(v);
        container.addView(v);
        return v;
    }
}
