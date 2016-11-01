package com.sofacity.laichushu.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.ui.widget.BookPlayActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.UIUtil;

import java.io.File;

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
            detailMoneyTv, detailRewardTv, createtimeTv, refreshtimeTv, numberTv, subscriptionTv,
            detailTitleTv, priceTv, lookupTv, probationTv, payTv, msgTv, rewardTv, briefTv,
            authorNameTv, individualTv, authorBriefTv, commentNumberTv;
    private RadioButton readerRbn, dresserRbn;
    private HomeHotModel.DataBean bean;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bookdetail);
        initTitleBar("图书详情");
        bean = getIntent().getParcelableExtra("bean");
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
        detailBookIv = (ImageView) findViewById(R.id.iv_detail_book);//封面
        detailTitleTv = (TextView) findViewById(R.id.tv_detail_title);//书名
        detailRatbarTv = (RatingBar) findViewById(R.id.ratbar_detail_num);
        detailMarkTv = (TextView) findViewById(R.id.tv_detail_mark);
        detailCommentTv = (TextView) findViewById(R.id.tv_detail_comment);//评论数
        detailAuthorTv = (TextView) findViewById(R.id.tv_detail_author);//作者
        detailTypeTv = (TextView) findViewById(R.id.tv_detail_type);//分类
        detailWordTv = (TextView) findViewById(R.id.tv_detail_word);//字数
        detailMoneyTv = (TextView) findViewById(R.id.tv_detail_money);//打赏金额
        detailRewardTv = (TextView) findViewById(R.id.tv_detail_reward);//打赏人数
        //设置数据
        GlideUitl.loadImg(this, bean.getCoverUrl(), detailBookIv);//封面
        detailTitleTv.setText(bean.getArticleName());//书名
        detailCommentTv.setText("(" + bean.getCommentNum() + ")评论");//评论数
        detailAuthorTv.setText(bean.getAuthorName());//作者
        detailTypeTv.setText(bean.getTopCategoryName());//分类
        detailWordTv.setText("约"+bean.getWordNum()+"字");//字数
        detailMoneyTv.setText(bean.getAwardMoney() + "元");//打赏金额
        detailRewardTv.setText(bean.getAwardNum() + "人打赏");//打赏人数
    }

    /**
     * 初始化发布状态
     */
    private void initPublic() {
        //未出版
        nopublishRay = (RelativeLayout) findViewById(R.id.ray_nopublish);//未出版布局
        createtimeTv = (TextView) findViewById(R.id.tv_createtime);//更新时间
        refreshtimeTv = (TextView) findViewById(R.id.tv_refreshtime);//刷新时间
        numberTv = (TextView) findViewById(R.id.tv_number);//订阅人数
        subscriptionTv = (TextView) findViewById(R.id.tv_subscription);//订阅按钮
        //已出版
        publishLay = (LinearLayout) findViewById(R.id.lay_publish);//已出版布局
        priceTv = (TextView) findViewById(R.id.tv_price);//价格
        probationTv = (TextView) findViewById(R.id.tv_probation);//免费试读按钮
        payTv = (TextView) findViewById(R.id.tv_pay);//购买按钮
        if (bean.getStatus().equals("1")) {//1 未出版
            nopublishRay.setVisibility(View.VISIBLE);
            publishLay.setVisibility(View.INVISIBLE);
//            createtimeTv.setText();
//            refreshtimeTv.setText();
            numberTv.setText(bean.getSubscribeNum());
            subscriptionTv.setOnClickListener(this);
        } else {//2 已发表 3 出版
            nopublishRay.setVisibility(View.INVISIBLE);
            publishLay.setVisibility(View.VISIBLE);
//            priceTv.setText(bean.);//价格
            probationTv.setOnClickListener(this);//免费试读按钮
            payTv.setOnClickListener(this);//购买按钮
        }
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
            GlideUitl.loadRandImg(this, "", headIv);
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
            GlideUitl.loadImg(this, "", bookIv);
            likeLay.addView(likeItemView);
        }
        lookupTv.setOnClickListener(this);
        readLay.setOnClickListener(this);
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
                UIUtil.openActivity(this, AllCommentActivity.class);
                break;
            case R.id.lay_read://阅读
                Bundle bundle = new Bundle();
                String bookname = "12345";
                String path = Environment.getExternalStorageDirectory() + File.separator
                        + "123.txt";
                bundle.putString("bookname", bookname);
                bundle.putString("bookpath", path);
                UIUtil.openActivity(this, BookPlayActivity.class, bundle);
                break;
            case R.id.tv_subscription://订阅

                break;
             case R.id.tv_probation://免费试读

                break;
            case R.id.tv_pay://购买

                break;
        }
    }
}
