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
import com.laichushu.book.event.RefurshMaterialEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.createnewmaterial.CreateNewMaterialPresenter;
import com.laichushu.book.mvp.createnewmaterial.CreateNewMaterialView;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.MineRichEditor;
import com.laichushu.book.utils.HtmlUtil;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;


/**
 * 创建素材页面
 * Created by wangtong on 2016/11/22.
 */

public class CreateMaterialActivity extends MvpActivity2<CreateNewMaterialPresenter> implements CreateNewMaterialView, View.OnClickListener {

    private TextView titleTv;
    private ImageView finishIv;
    private EditText materialEt;
    private Button createBtn;
    private String articleId;
    private String parentId;
    private MineRichEditor mEditor;
    private StringBuilder builder;
    private String path;
    private TextView materialTv;
    private String type;

    @Override
    protected CreateNewMaterialPresenter createPresenter() {
        return new CreateNewMaterialPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_createnewmaterial);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        materialEt = (EditText) mSuccessView.findViewById(R.id.et_material);
        createBtn = (Button) mSuccessView.findViewById(R.id.btn_create);
        materialTv = (TextView) mSuccessView.findViewById(R.id.tv_material);
        mEditor = (MineRichEditor) mSuccessView.findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
//            mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("请输入简介内容");

        mEditor.setOnTextChangeListener(new MineRichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                builder = new StringBuilder(text);
                LoggerUtil.e(builder.toString());
            }
        });
        mSuccessView.findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Album.startAlbum(mActivity, ConstantValue.ACTIVITY_REQUEST_SELECT_PHOTO
//                        , 1                                                         // 指定选择数量。
//                        , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
//                        , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。
                ToastUtil.showToast("不可加入图片");
            }
        });
        mvpPresenter.setfunction(mSuccessView, mEditor);


        finishIv.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        articleId = getIntent().getStringExtra("articleId");
        parentId = getIntent().getStringExtra("parentId");
        String title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");


//        ;
        //1、新建 2、编辑
        if (type.equals("1")) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            titleTv.setText("添加素材");
            materialTv.setText(title);
        } else {
            String dir = getIntent().getStringExtra("dir");
            materialEt.setHint(title);
            materialTv.setText(dir);
            createBtn.setText("修改");
            titleTv.setText("编辑素材");
            new Thread() {
                @Override
                public void run() {
                    try {
                        String html = getIntent().getStringExtra("html");
                        final String urlSource = HtmlUtil.getURLSource(html);
                        UIUtil.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEditor.setHtml(urlSource);
                                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    @Override
    public void getCreateSourceMaterial(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("创建成功");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().postSticky(new RefurshMaterialEvent(true));
                    finish();
                }
            }, 1700);
        } else {
            ToastUtil.showToast("创建失败");
            LoggerUtil.toJson(model);
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
//            path = ConstantValue.PHOTO_SEVERCE_PATH + data;
            path = data;
            LoggerUtil.e(path);
            mEditor.insertImage(path, "dachshund");
        } else {
            ToastUtil.showToast("添加图片失败");
        }
    }

    /**
     * 修改素材
     *
     * @param model
     */
    @Override
    public void getEditMaterialDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("修改成功");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().postSticky(new RefurshMaterialEvent(true));
                    finish();
                }
            }, 1700);
        } else {
            ToastUtil.showToast("修改失败");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
        LoggerUtil.e(msg);
        ToastUtil.showToast("创建失败");
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        LoggerUtil.e(msg);
        ToastUtil.showToast("修改失败");
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
            case R.id.btn_create:
                String name = materialEt.getText().toString();
                String content = "";
                if (builder != null) {
                    content = builder.toString();
                }
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showToast("请输入素材名称");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showToast("请输入素材内容");
                    return;
                }
                if (type.equals("1")) {
                    mvpPresenter.createSourceMaterial(articleId, name, parentId, content);
                } else {
                    mvpPresenter.editMaterialBook(parentId, name, content);
                }
                break;
            case R.id.iv_title_finish:
                finish();
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
