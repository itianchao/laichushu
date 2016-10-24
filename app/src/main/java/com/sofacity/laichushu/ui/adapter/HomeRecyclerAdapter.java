package com.sofacity.laichushu.ui.adapter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.otherbean.HomeHotImgBean;
import com.sofacity.laichushu.mvp.home.HomePresenter;
import com.sofacity.laichushu.ui.activity.CampaignActivity;
import com.sofacity.laichushu.ui.widget.TypePopWindow;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页下拉刷新加载更多 recycler
 * Created by wt on 2016/10/17.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private Activity mActivity;
    private ArrayList<String> mData;
    private ArrayList<String> mHotData;
    private RadioButton rankRbn;
    public HomePresenter mvpPresenter;

    public HomeRecyclerAdapter(ArrayList mData, Activity mActivity, ArrayList mHotData, HomePresenter mvpPresenter) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mHotData = mHotData;//最热
        this.mvpPresenter = mvpPresenter;

        rankingList.add("排行");
        rankingList.add("评分最高");
        rankingList.add("打赏最多");
        rankingList.add("订阅最多");
        rankingList.add("评论最多");
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size()+2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //多重行视图
    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;
    private final static int TYPE3 = 2;
    //radiobutton 的状态
    public static int STATE = 1;//状态默认点全部
    public final static int STATE1 = 1;//全部
    public final static int STATE2 = 2;//活动
    public final static int STATE3 = 3;//同城
    public final static int STATE4 = 4;//排行

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
        switch (viewType) {
            case TYPE1:
                itemView = UIUtil.inflate(R.layout.item_home_hot);
                holder = new ViewHolder1(itemView);
                break;
            case TYPE2:
                itemView = UIUtil.inflate(R.layout.item_home_bar);
                holder = new ViewHolder2(itemView);
                break;
            case TYPE3:
                itemView = UIUtil.inflate(R.layout.item_home);
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
            if (i % 3 == 0) {
                homeHotImgBean = new HomeHotImgBean();
                homeHotImgBean.setFristImg(mHotData.get(i));
                homeHotImgBean.setFristTitle("书名1");
                homeHotImgBean.setFristName("作者名1");
            } else if (i % 3 == 1) {
                homeHotImgBean.setSecondImg(mHotData.get(i));
                homeHotImgBean.setSecondTitle("书名2");
                homeHotImgBean.setSecondName("作者名2");
            } else {
                homeHotImgBean.setThirdImg(mHotData.get(i));
                homeHotImgBean.setThirdTitle("书名3");
                homeHotImgBean.setThirdName("作者名3");
            }
            if (i != 0 && i % 3 == 2 || i == mHotData.size() - 1) {
                homeHotImgBeans.add(homeHotImgBean);
            }
        }
        //
        final int[] range = new int[1];
        if (position == 0) {
            ((ViewHolder1) holder).hotVp.setAdapter(new HomeHotViewPagerAdapter(homeHotImgBeans, mActivity));
            ((ViewHolder1) holder).pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ((ViewHolder1) holder).pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    range[0] = ((ViewHolder1) holder).ll_container.getChildAt(1).getLeft() - ((ViewHolder1) holder).ll_container.getChildAt(0).getLeft();
                }
            });
            for (int i = 0; i < Math.ceil(mHotData.size() / 3); i++) {
                if (((ViewHolder1) holder).ll_container.getChildCount()> Math.ceil(mHotData.size() / 3)-1){
                    return;
                }
                ImageView imageView = new ImageView(mActivity);
                imageView.setBackgroundResource(R.drawable.shape_point_hollow);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i > 0) {
                    params.leftMargin = UIUtil.px2dip(10);
                }
                imageView.setLayoutParams(params);
                ((ViewHolder1) holder).ll_container.addView(imageView);
            }
            ((ViewHolder1) holder).hotVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            if (mHotData.size() != 0) {
                ((ViewHolder1) holder).bookNumTv.setText(mHotData.size() + "本");
            }
            ((ViewHolder1) holder).bookNumTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转热门详情页
                }
            });
        } else if (position == 1) {
            ((ViewHolder2) holder).allRbn.setOnClickListener(this);
            ((ViewHolder2) holder).activityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).cityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).rankingRbn.setOnClickListener(this);
            this.position = SharePrefManager.getPosition();
            switch (this.position){
                case 0:
                    ((ViewHolder2) holder).allRbn.setChecked(true);
                    break;
                case 1:
                    ((ViewHolder2) holder).allRbn.setChecked(true);
                    break;
                case 2:
                    ((ViewHolder2) holder).activityRbn.setChecked(true);
                    break;
                case 3:
                    ((ViewHolder2) holder).cityRbn.setChecked(true);
                    break;
                case 4:
                    ((ViewHolder2) holder).rankingRbn.setChecked(true);
                    break;
            }

        } else {
            switch(STATE){
                case STATE1://全部
                    ((ViewHolder3) holder).fristFay.setVisibility(View.VISIBLE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.GONE);
                    GlideUitl.loadImg(mActivity, mData.get(position - 2), ((ViewHolder3) holder).bookIv);
                    ((ViewHolder3) holder).fristFay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转详情页

                        }
                    });
                    break;
                case STATE2://活动
                    ((ViewHolder3) holder).fristFay.setVisibility(View.GONE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.VISIBLE);
                    GlideUitl.loadImg(mActivity, mData.get(position - 2), ((ViewHolder3) holder).activityIv);
                    ((ViewHolder3) holder).secondFay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转详情页
                            UIUtil.openActivity(mActivity, CampaignActivity.class);
                        }
                    });
                    break;
                case STATE3://同城
                    ((ViewHolder3) holder).fristFay.setVisibility(View.GONE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.GONE);
                    break;
                case STATE4://排行
                    ((ViewHolder3) holder).fristFay.setVisibility(View.GONE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.GONE);
                    break;
            }
        }
    }
    /**
     * base
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    /**
     * 最热
     */
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
    /**
     * 全部、活动、同城、排行 选择容器
     */
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
            rankRbn = rankingRbn;
        }
    }

    /**
     * 全部、活动、同城、排行选择内容
     */
    public class ViewHolder3 extends ViewHolder {
        //        全部
        private FrameLayout fristFay;
        private ImageView bookIv;
        private TextView titleTv;
        private TextView typeTv;
        private RatingBar numRb;
        private TextView markTv;
        private TextView commentTv;
        private TextView authorTv;
        private TextView wordTv;
        private TextView moneyTv;
        private TextView rewardTv;
        //        活动
        private FrameLayout secondFay;
        private TextView nameTv;
        private ImageView activityIv;
        private ImageView stateIv;
        private TextView briefTv;
        private TextView startTimeTv;
        private TextView endTimeTv;
        private TextView numberTv;
        private LinearLayout parentLyt;

        public ViewHolder3(View itemView) {
            super(itemView);
            fristFay = (FrameLayout) itemView.findViewById(R.id.fay_type_frist);
            bookIv = (ImageView) itemView.findViewById(R.id.iv_book);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            typeTv = (TextView) itemView.findViewById(R.id.tv_type);
            numRb = (RatingBar) itemView.findViewById(R.id.ratbar_num);
            markTv = (TextView) itemView.findViewById(R.id.tv_mark);
            commentTv = (TextView) itemView.findViewById(R.id.tv_comment);
            authorTv = (TextView) itemView.findViewById(R.id.tv_author);
            wordTv = (TextView) itemView.findViewById(R.id.tv_word);
            moneyTv = (TextView) itemView.findViewById(R.id.tv_money);
            rewardTv = (TextView) itemView.findViewById(R.id.tv_reward);

            secondFay = (FrameLayout) itemView.findViewById(R.id.fay_type_second);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            activityIv = (ImageView) itemView.findViewById(R.id.iv_activity_img);
            stateIv = (ImageView) itemView.findViewById(R.id.iv_activity_state);
            briefTv = (TextView) itemView.findViewById(R.id.tv_brief);
            startTimeTv = (TextView) itemView.findViewById(R.id.tv_starttime);
            endTimeTv = (TextView) itemView.findViewById(R.id.tv_endtime);
            numberTv = (TextView) itemView.findViewById(R.id.tv_number);
            parentLyt = (LinearLayout) itemView.findViewById(R.id.lyt_parent);
        }
    }
    /**
     * 全部、活动、同城、排行 点击事件
     */
    ArrayList<String> rankingList = new ArrayList<>();
    int position = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbn_all:
                if (position ==1){
                    return;
                }
                STATE = STATE1;
                //请求网络
                mvpPresenter.initData(STATE);
                position = 1;
                break;
            case R.id.rbn_activity:
                if (position ==2){
                    return;
                }
                STATE = STATE2;
                //请求网络
                mvpPresenter.initData(STATE);
                position = 2;
                break;
            case R.id.rbn_citywide:
                if (position ==3){
                    return;
                }
                STATE = STATE3;
                mData.clear();
                //请求网络
                position = 3;
                break;
            case R.id.rbn_ranking:
//                STATE = STATE4;
                mData.clear();
                //请求网络
                TypePopWindow popWindow = new TypePopWindow(mActivity, rankingList);
                popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        rankRbn.setText(rankingList.get(position));
                    }
                });
                popWindow.setWidth(v.getWidth() + UIUtil.dip2px(1));
                popWindow.setHeight(UIUtil.dip2px(200));
                popWindow.showAsDropDown(v);
                break;
        }
        SharePrefManager.setPosition(position);
        notifyDataSetChanged();
    }

    public void setmData(ArrayList<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
