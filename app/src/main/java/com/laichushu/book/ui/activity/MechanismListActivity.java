package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.MechanismList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.adapter.MechanismListAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构列表
 * Created by wt on 2016/11/24.
 */

public class MechanismListActivity extends MvpActivity2 implements View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    //1、读者组 2、专业社 3、教育社 4、大众社
    private String type = "2";
    private String pageSize = ConstantValue.PAGESIZE1;
    private ArrayList<MechanismListBean.DataBean> mData1 = new ArrayList<>();//专业社
    private ArrayList<MechanismListBean.DataBean> mData2 = new ArrayList<>();//教育社
    private ArrayList<MechanismListBean.DataBean> mData3 = new ArrayList<>();//大众社
    private int pageNo1 = 1;
    private int pageNo2 = 1;
    private int pageNo3 = 1;
    private String userId = ConstantValue.USERID;
    private MechanismList_Paramet paramet = new MechanismList_Paramet(type, pageNo1+"", pageSize,userId);

    private PullLoadMoreRecyclerView mRecyclerView;
    private MechanismListAdapter mAdapter;
    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismlist);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView searchIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        RadioButton fristRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);
        RadioButton scondRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);
        RadioButton thridRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_03);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_mechanism);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setOnPullLoadMoreListener(this);
        mAdapter = new MechanismListAdapter(this,mData1);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setFooterViewText("加载中");
        fristRbn.setOnClickListener(this);
        scondRbn.setOnClickListener(this);
        thridRbn.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        GlideUitl.loadImg2(this, R.drawable.search_icon, searchIv);
        titleTv.setText("机构");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        loadMechanismListData("2");
        articleId = getIntent().getStringExtra("articleId");
    }
    int position = 0;
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other:
                // TODO: 2016/11/24  搜索
                break;
            case R.id.rbn_01:
                if (position != 1){
                    type = "2";
                    if (pageNo1 == 1){
                        loadMechanismListData(type);
                    }else {
                        mAdapter.setmData(mData1);
                        mAdapter.notifyDataSetChanged();
                    }
                    position = 1;
                }
                break;

            case R.id.rbn_02:
                if (position != 2) {
                    type = "3";
                    if (pageNo2 == 1) {
                        loadMechanismListData(type);
                    } else {
                        mAdapter.setmData(mData1);
                        mAdapter.notifyDataSetChanged();
                    }
                    position = 2;
                }
                break;

            case R.id.rbn_03:
                if (position != 3) {
                    type = "4";
                    if (pageNo3 == 1) {
                        loadMechanismListData(type);
                    } else {
                        mAdapter.setmData(mData1);
                        mAdapter.notifyDataSetChanged();
                    }
                    position = 3;
                }
                break;
        }

    }

    /**
     * 请求获取机构列表
     */
    public void loadMechanismListData(String type) {
        paramet.setType(type);
        LoggerUtil.e("获取机构列表");

        addSubscription(apiStores.getMechanismList(paramet), new ApiCallback<MechanismListBean>() {
            @Override
            public void onSuccess(MechanismListBean model) {
                getDataSuccess(model);
            }


            @Override
            public void onFailure(int code, String msg) {
                getFailure("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
    /**
     * 请求网络成功
     */
    private void getDataSuccess(MechanismListBean model) {
        mRecyclerView.setPullLoadMoreCompleted();
        if (model.isSuccess()) {
            switch(type){
                case "2":
                    if (pageNo1==1){
                        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    }
                    if (null != model.getData() && !model.getData().isEmpty()){
                        mData1.addAll(model.getData());
                        pageNo1++;
                    }
                    mAdapter.setmData(mData1);
                    mAdapter.notifyDataSetChanged();
                    break;
                case "3":
                    if (pageNo1==2){
                        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    }
                    if (null != model.getData() && !model.getData().isEmpty()){
                        mData2.addAll(model.getData());
                        pageNo2++;
                    }
                    mAdapter.setmData(mData2);
                    mAdapter.notifyDataSetChanged();
                    break;
                case "4":
                    if (pageNo1==3){
                        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    }
                    if (null != model.getData() && !model.getData().isEmpty()){
                        mData3.addAll(model.getData());
                        pageNo3++;
                    }
                    mAdapter.setmData(mData3);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            LoggerUtil.e(model.getErrMsg());
            reLoadData();
        }
    }
    /**
     * 请求网络失败
     */
    private void getFailure(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        LoggerUtil.e(msg);
        reLoadData();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        switch(type){
            case "2":
                mData1.clear();
                pageNo1 = 1;
                paramet.setPageNo(pageNo1+"");
                break;
            case "3":
                mData2.clear();
                pageNo2 = 1;
                paramet.setPageNo(pageNo2+"");
                break;
            case "4":
                mData3.clear();
                pageNo3= 1;
                paramet.setPageNo(pageNo3+"");
                break;
        }
        loadMechanismListData(type);
    }
    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        loadMechanismListData(type);
    }

    public void reLoadData(){
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                loadMechanismListData(type);
            }
        });
    }

}
