package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;
import com.sofacity.laichushu.mvp.bookdetail.AuthorDetailModle;
import com.sofacity.laichushu.mvp.bookdetail.BookDetailPresenter;
import com.sofacity.laichushu.mvp.bookdetail.BookDetailView;
import com.sofacity.laichushu.mvp.bookdetail.SubscribeArticleModle;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.ui.widget.BookPlayActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * 图书详情
 * Created by wangtong on 2016/10/25.
 */
public class BookDetailActivity extends MvpActivity<BookDetailPresenter> implements BookDetailView, View.OnClickListener {

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
    private ArrayList<HomeHotModel.DataBean> mdata = new ArrayList();
    private ArrayList<ArticleCommentModle.DataBean> mCommentdata = new ArrayList();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bookdetail);
        initTitleBar("图书详情");
        bean = getIntent().getParcelableExtra("bean");
        initFindViewById();
        String authorId = bean.getAuthorId();
        String articleId = bean.getArticleId();
        mvpPresenter.loadAuthorData(authorId);
        mvpPresenter.loadBestLikeSuggest(articleId);
        mvpPresenter.loadCommentData(articleId);
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
        detailRatbarTv = (RatingBar) findViewById(R.id.ratbar_detail_num);//星级
        detailMarkTv = (TextView) findViewById(R.id.tv_detail_mark);//评分
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
        detailWordTv.setText("约" + bean.getWordNum() + "字");//字数
        detailMoneyTv.setText(bean.getAwardMoney() + "元");//打赏金额
        detailRewardTv.setText(bean.getAwardNum() + "人打赏");//打赏人
        detailMarkTv.setText(bean.getScore() + "分");//评分
        detailRatbarTv.setRating(bean.getLevel());//星级
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
            createtimeTv.setText(bean.getCreateDate());
            refreshtimeTv.setText(bean.getUpdateDate());
            numberTv.setText(bean.getSubscribeNum() + "");
            subscriptionTv.setOnClickListener(this);
        } else {//2 已发表 3 出版
            nopublishRay.setVisibility(View.INVISIBLE);
            publishLay.setVisibility(View.VISIBLE);
            String price = "￥ " + bean.getPrice();
            priceTv.setText(price);//价格
            probationTv.setOnClickListener(this);//免费试读按钮
            payTv.setOnClickListener(this);//购买按钮
        }
        if (bean.isSubscribe()) {
            subscriptionTv.setText("订阅更新");
        } else {
            subscriptionTv.setText("已订阅");
        }
        if (bean.isPurchase()) {
            payTv.setText("已购买");
            probationTv.setVisibility(View.INVISIBLE);
        } else {
            payTv.setText("购买");
            probationTv.setVisibility(View.VISIBLE);
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
        briefTv.setText(bean.getIntroduce());//简介
        String msg = "收到的打赏：" + bean.getAwardMoney() + "元(" + bean.getAwardNum() + "人)";
        msgTv.setText(msg);

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
        readerRbn.setOnClickListener(this);
        dresserRbn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
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
                if (!subscriptionTv.getText().equals(" 已订阅 ")) {
                    mvpPresenter.loadSubscribeArticle(bean.getArticleId());
                } else {
                    ToastUtil.showToast(" 已订阅 ");
                }
                break;
            case R.id.tv_probation://免费试读

                break;
            case R.id.tv_pay://购买

                break;
            case R.id.rbn_dresser://大咖评论

                break;
            case R.id.rbn_reader://普通评论

                break;
        }
    }

    @Override
    public void getAuthorDetailData(AuthorDetailModle model) {
        if (model.isSuccess()) {
            if (model.getData() != null) {
                AuthorDetailModle.DataBean data = model.getData();
                GlideUitl.loadRandImg(this, data.getPhoto(), authorHeadIv);
                authorNameTv.setText(data.getNickName());//名字
                authorBriefTv.setText(data.getAuthorIntroduction());//简介
                individualTv.setText(data.getArticleNum() + "");//出版数量
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
            Logger.e(model.getErrorMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void getBestLikeSuggestlData(HomeHotModel model) {
        if (model.isSuccess()) {
            mdata.clear();
            if (model.getData() != null) {
                mdata = model.getData();
                for (HomeHotModel.DataBean dataBean : mdata) {
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

                    GlideUitl.loadImg(this, dataBean.getCoverUrl(), bookIv);//封面
                    titleTv.setText(dataBean.getArticleName());//书名
                    commentTv.setText("(" + bean.getCommentNum() + ")评论");//评论数
                    authorTv.setText(bean.getAuthorName());//作者
                    typeTv.setText(bean.getTopCategoryName());//分类
                    wordTv.setText("约" + bean.getWordNum() + "字");//字数
                    moneyTv.setText(bean.getAwardMoney() + "元");//打赏金额
                    rewardTv.setText(bean.getAwardNum() + "人打赏");//打赏人
                    markTv.setText(bean.getScore() + "分");//评分
                    numRb.setRating(bean.getLevel());//星级
                    likeLay.addView(likeItemView);
                    likeItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", bean);
                            UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
                            finish();
                        }
                    });
                }
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    public void getSubscribeArticleData(SubscribeArticleModle model) {
        if (model.isSuccess()) {
            subscriptionTv.setText(" 已订阅 ");
            int num = Integer.parseInt(numberTv.getText().toString()) + 1;
            bean.setSubscribeNum(num);
            numberTv.setText(bean.getSubscribeNum() + "");
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    public void getArticleCommentData(ArticleCommentModle model) {
        if (model.isSuccess()) {
            if (model.getData() == null) {
                return;
            }
            mCommentdata = model.getData();
            //书评
            commentLay.removeAllViews();
            for (int i = 0; i < mCommentdata.size(); i++) {
                final ArticleCommentModle.DataBean dataBean = mCommentdata.get(i);
                View commentItemView = UIUtil.inflate(R.layout.item_comment_connet);
                ImageView headIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_head);
                TextView nameTv = (TextView) commentItemView.findViewById(R.id.tv_comment_name);
                TextView contentTv = (TextView) commentItemView.findViewById(R.id.tv_comment_content);
                TextView timeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_time);
                final ImageView likeIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_like);
                TextView likeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_like);
                TextView numberTv = (TextView) commentItemView.findViewById(R.id.tv_comment_number);
                ImageView inIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_in);
                GlideUitl.loadRandImg(this, dataBean.getPhoto(), headIv);//头像
                nameTv.setText(dataBean.getNickName());//用户名
                contentTv.setText(dataBean.getContent());//评论内容
                timeTv.setText(dataBean.getCreateDate());//创建时间
                likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
                numberTv.setText(dataBean.getReplyNum() + "");//回复人数
                if (dataBean.isIsLike()) {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                } else {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                }
                final boolean[] isLike = {dataBean.isIsLike()};
                likeTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isLike[0]) {
                            GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                            isLike[0] = false;
                        } else {
                            GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                            isLike[0] = true;
                        }
                    }
                });
                commentItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2016/11/4  去评论详情
                    }
                });
                commentLay.addView(commentItemView);
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

}
