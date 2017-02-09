package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.otherbean.ProbationNumModle;
import com.laichushu.book.event.RefrushHomeCategroyEvent;
import com.laichushu.book.event.RefrushHomeSearchEvent;
import com.laichushu.book.event.RefrushWriteFragmentEvent;
import com.laichushu.book.event.RefurshBookCommentListEvent;
import com.laichushu.book.event.RefurshHomeEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.home.bookdetail.BookDetailModle;
import com.laichushu.book.mvp.home.bookdetail.BookDetailPresenter;
import com.laichushu.book.mvp.home.bookdetail.BookDetailView;
import com.laichushu.book.mvp.home.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 图书详情
 * Created by wangtong on 2016/10/25.
 */
public class BookDetailActivity extends MvpActivity2<BookDetailPresenter> implements BookDetailView, View.OnClickListener {

    private ImageView detailBookIv, authorHeadIv;
    private RatingBar detailRatbarTv;
    private RelativeLayout nopublishRay;
    private LinearLayout publishLay, readLay, commentLay, likeLay;
    private TextView detailMarkTv, detailCommentTv, detailAuthorTv, detailTypeTv, detailWordTv,
            detailReadNumTv, createtimeTv, refreshtimeTv, numberTv, subscriptionTv,
            detailTitleTv, priceTv, lookupTv, probationTv, payTv, msgTv, rewardTv, briefTv,
            authorNameTv, authorTv2, individualTv, authorBriefTv, commentNumberTv;
    private RadioButton readerRbn, dresserRbn;
    private HomeHotModel.DataBean bean;
    private String type = "1";
    private String articleId;
    private int position = 0;
    private String collectType = null;
    private ImageView comentIv;
    private TextView stateTv;
    private ImageView detailBookStatueIv;
    private BookDetailModle.DataBean.ArticleDataBean articleData;
    private BookDetailModle.DataBean.AuthorDataBean authorData;
    private ArrayList<HomeHotModel.DataBean> bestLikeData;
    private ArrayList<ArticleCommentModle.DataBean> scoreCattleData;
    private ArrayList<ArticleCommentModle.DataBean> scoreReaderData;
    private View mSuccessView;

    /**
     * @return 控制器
     */
    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        mSuccessView = UIUtil.inflate(R.layout.activity_bookdetail);
        initTitleBar("图书详情");
        initFindViewById();
        return mSuccessView;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        bean = getIntent().getParcelableExtra("bean");
        articleId = bean.getArticleId();
        mvpPresenter.getBookById(articleId);
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
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("图书详情");
    }
    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        comentIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
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
        detailBookIv = (ImageView) mSuccessView.findViewById(R.id.iv_detail_book);//封面
        detailBookStatueIv = (ImageView) mSuccessView.findViewById(R.id.iv_book_statue);//封面状态
        detailTitleTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_title);//书名
        detailRatbarTv = (RatingBar) mSuccessView.findViewById(R.id.ratbar_detail_num);//星级
        detailMarkTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_mark);//评分
        detailCommentTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_comment);//评论数
        detailAuthorTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_author);//作者
        detailTypeTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_type);//分类
        detailWordTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_word);//字数
        detailReadNumTv = (TextView) mSuccessView.findViewById(R.id.tv_detail_read_num);//阅读人数
    }

    /**
     * 初始化发布状态
     */
    private void initPublic() {
        stateTv = (TextView) mSuccessView.findViewById(R.id.tv_state);//阅读按钮
        //未出版
        nopublishRay = (RelativeLayout) mSuccessView.findViewById(R.id.ray_nopublish);//未出版布局
        createtimeTv = (TextView) mSuccessView.findViewById(R.id.tv_createtime);//更新时间
        refreshtimeTv = (TextView) mSuccessView.findViewById(R.id.tv_refreshtime);//刷新时间
        numberTv = (TextView) mSuccessView.findViewById(R.id.tv_number);//订阅人数
        subscriptionTv = (TextView) mSuccessView.findViewById(R.id.tv_subscription);//订阅按钮
        //已出版
        publishLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_publish);//已出版布局
        priceTv = (TextView) mSuccessView.findViewById(R.id.tv_price);//价格
        probationTv = (TextView) mSuccessView.findViewById(R.id.tv_probation);//免费试读按钮
        payTv = (TextView) mSuccessView.findViewById(R.id.tv_pay);//购买按钮
    }

    /**
     * 阅读 简介 作者
     */
    private void initRead() {
        readLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_read);
        msgTv = (TextView) mSuccessView.findViewById(R.id.tv_msg);
        rewardTv = (TextView) mSuccessView.findViewById(R.id.btn_reward);
        briefTv = (TextView) mSuccessView.findViewById(R.id.tv_brief);
        authorHeadIv = (ImageView) mSuccessView.findViewById(R.id.iv_author_head);
        authorNameTv = (TextView) mSuccessView.findViewById(R.id.tv_author_name);
        authorTv2 = (TextView) mSuccessView.findViewById(R.id.textView2);
        individualTv = (TextView) mSuccessView.findViewById(R.id.tv_individual);
        authorBriefTv = (TextView) mSuccessView.findViewById(R.id.tv_author_brief);
        rewardTv.setOnClickListener(this);
        authorHeadIv.setOnClickListener(this);
        readLay.setOnClickListener(this);
    }

    /**
     * 书评 and 喜欢
     */
    private void initCommentAndLike() {
        commentNumberTv = (TextView) mSuccessView.findViewById(R.id.tv_comment_number);
        readerRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_reader);
        dresserRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_dresser);
        commentLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_comment);
        lookupTv = (TextView) mSuccessView.findViewById(R.id.tv_lookup);
        likeLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_like);
        lookupTv.setOnClickListener(this);
        readerRbn.setOnClickListener(this);
        dresserRbn.setOnClickListener(this);
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
                //分享
                String shareContent = "#来出书邀请您看好书#一起来看<<" + bean.getArticleName() + ">>吧!";
                String linkUrl = Base64Utils.getStringUrl(articleId, ConstantValue.SHARE_TYPR_BOOK);
                ShareUtil.showShare(mActivity, linkUrl, shareContent, bean.getCoverUrl(), bean.getIntroduce(), bean.getName());
                break;
            case R.id.iv_title_another://收藏
                mvpPresenter.collectSave(articleId, collectType, ConstantValue.BOOKCOMMENTTYPE);
                break;
            case R.id.tv_lookup://查看更多评论
                Bundle bundle1 = new Bundle();
                bundle1.putString("articleId", articleId);
                bundle1.putInt("commentNum", articleData.getCommentNum());
                bundle1.putBoolean("isScore", articleData.isScore());
                UIUtil.openActivity(this, AllCommentActivity.class, bundle1);
                break;
            case R.id.lay_read://阅读
                if (articleData.getStatus().equals("2")) {//电子书打赏后才能阅读
                    if (!articleData.isAward()) {
                        ToastUtil.showToast("请打赏后阅读");
                    } else {
                        ConstantValue.ISREADER = false;
                        mvpPresenter.getDownloadUrl(articleId, articleData.getAuthorId(), articleData.getCoverUrl(), articleData.getIntroduce(),articleData.getArticleName());//获取下载url
                    }
                } else if (articleData.getStatus().equals("4")) {//已出版购买后才能阅读
                    if (articleData.isPurchase()) {//购买
                        ConstantValue.ISREADER = false;
                        mvpPresenter.getDownloadUrl(articleId, articleData.getAuthorId(), articleData.getCoverUrl(), articleData.getIntroduce(),articleData.getArticleName());//获取下载url
                    } else {//未购买
                        ToastUtil.showToast("请购买后阅读");
                    }
                } else {//未出版
                    ConstantValue.ISREADER = false;
                    mvpPresenter.loadJurisdiction(articleId);
                }
                break;
            case R.id.tv_subscription://订阅
                if (subscriptionTv.getText().equals(" 取消订阅 ")) {
                    mvpPresenter.loadSubscribeArticle(articleId, "1");
                    articleData.setIsSubscribe(false);
                    bean.setIsSubscribe(false);
                    subscriptionTv.setEnabled(false);
                } else {
                    mvpPresenter.loadSubscribeArticle(articleId, "0");
                    articleData.setIsSubscribe(true);
                    bean.setIsSubscribe(true);
                    subscriptionTv.setEnabled(false);
                }
                break;
            case R.id.tv_probation://免费试读
                mvpPresenter.getProbationNum(bean.getArticleId());
                break;
            case R.id.tv_pay://购买
                //弹出对话框确认
                if (!payTv.getText().toString().equals("已购买")) {
                    mvpPresenter.getBalace();
                }
                break;
            case R.id.btn_reward://打赏
                //弹出对话框确认
                mvpPresenter.getBalace2();
                break;
            case R.id.rbn_dresser://大咖评论
                if (position != 1) {
                    commentLay.removeAllViews();
                    type = "2";
                    getArticleCommentData(scoreCattleData);
//                    mvpPresenter.loadCommentData(articleId, type);
                    position = 1;
                }
                break;
            case R.id.rbn_reader://普通评论
                if (position != 0) {
                    commentLay.removeAllViews();
                    type = "1";
//                    mvpPresenter.loadCommentData(articleId, type);
                    getArticleCommentData(scoreReaderData);
                    position = 0;
                }
                break;
            case R.id.iv_author_head:
                //跳转用户主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", articleData.getAuthorId());
                if (SharePrefManager.getUserId().equals(articleData.getAuthorId())) {
                    UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                } else {
                    UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                }
                break;
        }
    }

    /**
     * 作者信息
     */
    public void getAuthorDetailData() {
        if (authorData != null) {
            GlideUitl.loadRandImg(this, authorData.getPhoto(), authorHeadIv);
            authorTv2.setText("已发表");
            authorNameTv.setText(authorData.getNickName());//名字
            authorBriefTv.setText(TextUtils.isEmpty(authorData.getAuthorIntroduction()) ? "暂无作者简介" : authorData.getAuthorIntroduction());//简介
            individualTv.setText(authorData.getArticleNum() + "");//出版数量
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    /**
     * 购买结果
     *
     * @param model
     */
    @Override
    public void getPayResult(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("购买成功");
            payTv.setText("已购买");
            payTv.setBackgroundColor(Color.GRAY);
            articleData.setIsPurchase(true);
            bean.setIsPurchase(true);
            articleData.setPurchase(true);
            if (articleData.isPurchase()) {//电子书
                probationTv.setVisibility(View.INVISIBLE);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 获取图书信息接口成功
     *
     * @param modle
     */
    @Override
    public void getBookDataSuccess(BookDetailModle modle) {
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            articleData = modle.getData().getArticleData();
            authorData = modle.getData().getAuthorData();
            bestLikeData = modle.getData().getBestLikeData();
            scoreCattleData = modle.getData().getScoreCattleData();
            scoreReaderData = modle.getData().getScoreReaderData();
            getBookData();//
            getBestLikeSuggestlData();//猜你喜欢
            getAuthorDetailData();//作者详情
            getArticleCommentData(scoreReaderData);//评论
            getStatue();
        } else {
//            ToastUtil.showToast(modle.getErrMsg());
            refrushErrorView();
        }
    }

    /**
     * 获取图书信息接口失败
     *
     * @param msg
     */
    @Override
    public void getBookDataError(String msg) {
        LoggerUtil.e(msg);

        refrushErrorView();
    }

    /**
     * 设置图书基本数据
     */
    private void getBookData() {
        GlideUitl.loadImg(this, articleData.getCoverUrl(), detailBookIv);//封面
        detailTitleTv.setText(articleData.getArticleName());//书名
        detailCommentTv.setText("(" + articleData.getCommentNum() + "评论)");//评论数
        detailAuthorTv.setText(articleData.getAuthorName());//作者
        detailTypeTv.setText(articleData.getTopCategoryName());//分类
        detailWordTv.setText("约" + articleData.getWordNum() + "字");//字数
        detailReadNumTv.setText(articleData.getBrowseNum() + "人");//阅读人数
        detailMarkTv.setText(articleData.getScore() + "分");//评分
        detailRatbarTv.setRating(articleData.getLevel());//星级
        briefTv.setText(articleData.getIntroduce());//简介
        String msg = "收到的打赏：" + articleData.getAwardMoney() + "元(" + articleData.getAwardNum() + "人打赏)";
        msgTv.setText(msg);
        if (articleData.isCollect()) {//已收藏
            comentIv.setImageResource(R.drawable.activity_keep2);
            collectType = "1";
        } else {
            comentIv.setImageResource(R.drawable.activity_keep);
            collectType = "0";
        }
        switch (articleData.getStatus()) {
            case "1":
                GlideUitl.loadImg(this, R.drawable.icon_book_statue2, detailBookStatueIv);
                break;
            case "2":
                GlideUitl.loadImg(this, R.drawable.icon_book_statue3, detailBookStatueIv);
                break;
            default:
                GlideUitl.loadImg(this, R.drawable.icon_book_statue1, detailBookStatueIv);
                break;
        }
        bean.setCommentNum(articleData.getCommentNum());
    }

    private void getStatue() {
        if (articleData.getStatus().equals("1")) {//1 未出版
            nopublishRay.setVisibility(View.VISIBLE);
            publishLay.setVisibility(View.INVISIBLE);
            createtimeTv.setText(articleData.getCreateDate());
            refreshtimeTv.setText(articleData.getUpdateDate());
            numberTv.setText(articleData.getSubscribeNum() + "");
            subscriptionTv.setOnClickListener(this);
            stateTv.setText("阅读");
        } else {//2 已发表 3 出版
            nopublishRay.setVisibility(View.INVISIBLE);
            publishLay.setVisibility(View.VISIBLE);
            String price = "￥ " + articleData.getPrice();
            priceTv.setText(price);//价格
            if (articleData.getStatus().equals("2") || articleData.isPurchase()) {//电子书
                probationTv.setVisibility(View.INVISIBLE);
            } else {
                probationTv.setVisibility(View.VISIBLE);
                probationTv.setOnClickListener(this);//免费试读按钮
            }
            payTv.setOnClickListener(this);//购买按钮
            stateTv.setText("阅读");
        }
        if (!TextUtils.isEmpty(articleData.getStatus())) {
            if (articleData.getStatus().equals("2")) {//电子书
                payTv.setVisibility(View.INVISIBLE);
            } else {
                payTv.setVisibility(View.VISIBLE);
            }
        }

        if (articleData.isSubscribe()) {
            subscriptionTv.setText(" 取消订阅 ");
        } else {
            subscriptionTv.setText("订阅更新");
        }
        if (articleData.isPurchase()) {
            payTv.setText("已购买");
            payTv.setBackgroundColor(Color.GRAY);
        } else {
            payTv.setText("购买");
            payTv.setBackgroundColor(Color.RED);
        }
    }

    /**
     * 猜你喜欢的书
     */
    public void getBestLikeSuggestlData() {

        if (bestLikeData != null) {
            likeLay.removeAllViews();
            for (final HomeHotModel.DataBean dataBean : bestLikeData) {
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
                commentTv.setText("(" + dataBean.getCommentNum() + ")评论");//评论数
                authorTv.setText(dataBean.getAuthorName());//作者
                typeTv.setText(dataBean.getTopCategoryName());//分类
                wordTv.setText("约" + dataBean.getWordNum() + "字");//字数
                moneyTv.setText(dataBean.getAwardMoney() + "元");
                rewardTv.setText("(" + dataBean.getAwardNum() + "人打赏)");
                markTv.setText(dataBean.getScore() + "分");//评分
                numRb.setRating(dataBean.getLevel());//星级
                likeLay.addView(likeItemView);
                likeItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", dataBean);
                        bundle.putString("pageMsg", "猜你喜欢");
                        UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
                        finish();
                    }
                });

            }
        }

    }

    /**
     * 订阅
     *
     * @param model
     * @param typestate
     */
    @Override
    public void getSubscribeArticleData(SubscribeArticleModle model, String typestate) {
        subscriptionTv.setEnabled(true);
        if (model.isSuccess()) {
            if (typestate.equals("1")) {
                subscriptionTv.setText(" 订阅更新 ");
                int num = Integer.parseInt(numberTv.getText().toString()) - 1;
                articleData.setSubscribeNum(num);
                bean.setSubscribeNum(num);
                numberTv.setText(articleData.getSubscribeNum() + "");
            } else {
                subscriptionTv.setText(" 取消订阅 ");
                int num = Integer.parseInt(numberTv.getText().toString()) + 1;
                articleData.setSubscribeNum(num);
                bean.setSubscribeNum(num);
                numberTv.setText(articleData.getSubscribeNum() + "");
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    /**
     * 全部评论
     *
     * @param scoreReaderData
     */
    public void getArticleCommentData(ArrayList<ArticleCommentModle.DataBean> scoreReaderData) {
        if (scoreReaderData == null) {
            return;
        }
        //书评
        commentLay.removeAllViews();
        for (int i = 0; i < scoreReaderData.size(); i++) {
            final ArticleCommentModle.DataBean dataBean = scoreReaderData.get(i);
            View commentItemView = UIUtil.inflate(R.layout.item_comment_connet);
            ImageView headIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_head);
            ImageView gradeDetailsIv = (ImageView) commentItemView.findViewById(R.id.iv_gradeDetail);
            TextView gradeNameTv = (TextView) commentItemView.findViewById(R.id.tv_gradeName);
            TextView nameTv = (TextView) commentItemView.findViewById(R.id.tv_comment_name);
            TextView contentTv = (TextView) commentItemView.findViewById(R.id.tv_comment_content);
            TextView timeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_time);
            final ImageView likeIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_like);
            final TextView likeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_like);
            final TextView numberTv = (TextView) commentItemView.findViewById(R.id.tv_comment_number);
            ImageView inIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_in);
            GlideUitl.loadRandImg(this, dataBean.getPhoto(), headIv);//头像
            nameTv.setText(dataBean.getNickName());//用户名
            contentTv.setText(dataBean.getContent());//评论内容
            timeTv.setText(dataBean.getCommentTime());//创建时间
            likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
            numberTv.setText(dataBean.getReplyNum() + "");//回复人数
            likeIv.setTag(R.id.image_tag, i);
            //评论者等级
            if (null != dataBean.getLevelType()) {
                gradeDetailsIv.setVisibility(View.VISIBLE);
                gradeNameTv.setVisibility(View.VISIBLE);
                switch (dataBean.getLevelType()) {
                    case "1":
                        gradeNameTv.setText("金牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_gold_medal2x, gradeDetailsIv);
                        break;
                    case "2":
                        gradeNameTv.setText("银牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_silver_medal2x, gradeDetailsIv);
                        break;
                    case "3":
                        gradeNameTv.setText("铜牌作家");
                        GlideUitl.loadImg(mActivity, R.drawable.icon_copper_medal2x, gradeDetailsIv);
                        break;
                }
            } else {
                gradeDetailsIv.setVisibility(View.GONE);
                gradeNameTv.setText("暂无等级");

            }

            if (dataBean.isIsLike()) {
                GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
            } else {
                GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
            }
            likeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.isIsLike()) {
                        mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "1", likeIv);
                    } else {
                        mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "0", likeIv);
                    }
                }
            });
            commentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2016/11/4  去评论详情
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", dataBean);
                    bundle.putString("type", type);
                    bundle.putString("tag", "replay");
                    UIUtil.openActivity(mActivity, CommentDetailActivity.class, bundle);
                }
            });
            commentLay.addView(commentItemView);
//                numberIv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString("commentId", dataBean.getSourceId());
//                        UIUtil.openActivity(BookDetailActivity.this, CommentSendActivity.class, bundle);
//                    }
//                });
            headIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转用户主页
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userId", dataBean.getUserId());
                    if (SharePrefManager.getUserId().equals(dataBean.getUserId())) {
                        UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                    } else {
                        UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                    }
                }
            });
        }
    }

    /**
     * 余额查询
     *
     * @param model
     */
    @Override
    public void getBalanceData(BalanceBean model) {
        if (model.isSuccess()) {
            String balance = model.getData().getMoney();
            if (!payTv.getText().toString().equals("已购买")) {
                double payMoney = articleData.getPrice();
                double price = articleData.getPrice();
                String articleName = articleData.getArticleName();
                String authorName = articleData.getAuthorName();
                mvpPresenter.showdialog(articleId, payMoney + "", balance, price, articleName, authorName);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 查询余额后打赏
     */
    @Override
    public void getBalance2Data(BalanceBean model) {
        if (model.isSuccess()) {
            String balance = model.getData().getMoney();
            double maxLimit = model.getData().getMaxLimit();
            double minLimit = model.getData().getMinLimit();
            String accepterId = articleData.getAuthorId();
            mvpPresenter.openReward(balance + "", accepterId, articleId, maxLimit, minLimit);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 打赏回调
     *
     * @param model
     */
    @Override
    public void getRewardMoneyData(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("打赏成功，感谢支持");
            mvpPresenter.getBookById(articleId);//打赏成功刷新页面
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 点赞
     *
     * @param model
     * @param typeState
     * @param likeIv
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String typeState, ImageView likeIv) {
        int index = (int) likeIv.getTag(R.id.image_tag);
        if (model.isSuccess()) {
            getArticleCommentData(scoreReaderData);
            if (typeState.equals("0")) {//点赞
                Logger.e("点赞");
                if ((position == 0)) {//普通
                    ArticleCommentModle.DataBean dataBean = scoreReaderData.get(index);
                    dataBean.setIsLike(true);
                    dataBean.setLikeNum(dataBean.getLikeNum() + 1);
                    scoreReaderData.set(index, dataBean);
                    getArticleCommentData(scoreReaderData);
                } else {//大咖
                    ArticleCommentModle.DataBean dataBean = scoreCattleData.get(index);
                    dataBean.setIsLike(true);
                    dataBean.setLikeNum(dataBean.getLikeNum() + 1);
                    scoreCattleData.set(index, dataBean);
                    getArticleCommentData(scoreCattleData);
                }
            } else {//取消赞
                Logger.e("取消赞");
                if ((position == 0)) {//普通
                    ArticleCommentModle.DataBean dataBean = scoreReaderData.get(index);
                    dataBean.setIsLike(false);
                    dataBean.setLikeNum(dataBean.getLikeNum() - 1);
                    scoreReaderData.set(index, dataBean);
                    getArticleCommentData(scoreReaderData);
                } else {//大咖
                    ArticleCommentModle.DataBean dataBean = scoreCattleData.get(index);
                    dataBean.setIsLike(false);
                    dataBean.setLikeNum(dataBean.getLikeNum() - 1);
                    scoreCattleData.set(index, dataBean);
                    getArticleCommentData(scoreCattleData);
                }
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 收藏
     *
     * @param model
     */
    @Override
    public void collectSaveData(RewardResult model) {
        if (model.isSuccess()) {
            if (collectType.equals("0")) {
                collectType = "1";
                comentIv.setImageResource(R.drawable.activity_keep2);
                ToastUtil.showToast("收藏成功");
                articleData.setCollect(true);
                bean.setCollect(true);
            } else {
                comentIv.setImageResource(R.drawable.activity_keep);
                collectType = "0";
                ToastUtil.showToast("已取消收藏");
                articleData.setCollect(false);
                bean.setCollect(false);
            }
        } else {
            ToastUtil.showToast("收藏失败");
        }
    }

    /**
     * 阅读权限
     *
     * @param model
     */
    @Override
    public void getJurisdictionData(RewardResult model) {
        if (model.isSuccess()) {
            Bundle bundle = new Bundle();
            bundle.putString("articleId", articleId);
            bundle.putString("bookName",articleData.getArticleName() );
            UIUtil.openActivity(this, DirectoriesActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 获取试读 成功
     *
     * @param model
     */
    @Override
    public void getProbationNumDataSuccess(ProbationNumModle model) {
        if (model.isSuccess()) {
            int endLimit = model.getData().getEndLimit();
            ConstantValue.ISREADER_NUMBER = endLimit;
            ConstantValue.ISREADER = true;
            String url = model.getData().getUrl();
            String bookName=articleData.getArticleName();
            mvpPresenter.openFile(url, bookName,articleId, articleData.getAuthorId(), articleData.getCoverUrl(), articleData.getIntroduce());//获取下载url
        } else {
            ToastUtil.showToast("试读失败");
            LoggerUtil.e(model.getErrMsg());
        }
    }

    /**
     * 获取试读 失败
     *
     * @param msg
     */
    @Override
    public void getProbationNumDataFail(String msg) {
        ToastUtil.showToast("试读失败");
    }


    /**
     * 刷新评论列表 和 评论数
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshBookCommentListEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            position = 1;
            onClick(readerRbn);
            mvpPresenter.getBookById(articleId);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finish() {
        super.finish();
        String pageMsg = getIntent().getStringExtra("pageMsg");
        switch (pageMsg) {
            case "写作和作品管理":
                EventBus.getDefault().postSticky(new RefrushWriteFragmentEvent(true, bean));
                break;
            case "猜你喜欢":
                break;
            case "首页分类列表":
                EventBus.getDefault().postSticky(new RefrushHomeCategroyEvent(true, bean));
                break;
            case "首页搜索":
                EventBus.getDefault().postSticky(new RefrushHomeSearchEvent(true, bean));
                break;
            case "热门推荐列表":
                break;
            case "消息评论详情":
                break;
            case "UserWorkList":
                break;
            case "消息喜欢":
                break;
            case "浏览收藏详情":
                break;
            case "首页轮播图":
                break;
            default://默认刷新首页
                EventBus.getDefault().postSticky(new RefurshHomeEvent(true, bean));
                break;
        }
    }

    public BookDetailModle.DataBean.ArticleDataBean getArticleData() {
        return articleData;
    }

    public void setArticleData(BookDetailModle.DataBean.ArticleDataBean articleData) {
        this.articleData = articleData;
    }

    public HomeHotModel.DataBean getBean() {
        return bean;
    }

    public void setBean(HomeHotModel.DataBean bean) {
        this.bean = bean;
    }

    /**
     * 重新加载
     */
    public void refrushErrorView() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.getBookById(articleId);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 5) {
            boolean isScore = data.getBooleanExtra("isScore", false);
            articleData.setScore(isScore);
        }
    }
}
