package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.laichushu.book.R;
import com.laichushu.book.mvp.write.directories.BookMoudle;
import com.laichushu.book.mvp.write.directories.DirectoriesPresenter;
import com.laichushu.book.mvp.write.directories.DirectoriesView;
import com.laichushu.book.mvp.write.directories.MaterialContentModel;
import com.laichushu.book.mvp.write.directories.MaterialListModel;
import com.laichushu.book.ui.adapter.BookListAdapter;
import com.laichushu.book.ui.adapter.DirectoriesAdapter;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 目录
 * Created by wangtong on 2016/11/7.
 */
public class DirectoriesActivity extends MvpActivity2<DirectoriesPresenter> implements DirectoriesView, View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView finishIv;
    private RadioButton materialRbn;
    private RadioButton dirRbn;
    private ListView dirLv;
    private String articleId, bookName;
    private ArrayList<MaterialListModel.DataBean> mData = new ArrayList<>();
    private DirectoriesAdapter directoriesAdapter;
    private ListView bookLv;
    private ArrayList<BookMoudle.DataBean> mBookdata = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    @Override
    protected DirectoriesPresenter createPresenter() {
        return new DirectoriesPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_directories);
        finishIv = (ImageView) inflate.findViewById(R.id.iv_title_finish);
        materialRbn = (RadioButton) inflate.findViewById(R.id.rbn_material);
        dirRbn = (RadioButton) inflate.findViewById(R.id.rbn_dir);
        dirLv = (ListView) inflate.findViewById(R.id.lv_dir);
        bookLv = (ListView) inflate.findViewById(R.id.lv_book);
        bookLv.setVisibility(View.VISIBLE);
        dirLv.setVisibility(View.GONE);
        finishIv.setOnClickListener(this);
        materialRbn.setOnClickListener(this);
        dirRbn.setOnClickListener(this);
        directoriesAdapter = new DirectoriesAdapter(this, mData);
        dirLv.setAdapter(directoriesAdapter);
        bookListAdapter = new BookListAdapter(this, mBookdata);
        bookLv.setAdapter(bookListAdapter);
        bookLv.setOnItemClickListener(this);
        dirLv.setOnItemClickListener(this);
        return inflate;
    }

    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("目录模式");
    }

    @Override
    protected void initData() {
        articleId = getIntent().getStringExtra("articleId");//图书Id
        bookName = getIntent().getStringExtra("bookName");//图书Id
        mvpPresenter.loadMaterialListData(articleId);
        mvpPresenter.loadBookData(articleId);
    }

    @Override
    public void getDataSuccess(MaterialListModel model) {
        if (model.isSuccess()) {
            if (null != model.getData()) {
                mData = model.getData();
            } else {
                ToastUtil.showToast(R.string.errMsg_empty);
            }
            directoriesAdapter.setmData(mData);
            directoriesAdapter.notifyDataSetChanged();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refrushErrorView(1);
        }
    }

    @Override
    public void getMaterialContentData(MaterialContentModel model) {

    }

    @Override
    public void getBookListData(BookMoudle model) {
        if (model.isSuccess()) {
            if (null != model.getData()) {
                mBookdata = model.getData();
            } else {
                ToastUtil.showToast(R.string.errMsg_empty);
            }
            bookListAdapter.setmBookdata(mBookdata);
            bookListAdapter.notifyDataSetChanged();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.showToast("网络获取数据失败！");
        Logger.e(msg);
        refrushErrorView(1);
    }

    @Override
    public void getDataFail2(String msg) {
        ToastUtil.showToast("网络获取数据失败！");
        Logger.e(msg);
        refrushErrorView(2);
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
        switch (parent.getId()) {
            case R.id.lv_book:
                // TODO: 2016/11/8  跳转书籍
                BookMoudle.DataBean dataBean1 = mBookdata.get(position);
                Bundle bundle = new Bundle();
                String path = mBookdata.get(position).getContentUrlApp();
                String name = mBookdata.get(position).getName();
                String parentId = mBookdata.get(position).getId();
                bundle.putString("path", path);
                bundle.putString("title", name);
                bundle.putString("name", name);
                bundle.putString("bookName", bookName);
                bundle.putString("content", mBookdata.get(position).getContent());
                bundle.putString("parentId", parentId);
                bundle.putString("type", "1");
                bundle.putString("articleId", articleId);
                if (mBookdata.get(position).isIsSection()) {
                    UIUtil.openActivity(this, PartActivity.class, bundle);
                } else {
                    if (!TextUtils.isEmpty(path)) {
                        UIUtil.openActivity(this, NopublishBookActivity.class, bundle);
                    }
                }
                break;
            case R.id.lv_dir:
                MaterialListModel.DataBean dataBean = mData.get(position);
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", dataBean.getName());
                bundle2.putString("path", dataBean.getContentUrlApp());
                bundle2.putString("parentId", dataBean.getId());
                bundle2.putString("name", dataBean.getName());
                bundle2.putString("type", "2");
                bundle2.putString("articleId", articleId);
                if (mData.get(position).isIsSection()) {
                    UIUtil.openActivity(this, PartActivity.class, bundle2);
                } else {
                    if (!TextUtils.isEmpty(dataBean.getContentUrlApp())) {
                        UIUtil.openActivity(mActivity, NopublishBookActivity.class, bundle2);
                    }
                }
                break;
        }

    }

    /**
     * 重新加载
     */
    public void refrushErrorView(final int type) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                switch (type) {
                    case 1:
                        mvpPresenter.loadMaterialListData(articleId);
                        break;
                    case 2:
                        mvpPresenter.loadBookData(articleId);
                        break;
                }
            }
        });
    }
}
