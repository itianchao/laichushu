package com.laichushu.book.mvp.createnewbook;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;


/**
 * 评价详情
 * Created by wangtong on 2016/11/4.
 */
public class CreateNewBookPersenter extends BasePresenter<CreateNewBookView> {
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private CreateNewBookActivity mActivity;

    private String userId = SharePrefManager.getUserId();

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
     */
    public void commitNewBook() {
        Logger.e("创建新书");
    }

    /**
     * 选择模版 对话框
     */
    public void openAlertDialog() {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_photo);

        //从模版中选择
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
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
}
