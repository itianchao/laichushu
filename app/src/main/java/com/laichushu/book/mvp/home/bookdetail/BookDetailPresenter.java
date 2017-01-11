package com.laichushu.book.mvp.home.bookdetail;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.JsonBean.UrlResult;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.DownloadEpubFilePermission_Paramet;
import com.laichushu.book.bean.netbean.FindByBookId_Paramet;
import com.laichushu.book.bean.netbean.Jurisdiction_Paramet;
import com.laichushu.book.bean.netbean.Purchase_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.bean.netbean.SubscribeArticle_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.bean.otherbean.BaseBookEntity;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 图书详情 presenter
 * Created by wangtong on 2016/10/12.
 */
public class BookDetailPresenter extends BasePresenter<BookDetailView> {
    private BookDetailActivity mActivity;
    private String userId = ConstantValue.USERID;

    //初始化构造
    public BookDetailPresenter(BookDetailView view) {
        attachView(view);
        mActivity = (BookDetailActivity) view;
    }

    /**
     * 根据Id获取图书
     *
     * @param articleId 图书Id
     */
    public void getBookById(String articleId) {
        FindByBookId_Paramet paramet = new FindByBookId_Paramet(userId, articleId);
        addSubscription(apiStores.getBookById(paramet), new ApiCallback<BookDetailModle>() {
            @Override
            public void onSuccess(BookDetailModle model) {
                mvpView.getBookDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getBookDataError(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 订阅
     *
     * @param aricleId
     * @param type
     */
    public void loadSubscribeArticle(String aricleId, final String type) {
        mvpView.showLoading();
        SubscribeArticle_Paramet paramet = new SubscribeArticle_Paramet(userId, aricleId, type);
        Logger.e("订阅求参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.subscribeArticle(paramet), new ApiCallback<SubscribeArticleModle>() {
            @Override
            public void onSuccess(SubscribeArticleModle model) {
                mvpView.getSubscribeArticleData(model, type);
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
     *
     * @param articleId
     * @param type
     */
//    public void loadCommentData(String articleId, String type) {
//        mvpView.showLoading();
//        Comment_Paramet paramet = new Comment_Paramet(articleId, "5", "1", userId, type);
//        Logger.e("评论参数");
//        Logger.json(new Gson().toJson(paramet));
//        addSubscription(apiStores.articleComment(paramet), new ApiCallback<ArticleCommentModle>() {
//            @Override
//            public void onSuccess(ArticleCommentModle model) {
//                mvpView.getArticleCommentData(model);
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                mvpView.getDataFail("code+" + code + "/msg:" + msg);
//            }
//
//            @Override
//            public void onFinish() {
//                mvpView.hideLoading();
//            }
//        });
//    }

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
    public void showdialog(final String articleId, final String payMoney, double balance, double price, String bookName, String authorName) {

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
                mActivity.getBean().setPurchase(true);
                mActivity.getArticleData().setPurchase(true);
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
     * 查询余额
     */
    public void getBalace() {
        mvpView.showLoading();
        Balance_Paramet paramet = new Balance_Paramet(userId);
        Logger.e("余额参数");
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
        Logger.e("余额参数");
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
    public void openReward(String balance, final String accepterId, final String articleId, final double maxLimit, final double minLimit) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_reward);
        final EditText payEt = (EditText) customerView.findViewById(R.id.et_pay);
        TextView balanceTv = (TextView) customerView.findViewById(R.id.tv_balance);
        payEt.setHint("只能打赏"+minLimit+"-"+maxLimit+"金额");
        balanceTv.setText("当前余额："+balance);
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
                String pay = payEt.getText().toString();

                if (TextUtils.isEmpty(pay)) {
                    ToastUtil.showToast("请输入打赏金额");
                } else {
                    try {
                        if (Double.parseDouble(pay) >= minLimit && Double.parseDouble(pay) <= maxLimit) {
                            if (pay.contains(".") && pay.substring(pay.indexOf(".")+1,pay.length()).length()>2) {
                                ToastUtil.showToast("不能超过小数点后两位");
                            } else {
                                // TODO: 2016/11/8 请求打赏
                                rewardMoney(userId, accepterId, articleId, pay);
                                mActivity.getBean().setAward(true);
                                mActivity.getArticleData().setAward(true);
                                dialogBuilder.dismiss();
                            }

                        } else {
                            ToastUtil.showToast("只能打赏"+minLimit+"-"+maxLimit+"金额");
                        }
                    } catch (NumberFormatException e) {
                        ToastUtil.showToast("请输入正确的价格");
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
     *
     * @param money
     * @param articleId
     * @param accepterId
     * @param awarderId
     */
    public void rewardMoney(String awarderId, String accepterId, String articleId, String money) {
        mvpView.showLoading();
        RewardMoney_Paramet paramet = new RewardMoney_Paramet(awarderId, accepterId, articleId, money);
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
     *
     * @param sourceId
     * @param type
     * @param likeIv
     */
    public void saveScoreLikeData(String sourceId, final String type, final ImageView likeIv) {
        mvpView.showLoading();
        String sourceType = "1";
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId, sourceId, sourceType, type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model, type, likeIv);
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
     *
     * @param targetId
     * @param type
     * @param collectType
     */
    public void collectSave(String targetId, String type, String collectType) {
        mvpView.showLoading();
        CollectSave_Paramet paramet = new CollectSave_Paramet(userId, targetId, collectType, type);
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
        Jurisdiction_Paramet paramet = new Jurisdiction_Paramet(articleId, userId, "1", "1000");
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

    /**
     * 获取下载url接口文件
     *
     * @param articleId
     */
    public void getDownloadUrl(final String articleId, final String author, final String photo, final String brife) {
        mvpView.showLoading();
        DownloadEpubFilePermission_Paramet paramet = new DownloadEpubFilePermission_Paramet(articleId);
        addSubscription(apiStores.downloadEpubFile(paramet), new ApiCallback<UrlResult>() {
            @Override
            public void onSuccess(UrlResult modle) {
                if (modle.isSuccess()) {
                    String url = modle.getData();
                    String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + ".epub";
                    if (new File(path).exists()) {
                        mvpView.hideLoading();
                        BaseBookEntity baseBookEntity = new BaseBookEntity();
                        baseBookEntity.setBook_path(path);
                        UIUtil.startBookFBReaderActivity(mActivity, baseBookEntity, articleId, author,photo,brife);
                    } else {
                        downloadEpub(url, articleId, author,photo,brife);
                    }

                } else {
                    ToastUtil.showToast(modle.getErrMsg());
                    mvpView.hideLoading();
                }

            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.showToast("请检查网络");
                mvpView.hideLoading();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 下载epub文件
     *
     * @param url
     * @param articleId
     * @param author
     */
    public void downloadEpub(final String url, final String articleId, final String author,final String photo, final String brife) {

        Call<ResponseBody> call = apiStores.downloadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), articleId,"epub");
                    if (writtenToDisk) {
                        mvpView.hideLoading();
                        String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + ".epub";
                        BaseBookEntity baseBookEntity = new BaseBookEntity();
                        baseBookEntity.setBook_path(path);
                        UIUtil.startBookFBReaderActivity(mActivity, baseBookEntity, articleId, author,photo,brife);
                    } else {
                        ToastUtil.showToast("请检查网络");
                    }
                } else {
                    mvpView.hideLoading();
                    ToastUtil.showToast("请检查网络");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mvpView.hideLoading();
                ToastUtil.showToast("请检查网络");
            }
        });
    }

    /**
     * 写入文件
     *
     * @param body
     * @param articleId
     * @return
     */
    private boolean writeResponseBodyToDisk(ResponseBody body, String articleId,String suffix) {
        try {
            // todo change the file location/name according to your needs
            String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + "."+suffix;

            File futureStudioIconFile = new File(path);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                mvpView.hideLoading();
                futureStudioIconFile.delete();
                return false;
            } finally {
                mvpView.hideLoading();
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            mvpView.hideLoading();
            String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + "."+suffix;
            File futureStudioIconFile = new File(path);
            futureStudioIconFile.delete();
            return false;
        }
    }
}

