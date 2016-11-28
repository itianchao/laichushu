package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshCommentListEvent;
import com.laichushu.book.event.RefurshHomeEvent;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.bookdetail.BookDetailPresenter;
import com.laichushu.book.mvp.bookdetail.BookDetailView;
import com.laichushu.book.mvp.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;

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
    private ArrayList<ArticleCommentModle.DataBean> mCommentdata = new ArrayList();
    private ImageView likeIv;
    private boolean isCheck;
    private ArticleCommentModle model;
    private String type = "1";
    private String articleId;
    private int position = 0;
    private String collectType = "0";
    private ImageView comentIv;

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
        }else {
            comentIv.setImageResource(R.drawable.activity_keep);
            collectType = "0";

        }

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
            subscriptionTv.setText(" 取消订阅 ");
        } else {
            subscriptionTv.setText("订阅更新");
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
        rewardTv.setOnClickListener(this);
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
                EventBus.getDefault().postSticky(new RefurshHomeEvent(true));
                finish();
                break;
            case R.id.iv_title_other://分享
                break;
            case R.id.iv_title_another://收藏
                String booktype =  "1";
                mvpPresenter.collectSave(articleId, collectType,booktype);
                break;
            case R.id.tv_lookup://查看更多评论
                Bundle bundle1 = new Bundle();
                bundle1.putString("articleId",articleId);
                UIUtil.openActivity(this, AllCommentActivity.class,bundle1);
                break;
            case R.id.lay_read://阅读
//                Bundle bundle = new Bundle();
//                String bookname = "12345";
//                String path = Environment.getExternalStorageDirectory() + File.separator
//                        + "123.txt";
//                bundle.putString("bookname", bookname);
//                bundle.putString("bookpath", path);
//                UIUtil.openActivity(this, BookPlayActivity.class, bundle);
                mvpPresenter.loadJurisdiction(articleId);
                // TODO: 2016/11/7 阅读
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

                break;
            case R.id.tv_pay://购买
                //弹出对话框确认
                mvpPresenter.getBalace();
                break;
            case R.id.btn_reward://打赏
                //弹出对话框确认
                mvpPresenter.getBalace2();
                break;
            case R.id.rbn_dresser://大咖评论
                if (position!=1){
                    commentLay.removeAllViews();
                    type = "2";
                    mvpPresenter.loadCommentData(articleId,type);
                    position = 1;
                }
                break;
            case R.id.rbn_reader://普通评论
                if (position!=0){
                    commentLay.removeAllViews();
                    type = "1";
                    mvpPresenter.loadCommentData(articleId,type);
                    position = 0;
                }
                break;
            case R.id.tv_detail_reward:
                mvpPresenter.getBalace2();
                break;
        }
    }

    /**
     * 作者信息
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
     * @param model
     */
    @Override
    public void getPayResult(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("购买成功");
            payTv.setText("已购买");
            bean.setIsPurchase(true);
        }else {
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
      * @param model
     */
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
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 订阅
     * @param model
     * @param typestate
     */
    @Override
    public void getSubscribeArticleData(SubscribeArticleModle model, String typestate) {
        subscriptionTv.setEnabled(true);
        if (model.isSuccess()) {
            if (typestate.equals("1")){
                subscriptionTv.setText(" 订阅更新 ");
                int num = Integer.parseInt(numberTv.getText().toString()) - 1;
                bean.setSubscribeNum(num);
                numberTv.setText(bean.getSubscribeNum() + "");
            }else {
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
                            mvpPresenter.saveScoreLikeData(dataBean.getScoreId(),"1");
//                            GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                            dataBean.setIsLike(false);
                            dataBean.setLikeNum(dataBean.getLikeNum()-1);
                            likeTv.setText(dataBean.getLikeNum() + "");
                        } else {
                            mvpPresenter.saveScoreLikeData(dataBean.getScoreId(),"0");
//                            GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                            dataBean.setIsLike(true);
                            dataBean.setLikeNum(dataBean.getLikeNum()+1);
                            likeTv.setText(dataBean.getLikeNum() + "");
                        }
                    }
                });
                inIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2016/11/4  去评论详情
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean",dataBean);
                        bundle.putString("type",type);
                        UIUtil.openActivity(mActivity,CommentDetailActivity.class,bundle);
                    }
                });
                commentLay.addView(commentItemView);
                numberTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("commentId", dataBean.getScoreId());
                        UIUtil.openActivity(BookDetailActivity.this,CommentSendActivity.class,bundle);
                    }
                });
            }
        } else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    /**
     * 余额查询
     * @param model
     */
    @Override
    public void getBalanceData(BalanceBean model) {
        if (model.isSuccess()){
            double balance = model.getData();
            if (!payTv.getText().toString().equals("已购买")){
                double payMoney = bean.getPrice();
                double price = bean.getPrice();
                String articleName = bean.getArticleName();
                String authorName = bean.getAuthorName();
                mvpPresenter.showdialog(articleId, payMoney+"",balance,price,articleName,authorName);
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }
    /**
     *     查询余额后打赏
     */
    @Override
    public void getBalance2Data(BalanceBean model) {
        if (model.isSuccess()){
            double balance = model.getData();
            String accepterId = bean.getAuthorId();
            mvpPresenter.openReward(balance+"", accepterId, articleId);
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 打赏回调
     * @param model
     */
    @Override
    public void getRewardMoneyData(RewardResult model) {
        if (model.isSuccess()){
            ToastUtil.showToast("打赏成功，感谢支持");
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }
    /**
     * 点赞
     * @param model
     * @param typeState
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String typeState) {
        if (model.isSuccess()) {
            getArticleCommentData(this.model);
            if (typeState.equals("0")){//点赞
                Logger.e("点赞");
            }else {//取消赞
                Logger.e("取消赞");
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 收藏
     * @param model
     */
    @Override
    public void collectSaveData(RewardResult model) {
        if (model.isSuccess()) {
            if (collectType.equals("0")){
                collectType = "1";
                comentIv.setImageResource(R.drawable.activity_keep2);
                ToastUtil.showToast("收藏成功");
                bean.setCollect(true);
            }else {
                comentIv.setImageResource(R.drawable.activity_keep);
                collectType = "0";
                ToastUtil.showToast("取消成功");
                bean.setCollect(false);
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getJurisdictionData(RewardResult model) {
        if (model.isSuccess()) {
            Bundle bundle = new Bundle();
            bundle.putString("articleId", articleId);
            UIUtil.openActivity(this, DirectoriesActivity.class, bundle);
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshCommentListEvent event){
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            position = 1;
            onClick(readerRbn);
            mvpPresenter.loadCommentData(articleId,"1");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
