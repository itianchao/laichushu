package com.laichushu.book.mvp.write;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.laichushu.book.bean.netbean.Regist_Paramet;
import com.laichushu.book.mvp.regist2.RegistModel2;
import com.laichushu.book.mvp.regist2.RegistView2;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.Regist2Activity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.Validator;

/**
 * 写书 presenter
 * Created by wangtong on 2016/11/16.
 */
public class WritePresenter extends BasePresenter<WriteView> {

    //初始化构造
    public WritePresenter(WriteView view) {
        attachView(view);
    }

}
