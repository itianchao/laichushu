package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.laichushu.book.utils.GlideUitl;

import java.util.ArrayList;

/**
 * 引导页viewpager 的控制器
 * Created by wangtong on 2016/10/11.
 */
public class GuideAdapter extends PagerAdapter {

    private ArrayList<Integer> imageList;
    private Activity mActivity;

    public GuideAdapter(ArrayList imageList,Activity mActivity) {
        this.imageList = imageList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return imageList==null?0:imageList.size();
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
        GlideUitl.loadImg(mActivity,imageList.get(position),v);
        container.addView(v);
        return v;
    }
}