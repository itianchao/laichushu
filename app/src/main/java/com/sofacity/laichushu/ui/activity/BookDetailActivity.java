package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 图书详情
 * Created by wangtong on 2016/10/25.
 */
public class BookDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView detailBookIv, authorHeadIv;
    private RatingBar detailRatbarTv;
    private RelativeLayout nopublishRay;
    private LinearLayout publishLay, readLay, commentLay, likeLay;
    private TextView detailMarkTv, detailCommentTv, detailAuthorTv, detailTypeTv, detailWordTv,
            detailMoneyTv,detailRewardTv, createtimeTv, refreshtimeTv, numberTv, subscriptionTv,
            detailTitleTv, priceTv,lookupTv,probationTv, payTv, msgTv, rewardTv, briefTv,
            authorNameTv, individualTv, authorBriefTv, commentNumberTv;
    private RadioButton readerRbn, dresserRbn;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bookdetail);
        initTitleBar("图书详情");
        initFindViewById();
    }

    /**
     * findViewById
     */
    private void initFindViewById() {
        initDetailBook();
        initPublic();
        initRead();
        initCommentAndLike();
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
        comentIv.setImageResource(R.drawable.activity_keep);
        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        comentIv.setOnClickListener(this);
    }

    /**
     * 初始化图书详情
     */
    private void initDetailBook() {
        detailBookIv = (ImageView) findViewById(R.id.iv_detail_book);
        detailTitleTv = (TextView) findViewById(R.id.tv_detail_title);
        detailRatbarTv = (RatingBar) findViewById(R.id.ratbar_detail_num);
        detailMarkTv = (TextView) findViewById(R.id.tv_detail_mark);
        detailCommentTv = (TextView) findViewById(R.id.tv_detail_comment);
        detailAuthorTv = (TextView) findViewById(R.id.tv_detail_author);
        detailTypeTv = (TextView) findViewById(R.id.tv_detail_type);
        detailWordTv = (TextView) findViewById(R.id.tv_detail_word);
        detailMoneyTv = (TextView) findViewById(R.id.tv_detail_money);
        detailRewardTv = (TextView) findViewById(R.id.tv_detail_reward);
    }

    /**
     * 初始化发布状态
     */
    private void initPublic() {
        //未出版
        nopublishRay = (RelativeLayout) findViewById(R.id.ray_nopublish);
        createtimeTv = (TextView) findViewById(R.id.tv_createtime);
        refreshtimeTv = (TextView) findViewById(R.id.tv_refreshtime);
        numberTv = (TextView) findViewById(R.id.tv_number);
        subscriptionTv = (TextView) findViewById(R.id.tv_subscription);
        //已出版
        publishLay = (LinearLayout) findViewById(R.id.lay_publish);
        priceTv = (TextView) findViewById(R.id.tv_price);
        probationTv = (TextView) findViewById(R.id.tv_probation);
        payTv = (TextView) findViewById(R.id.tv_pay);
    }

    /**
     * 阅读 简介 作者
     */
    private void initRead() {
        readLay = (LinearLayout) findViewById(R.id.lay_read);
        msgTv = (TextView) findViewById(R.id.tv_msg);
        rewardTv = (TextView) findViewById(R.id.btn_reward);
        briefTv = (TextView) findViewById(R.id.tv_brief);
        authorHeadIv = (ImageView) findViewById(R.id.iv_author_head);
        authorNameTv = (TextView) findViewById(R.id.tv_author_name);
        individualTv = (TextView) findViewById(R.id.tv_individual);
        authorBriefTv = (TextView) findViewById(R.id.tv_author_brief);
    }

    /**
     * 书评 and 喜欢
     */
    private void initCommentAndLike() {
        commentNumberTv = (TextView) findViewById(R.id.tv_comment_number);
        readerRbn = (RadioButton) findViewById(R.id.rbn_reader);
        dresserRbn = (RadioButton) findViewById(R.id.rbn_dresser);
        commentLay = (LinearLayout) findViewById(R.id.lay_comment);
        lookupTv = (TextView) findViewById(R.id.tv_lookup);
        likeLay = (LinearLayout) findViewById(R.id.lay_like);
    }

    @Override
    protected void initData() {
        //书评和猜你喜欢
        for (int i = 0; i < 4; i++) {
            View commentItemView = UIUtil.inflate(R.layout.item_comment_connet);
            ImageView headIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_head);
            TextView nameTv = (TextView) commentItemView.findViewById(R.id.tv_comment_name);
            TextView contentTv = (TextView) commentItemView.findViewById(R.id.tv_comment_content);
            TextView timeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_time);
            ImageView likeIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_like);
            TextView likeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_like);
            TextView numberTv = (TextView) commentItemView.findViewById(R.id.tv_comment_number);
            ImageView inIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_in);
            GlideUitl.loadRandImg(this,"",headIv);
            commentLay.addView(commentItemView);
        }
        for (int i = 0; i < 4; i++) {
            View likeItemView = UIUtil.inflate(R.layout.item_home_book);
            ImageView bookIv = (ImageView) likeItemView.findViewById(R.id.iv_book);
            TextView titleTv = (TextView) likeItemView.findViewById(R.id.tv_title);
            TextView typeTv = (TextView) likeItemView.findViewById(R.id.tv_type);
            RatingBar numRb = (RatingBar) likeItemView.findViewById(R.id.ratbar_num);
            TextView markTv = (TextView) likeItemView.findViewById(R.id.tv_mark);
            TextView commentTv = (TextView) likeItemView.findViewById(R.id.tv_comment);
            TextView authorTv = (TextView) likeItemView.findViewById(R.id.tv_author);
            TextView wordTv = (TextView) likeItemView.findViewById(R.id.tv_word);
            TextView moneyTv = (TextView) likeItemView.findViewById(R.id.tv_money);
            TextView rewardTv = (TextView) likeItemView.findViewById(R.id.tv_reward);
            GlideUitl.loadImg(this,"",bookIv);
            likeLay.addView(likeItemView);
        }
        lookupTv.setOnClickListener(this);
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
            case R.id.iv_title_another://收藏
                break;
            case R.id.tv_lookup://查看更多评论
                UIUtil.openActivity(this,AllCommentActivity.class);
                break;
        }
    }
}
