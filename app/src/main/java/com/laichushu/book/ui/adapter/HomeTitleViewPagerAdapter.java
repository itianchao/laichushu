package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.home.HomePresenter;

import java.util.ArrayList;

/**
 * 首页标题viewpager 的控制器
 * Created by wangtong on 2016/10/17.
 */
public class HomeTitleViewPagerAdapter extends PagerAdapter {

    private ArrayList<HomeModel.DataBean> imageList;
    private Activity mActivity;
    private HomePresenter mvpPresenter;

    public HomeTitleViewPagerAdapter(ArrayList<HomeModel.DataBean> imageList, Activity mActivity, HomePresenter mvpPresenter) {
        this.imageList = imageList;
        this.mActivity = mActivity;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public int getCount() {
        return imageList == null ? 0 : Integer.MAX_VALUE;
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
    public Object instantiateItem(final ViewGroup container, final int position) {
        final ImageView v = new ImageView(mActivity);
        HomeModel.DataBean dataBean = null;
        if (imageList.size() != 0) {
            dataBean = imageList.get(position % (imageList.size() == 0 ? 1 : imageList.size()));
            Glide.with(mActivity).load(dataBean.getUrl()).centerCrop().into(v);
        }
        final HomeModel.DataBean finalDataBean = dataBean;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(finalDataBean.getSourceId())) {
                    if (finalDataBean.getType().equals("1")) {//图书
                        mvpPresenter.getBookById(finalDataBean.getSourceId(),position % (imageList.size() == 0 ? 1 : imageList.size()));
                    }else {//活动
                        mvpPresenter.getActivityById(finalDataBean.getSourceId(),position % (imageList.size() == 0 ? 1 : imageList.size()));
                    }
                }
            }
        });
        container.addView(v);
        return v;
    }
}
