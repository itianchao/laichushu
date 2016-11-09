package com.sofacity.laichushu.ui.activity;

import android.widget.ImageView;
import android.widget.ListView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.part.PartModel;
import com.sofacity.laichushu.mvp.part.PartPresenter;
import com.sofacity.laichushu.mvp.part.PartView;
import com.sofacity.laichushu.ui.base.MvpActivity;

/**
 * 节
 * Created by wangtong on 2016/11/7.
 */
public class PartActivity extends MvpActivity<PartPresenter> implements PartView {

    private ImageView finishIv;
    private String articleId;
    private ListView bookLv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_part);
        finishIv = (ImageView)findViewById(R.id.iv_title_finish);
        bookLv = (ListView)findViewById(R.id.lv_book);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected PartPresenter createPresenter() {
        return new PartPresenter(this);
    }

    @Override
    public void getDataSuccess(PartModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }
}
