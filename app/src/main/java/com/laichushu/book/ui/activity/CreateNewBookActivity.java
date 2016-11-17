package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.mvp.createnewbook.CreateNewBookModle;
import com.laichushu.book.mvp.createnewbook.CreateNewBookPersenter;
import com.laichushu.book.mvp.createnewbook.CreateNewBookView;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.github.lijunguan.imgselector.ImageSelector;

/**
 * 创建新书
 * Created by wangtong on 2016/11/17.
 */

public class CreateNewBookActivity extends MvpActivity2<CreateNewBookPersenter> implements View.OnClickListener, CreateNewBookView {

    private EditText booknameEt;//书名
    private TextView categoryTv;//分类内容
    private ImageView categoryIv;//分类选择
    private TextView permissionTv;//权限内容
    private ImageView permissionIv;//权限选择
    private Button createBtn;//创建
    private EditText briefEt;//简介
    private ImageView coverIv;//封面

    @Override
    protected CreateNewBookPersenter createPresenter() {
        return new CreateNewBookPersenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_createnewbook);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        booknameEt = (EditText) mSuccessView.findViewById(R.id.et_bookname);//书名
        categoryTv = (TextView) mSuccessView.findViewById(R.id.tv_category);//分类内容
        categoryIv = (ImageView) mSuccessView.findViewById(R.id.iv_category);//分类选择
        permissionTv = (TextView) mSuccessView.findViewById(R.id.tv_permission);//权限内容
        permissionIv = (ImageView) mSuccessView.findViewById(R.id.iv_permission);//权限选择
        briefEt = (EditText) mSuccessView.findViewById(R.id.et_brief);//简介
        createBtn = (Button) mSuccessView.findViewById(R.id.btn_create);//创建
        coverIv = (ImageView) mSuccessView.findViewById(R.id.iv_cover);//封面
        finishIv.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        coverIv.setOnClickListener(this);
        titleTv.setText("创建新书");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        mvpPresenter.loadCategoryData();
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                EventBus.getDefault().postSticky(new RefurshWriteFragmentEvent(true));
                break;
            case R.id.btn_create://创建

                break;
            case R.id.iv_cover://封面选择
                openAlertDialog();
                break;
        }
    }

    /**
     * 获取分类信息
     * @param modle
     */
    @Override
    public void getCategoryDataSuccess(CreateNewBookModle modle) {

    }
    /**
     * 提交服务器创建新书
     * @param modle
     */
    @Override
    public void getDataSuccess(HomeHotModel modle) {

    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 选择模版 对话框
     */
    private void openAlertDialog() {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
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
                ImageSelector imageSelector = ImageSelector.getInstance();
                mvpPresenter.loadConfig(imageSelector).startSelect(mActivity);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, this)                // 添加自定义View
                .show();
    }

    /**
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.REQUEST_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> imagesPath = data.getStringArrayListExtra(ImageSelector.SELECTED_RESULT);
                if (imagesPath != null && imagesPath.size()>0) {
                    String path = imagesPath.get(0);
                    GlideUitl.loadImg(mActivity,path,coverIv);
                }
            }
        }
    }
}
