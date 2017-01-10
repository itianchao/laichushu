package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laichushu.book.mvp.find.FindPresenter;
import com.laichushu.book.mvp.home.homelist.HomeModel;

import java.util.ArrayList;

/**
 * Created by PCPC on 2016/12/19.
 */

public class FindTitleViewPagerAdapter extends PagerAdapter {

    private ArrayList<HomeModel.DataBean> imageList;
    private Activity mActivity;
    private FindPresenter mvpPresenter;

    public FindTitleViewPagerAdapter(ArrayList<HomeModel.DataBean> imageList, Activity mActivity, FindPresenter mvpPresenter) {
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
                if (finalDataBean.getType().equals("1")) {//图书
//                    mvpPresenter.getBookById(finalDataBean.getSourceId(),position % (imageList.size() == 0 ? 1 : imageList.size()));
                }else {//活动
//                    mvpPresenter.getActivityById(finalDataBean.getSourceId(),position % (imageList.size() == 0 ? 1 : imageList.size()));
                }
            }
        });
        container.addView(v);
        return v;
    }
}
