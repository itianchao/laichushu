package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.mvp.bookcast.BookcastPresener;
import com.laichushu.book.mvp.bookcast.BookcastView;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.homesearch.HomeSearchView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.MyBookCastAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyBookCastActivity extends MvpActivity2<BookcastPresener> implements BookcastView,View.OnClickListener, RadioGroup.OnCheckedChangeListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mRecyclerView;
    private GridView gdView;
    private RadioGroup radioGroup;
    private RadioButton rbScan, rbCollection;
    private View lineLeft, lineRight;
    private int PAGE_NO = 1, PAGE_SIZE = 9;
    private List<HomeHotModel.DataBean> scanData = new ArrayList<>();
    private MyBookCastAdapter scanAdapter;

    @Override
    protected BookcastPresener createPresenter() {
        return new BookcastPresener(this);
    }

    @Override
    protected View createSuccessView() {
        View bookCastView = UIUtil.inflate(R.layout.activity_my_book_cast);
        ivBack = ((ImageView) bookCastView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) bookCastView.findViewById(R.id.tv_title));
        lineLeft = bookCastView.findViewById(R.id.myCast_left);
        lineRight = bookCastView.findViewById(R.id.myCast_right);
        gdView = ((GridView) bookCastView.findViewById(R.id.gv_list));
        mRecyclerView = (PullLoadMoreRecyclerView) bookCastView.findViewById(R.id.ryv_bookCast);
        radioGroup = ((RadioGroup) bookCastView.findViewById(R.id.rg_bookList));
        rbScan = ((RadioButton) bookCastView.findViewById(R.id.rb_scan));
        rbCollection = ((RadioButton) bookCastView.findViewById(R.id.rb_collection));

        return bookCastView;
    }


    @Override
    protected void initData() {
        mvpPresenter.LoadData();
        tvTitle.setText("作品管理");
        ivBack.setOnClickListener(this);
        lineLeft.setOnClickListener(this);
        lineRight.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        //初始化mRecyclerView
        mRecyclerView.setLinearLayout();
        mRecyclerView.setFooterViewText("加载中");
        scanAdapter = new MyBookCastAdapter(mActivity, scanData);
        gdView.setAdapter(scanAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_scan:
                //点击浏览
                lineLeft.setVisibility(View.VISIBLE);
                lineRight.setVisibility(View.INVISIBLE);
                scanData.clear();
                mvpPresenter.LoadData();
                break;
            case R.id.rb_collection:
                //点击收藏
                lineLeft.setVisibility(View.INVISIBLE);
                lineRight.setVisibility(View.VISIBLE);

                break;
        }
    }

    @Override
    public void onRefresh() {
        scanData.clear();
        PAGE_NO=1;
        mvpPresenter.getParamet().setPageNo(PAGE_NO+"");
        mvpPresenter.LoadData();//请求网络获取搜索列表

    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(PAGE_NO+"");
        mvpPresenter.LoadData();//请求网络获取搜索列表
    }

    @Override
    public void getDataSuccess(HomeHotModel model) {
        scanData.clear();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            scanData = model.getData();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!scanData.isEmpty()) {
               scanAdapter.refreshAdapter(scanData);
                PAGE_NO++;
            }else {

            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }
}
