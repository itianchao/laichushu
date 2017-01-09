package com.laichushu.book.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.mvp.find.mechanism.mechanismsearch.MechanismSearchPresenter;
import com.laichushu.book.mvp.find.mechanism.mechanismsearch.MechanismSearchView;
import com.laichushu.book.ui.adapter.MechanismSearchListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 机构搜索页
 * Created by wt on 2016/12/15.
 */

public class MechanismSearchActivity extends MvpActivity2<MechanismSearchPresenter> implements MechanismSearchView, View.OnClickListener,TextView.OnEditorActionListener, PullLoadMoreRecyclerView.PullLoadMoreListener{

    private ImageView finishIV;
    private EditText searchEt;
    private PullLoadMoreRecyclerView mRecyclerView;
    private ArrayList<MechanismListBean.DataBean> mData = new ArrayList<>();
    private String search;
    private int pageNo = 1;
    private MechanismSearchListAdapter mAdapter;
    private String articleId;
    @Override
    protected MechanismSearchPresenter createPresenter() {
        return new MechanismSearchPresenter(this);
    }

    /**
     * 成功页面
     *
     * @return
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismsearch);
        finishIV = (ImageView) mSuccessView.findViewById(R.id.iv_finish);//返回
        searchEt = (EditText) mSuccessView.findViewById(R.id.et_search);//搜索
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_mechanismsearch);//搜索列表
        mRecyclerView.setLinearLayout();
        mAdapter = new MechanismSearchListAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
        finishIV.setOnClickListener(this);
        searchEt.setOnClickListener(this);
        searchEt.setOnEditorActionListener(this);

        return mSuccessView;
    }

    @Override
    protected void initData() {
        articleId = getIntent().getStringExtra("articleId");
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getDataSuccess(MechanismListBean model) {
        hideLoading();
        if (model.isSuccess()) {
            if (!model.getData().isEmpty()){
                pageNo++;
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
            }else {
                if ((pageNo == 1)) {
                    ToastUtil.showToast("没搜到");
                }else {
                    ToastUtil.showToast("已经到头了");
                }
            }
        }else {
            ToastUtil.showToast("搜索失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
        ToastUtil.showToast("搜索失败");
    }

    /**
     * 显示进度条
     */
    @Override
    public void showLoading() {
        showProgressDialog();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish:
                finish();
                break;
            case R.id.et_search:
                break;
            case R.id.tv_clear:
//                if (hisList != null) {
//                    for (int i = hisList.size() - 1; i >= 0; i--) {
//                        dao.delete(hisList.get(i));
//                    }
//                    hisList.clear();
//                    historyAdapter.notifyDataSetChanged();
//                }
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            //处理事件
            mData.clear();
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
        mData.clear();
        pageNo = 1;
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadSearchResultData(search);//请求网络获取搜索列表
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadSearchResultData(search);//请求网络获取搜索列表
    }

    public String getArticleId() {
        return articleId;
    }
}
