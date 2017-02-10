package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.SpeakpdfDetail_Paramet;
import com.laichushu.book.bean.otherbean.SpeakListModle;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.retrofit.ApiDownBack;
import com.laichushu.book.ui.adapter.CourseraSpeakAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.File;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发现 - 课程 - 讲义
 * Created by wangtong on 2017/1/9.
 */

public class CourseSpeakFragment extends MvpFragment2 implements PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener, OnPageChangeListener {
    private Integer pageNumber = 1;
    public PullLoadMoreRecyclerView mRecyclerView;
    public CourseraSpeakAdapter mAdapter;
    public ArrayList<SpeakListModle.DataBean.HandOutsListBean> mData;
    public int pageNo = 1;
    public String pageSize = ConstantValue.PAGESIZE;
    public ImageView emptyIv;
    public Button cacelBtn;
    public PDFView mPdfView;
    public LinearLayout contentFay;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_coursespeak);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_speak);
        contentFay = (LinearLayout) mSuccessView.findViewById(R.id.fay_content);
        cacelBtn = (Button) mSuccessView.findViewById(R.id.btn_cancel);
        mPdfView = (PDFView) mSuccessView.findViewById(R.id.pdfView);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setPullRefreshEnable(false);
//        mRecyclerView.setPushRefreshEnable(t);
        mAdapter = new CourseraSpeakAdapter(this,mActivity, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(this);
        return mSuccessView;
    }

    @Override
    public void initData() {
        cacelBtn.setOnClickListener(this);
        String lessonId = getArguments().getString("lessonId");
        loadSpeakListData(lessonId);
    }

    private void loadSpeakListData(String lessonId) {
        SpeakpdfDetail_Paramet paramet = new SpeakpdfDetail_Paramet(lessonId, pageNo + "", pageSize);
        addSubscription(apiStores.getSpeakpdfList(paramet), new ApiCallback<SpeakListModle>() {
            @Override
            public void onSuccess(SpeakListModle model) {
                UIUtil.postPullLoadMoreCompleted(mRecyclerView);
                if (model.isSuccess()) {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    if (model.getData().getHandOutsList().isEmpty()) {
                        emptyIv.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                        contentFay.setVisibility(View.GONE);
                    } else {
                        mData = model.getData().getHandOutsList();
                        emptyIv.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        contentFay.setVisibility(View.GONE);
                        mAdapter.setmData(mData);
                    }
                } else {
                    reloadErrorBtn();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                reloadErrorBtn();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        refreshPage(LoadingPager.PageState.STATE_LOADING);
        mData.clear();
        initData();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onLoadMore() {

    }

    /**
     * 重新加载
     */
    public void reloadErrorBtn() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                onRefresh();
            }
        });
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_cancel:
                emptyIv.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                contentFay.setVisibility(View.GONE);
                break;
        }
    }
    /**
     * 翻页回调
     *
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }
    /**
     * 打开pdf
     *
     * @param path
     * @param jumpToFirstPage
     */
    public void display(String path, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        File file = new File(path);
        mPdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this).swipeVertical(true)
                .swipeVertical(true) //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                .load();

        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }
    /**
     * 下载文件
     *
     * @param path
     * @param name
     */
    public void downloadPdf(String path, final String name) {
        Call<ResponseBody> call = apiStores.downloadFile(path);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = ApiDownBack.writeResponseBodyToDisk(response.body(), name, "pdf");
                    if (writtenToDisk) {
                        String path = ConstantValue.LOCAL_PATH.SD_PATH + name + ".pdf";
                        //下载成功
                        display(path, false);
                    } else {
                        reloadErrorBtn();
                    }
                } else {
                    reloadErrorBtn();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                reloadErrorBtn();
            }
        });
    }
}
