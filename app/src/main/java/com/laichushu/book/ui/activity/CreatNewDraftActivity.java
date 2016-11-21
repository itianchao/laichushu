package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftPersenter;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.MineRichEditor;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

/**
 * 创建草稿
 * Created by wangtong on 2016/11/19.
 */

public class CreatNewDraftActivity extends MvpActivity2<CreateNewDraftPersenter> implements CreateNewDraftView, View.OnClickListener {

    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private TextView titleTv;
    private ImageView finishIv;
    private EditText draftmEt;
    private MineRichEditor mEditor;
    private Button createBtn;
    private StringBuilder builder;
    private String articleId;
    private String path = "";
    private String type;

    @Override
    protected View createSuccessView() {
        articleId = getIntent().getStringExtra("articleId");
        View mSuccessView = UIUtil.inflate(R.layout.activity_creatnewdraft);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        draftmEt = (EditText) mSuccessView.findViewById(R.id.et_draft);
        mEditor = (MineRichEditor) mSuccessView.findViewById(R.id.editor);
        createBtn = (Button) mSuccessView.findViewById(R.id.btn_create);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //    mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("从这里开始");
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                builder = new StringBuilder(text);
                LoggerUtil.e(builder.toString());
            }
        });
        mSuccessView.findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                        , 1                                                         // 指定选择数量。
                        , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。

            }
        });
        mvpPresenter.setfunction(mSuccessView, mEditor);
        titleTv.setText("创建草稿");
        finishIv.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }else {
            //TODO: 2016/11/21 请求草稿 并设置进去
//            mvpPresenter.
        }
    }

    @Override
    protected CreateNewDraftPersenter createPresenter() {
        return new CreateNewDraftPersenter(this);
    }

    @Override
    public void getDataSuccess(RewardResult modle) {
        hideLoading();
        if (modle.isSuccess()) {
            ToastUtil.showToast("创建成功");
        } else {
            Logger.e(modle.getErrMsg());
            ToastUtil.showToast("创建失败");
        }
    }

    /**
     * 本地图片转换成网络图片
     *
     * @param modle
     */
    @Override
    public void getCommitPhotoDataSuccess(CreateNewDraftModle modle) {
        if (modle.isSuccess()) {
            String data = modle.getData();
            path = ConstantValue.PHOTO_SEVERCE_PATH + data;
            LoggerUtil.e(path);
            mEditor.insertImage(path, "dachshund");
        } else {
            ToastUtil.showToast("添加图片失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
        Logger.e(msg);
        ToastUtil.showToast("创建失败");
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        Logger.e(msg);
        ToastUtil.showToast("图片添加失败");
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.btn_create:
                String name = draftmEt.getText().toString();
                String content = builder.toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showToast("请输入章节名称");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showToast("请输入章节内容");
                    return;
                }
                mvpPresenter.createNewDraft(articleId, name, content);
                break;
        }
    }

    /**
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> imagesPath = Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                //压缩图片
                showLoading();
                File file = new File(path);
                mvpPresenter.commitPhoto(file);
            }
        }
    }
}
