package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.otherbean.HomeHotImgBean;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页下拉刷新加载更多 recycler
 * Created by wt on 2016/10/17.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private Activity mActivity;
    private ArrayList mData;
    private ArrayList<String> mHotData;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ArrayList<HomeHotImgBean> homeHotImgBeans = new ArrayList<>();
        //处理数据
        HomeHotImgBean homeHotImgBean = null;
        for (int i = 0; i < mHotData.size(); i++) {
            if (i%3 == 0){
                homeHotImgBean = new HomeHotImgBean();
                homeHotImgBean.setFristImg(mHotData.get(i));
                homeHotImgBean.setFristTitle("书名1");
                homeHotImgBean.setFristName("作者名1");
            }else if (i%3 ==1){
                homeHotImgBean.setSecondImg(mHotData.get(i));
                homeHotImgBean.setSecondTitle("书名2");
                homeHotImgBean.setSecondName("作者名2");
            }else {
                homeHotImgBean.setThirdImg(mHotData.get(i));
                homeHotImgBean.setThirdTitle("书名3");
                homeHotImgBean.setThirdName("作者名3");
            }
            if (i!=0&&i%3 == 2||i == mHotData.size()-1){
                homeHotImgBeans.add(homeHotImgBean);
            }
        }
        //
        final int[] range = new int[1];
        if (position == 0) {
            ((ViewHolder1)holder).hotVp.setAdapter(new HomeHotViewPagerAdapter(homeHotImgBeans,mActivity));
            ((ViewHolder1)holder).pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ((ViewHolder1) holder).pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    range[0] = ((ViewHolder1) holder).ll_container.getChildAt(1).getLeft() - ((ViewHolder1) holder).ll_container.getChildAt(0).getLeft();
                }
            });
            for (int i = 0; i < Math.ceil(mHotData.size()/3); i++) {
                ImageView imageView = new ImageView(mActivity);
                imageView.setBackgroundResource(R.drawable.shape_point_hollow);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                if(i>0){
                    params.leftMargin = UIUtil.px2dip(10);
                }
                imageView.setLayoutParams(params);
                ((ViewHolder1) holder).ll_container.addView(imageView);
            }
            ((ViewHolder1)holder).hotVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    int move = (int) ((position + positionOffset) * range[0]);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.leftMargin = move;
                    ((ViewHolder1) holder).pointIv.setLayoutParams(params);
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            if (mHotData.size()!=0){
                ((ViewHolder1) holder).bookNumTv.setText(mHotData.size()+"本");
            }
        } else if (position == 1) {
            ((ViewHolder2) holder).allRbn.setOnClickListener(this);
            ((ViewHolder2) holder).activityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).cityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).rankingRbn.setOnClickListener(this);
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

        private TextView bookNumTv;
        private ImageView pointIv;
        private LinearLayout ll_container;
        private ViewPager hotVp;

        public ViewHolder1(View itemView) {
            super(itemView);
            hotVp = (ViewPager) itemView.findViewById(R.id.vp_home_hot);
            ll_container = (LinearLayout) itemView.findViewById(R.id.ll_container);
            pointIv = (ImageView) itemView.findViewById(R.id.iv_point);
            bookNumTv = (TextView) itemView.findViewById(R.id.tv_booknum);
        }
    }
    //全部、活动、同城、排行 选择容器
    public class ViewHolder2 extends ViewHolder {

        private RadioButton allRbn;
        private RadioButton activityRbn;
        private RadioButton cityRbn;
        private RadioButton rankingRbn;

        public ViewHolder2(View itemView) {
            super(itemView);
            allRbn = (RadioButton) itemView.findViewById(R.id.rbn_all);
            activityRbn = (RadioButton) itemView.findViewById(R.id.rbn_activity);
            cityRbn = (RadioButton) itemView.findViewById(R.id.rbn_citywide);
            rankingRbn = (RadioButton) itemView.findViewById(R.id.rbn_ranking);
        }
    }
    //全部、活动、同城、排行 选择内容
    public class ViewHolder3 extends ViewHolder {
        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }

    //全部、活动、同城、排行 点击事件
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rbn_all:
                break;
            case R.id.rbn_activity:
                break;
            case R.id.rbn_citywide:
                break;
            case R.id.rbn_ranking:
                break;
        }
    }
}
