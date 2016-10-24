package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 活动详情
 * Created by wangtong on 2016/10/24.
 */
public class CampaignActivity extends BaseActivity implements View.OnClickListener {

    private TextView joinTv;
    private TextView numTv;
    private TextView endTimeTv;
    private TextView startTimeTv;
    private TextView activityNameTv;
    private ImageView activityImgIv;
    private TextView detailsTv;
    private LinearLayout parentLay;
    private ImageView stateIv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_campaign);
        initTitleBar("活动详情");
        findViewById();
    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        ImageView shareIv = (ImageView) findViewById(R.id.iv_title_other);
        ImageView comentIv = (ImageView) findViewById(R.id.iv_title_another);
        titleTv.setText(title);
        shareIv.setImageResource(R.drawable.activity_share);
        comentIv.setImageResource(R.drawable.activity_comment);
        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        comentIv.setOnClickListener(this);
    }

    /**
     * activityImgIv    大图
     * activityNameTv   活动名称
     * startTimeTv      开始时间
     * endTimeTv        结束时间
     * numTv            参加人数
     * joinTv           参加活动按钮
     * detailsTv        活动详情介绍
     * parentLay        活动结果的容器
     */
    private void findViewById() {
        activityImgIv = (ImageView) findViewById(R.id.iv_activity_img);
        stateIv = (ImageView) findViewById(R.id.iv_activity_state);
        activityNameTv = (TextView) findViewById(R.id.tv_activity_name);
        startTimeTv = (TextView) findViewById(R.id.tv_activity_start);
        endTimeTv = (TextView) findViewById(R.id.tv_activity_end);
        numTv = (TextView) findViewById(R.id.tv_activity_num);
        joinTv = (TextView) findViewById(R.id.tv_join);
        detailsTv = (TextView) findViewById(R.id.tv_details);
        parentLay = (LinearLayout) findViewById(R.id.lay_result);
    }

    @Override
    protected void initData() {
        joinTv.setOnClickListener(this);
        GlideUitl.loadImg(this, "", activityImgIv);
        GlideUitl.loadImg(this, R.drawable.activity_start, stateIv);
        activityNameTv.setText("南昌市征文大赛");
        startTimeTv.setText("开始时间：2016-07-09");
        endTimeTv.setText("结束时间：2016-08-09");
        numTv.setText("报名人数：88人");
        detailsTv.setText("    个人简介，是当事人全面而简洁地介绍自身情况的一种书面表达方式。求职过程中撰写的个人简介是求职者向欲供职单位全面、简洁、条理清晰地自我介绍、自我推荐的文书。简介是应用写作学研究的一种日常应用文体。");
        for (int i = 1; i <= 20; i++) {
            View itemView = UIUtil.inflate(R.layout.item_home_activity_details);
            TextView bonusTv = (TextView) itemView.findViewById(R.id.tv_bonus);
            ImageView headIv = (ImageView) itemView.findViewById(R.id.iv_head);
            TextView usernameIv = (TextView) itemView.findViewById(R.id.iv_username);
            TextView booknameIv = (TextView) itemView.findViewById(R.id.iv_bookname);
            bonusTv.setText(i + "");
            String path = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476714818&di=2b104d4a35a140ed0a28e694a560e731&src=http://pic38.nipic.com/20140228/2531170_213554844000_2.jpg";
            GlideUitl.loadRandImg(this, path, headIv);
            usernameIv.setText("超人");
            booknameIv.setText("雪中悍刀行");
            parentLay.addView(itemView);
        }
    }

    /**
     * 点击事件
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other://分享
                break;
            case R.id.iv_title_another://评论
                break;
            case R.id.tv_join://参加活动
                break;
        }
    }
}
