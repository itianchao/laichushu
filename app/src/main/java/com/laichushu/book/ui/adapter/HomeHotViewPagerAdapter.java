package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.bean.otherbean.HomeHotImgBean;
import com.laichushu.book.R;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

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
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = UIUtil.inflate(R.layout.item_home_hot_child);
        ImageView hot1Iv = (ImageView) itemView.findViewById(R.id.iv_hot1);
        ImageView hot2Iv = (ImageView) itemView.findViewById(R.id.iv_hot2);
        ImageView hot3Iv = (ImageView) itemView.findViewById(R.id.iv_hot3);
        ImageView status1Iv = (ImageView) itemView.findViewById(R.id.iv_status1);
        ImageView status2Iv = (ImageView) itemView.findViewById(R.id.iv_status2);
        ImageView status3Iv = (ImageView) itemView.findViewById(R.id.iv_status3);
        TextView title1Tv = (TextView) itemView.findViewById(R.id.tv_title1);
        TextView title2Tv = (TextView) itemView.findViewById(R.id.tv_title2);
        TextView title3Tv = (TextView) itemView.findViewById(R.id.tv_title3);
        TextView name1Tv = (TextView) itemView.findViewById(R.id.tv_name1);
        TextView name2Tv = (TextView) itemView.findViewById(R.id.tv_name2);
        TextView name3Tv = (TextView) itemView.findViewById(R.id.tv_name3);

        final HomeHotImgBean homeHotImgBean = imageList.get(position);
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
        hot1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转图书详情
                HomeHotModel.DataBean fristBean = imageList.get(position).getFristBean();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",fristBean);
                bundle.putString("pageMsg", "首页热门推荐");
                UIUtil.openActivity(mActivity, BookDetailActivity.class,bundle);
            }
        });
        hot2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转图书详情
                HomeHotModel.DataBean secondBean = imageList.get(position).getSecondBean();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",secondBean);
                bundle.putString("pageMsg", "首页热门推荐");
                UIUtil.openActivity(mActivity, BookDetailActivity.class,bundle);
            }
        });
        hot3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转图书详情
                HomeHotModel.DataBean thirdtBean = imageList.get(position).getThirdtBean();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",thirdtBean);
                bundle.putString("pageMsg", "首页热门推荐");
                UIUtil.openActivity(mActivity, BookDetailActivity.class,bundle);
            }
        });
        //设置书名
        if (!TextUtils.isEmpty(homeHotImgBean.getFristTitle())) {
            title1Tv.setText(homeHotImgBean.getFristTitle());
            switch(homeHotImgBean.getFristBean().getStatus()){
                case "1":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue2, status1Iv);
                    break;
                case "2":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue3, status1Iv);
                    break;
                default:
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue1, status1Iv);
                    break;
            }
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getSecondTitle())) {
            title2Tv.setText(homeHotImgBean.getSecondTitle());
            switch(homeHotImgBean.getSecondBean().getStatus()){
                case "1":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue2, status2Iv);
                    break;
                case "2":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue3, status2Iv);
                    break;
                default:
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue1, status2Iv);
                    break;
            }
        }
        if (!TextUtils.isEmpty(homeHotImgBean.getThirdTitle())) {
            title3Tv.setText(homeHotImgBean.getThirdTitle());
            switch(homeHotImgBean.getThirdtBean().getStatus()){
                case "1":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue2, status3Iv);
                    break;
                case "2":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue3, status3Iv);
                    break;
                default:
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue1, status3Iv);
                    break;
            }
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
