package com.laichushu.book.mvp.write.createnewmaterial;

import android.support.v4.util.ArrayMap;
import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CreateSourceMaterial_Paramet;
import com.laichushu.book.bean.netbean.EditMaterialBook_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.write.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CreateMaterialActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.widget.MineRichEditor;
import com.laichushu.book.utils.LoggerUtil;
import com.orhanobut.logger.Logger;

import java.io.File;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 创建素材 presenter
 * Created by wangtong on 2016/10/12.
 */
public class CreateNewMaterialPresenter extends BasePresenter<CreateNewMaterialView> {
    private CreateMaterialActivity mActivity;
    private String userId = ConstantValue.USERID;

    //初始化构造
    public CreateNewMaterialPresenter(CreateNewMaterialView view) {
        attachView(view);
        mActivity = (CreateMaterialActivity) view;
    }

    /**
     * 创建素材文件夹
     *
     * @param articleId    书Id
     * @param materialName 内容
     * @param parentId     文件夹Id
     * @param content      内容
     */
    public void createSourceMaterial(String articleId, String materialName, String parentId, String content) {

        LoggerUtil.e("创建素材文件夹");
        CreateSourceMaterial_Paramet paramet = new CreateSourceMaterial_Paramet(articleId, materialName, parentId, content);
        LoggerUtil.toJson(paramet);
        mvpView.showLoading();
        addSubscription(apiStores.createSourceMaterial(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getCreateSourceMaterial(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code：" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    public void setfunction(View mSuccessView, final MineRichEditor mEditor) {
        mSuccessView.findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });

        mSuccessView.findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.redo();
            }
        });

        mSuccessView.findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        mSuccessView.findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });

//        mSuccessView.findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSubscript();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSuperscript();
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setStrikeThrough();
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setUnderline();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(1);
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(2);
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(3);
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(4);
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(5);
//            }
//        });
//
//        mSuccessView.findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(6);
//            }
//        });

//        mSuccessView.findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
//            private boolean isChanged;
//
//            @Override public void onClick(View v) {
//                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
//                isChanged = !isChanged;
//            }
//        });

//        mSuccessView.findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
//            private boolean isChanged;
//
//            @Override public void onClick(View v) {
//                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
//                isChanged = !isChanged;
//            }
//        });

//        mSuccessView.findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.setIndent();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.setOutdent();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.setAlignLeft();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.setAlignCenter();
//            }
//        });

//        mSuccessView.findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.setAlignRight();
//            }
//        });

        mSuccessView.findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        mSuccessView.findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        mSuccessView.findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

//        mSuccessView.findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
//            }
//        });
//        mSuccessView.findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                mEditor.insertTodo();
//            }
//        });
    }

    /**
     * 转换网络图片
     *
     * @param file 上传文件
     */
    public void commitPhoto(File file) {
        mvpView.showLoading();
        Logger.e("转换网络图片");
        ArrayMap<String, RequestBody> params = new ArrayMap<>();
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(file));
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), userId);

        params.put("userId", requestBody2);

        addSubscription(apiStores.uploadImg(requestBody1, params), new ApiCallback<CreateNewDraftModle>() {
            @Override
            public void onSuccess(CreateNewDraftModle model) {
                mvpView.getCommitPhotoDataSuccess(model);
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

    public void editMaterialBook(String id, String materialName, String content) {
        mvpView.showLoading();
        Logger.e("保存编辑");
        EditMaterialBook_Paramet paramet = new EditMaterialBook_Paramet(id, materialName, content, userId);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.editMaterialBook(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getEditMaterialDataSuccess(model);
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
}
