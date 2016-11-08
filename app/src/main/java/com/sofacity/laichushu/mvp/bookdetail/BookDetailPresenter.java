package com.sofacity.laichushu.mvp.bookdetail;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.bean.JsonBean.RewardResult;
import com.sofacity.laichushu.bean.netbean.AuthorDetail_Paramet;
import com.sofacity.laichushu.bean.netbean.BestLike_Paramet;
import com.sofacity.laichushu.bean.netbean.Comment_Paramet;
import com.sofacity.laichushu.bean.netbean.Purchase_Paramet;
import com.sofacity.laichushu.bean.netbean.SubscribeArticle_Paramet;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.BookDetailActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * 图书详情 presenter
 * Created by wangtong on 2016/10/12.
 */
public class BookDetailPresenter extends BasePresenter<BookDetailView> {
    private BookDetailActivity mActivity;
    private String pageSize = "5";
    private String pageNo = "1";
    private BestLike_Paramet paramet;
    private String userId = SharePrefManager.getUserId();

    //初始化构造
    public BookDetailPresenter(BookDetailView view) {
        attachView(view);
        mActivity = (BookDetailActivity) view;
    }

    public void loadAuthorData(String authorId) {
        mvpView.showLoading();
        AuthorDetail_Paramet paramet = new AuthorDetail_Paramet(authorId);
        Logger.e("作者详情请求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.authorDetail(paramet),
                new ApiCallback<AuthorDetailModle>() {
                    @Override
                    public void onSuccess(AuthorDetailModle model) {
                        mvpView.getAuthorDetailData(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail("code+" + code + "/msg:" + msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

    public void loadBestLikeSuggest(String articleId) {
        mvpView.showLoading();
        paramet = new BestLike_Paramet(articleId, pageSize, pageNo, userId);
        Logger.e("喜欢本书的也喜欢请求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.bestLikeSuggest(paramet),
                new ApiCallback<HomeHotModel>() {
                    @Override
                    public void onSuccess(HomeHotModel model) {
                        mvpView.getBestLikeSuggestlData(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail("code+" + code + "/msg:" + msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    public BestLike_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(BestLike_Paramet paramet) {
        this.paramet = paramet;
    }

    public void loadSubscribeArticle(String aricleId) {
        mvpView.showLoading();
        SubscribeArticle_Paramet paramet = new SubscribeArticle_Paramet(userId, aricleId);
        Logger.e("订阅求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.subscribeArticle(paramet), new ApiCallback<SubscribeArticleModle>() {
            @Override
            public void onSuccess(SubscribeArticleModle model) {
                mvpView.getSubscribeArticleData(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    public void loadCommentData(String articleId) {
        mvpView.showLoading();
        Comment_Paramet paramet = new Comment_Paramet(articleId, "5", "1", userId);
        Logger.e("评论参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.articleComment(paramet), new ApiCallback<ArticleCommentModle>() {
            @Override
            public void onSuccess(ArticleCommentModle model) {
                mvpView.getArticleCommentData(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 买书
     * @param articleId
     * @param payMoney
     */
    public void buyBook(String articleId, String payMoney) {
        mvpView.showLoading();
        Purchase_Paramet paramet = new Purchase_Paramet(articleId,userId,payMoney);
        Logger.e("评论参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.payBook(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getPayResult(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 购买确认对话框
     * @param articleId
     * @param payMoney
     */
    public void showdialog(final String articleId, final String payMoney) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("确认购买本书吗？").setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                buyBook(articleId, payMoney);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
