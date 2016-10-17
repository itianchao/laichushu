package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页下拉刷新加载更多 recycler
 * Created by wt on 2016/10/17.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private Activity mActivity;
    private ArrayList mData;
    private ArrayList mHotData;

    public HomeRecyclerAdapter(ArrayList mData, Activity mActivity, ArrayList mHotData) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mHotData = mHotData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;
    private final static int TYPE3 = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE1;
        } else if (position == 1) {
            return TYPE2;
        } else {
            return TYPE3;
        }
    }

    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        ViewHolder holder = null;
        switch(viewType) {
            case TYPE1:
                itemView = UIUtil.inflate(R.layout.item_home_hot);
                holder = new ViewHolder1(itemView);
                break;
            case TYPE2:
                itemView = UIUtil.inflate(R.layout.item_home_bar);
                holder = new ViewHolder2(itemView);
                break;
            case TYPE3:
                itemView = UIUtil.inflate(R.layout.item_home_book);
                holder = new ViewHolder3(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            ((ViewHolder1)holder).hotVp.setAdapter(new HomeHotViewPagerAdapter(mHotData,mActivity));
        } else if (position == 1) {

        } else {

        }
    }
    //base
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    //最热
    public class ViewHolder1 extends ViewHolder {

        private ImageView pointIv;
        private LinearLayout ll_container;
        private ViewPager hotVp;

        public ViewHolder1(View itemView) {
            super(itemView);
            hotVp = (ViewPager) itemView.findViewById(R.id.vp_home_hot);
            ll_container = (LinearLayout) itemView.findViewById(R.id.ll_container);
            pointIv = (ImageView) itemView.findViewById(R.id.iv_point);
        }
    }
    //全部、活动、同城、排行 选择容器
    public class ViewHolder2 extends ViewHolder {
        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }
    //全部、活动、同城、排行 选择内容
    public class ViewHolder3 extends ViewHolder {
        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }
}
