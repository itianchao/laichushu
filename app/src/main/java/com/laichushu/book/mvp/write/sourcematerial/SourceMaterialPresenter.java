package com.laichushu.book.mvp.write.sourcematerial;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.DeleteMaterial_Paramet;
import com.laichushu.book.bean.netbean.MaterialRename_Paramet;
import com.laichushu.book.bean.netbean.SourceMaterialList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.SourceMaterialActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 素材 presenter
 * Created by wangtong on 2016/11/16.
 */
public class SourceMaterialPresenter extends BasePresenter<SourceMaterialView> {
    private SourceMaterialActivity mActivity;
    private String userId = ConstantValue.USERID;

    //初始化构造
    public SourceMaterialPresenter(SourceMaterialView view) {
        attachView(view);
        mActivity = (SourceMaterialActivity) view;
    }

    /**
     * 获取素材列表
     *
     * @param parentId
     */
    public void getSourceMaterialList(String parentId) {
        LoggerUtil.e("获取素材列表");
        SourceMaterialList_Paramet paramet = new SourceMaterialList_Paramet(parentId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getSourceMaterialList(paramet), new ApiCallback<SourceMaterialModle>() {
            @Override
            public void onSuccess(SourceMaterialModle model) {
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

    public void openDeleteDialog(final String id,final int index) {
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
                deleteMaterial(id,index);//删除素材接口
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
     * @param id
     */
    public void deleteMaterial(String id, final int index){
        mvpView.showLoading();
        LoggerUtil.e("获取素材文件夹列表");
        DeleteMaterial_Paramet paramet = new DeleteMaterial_Paramet(id,userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.deleteMaterial(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteMateialDataSuccess(model,index);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDeleteMateialDataFail("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 素材重命名接口
     * @param index
     */
    public void materialRename(String id, final String rename, final int index){
        mvpView.showLoading();
        LoggerUtil.e("素材重命名");
        MaterialRename_Paramet paramet = new MaterialRename_Paramet(id,rename,userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.materialRename(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getMaterialRenameDataSuccess(model,index,rename);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getMaterialRenameDataFail("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
    public void openRameDialog(final String id,final int index) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_rename);
        TextView dialogTitleTv = (TextView) customerView.findViewById(R.id.tv_dialog_title);
        dialogTitleTv.setText("确认修改素材名称?");
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
                if(TextUtils.isEmpty(name)){
                    dialogEt.startAnimation(ShakeAnim.shakeAnimation(3));
                }else {
                    materialRename(id, name,index);//删除素材接口
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
}
