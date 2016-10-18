package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.otherbean.HomeHotImgBean;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页最热viewpager 的控制器
 * Created by wangtong on 2016/10/17.
 */
public class HomeHotViewPagerAdapter extends PagerAdapter {

    private ArrayList<HomeHotImgBean> imageList;
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
        TextView title1Tv = (TextView) itemView.findViewById(R.id.tv_title1);
        TextView title2Tv = (TextView) itemView.findViewById(R.id.tv_title2);
        TextView title3Tv = (TextView) itemView.findViewById(R.id.tv_title3);
        TextView name1Tv = (TextView) itemView.findViewById(R.id.tv_name1);
        TextView name2Tv = (TextView) itemView.findViewById(R.id.tv_name2);
        TextView name3Tv = (TextView) itemView.findViewById(R.id.tv_name3);

        HomeHotImgBean homeHotImgBean = imageList.get(position);
        //设置图片
        if (!TextUtils.isEmpty(homeHotImgBean.getFristImg())) {
            GlideUitl.loadImg(mActivity, homeHotImgBean.getFristImg(), hot1Iv);
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getSecondImg())) {
            GlideUitl.loadImg(mActivity, homeHotImgBean.getSecondImg(), hot2Iv);
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getThirdImg())) {
            GlideUitl.loadImg(mActivity,homeHotImgBean.getThirdImg(),hot3Iv);
        }
        //设置书名
        if (!TextUtils.isEmpty(homeHotImgBean.getFristTitle())) {
            title1Tv.setText(homeHotImgBean.getFristTitle());
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getSecondTitle())) {
            title2Tv.setText(homeHotImgBean.getSecondTitle());
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getThirdTitle())) {
            title3Tv.setText(homeHotImgBean.getThirdTitle());
        }
        //设置作者名
        if (!TextUtils.isEmpty(homeHotImgBean.getFristName())) {
            name1Tv.setText(homeHotImgBean.getFristName());
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getSecondName())) {
            name2Tv.setText(homeHotImgBean.getSecondName());
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getThirdName())) {
            name3Tv.setText(homeHotImgBean.getThirdName());
        }
        container.addView(itemView);
        return itemView;
    }
}
