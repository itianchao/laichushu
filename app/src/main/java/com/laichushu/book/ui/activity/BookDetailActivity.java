package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.otherbean.BaseBookEntity;
import com.laichushu.book.event.RefrushWriteFragmentEvent;
import com.laichushu.book.event.RefurshCommentListEvent;
import com.laichushu.book.event.RefurshHomeEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.bookdetail.BookDetailPresenter;
import com.laichushu.book.mvp.bookdetail.BookDetailView;
import com.laichushu.book.mvp.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
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
    private ArrayList<HomeHotModel.DataBean> mLikeData = new ArrayList();
    private ArrayList<ArticleCommentModle.DataBean> mCommentdata = new ArrayList();
    private ImageView likeIv;
    private boolean isCheck;
    private ArticleCommentModle model;
    private String type = "1";
    private String articleId;
    private int position = 0;
    private String collectType = null;
    private ImageView comentIv;
    private TextView stateTv;
    private ImageView detailBookStatueIv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bookdetail);
        EventBus.getDefault().register(this);
        initTitleBar("图书详情");
        bean = getIntent().getParcelableExtra("bean");
        articleId = bean.getArticleId();
        initFindViewById();
        String authorId = bean.getAuthorId();
        mvpPresenter.loadAuthorData(authorId);
        mvpPresenter.loadBestLikeSuggest(articleId);
        mvpPresenter.loadCommentData(articleId, type);
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
        comentIv = (ImageView) findViewById(R.id.iv_title_another);
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
        detailBookStatueIv = (ImageView) findViewById(R.id.iv_book_statue);//封面状态
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
        if (bean.isCollect()) {//已收藏
            comentIv.setImageResource(R.drawable.activity_keep2);
            collectType = "1";
        } else {
            comentIv.setImageResource(R.drawable.activity_keep);
            collectType = "0";

        }
        switch(bean.getStatus()){
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
    }

    /**
     * 初始化发布状态
     */
    private void initPublic() {
        stateTv = (TextView) findViewById(R.id.tv_state);//阅读按钮
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
        probationTv.setVisibility(View.INVISIBLE);//隐藏免费试读
        payTv = (TextView) findViewById(R.id.tv_pay);//购买按钮
        if (bean.getStatus().equals("1")) {//1 未出版
            nopublishRay.setVisibility(View.VISIBLE);
            publishLay.setVisibility(View.INVISIBLE);
            createtimeTv.setText(bean.getCreateDate());
            refreshtimeTv.setText(bean.getUpdateDate());
            numberTv.setText(bean.getSubscribeNum() + "");
            subscriptionTv.setOnClickListener(this);
            stateTv.setText("试读");
        } else if(bean.getStatus().equals("2")){//电子书隐藏
            payTv.setVisibility(View.INVISIBLE);
        }else {//2 已发表 3 出版
            nopublishRay.setVisibility(View.INVISIBLE);
            publishLay.setVisibility(View.VISIBLE);
            String price = "￥ " + bean.getPrice();
            priceTv.setText(price);//价格
            probationTv.setOnClickListener(this);//免费试读按钮
            payTv.setOnClickListener(this);//购买按钮
            stateTv.setText("阅读");
        }
        if(!TextUtils.isEmpty(bean.getExpressStatus())){
            if (bean.getExpressStatus().equals("3")){//电子书
                payTv.setVisibility(View.INVISIBLE);
            }
        }

        if (bean.isSubscribe()) {
            subscriptionTv.setText(" 取消订阅 ");
        } else {
            subscriptionTv.setText("订阅更新");
        }
        if (bean.isPurchase()) {
            payTv.setText("已购买");
        } else {
            payTv.setText("购买");
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
        rewardTv.setOnClickListener(this);
        authorHeadIv.setOnClickListener(this);
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
//                ShareSdkUtils.showShare(mActivity);
                break;
            case R.id.iv_title_another://收藏
                mvpPresenter.collectSave(articleId, collectType, ConstantValue.BOOKCOMMENTTYPE);
                break;
            case R.id.tv_lookup://查看更多评论
                Bundle bundle1 = new Bundle();
                bundle1.putString("articleId", articleId);
                UIUtil.openActivity(this, AllCommentActivity.class, bundle1);
                break;
            case R.id.lay_read://阅读
                if (bean.getStatus().equals("2")&&!bean.isAward()){//电子书打赏后才能阅读
                    ToastUtil.showToast("请打赏后阅读");
                }else if (bean.getStatus().equals("2")&&bean.isAward()){
                    mvpPresenter.getDownloadUrl(articleId,bean.getAuthorId());//获取下载url
                }else if (bean.getStatus().equals("4")){//已出版购买后才能阅读
                    if (bean.isPurchase()){//购买
                        mvpPresenter.getDownloadUrl(articleId,bean.getAuthorId());//获取下载url
                    }else {//未购买
                        ToastUtil.showToast("请购买后阅读");
                    }
                }else {//未出版
                    mvpPresenter.loadJurisdiction(articleId);
                }
                break;
            case R.id.tv_subscription://订阅
                if (subscriptionTv.getText().equals(" 取消订阅 ")) {
                    mvpPresenter.loadSubscribeArticle(articleId, "1");
                    bean.setIsSubscribe(false);
                    subscriptionTv.setEnabled(false);
                } else {
                    mvpPresenter.loadSubscribeArticle(articleId, "0");
                    bean.setIsSubscribe(true);
                    subscriptionTv.setEnabled(false);
                }
                break;
            case R.id.tv_probation://免费试读
                BaseBookEntity baseBookEntity = new BaseBookEntity();
                baseBookEntity.setBook_path(ConstantValue.LOCAL_PATH.SD_PATH + "test.epub");
                UIUtil.startBookFBReaderActivity(this, baseBookEntity,articleId,bean.getAuthorId());
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
                    mvpPresenter.loadCommentData(articleId, type);
                    position = 1;
                }
                break;
            case R.id.rbn_reader://普通评论
                if (position != 0) {
                    commentLay.removeAllViews();
                    type = "1";
                    mvpPresenter.loadCommentData(articleId, type);
                    position = 0;
                }
                break;
            case R.id.tv_detail_reward:
                mvpPresenter.getBalace2();
                break;
            case R.id.iv_author_head:
                //跳转用户主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", bean.getAuthorId());
                if (SharePrefManager.getUserId().equals(bean.getAuthorId())) {
                    UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                } else {
                    UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                }
                break;
        }
    }

    /**
     * 作者信息
     *
     * @param model
     */
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
            ToastUtil.showToast(model.getErrMsg());
            Logger.e(model.getErrMsg());
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
            bean.setIsPurchase(true);
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
     * 猜你喜欢的书
     *
     * @param model
     */
    @Override
    public void getBestLikeSuggestlData(HomeHotModel model) {
        if (model.isSuccess()) {
            mLikeData.clear();
            if (model.getData() != null) {
                mLikeData = model.getData();
                for (final HomeHotModel.DataBean dataBean : mLikeData) {
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
                    moneyTv.setText(dataBean.getAwardMoney() + "元");//打赏金额
                    rewardTv.setText(dataBean.getAwardNum() + "人打赏");//打赏人
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
        } else {
            ToastUtil.showToast(model.getErrMsg());
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
                bean.setSubscribeNum(num);
                numberTv.setText(bean.getSubscribeNum() + "");
            } else {
                subscriptionTv.setText(" 取消订阅 ");
                int num = Integer.parseInt(numberTv.getText().toString()) + 1;
                bean.setSubscribeNum(num);
                numberTv.setText(bean.getSubscribeNum() + "");
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    /**
     * 全部评论
     *
     * @param model
     */
    @Override
    public void getArticleCommentData(ArticleCommentModle model) {
        if (model.isSuccess()) {
            this.model = model;
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
                likeIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_like);
                final TextView likeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_like);
                final TextView numberTv = (TextView) commentItemView.findViewById(R.id.tv_comment_number);
                ImageView inIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_in);
                GlideUitl.loadRandImg(this, dataBean.getPhoto(), headIv);//头像
                nameTv.setText(dataBean.getNickName());//用户名
                contentTv.setText(dataBean.getContent());//评论内容
                timeTv.setText(dataBean.getCommentTime());//创建时间
                likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
                numberTv.setText(dataBean.getReplyNum() + "");//回复人数
                if (dataBean.isIsLike()) {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                } else {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                }
                likeIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataBean.isIsLike()) {
                            mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "1");
                            dataBean.setIsLike(false);
                            if (dataBean.isIsLike()){
                                GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                            }else {
                                GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                            }
                            dataBean.setLikeNum(dataBean.getLikeNum() - 1);
                            likeTv.setText(dataBean.getLikeNum() + "");
                        } else {
                            mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "0");
                            dataBean.setIsLike(true);
                            if (dataBean.isIsLike()){
                                GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                            }else {
                                GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                            }
                            dataBean.setLikeNum(dataBean.getLikeNum() + 1);
                            likeTv.setText(dataBean.getLikeNum() + "");
                        }
                    }
                });
                inIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2016/11/4  去评论详情
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", dataBean);
                        bundle.putString("type", type);
                        UIUtil.openActivity(mActivity, CommentDetailActivity.class, bundle);
                    }
                });
                commentLay.addView(commentItemView);
                numberTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("commentId", dataBean.getSourceId());
                        UIUtil.openActivity(BookDetailActivity.this, CommentSendActivity.class, bundle);
                    }
                });
                headIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId",dataBean.getUserId());
                        if (SharePrefManager.getUserId().equals(dataBean.getNickName())) {
                            UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                        }
                    }
                });
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
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
            double balance = model.getData();
            if (!payTv.getText().toString().equals("已购买")) {
                double payMoney = bean.getPrice();
                double price = bean.getPrice();
                String articleName = bean.getArticleName();
                String authorName = bean.getAuthorName();
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
            double balance = model.getData();
            String accepterId = bean.getAuthorId();
            mvpPresenter.openReward(balance + "", accepterId, articleId);
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
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 点赞
     *
     * @param model
     * @param typeState
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String typeState) {
        if (model.isSuccess()) {
            getArticleCommentData(this.model);
            if (typeState.equals("0")) {//点赞
                Logger.e("点赞");
            } else {//取消赞
                Logger.e("取消赞");
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
                bean.setCollect(true);
            } else {
                comentIv.setImageResource(R.drawable.activity_keep);
                collectType = "0";
                ToastUtil.showToast("取消成功");
                bean.setCollect(false);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getJurisdictionData(RewardResult model) {
        if (model.isSuccess()) {
            Bundle bundle = new Bundle();
            bundle.putString("articleId", articleId);
            UIUtil.openActivity(this, DirectoriesActivity.class, bundle);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshCommentListEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            position = 1;
            onClick(readerRbn);
            mvpPresenter.loadCommentData(articleId, "1");
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
        switch(pageMsg){
            case "写作和作品管理":
                EventBus.getDefault().postSticky(new RefrushWriteFragmentEvent(true,bean));
                break;
            default://默认刷新首页
                EventBus.getDefault().postSticky(new RefurshHomeEvent(true,bean));
                break;
        }
    }

    public HomeHotModel.DataBean getBean() {
        return bean;
    }

    public void setBean(HomeHotModel.DataBean bean) {
        this.bean = bean;
    }
}
