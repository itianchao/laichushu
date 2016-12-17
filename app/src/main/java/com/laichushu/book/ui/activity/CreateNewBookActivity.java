package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.event.RefurshPhotoPathEvent;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.createnewbook.CreateNewBookModle;
import com.laichushu.book.mvp.createnewbook.CreateNewBookPersenter;
import com.laichushu.book.mvp.createnewbook.CreateNewBookView;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypeCategoryPopWindow;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


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
    private LinearLayout categoryLay;//分类容器
    private LinearLayout permissionLay;//权限容器
    private ArrayList<CategoryModle.DataBean> mParentData = new ArrayList<>();
    private File compressedImageFile;
    private String permission = "1";
    private String parentId;
    private String childId;
    private String path;

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
        permissionLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_permission);//封面
        finishIv.setOnClickListener(this);
        categoryLay.setOnClickListener(this);
        coverIv.setOnClickListener(this);
        titleTv.setText("创建新书");
        categoryIv.setOnClickListener(this);
        permissionLay.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        mvpPresenter.loadCategoryData();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                break;
            case R.id.btn_create://创建
                String name = booknameEt.getText().toString().trim();
                String introduce = briefEt.getText().toString().trim();
                String category = categoryTv.getText().toString().trim();
                if(mvpPresenter.isCheck(name, introduce, category, path)){
                    if (null!=compressedImageFile){
                        mvpPresenter.commitNewBook(compressedImageFile, name, parentId, childId, permission, introduce, "");
                    }else {
                        mvpPresenter.commitNewBook(null, name, parentId, childId, permission, introduce, path);
                    }
                }
                break;
            case R.id.iv_cover://封面选择
                path = "";
                compressedImageFile = null;
                String bookname = booknameEt.getText().toString().trim();
                if (TextUtils.isEmpty(bookname)){
                    ToastUtil.showToast("请先输入书名");
                    return;
                }
                mvpPresenter.openAlertDialog(bookname);
                break;
            case R.id.lay_category://分类
                final TypeCategoryPopWindow popWindow = new TypeCategoryPopWindow(mActivity, mParentData);
                popWindow.setListItemClickListener(new TypeCategoryPopWindow.IListItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        int i = popWindow.getCount()[0];
                        categoryTv.setText(mParentData.get(i).getChild().get(position).getName());
                        parentId = mParentData.get(i).getId();
                        childId = mParentData.get(i).getChild().get(position).getId();
                    }
                });
                popWindow.setWidth(v.getWidth());
                popWindow.setHeight(UIUtil.dip2px(300));
                popWindow.showAsDropDown(v);
                break;
            case R.id.lay_permission:
                mvpPresenter.openPermissionAlertDialog(permissionTv);
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

    @Override
    public void getDataSuccess(CreateNewBookModle modle) {

    }
    /**
     * 提交服务器创建新书
     *
     * @param modle
     */

    @Override
    public void commitNewBook(RewardResult modle) {
        hideLoading();
        if (modle.isSuccess()) {
            ToastUtil.showToast("创建成功");
            Cache_JsonDao cache_jsonDao = BaseApplication.getDaoSession(this).getCache_JsonDao();
            List<Cache_Json> cache_jsons = cache_jsonDao.queryBuilder()
                    .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).build().list();
            Cache_Json cache_json = cache_jsons.get(0);
            PersonalCentreResult result = new Gson().fromJson(cache_json.getJson(), PersonalCentreResult.class);
            if (result.getArticleCount() != null) {
                result.setArticleCount(Integer.parseInt(result.getArticleCount())+1+"");
            }else {
                result.setArticleCount("1");
            }
            cache_json.setJson(new Gson().toJson(result));
            cache_jsonDao.update(cache_json);
            UIUtil.postDelayed(new TimerTask() {
                @Override
                public void run() {
                    EventBus.getDefault().postSticky(new RefurshWriteFragmentEvent(true));
                    finish();
                }
            },1700);
        }else {
            ToastUtil.showToast("创建失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
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

    @Override
    public void getDataFail2(String s) {
        Logger.e(s);
        ToastUtil.showToast("加载失败");
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
                path = imagesPath.get(0);
                GlideUitl.loadImg(mActivity, path, coverIv);
                //压缩图片
                compressedImageFile = new File(path);
            }
        }
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshPhotoPathEvent event){
        EventBus.getDefault().removeStickyEvent(event);
        if (!TextUtils.isEmpty(event.url)){
            path = event.url;
            GlideUitl.loadImg(mActivity, path, coverIv);
        }
    }
}
