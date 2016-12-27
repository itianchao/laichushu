package com.laichushu.book.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findgroup.groupsearch.FindGroupSearchPresenter;
import com.laichushu.book.mvp.findgroup.groupsearch.FindGroupSearchView;
import com.laichushu.book.ui.adapter.MechanismSearchListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 发现 - 小组 - 搜索
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupSearchActivity extends MvpActivity2<FindGroupSearchPresenter> implements FindGroupSearchView, View.OnClickListener, TextView.OnEditorActionListener, PullLoadMoreRecyclerView.PullLoadMoreListener, AdapterView.OnItemClickListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private ImageView finishIV;
    private EditText searchEt;
    private String search;
    private TextView clearTv;
    private LinearLayout searchLay;
    private ListView searchLv;
    private ListView hotLv;
    private ImageView emptyIv;

    @Override
    protected FindGroupSearchPresenter createPresenter() {
        return new FindGroupSearchPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupsearch);
        finishIV = (ImageView) mSuccessView.findViewById(R.id.iv_finish);//返回
        searchEt = (EditText) mSuccessView.findViewById(R.id.et_search);//搜索
        clearTv = (TextView) mSuccessView.findViewById(R.id.tv_clear);//清除搜索历史
        searchLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_search);//整个搜索布局
        searchLv = (ListView) mSuccessView.findViewById(R.id.lv_history);//搜索历史的容器
        hotLv = (ListView) mSuccessView.findViewById(R.id.lay_hot_child);//hot容器
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_search);//搜索列表
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);//搜索空结果
        mRecyclerView.setLinearLayout();
//        mAdapter = new MechanismSearchListAdapter(this,mData);
//        mRecyclerView.setAdapter(mAdapter);
//        hotLv.setAdapter(mHotAdapter);
        finishIV.setOnClickListener(this);
        searchEt.setOnClickListener(this);
        clearTv.setOnClickListener(this);
        searchEt.setOnEditorActionListener(this);
        mRecyclerView.setOnPullLoadMoreListener(this);
        searchLv.setOnItemClickListener(this);
        hotLv.setOnItemClickListener(this);
        return mSuccessView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_finish:
                finish();
                break;
            case R.id.et_search:
                searchLay.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                emptyIv.setVisibility(View.GONE);
                break;
            case R.id.tv_clear:
                break;
        }
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            //处理事件
//            mData.clear();
            search = searchEt.getText().toString().trim();
            mvpPresenter.loadSearchResultData(search);
        }
        return false;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.lv_history://历史记录
//                searchEt.setText(list.get(position).getHistory());
                onEditorAction(searchEt, EditorInfo.IME_ACTION_SEARCH, null);
                break;
            case R.id.lay_hot_child://热门搜索
//                searchEt.setText(mHotData.get(position).getArticleName());
                onEditorAction(searchEt, EditorInfo.IME_ACTION_SEARCH, null);
                break;
        }
    }
}
