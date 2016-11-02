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

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.db.DaoSession;
import com.sofacity.laichushu.db.Search_History;
import com.sofacity.laichushu.db.Search_HistoryDao;
import com.sofacity.laichushu.global.BaseApplication;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchModel;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchPresenter;
import com.sofacity.laichushu.mvp.HomeSearch.HomeSearchView;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.ui.adapter.HomeSearchAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 首页搜索页面
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchActivity extends MvpActivity<HomeSearchPresenter> implements HomeSearchView, View.OnClickListener, TextView.OnEditorActionListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ImageView finishIV;
    private EditText searchEt;
    private TextView clearTv;
    private LinearLayout searchLay;
    private ListView searchLv;
    private LinearLayout childLay;
    private PullLoadMoreRecyclerView bookRyv;
    private String pageNo = "2";
    private String search = "";
    private ArrayList<HomeHotModel.DataBean> mData = new ArrayList<>();
    private ArrayList<HomeHotModel.DataBean> mAllData = new ArrayList<>();
    private HomeSearchAdapter mAdapter;
    private Search_HistoryDao dao = new BaseApplication().getSearchHistory();

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
        bookRyv.setOnPullLoadMoreListener(this);
        mAdapter = new HomeSearchAdapter(mAllData,this);
        bookRyv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        List<Search_History> list = dao.queryBuilder().build().list();
//        searchLv.setAdapter(historyAdapter);
    }

    @Override
    protected HomeSearchPresenter createPresenter() {
        return new HomeSearchPresenter(this);
    }

    @Override
    public void getDataSuccess(HomeHotModel model) {
        hideLoading();
        mData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                bookRyv.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()){
            mData = model.getData();
            if (!mData.isEmpty()){
                searchLay.setVisibility(View.GONE);
                bookRyv.setVisibility(View.VISIBLE);
                mAllData.addAll(mData);
                mAdapter.setmAllData(mAllData);
                mAdapter.notifyDataSetChanged();
            }
            pageNo = Integer.parseInt(pageNo)+1+"";
        }else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
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
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            //处理事件
            mAllData.clear();
            search = searchEt.getText().toString().trim();
            mvpPresenter.LoadData(search);
            //将记录添加到数据库中
            Search_History history = new Search_History(null,search);
            dao.insert(history);
            List<Search_History> list = dao.queryBuilder().build().list();
            if (list.size()>5){
                dao.delete(list.get(0));
            }
        }
        return false;
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mAllData.clear();
        pageNo = "1";
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.LoadData(search);//请求网络获取搜索列表
    }
    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.LoadData(search);//请求网络获取搜索列表
    }
}
