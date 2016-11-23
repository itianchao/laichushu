package com.laichushu.book.mvp.createnewbook;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ArticleSave_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CoverDirActivity;
import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;

import java.io.File;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 评价详情
 * Created by wangtong on 2016/11/4.
 */
public class CreateNewBookPersenter extends BasePresenter<CreateNewBookView> {
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private CreateNewBookActivity mActivity;
    private String userId = ConstantValue.USERID;

    public CreateNewBookPersenter(CreateNewBookView view) {
        attachView(view);
        this.mActivity = (CreateNewBookActivity) view;
    }

    /**
     * 获取分类信息
     */
    public void loadCategoryData() {
        Logger.e("获取分类信息");
        addSubscription(apiStores.getCategoryList(), new ApiCallback<CategoryModle>() {
            @Override
            public void onSuccess(CategoryModle model) {
                mvpView.getCategoryDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code: " + code + " \n msg: " + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 提交服务器创建新书
     *
     * @param file
     * @param name
     * @param topCategoryId
     * @param subCategoryId
     * @param permission
     * @param introduce
     */
    public void commitNewBook(File file, String name, String topCategoryId, String subCategoryId, String permission, String introduce) {
        Logger.e("创建新书");
        mvpView.showLoading();
        ArticleSave_Paramet paramet = new ArticleSave_Paramet(userId, name, topCategoryId, subCategoryId, permission, introduce, file);
        Logger.json(new Gson().toJson(paramet));
        ArrayMap<String, RequestBody> params = new ArrayMap<>();
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(file));
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), topCategoryId);
        RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), subCategoryId);
        RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"), permission);
        RequestBody requestBody7 = RequestBody.create(MediaType.parse("multipart/form-data"), introduce);

//        params.put("file", requestBody1);
        params.put("userId", requestBody2);
        params.put("name", requestBody3);
        params.put("topCategoryId", requestBody4);
        params.put("subCategoryId", requestBody5);
        params.put("permission", requestBody6);
        params.put("introduce", requestBody7);

        addSubscription(apiStores.createNewBook(requestBody1, params), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.commitNewBook(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 选择模版 对话框
     * @param bookname
     */
    public void openAlertDialog(final String bookname) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_photo);

        //从模版中选择
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("bookname",bookname);
                UIUtil.openActivity(mActivity,CoverDirActivity.class,bundle);
            }
        });
        //从相册中选择
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                        , 1                                                         // 指定选择数量。
                        , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。
                dialogBuilder.dismiss();
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
     * 选择权限 对话框
     *
     * @param permissionTv
     */
    public void openPermissionAlertDialog(final TextView permissionTv) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_permission);
        RadioButton publicRbn = (RadioButton) customerView.findViewById(R.id.rbn_public);
        RadioButton privateRbn = (RadioButton) customerView.findViewById(R.id.rbn_private);
        RadioButton masterRbn = (RadioButton) customerView.findViewById(R.id.rbn_master);
        RadioButton pressRbn = (RadioButton) customerView.findViewById(R.id.rbn_press);
        RadioButton fansRbn = (RadioButton) customerView.findViewById(R.id.rbn_fans);
        ImageView cancelBtn = (ImageView) customerView.findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        publicRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setPermission("1");
                dialogBuilder.dismiss();
                permissionTv.setText("默认公开");
            }
        });
        privateRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setPermission("2");
                permissionTv.setText("仅个人");
                dialogBuilder.dismiss();

            }
        });
        masterRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setPermission("3");
                permissionTv.setText("大咖");
                dialogBuilder.dismiss();
            }
        });
        pressRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setPermission("4");
                permissionTv.setText("出版社");
                dialogBuilder.dismiss();
            }
        });
        fansRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setPermission("5");
                permissionTv.setText("粉丝");
                dialogBuilder.dismiss();
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

    public boolean isCheck(String name, String introduce, String category, String path) {
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast("请输入书名");
            return false;
        }
        if (TextUtils.isEmpty(introduce)){
            ToastUtil.showToast("请输入简介");
            return false;
        }
        if (TextUtils.isEmpty(category)){
            ToastUtil.showToast("请选择分类");
            return false;
        }
        if (TextUtils.isEmpty(path)){
            ToastUtil.showToast("请选择图片");
        }
        return true;
    }
}
