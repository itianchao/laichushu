package com.sofacity.laichushu.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.db.Search_History;
import com.sofacity.laichushu.db.Search_HistoryDao;
import com.sofacity.laichushu.mvp.homesearch.HomeSearchPresenter;
import com.sofacity.laichushu.mvp.homesearch.HomeSearchView;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.ui.adapter.HomeSearchAdapter;
import com.sofacity.laichushu.ui.adapter.HomeSearchHistoryAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 首页搜索页面
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchActivity extends MvpActivity<HomeSearchPresenter> implements HomeSearchView, View.OnClickListener, TextView.OnEditorActionListener, PullLoadMoreRecyclerView.PullLoadMoreListener, AdapterView.OnItemClickListener {

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
    private Search_HistoryDao dao;
    private HomeSearchHistoryAdapter historyAdapter;
    private List<Search_History> list = new ArrayList<>();

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
        searchLv.setOnItemClickListener(this);
        mAdapter = new HomeSearchAdapter(mAllData, this);
        bookRyv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mvpPresenter.setupDatabase();
        dao = mvpPresenter.getSearch_historyDao();

        if (getHistoryList() != null) {
            this.list.addAll(getHistoryList());
            Collections.reverse(list);
            historyAdapter = new HomeSearchHistoryAdapter(this.list, this);
            searchLv.setAdapter(historyAdapter);
        }

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
        if (model.isSuccess()) {
            mData = model.getData();
            if (!mData.isEmpty()) {
                searchLay.setVisibility(View.GONE);
                bookRyv.setVisibility(View.VISIBLE);
                mAllData.addAll(mData);
                mAdapter.setmAllData(mAllData);
                mAdapter.notifyDataSetChanged();
            }
            pageNo = Integer.parseInt(pageNo) + 1 + "";
        } else {
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
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.tv_clear:
                if (list != null) {
                    dao.deleteAll();
                    list.clear();
                    historyAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.et_search:
                searchLay.setVisibility(View.VISIBLE);
                bookRyv.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 搜素键盘监听事件
     *
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
            boolean isSearch = true;
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getHistory().equals(search)) {
                        isSearch = false;
                        Search_History history1 = list.get(i);
                        Search_History history2 = list.get((list.size() - 1));
                        list.set(i, history2);
                        list.set(list.size() - 1, history1);
                        mAdapter.notifyDataSetChanged();
                        dao.updateInTx(history1, history2);
                        break;
                    }
                }
            }
            if (isSearch) {//判断是否相同
                //将记录添加到数据库中
                Search_History history = new Search_History(null, search);
                if (list.size() != 0) {
                    for (int i = list.size(); i > 0; i--) {
                        if (i == list.size()) {
                            list.add(list.get(i - 1));
                        } else if (i == 1) {
                            list.remove(1);
                        } else {
                            list.set(i, list.get(i - 1));
                        }
                    }
                }
                list.add(0, history);
                dao.insert(history);

                if (list.size() > 5) {
                    dao.delete(list.get(5));
                    list.remove(5);
                }
                historyAdapter.setList(list);
                historyAdapter.notifyDataSetChanged();
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

    public List<Search_History> getHistoryList() {
        QueryBuilder<Search_History> search_historyQueryBuilder = dao.queryBuilder();
        Query<Search_History> build = search_historyQueryBuilder.build();
        return build.list();
    }

    public HomeSearchPresenter getPresenter() {
        return mvpPresenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        searchEt.setText(list.get(position).getHistory());
        onEditorAction(searchEt, EditorInfo.IME_ACTION_SEARCH, null);
    }
}
