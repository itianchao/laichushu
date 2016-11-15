package com.sofacity.laichushu.mvp.bookdetail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.JsonBean.BalanceBean;
import com.sofacity.laichushu.bean.JsonBean.RewardResult;
import com.sofacity.laichushu.bean.netbean.AuthorDetail_Paramet;
import com.sofacity.laichushu.bean.netbean.Balance_Paramet;
import com.sofacity.laichushu.bean.netbean.BestLike_Paramet;
import com.sofacity.laichushu.bean.netbean.CollectSave_Paramet;
import com.sofacity.laichushu.bean.netbean.Comment_Paramet;
import com.sofacity.laichushu.bean.netbean.Jurisdiction_Paramet;
import com.sofacity.laichushu.bean.netbean.Purchase_Paramet;
import com.sofacity.laichushu.bean.netbean.RewardMoney_Paramet;
import com.sofacity.laichushu.bean.netbean.ScoreLike_Paramet;
import com.sofacity.laichushu.bean.netbean.SubscribeArticle_Paramet;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.BookDetailActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

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

    public void loadSubscribeArticle(String aricleId, final String type) {
        mvpView.showLoading();
        SubscribeArticle_Paramet paramet = new SubscribeArticle_Paramet(userId, aricleId,type);
        Logger.e("订阅求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.subscribeArticle(paramet), new ApiCallback<SubscribeArticleModle>() {
            @Override
            public void onSuccess(SubscribeArticleModle model) {
                mvpView.getSubscribeArticleData(model,type);
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
     * 获取评论参数
     * @param articleId
     * @param type
     */
    public void loadCommentData(String articleId, String type) {
        mvpView.showLoading();
        Comment_Paramet paramet = new Comment_Paramet(articleId, "5", "1", userId,type);
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
     *
     * @param articleId
     * @param payMoney
     */
    public void buyBook(String articleId, String payMoney) {
        mvpView.showLoading();
        Purchase_Paramet paramet = new Purchase_Paramet(articleId, userId, payMoney);
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
     *
     * @param articleId
     * @param payMoney
     * @param balance
     * @param price
     */
    public void showdialog(final String articleId, final String payMoney, double balance, double price,String bookName,String authorName) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_pay);
        TextView balaneTv = (TextView) customerView.findViewById(R.id.tv_balance);
        TextView priceTv = (TextView) customerView.findViewById(R.id.tv_price);
        TextView bookTv = (TextView) customerView.findViewById(R.id.tv_book);
        TextView authorTv = (TextView) customerView.findViewById(R.id.tv_author);
        balaneTv.setText("当前余额：" + balance);
        priceTv.setText("￥" + price);
        bookTv.setText("书名" + bookName);
        authorTv.setText("作者" + authorName);
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
                buyBook(articleId, payMoney);
            }
        });
    }

    /**
     * 查询余额
     */
    public void getBalace() {
        mvpView.showLoading();
        Balance_Paramet paramet = new Balance_Paramet(userId);
        Logger.e("评论参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getBalance(paramet), new ApiCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean model) {
                mvpView.getBalanceData(model);
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
    public void getBalace2() {
        mvpView.showLoading();
        Balance_Paramet paramet = new Balance_Paramet(userId);
        Logger.e("评论参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getBalance(paramet), new ApiCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean model) {
                mvpView.getBalance2Data(model);
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
     * 打赏 对话框
     */
    public void openReward(String balance, final String accepterId, final String articleId) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_reward);
        final EditText payEt =  (EditText)customerView.findViewById(R.id.et_pay);
        TextView balanceTv =  (TextView)customerView.findViewById(R.id.tv_balance);
        balanceTv.setText(balance);
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
                String pay = payEt.getText().toString();

                if (TextUtils.isEmpty(pay)){
                    ToastUtil.showToast("请输入打赏金额");
                }else {
                    if (Integer.parseInt(pay)>0||Integer.parseInt(pay)<100){
                        // TODO: 2016/11/8 请求打赏
                        rewardMoney(userId,accepterId,articleId,pay);
                    }else {
                        ToastUtil.showToast("只能打赏1-100金额");
                    }
                }
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();
    }
    /**
     * 打赏请求
     * @param money
     * @param articleId
     * @param accepterId
     * @param awarderId
     */
    public void rewardMoney(String awarderId,String accepterId, String articleId, String money){
        mvpView.showLoading();
        RewardMoney_Paramet paramet = new RewardMoney_Paramet(awarderId,accepterId,articleId,money);
        Logger.e("打赏参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.rewardMoney(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getRewardMoneyData(model);
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
     * 点赞 取消赞
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, final String type){
        mvpView.showLoading();
        ScoreLike_Paramet paramet = new ScoreLike_Paramet(sourceId,userId,type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model,type);
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
     * 收藏
     * @param targetId
     * @param type
     * @param collectType
     */
    public void collectSave(String targetId, String type, String collectType) {
        mvpView.showLoading();
        CollectSave_Paramet paramet = new CollectSave_Paramet(userId,targetId,type,collectType);
        Logger.e("收藏");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.collectSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.collectSaveData(model);
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

    public void loadJurisdiction(String articleId) {
        mvpView.showLoading();
        Jurisdiction_Paramet paramet = new Jurisdiction_Paramet(articleId,userId,"1","1000");
        Logger.e("阅读权限");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getJurisdiction(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getJurisdictionData(model);
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
}

