package com.laichushu.book.mvp.creatnewdraft;

import android.support.v4.util.ArrayMap;
import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CreateNewDraft_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CreatNewDraftActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

import java.io.File;

import id.zelory.compressor.Compressor;
import jp.wasabeef.richeditor.RichEditor;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 创建草稿
 * Created by wangtong on 2016/11/4.
 */
public class CreateNewDraftPersenter extends BasePresenter<CreateNewDraftView> {

    private CreatNewDraftActivity mActivity;

    private String userId = ConstantValue.USERID;
    ;

    public CreateNewDraftPersenter(CreateNewDraftView view) {
        attachView(view);
        this.mActivity = (CreatNewDraftActivity) view;
    }

    public void setfunction(View mSuccessView, final RichEditor mEditor) {
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

        mSuccessView.findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        mSuccessView.findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        mSuccessView.findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        mSuccessView.findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        mSuccessView.findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        mSuccessView.findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        mSuccessView.findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        mSuccessView.findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        mSuccessView.findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        mSuccessView.findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

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
                mvpView.getDataSuccess(model);
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
