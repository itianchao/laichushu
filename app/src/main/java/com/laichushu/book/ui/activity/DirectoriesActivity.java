package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.laichushu.book.mvp.directories.DirectoriesPresenter;
import com.laichushu.book.mvp.directories.DirectoriesView;
import com.laichushu.book.ui.adapter.DirectoriesAdapter;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;
import com.laichushu.book.mvp.directories.BookMoudle;
import com.laichushu.book.mvp.directories.MaterialContentModel;
import com.laichushu.book.mvp.directories.MaterialListModel;
import com.laichushu.book.ui.adapter.BookListAdapter;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 目录
 * Created by wangtong on 2016/11/7.
 */
public class DirectoriesActivity extends MvpActivity<DirectoriesPresenter> implements DirectoriesView, View.OnClickListener, AdapterView.OnItemClickListener {

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
        bookLv.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO: 2016/11/8  跳转书籍
        Bundle bundle = new Bundle();
        String path = mBookdata.get(position).getContent();
        String name = mBookdata.get(position).getName();
        String parentId = mBookdata.get(position).getId();
        bundle.putString("path",path);
        bundle.putString("title",name);
        bundle.putString("parentId",parentId);
        if (mBookdata.get(position).isIsSection()) {
            UIUtil.openActivity(this,PartActivity.class,bundle);
        }else {
            if (!TextUtils.isEmpty(path)){
                UIUtil.openActivity(this,NopublishBookActivity.class,bundle);
            }
        }
    }
}
