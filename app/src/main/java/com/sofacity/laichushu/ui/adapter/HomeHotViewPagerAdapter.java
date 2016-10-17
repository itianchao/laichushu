package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页最热viewpager 的控制器
 * Created by wangtong on 2016/10/17.
 */
public class HomeHotViewPagerAdapter extends PagerAdapter {

    private ArrayList<String> imageList;
    private Activity mActivity;

    public HomeHotViewPagerAdapter(ArrayList imageList, Activity mActivity) {
        this.imageList = imageList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return imageList == null ? 0 : imageList.size();
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
        View itemView = UIUtil.inflate(R.layout.item_home_hot_child);
        ImageView hot1Iv = (ImageView) itemView.findViewById(R.id.iv_hot1);
        ImageView hot2Iv = (ImageView) itemView.findViewById(R.id.iv_hot2);
        ImageView hot3Iv = (ImageView) itemView.findViewById(R.id.iv_hot3);

        if (position % 3 == 0) {
            Glide.with(mActivity).load(imageList.get(position)).centerCrop().into(hot1Iv);
        } else if (position % 3 == 1) {
            Glide.with(mActivity).load(imageList.get(position)).centerCrop().into(hot2Iv);
        } else {
            Glide.with(mActivity).load(imageList.get(position)).centerCrop().into(hot3Iv);
        }
        //非零 3的倍数加入、最后一个加入
        if (getCount()!=0 || position!= 0 && position % 3 == 0||getCount()-position == 0) {
            container.addView(itemView);
        }
        return itemView;
    }
}
