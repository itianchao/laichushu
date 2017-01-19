package com.laichushu.book.mvp.write.sourcematerialdir;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CreateSourceMaterialDir_Paramet;
import com.laichushu.book.bean.netbean.DeleteMaterial_Paramet;
import com.laichushu.book.bean.netbean.MaterialRename_Paramet;
import com.laichushu.book.bean.netbean.SourceMaterialDirList_Paramet;
import com.laichushu.book.bean.netbean.UpdateMaterialPermission_Paramet;
import com.laichushu.book.global.ConstantValue;
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
                mvpView.hideLoading();
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

    public void openRenameDialog(final String id, final int index) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_rename);
        TextView dialogTitleTv = (TextView) customerView.findViewById(R.id.tv_dialog_title);
//        dialogTitleTv.setText("确认修改素材文件夹名称?");
        dialogTitleTv.setText("修改素材文件夹名称?");
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        final EditText dialogEt = (EditText) customerView.findViewById(R.id.et_dialog);
        dialogEt.setHint("");
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogEt.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    dialogEt.startAnimation(ShakeAnim.shakeAnimation(3));
                } else {
                    materialRename(id, name, index);//素材重命名接口
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
     * @param materialPermission
     */
    public void openPermissionAlertDialog(Activity mActivity, final String articleId, String materialPermission) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_permission);
        RadioButton publicRbn = (RadioButton) customerView.findViewById(R.id.rbn_public);
        RadioButton privateRbn = (RadioButton) customerView.findViewById(R.id.rbn_private);
        RadioButton masterRbn = (RadioButton) customerView.findViewById(R.id.rbn_master);
        RadioButton pressRbn = (RadioButton) customerView.findViewById(R.id.rbn_press);
        RadioButton fansRbn = (RadioButton) customerView.findViewById(R.id.rbn_fans);
        ImageView cancelBtn = (ImageView) customerView.findViewById(R.id.btn_cancel);

        switch(materialPermission){
            case "1":
                publicRbn.setChecked(true);
                break;
            case "2":
                privateRbn.setChecked(true);
                break;
        }
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
    public void loadUpdateMerPermission(String id, final String permission) {
        mvpView.showLoading();
        LoggerUtil.e("发表");
        UpdateMaterialPermission_Paramet merParamet = new UpdateMaterialPermission_Paramet(userId, id, permission);
        addSubscription(apiStores.getUpdateMaterialPermissionDetails(merParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getUpdateMerPermission(model,permission);
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

    /**
     * 创建素材对话框
     */
    public void openNewDialog(final String articleId) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_rename);
        TextView dialogTitleTv = (TextView) customerView.findViewById(R.id.tv_dialog_title);
//        dialogTitleTv.setText("创建素材");
        dialogTitleTv.setText("添加文件夹");
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        final EditText dialogEt = (EditText) customerView.findViewById(R.id.et_dialog);
//        dialogEt.setHint("请输入素材名称");
        dialogEt.setHint("");
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogEt.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    dialogEt.startAnimation(ShakeAnim.shakeAnimation(3));
                }else {
                    createSourceMaterialDir(articleId,name);
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
     * 创建素材文件夹
     * @param articleId
     * @param foldName
     */
    public void createSourceMaterialDir(String articleId, String foldName){

        LoggerUtil.e("创建素材文件夹");
        CreateSourceMaterialDir_Paramet paramet = new CreateSourceMaterialDir_Paramet(articleId,foldName);
        LoggerUtil.toJson(paramet);
        mvpView.showLoading();
        addSubscription(apiStores.createSourceMaterialDir(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getCreateSourceMaterialDir(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code："+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
