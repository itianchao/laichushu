package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchModel;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchPresenter;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchView;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 首页搜索页面
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchActivity extends MvpActivity<HomeSearchPresenter> implements HomeSearchView, View.OnClickListener, TextView.OnEditorActionListener {

    private ImageView finishIV;
    private EditText searchEt;
    private TextView clearTv;
    private LinearLayout searchLay;
    private ListView searchLv;
    private LinearLayout childLay;
    private PullLoadMoreRecyclerView bookRyv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_homesearch);//页面布局
        finishIV = (ImageView) findViewById(R.id.iv_finish);//返回
        searchEt = (EditText) findViewById(R.id.et_search);//搜索
        clearTv = (TextView) findViewById(R.id.tv_clear);//清除搜索历史
        searchLay = (LinearLayout) findViewById(R.id.lay_search);//整个搜索布局
        searchLv = (ListView) findViewById(R.id.lv_history);//搜索历史的容器
        childLay = (LinearLayout) findViewById(R.id.lay_hot_child);//hot容器
        bookRyv = (PullLoadMoreRecyclerView) findViewById(R.id.ryv_book);//搜索结果

        finishIV.setOnClickListener(this);
        clearTv.setOnClickListener(this);
        searchEt.setOnClickListener(this);
        bookRyv.setLinearLayout();//设置垂直的RecyclerView
        searchEt.setOnEditorActionListener(this);
    }

    @Override
    protected HomeSearchPresenter createPresenter() {
        return new HomeSearchPresenter(this);
    }

    @Override
    public void getDataSuccess(HomeSearchModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_finish:
                finish();
                break;
            case R.id.tv_clear:
                break;
            case R.id.et_search:
                searchLay.setVisibility(View.VISIBLE);
                bookRyv.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 搜素键盘监听事件
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_DONE
                || (event != null && KeyEvent.KEYCODE_SEARCH == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
            //处理事件
            searchLay.setVisibility(View.GONE);
            bookRyv.setVisibility(View.VISIBLE);

        }
        return false;
    }
}
