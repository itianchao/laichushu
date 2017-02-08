package com.laichushu.book.retrofit;


import android.text.TextUtils;

import com.laichushu.book.bean.netbean.BaseModel;
import com.laichushu.book.global.ErrorCodeValue;
import com.laichushu.book.utils.ArticleDialogUtil;
import com.laichushu.book.utils.DialogUtil;
import com.laichushu.book.utils.NetDialogUtil;
import com.orhanobut.logger.Logger;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * retrofit 回调方法
 */
public abstract class ApiCallback<M extends BaseModel> extends Subscriber<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Logger.e("code: ", code);
            if (code == 504) {
                msg = "网络不给力";
                onFailure(code, msg);
                NetDialogUtil.showToast("网络错误，请检查网络！");
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
                onFailure(code, msg);
                NetDialogUtil.showToast("服务器异常，请稍后再试！");
            }
            onFailure(code, msg);
        } else {
            onFailure(0, "网络错误，请检查网络！");
            NetDialogUtil.showToast("网络错误，请检查网络！");
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        String code = model.getErrCode();
        if (!TextUtils.isEmpty(code)) {
            switch (code) {
                case ErrorCodeValue.TOKEN_ILLEGAL:
                    DialogUtil.showDialog();
                    break;
                case ErrorCodeValue.ARTICLE_DELETE:
                    ArticleDialogUtil.showDialog("图书已被删除，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_FREEZE:
                    ArticleDialogUtil.showDialog("图书已被冻结，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_UNONLINE:
                    ArticleDialogUtil.showDialog("图书为线下图书，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_ADD:
                    ArticleDialogUtil.showDialog("图书已下架，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_PUB:
                    ArticleDialogUtil.showDialog("图书出版中，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_NO_PERMISSION:
                    ArticleDialogUtil.showDialog("图书权限不足，页面即将关闭");
                    break;
                case ErrorCodeValue.ARTICLE_NO_EXIST:
                    ArticleDialogUtil.showDialog("图书不存在，页面即将关闭");
                    break;
            }
        } else {
            onSuccess(model);
        }
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
