package com.laichushu.book.mvp.sourcematerialdir;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.DeleteMaterial_Paramet;
import com.laichushu.book.bean.netbean.MaterialRename_Paramet;
import com.laichushu.book.bean.netbean.SourceMaterialDirList_Paramet;
import com.laichushu.book.bean.netbean.UpdateBookPermission_Paramet;
import com.laichushu.book.bean.netbean.UpdateMaterialPermission_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.SourceMaterialDirActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 素材文件夹 presenter
 * Created by wangtong on 2016/11/16.
 */
public class SourceMaterialDirPresenter extends BasePresenter<SourceMaterialDirView> {
    private SourceMaterialDirActivity mActivity;
    private String userId = ConstantValue.USERID;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    private String permission = "1";

    //初始化构造
    public SourceMaterialDirPresenter(SourceMaterialDirView view) {
        attachView(view);
        mActivity = (SourceMaterialDirActivity) view;
    }

    /**
     * 获取素材文件夹列表
     *
     * @param articleId
     */
    public void getSourceMaterialList(String articleId) {
        LoggerUtil.e("获取素材文件夹列表");
        SourceMaterialDirList_Paramet paramet = new SourceMaterialDirList_Paramet(userId, articleId);
        addSubscription(apiStores.getSourceMaterialDirList(paramet), new ApiCallback<SourceMaterialDirModle>() {
            @Override
            public void onSuccess(SourceMaterialDirModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void openDeleteDialog(final String id, final int index) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_delete);

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
                deleteMaterial(id, index);//删除素材接口
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
     * 删除素材接口
     *
     * @param id
     * @param index
     */
    public void deleteMaterial(String id, final int index) {
        mvpView.showLoading();
        LoggerUtil.e("获取素材文件夹列表");
        DeleteMaterial_Paramet paramet = new DeleteMaterial_Paramet(id, userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.deleteMaterial(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteMateialDataSuccess(model, index);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDeleteMateialDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 素材重命名接口
     *
     * @param index
     */
    public void materialRename(String id, final String rename, final int index) {
        mvpView.showLoading();
        LoggerUtil.e("素材重命名");
        MaterialRename_Paramet paramet = new MaterialRename_Paramet(id, rename, userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.materialRename(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getMaterialRenameDataSuccess(model, index, rename);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getMaterialRenameDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void openRameDialog(final String id, final int index) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_rename);

        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        final EditText dialogEt = (EditText) customerView.findViewById(R.id.et_dialog);

        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogEt.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    dialogEt.startAnimation(ShakeAnim.shakeAnimation(3));
                } else {
                    materialRename(id, name, index);//删除素材接口
                    dialogBuilder.dismiss();
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
     * 选择权限 对话框
     *
     * @param
     */
    public void openPermissionAlertDialog(Activity mActivity, final String articleId) {

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
                setPermission("1");
//                permissionTv.setText("默认公开");
                loadUpdateMerPermission(articleId, getPermission());
                dialogBuilder.dismiss();

            }
        });
        privateRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPermission("2");
//                permissionTv.setText("仅个人");
                loadUpdateMerPermission(articleId, getPermission());
                dialogBuilder.dismiss();
            }
        });
        masterRbn.setVisibility(View.GONE);
        masterRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPermission("3");
//                permissionTv.setText("编辑");
                loadUpdateMerPermission(articleId, getPermission());
                dialogBuilder.dismiss();
            }
        });
        pressRbn.setVisibility(View.GONE);
        pressRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPermission("4");
//                permissionTv.setText("出版社");
                loadUpdateMerPermission(articleId, getPermission());
                dialogBuilder.dismiss();
            }
        });
        fansRbn.setVisibility(View.GONE);
        fansRbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPermission("5");
//                permissionTv.setText("粉丝");
                loadUpdateMerPermission(articleId, getPermission());
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

    //修改素材权限
    public void loadUpdateMerPermission(String id, String permission) {
        mvpView.showLoading();
        LoggerUtil.e("发表");
        UpdateMaterialPermission_Paramet merParamet = new UpdateMaterialPermission_Paramet(userId, id, permission);
        addSubscription(apiStores.getUpdateMaterialPermissionDetails(merParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getUpdateMerPermission(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
