package com.laichushu.book.mvp.draftmodle;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ChapterRename_Paramet;
import com.laichushu.book.bean.netbean.CreateNewDraft_Paramet;
import com.laichushu.book.bean.netbean.DeleteDraft_Paramet;
import com.laichushu.book.bean.netbean.DraftList_Paramet;
import com.laichushu.book.bean.netbean.MaterialRename_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

/**
 * 草稿模式 presenter
 * Created by wangtong on 2016/11/18.
 */
public class DraftModlePresenter extends BasePresenter<DraftModleView> {
    private DraftModleActivity mActivity;
    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE3;
    private String articleId = "";

    private DraftList_Paramet paramet = new DraftList_Paramet(articleId, pageNo, pageSize, userId);

    //初始化构造
    public DraftModlePresenter(DraftModleView view) {
        attachView(view);
        mActivity = (DraftModleActivity) view;
    }

    /**
     * 获取草稿列表
     */
    public void getDraftList(String articleId) {
        Logger.e("获取草稿列表");
        paramet.setArticleId(articleId);
        addSubscription(apiStores.getDraftList(paramet), new ApiCallback<DraftModle>() {
            @Override
            public void onSuccess(DraftModle model) {
                mvpView.getDataSuccess(model);
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

    public DraftList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(DraftList_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 删除草稿
     */
    public void deleteDraftBook(String articleId, final int position) {
        mvpView.showLoading();
        Logger.e("删除草稿");
        DeleteDraft_Paramet paramet = new DeleteDraft_Paramet(articleId, userId);
        addSubscription(apiStores.deleteDraftBook(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteDraftBookDataSuccess(model, position);
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
     * 草稿重命名接口
     *
     * @param index
     */
    public void chapterRename(String id, final String rename, final int index) {
        mvpView.showLoading();
        LoggerUtil.e("草稿重命名");
        ChapterRename_Paramet paramet = new ChapterRename_Paramet(id, rename, userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.chapterRename(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getChapterRenameDataSuccess(model, index, rename);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getChapterRenameDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 重命名对话框
     *
     * @param id
     * @param index
     */
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
                    chapterRename(id, name, index);//重命名草稿接口
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
     * 创建草稿对话框
     */
    public void openNewDialog(final String articleId) {
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
        dialogEt.setHint("请输入草稿名称");
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogEt.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    dialogEt.startAnimation(ShakeAnim.shakeAnimation(3));
                } else {
                    createNewDraft(articleId, name, "");
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
     * 新建草稿
     *
     * @param articleId 书Id
     * @param name      草稿标题
     * @param content   内容
     */
    public void createNewDraft(String articleId, String name, String content) {
        mvpView.showLoading();
        Logger.e("新建草稿");
        CreateNewDraft_Paramet params = new CreateNewDraft_Paramet(articleId, name, content, userId);
        addSubscription(apiStores.createNewDraft(params), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getCreateNewDraftDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail3("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
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
                deleteDraftBook(id, index);//删除素材接口
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
