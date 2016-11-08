package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.directories.BookMoudle;
import com.sofacity.laichushu.mvp.directories.DirectoriesPresenter;
import com.sofacity.laichushu.mvp.directories.DirectoriesView;
import com.sofacity.laichushu.mvp.directories.MaterialContentModel;
import com.sofacity.laichushu.mvp.directories.MaterialListModel;
import com.sofacity.laichushu.ui.adapter.BookListAdapter;
import com.sofacity.laichushu.ui.adapter.DirectoriesAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;

import java.util.ArrayList;

/**
 * 目录
 * Created by wangtong on 2016/11/7.
 */
public class DirectoriesActivity extends MvpActivity<DirectoriesPresenter> implements DirectoriesView, View.OnClickListener {

    private ImageView finishIv;
    private RadioButton materialRbn;
    private RadioButton dirRbn;
    private ListView dirLv;
    private String articleId;
    private ArrayList<MaterialListModel.DataBean> mData = new ArrayList<>();
    private DirectoriesAdapter directoriesAdapter;
    private ListView bookLv;
    private ArrayList<BookMoudle.DataBean> mBookdata = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_directories);
        finishIv = (ImageView)findViewById(R.id.iv_title_finish);
        materialRbn = (RadioButton)findViewById(R.id.rbn_material);
        dirRbn = (RadioButton)findViewById(R.id.rbn_dir);
        dirLv = (ListView)findViewById(R.id.lv_dir);
        bookLv = (ListView)findViewById(R.id.lv_book);
        dirLv.setVisibility(View.VISIBLE);
        bookLv.setVisibility(View.GONE);
        finishIv.setOnClickListener(this);
        materialRbn.setOnClickListener(this);
        dirRbn.setOnClickListener(this);
        directoriesAdapter = new DirectoriesAdapter(this, mData);
        dirLv.setAdapter(directoriesAdapter);
        bookListAdapter = new BookListAdapter(this, mBookdata);
        bookLv.setAdapter(bookListAdapter);
    }

    @Override
    protected void initData() {
        articleId = getIntent().getStringExtra("articleId");//图书Id
        mvpPresenter.loadMaterialListData(articleId);
        mvpPresenter.loadBookData(articleId);
    }

    @Override
    protected DirectoriesPresenter createPresenter() {
        return new DirectoriesPresenter(this);
    }

    @Override
    public void getDataSuccess(MaterialListModel model) {
        if (model.isSuccess()) {
            if (model.getData() != null) {
                mData = model.getData();
                directoriesAdapter.setmData(mData);
                directoriesAdapter.notifyDataSetChanged();
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getMaterialContentData(MaterialContentModel model) {

    }

    @Override
    public void getBookListData(BookMoudle model) {
        if (model.isSuccess()) {
            if (model.getData() != null) {
                mBookdata = model.getData();
                bookListAdapter.setmBookdata(mBookdata);
                bookListAdapter.notifyDataSetChanged();
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.showToast("网络获取数据失败！");
        Logger.e(msg);
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
        switch(v.getId()){
            case R.id.rbn_material:
                dirLv.setVisibility(View.VISIBLE);
                bookLv.setVisibility(View.GONE);
                break;
            case R.id.rbn_dir:
                dirLv.setVisibility(View.GONE);
                bookLv.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }
}
