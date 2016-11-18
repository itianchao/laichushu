package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.mvp.createnewbook.CreateNewBookModle;
import com.laichushu.book.mvp.createnewbook.CreateNewBookPersenter;
import com.laichushu.book.mvp.createnewbook.CreateNewBookView;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypeCategoryPopWindow;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


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
    private ArrayList<CategoryModle.DataBean> mParentData = new ArrayList<>();
    private LinearLayout categoryLay;

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
        categoryLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_category);//封面
        finishIv.setOnClickListener(this);
        categoryLay.setOnClickListener(this);
        coverIv.setOnClickListener(this);
        titleTv.setText("创建新书");
        categoryIv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        mvpPresenter.loadCategoryData();
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
                mvpPresenter.openAlertDialog();
                break;
            case R.id.lay_category://分类
                final TypeCategoryPopWindow popWindow = new TypeCategoryPopWindow(mActivity, mParentData);
                popWindow.setListItemClickListener(new TypeCategoryPopWindow.IListItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        int i = popWindow.getCount()[0];
                        categoryTv.setText(mParentData.get(i).getChild().get(position).getName());
                    }
                });
                popWindow.setWidth(v.getWidth());
                popWindow.setHeight(UIUtil.dip2px(300));
                popWindow.showAsDropDown(v);
                break;
        }
    }

    /**
     * 获取分类信息
     *
     * @param modle
     */
    @Override
    public void getCategoryDataSuccess(CategoryModle modle) {
        if (modle.isSuccess()) {
            mParentData = modle.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(modle.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    /**
     * 提交服务器创建新书
     *
     * @param modle
     */
    @Override
    public void getDataSuccess(CreateNewBookModle modle) {

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
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> imagesPath = Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                GlideUitl.loadImg(mActivity, path, coverIv);
            }
        }
    }
}
