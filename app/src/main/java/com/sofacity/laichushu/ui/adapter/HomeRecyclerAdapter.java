package com.sofacity.laichushu.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomePresenter;
import com.sofacity.laichushu.ui.activity.BookDetailActivity;
import com.sofacity.laichushu.ui.activity.CampaignActivity;
import com.sofacity.laichushu.ui.activity.HotListActivity;
import com.sofacity.laichushu.ui.activity.MainActivity;
import com.sofacity.laichushu.ui.fragment.HomeFragment;
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

    private MainActivity mActivity;
    private ArrayList<HomeHotModel.DataBean> mData;
    private ArrayList<HomeHotModel.DataBean> mHotData;
    private RadioButton rankRbn;
    public HomePresenter mvpPresenter;

    public HomeRecyclerAdapter(ArrayList<HomeHotModel.DataBean> mData, MainActivity mActivity, ArrayList<HomeHotModel.DataBean> mHotData, HomePresenter mvpPresenter) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mHotData = mHotData;//最热
        this.mvpPresenter = mvpPresenter;

        rankingList.add("评分最高");//1
        rankingList.add("订阅最多");//2
        rankingList.add("打赏最多");//3
        rankingList.add("评论最多");//4
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 2;
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
            HomeHotModel.DataBean dataBean = mHotData.get(i);
            String coverUrl = dataBean.getCoverUrl();
            String articleName = dataBean.getArticleName();
            String authorName = dataBean.getAuthorName();
            if (coverUrl != null) {
                if (i % 3 == 0) {
                    homeHotImgBean = new HomeHotImgBean();
                    homeHotImgBean.setFristImg(coverUrl);
                    homeHotImgBean.setFristTitle(articleName);
                    homeHotImgBean.setFristName(authorName);
                    homeHotImgBean.setFristBean(dataBean);
                } else if (i % 3 == 1) {
                    homeHotImgBean.setSecondImg(coverUrl);
                    homeHotImgBean.setSecondTitle(articleName);
                    homeHotImgBean.setSecondName(authorName);
                    homeHotImgBean.setSecondBean(dataBean);
                } else {
                    homeHotImgBean.setThirdImg(coverUrl);
                    homeHotImgBean.setThirdTitle(articleName);
                    homeHotImgBean.setThirdName(authorName);
                    homeHotImgBean.setThirdtBean(dataBean);
                }
                if (i != 0 && i % 3 == 2 || i == mHotData.size() - 1) {
                    homeHotImgBeans.add(homeHotImgBean);
                }
            }
        }
        //
        final int[] range = new int[1];
        if (position == 0) {
            ((ViewHolder1) holder).hotVp.setAdapter(new HomeHotViewPagerAdapter(homeHotImgBeans, mActivity));
            for (int i = 0; i < Math.ceil(mHotData.size() / 3); i++) {
                if (((ViewHolder1) holder).ll_container.getChildCount() > Math.ceil(mHotData.size() / 3) - 1) {
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
            ((ViewHolder1) holder).pointIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ((ViewHolder1) holder).pointIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    if (((ViewHolder1) holder).ll_container.getChildCount() > 2)
                        range[0] = ((ViewHolder1) holder).ll_container.getChildAt(1).getLeft() - ((ViewHolder1) holder).ll_container.getChildAt(0).getLeft();
                }
            });
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
                    UIUtil.openActivity(mActivity, HotListActivity.class);
                }
            });

        } else if (position == 1) {
            ((ViewHolder2) holder).allRbn.setOnClickListener(this);
            ((ViewHolder2) holder).activityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).cityRbn.setOnClickListener(this);
            ((ViewHolder2) holder).rankingRbn.setOnClickListener(this);
            this.position = SharePrefManager.getPosition();
            switch (this.position) {
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
            switch (STATE) {
                case STATE1://全部
                    ((ViewHolder3) holder).fristFay.setVisibility(View.VISIBLE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.GONE);
                    final HomeHotModel.DataBean dataBean = mData.get(position - 2);
                    GlideUitl.loadImg(mActivity, dataBean.getCoverUrl(), ((ViewHolder3) holder).bookIv);
                    ((ViewHolder3) holder).fristFay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转图书详情页
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", dataBean);
                            UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
                        }
                    });
                    ((ViewHolder3) holder).titleTv.setText(dataBean.getArticleName());
                    ((ViewHolder3) holder).typeTv.setText(dataBean.getTopCategoryName());
                    ((ViewHolder3) holder).authorTv.setText(dataBean.getAuthorName());
                    ((ViewHolder3) holder).numRb.setRating(dataBean.getLevel());
                    ((ViewHolder3) holder).commentTv.setText("(" + dataBean.getCommentNum() + "评论)");
                    ((ViewHolder3) holder).wordTv.setText("约" + dataBean.getWordNum());
                    ((ViewHolder3) holder).moneyTv.setText(dataBean.getAwardMoney() + "元");
                    ((ViewHolder3) holder).rewardTv.setText("(" + dataBean.getAwardNum() + "人打赏)");
                    ((ViewHolder3) holder).markTv.setText(dataBean.getScore() + "分");
                    break;
                case STATE2://活动
                    final HomeHotModel.DataBean bean = mData.get(position - 2);
                    ((ViewHolder3) holder).fristFay.setVisibility(View.GONE);
                    ((ViewHolder3) holder).secondFay.setVisibility(View.VISIBLE);
                    ((ViewHolder3) holder).secondFay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转活动详情页
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", bean);
                            UIUtil.openActivity(mActivity, CampaignActivity.class, bundle);
                        }
                    });
                    GlideUitl.loadImg(mActivity, bean.getImgUrl(), ((ViewHolder3) holder).activityIv);

                    ((ViewHolder3) holder).nameTv.setText(bean.getActivityName());//活动name
                    ((ViewHolder3) holder).briefTv.setText(bean.getDetail());//简介
                    ((ViewHolder3) holder).startTimeTv.setText("开始时间：" + bean.getBeginTime());
                    ((ViewHolder3) holder).endTimeTv.setText("开始时间：" + bean.getEndTime());
                    ((ViewHolder3) holder).numberTv.setText(bean.getApplyAmount() + "人");
                    switch (bean.getStatus()) {
                        case "1":
                            GlideUitl.loadImg(mActivity, R.drawable.activity_start, ((ViewHolder3) holder).stateIv);
                            break;
                        case "2":
                            GlideUitl.loadImg(mActivity, R.drawable.activity_start, ((ViewHolder3) holder).stateIv);
                            break;
                        case "3":
                            GlideUitl.loadImg(mActivity, R.drawable.activity_start, ((ViewHolder3) holder).stateIv);
                            break;
                        case "4":
                            GlideUitl.loadImg(mActivity, R.drawable.activity_end, ((ViewHolder3) holder).stateIv);
                            break;
                    }
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
                if (position == 1) {
                    return;
                }
                mData.clear();
                mvpPresenter.setState(STATE1);
                STATE = STATE1;
                //请求网络
                mvpPresenter.loadHomeAllData(STATE + "");
                position = 1;
                break;
            case R.id.rbn_activity:
                if (position == 2) {
                    return;
                }
                mvpPresenter.setState(STATE2);
                STATE = STATE2;
                //请求网络
                position = 2;
                mData.clear();
                mvpPresenter.loadActivityData();
                break;
            case R.id.rbn_citywide:
                if (position == 3) {
                    return;
                }
                STATE = STATE3;
                mData.clear();
                //请求网络
                position = 3;
                break;
            case R.id.rbn_ranking:
//                STATE = STATE4;
                //请求网络
                TypePopWindow popWindow = new TypePopWindow(mActivity, rankingList);
                popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        mData.clear();
                        rankRbn.setText(rankingList.get(position));
                        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
                        HomeFragment home = (HomeFragment) fragmentManager.findFragmentByTag("home");
                        home.setType(position + 1 + "");
                        mvpPresenter.getParamet().setPageNo("1");
                        mvpPresenter.loadHomeAllData(position + 1 + "");
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

    public void setmData(ArrayList<HomeHotModel.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
