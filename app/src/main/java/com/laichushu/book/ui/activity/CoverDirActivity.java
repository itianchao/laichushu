package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.CoverModleList_Paramet;
import com.laichushu.book.bean.otherbean.CoverDirBean;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.CoverDirAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 封面
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirActivity extends MvpActivity2 implements View.OnClickListener {

    private String bookname;
    private PullLoadMoreRecyclerView mRecyclerView;
    private TextView titleTv;
    private ImageView finishIv;
    private boolean isInit = true;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private ArrayList<CoverDirBean.DataBean> mData = new ArrayList();

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_coverdir);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_coverdir);
        mRecyclerView.setGridLayout(3);
        mRecyclerView.setPushRefreshEnable(false);
        mRecyclerView.setPullRefreshEnable(false);
        titleTv.setText("模版选择");
        finishIv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        bookname = getIntent().getStringExtra("bookname");

        if (isInit) {
            loadCoverDirListData();
        }
        isInit = false;

    }

    private void loadCoverDirListData() {
        addSubscription(apiStores.getCoverModleList(new CoverModleList_Paramet()), new ApiCallback<CoverDirBean>() {
            @Override
            public void onSuccess(CoverDirBean model) {
                getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e("code:"+code+"msg:"+msg);
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                refrushData();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getDataSuccess(CoverDirBean model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mData.addAll(model.getData());
            mRecyclerView.setAdapter(new CoverDirAdapter(mData, this));
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            LoggerUtil.e(model.getErrMsg());
            refrushData();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void refrushData(){
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                mData.clear();
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                loadCoverDirListData();
            }
        });
    }
}
