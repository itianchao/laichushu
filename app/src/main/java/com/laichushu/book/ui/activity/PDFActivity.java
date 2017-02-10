package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiDownBack;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 展示PDF页面
 * Created by wangtong on 2017/1/11.
 */

public class PDFActivity extends MvpActivity2 implements OnPageChangeListener {
    private PDFView mPdfView;
    private Integer pageNumber = 1;
    private TextView titleTv;
    private ImageView backIv;
    private String title;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_pdf);
        mPdfView = (PDFView) mSuccessView.findViewById(R.id.pdfView);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        backIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        String path = getIntent().getStringExtra("path");
        String name = getIntent().getStringExtra("name");
        title = getIntent().getStringExtra("title");
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv.setText(title);
        String url = ConstantValue.LOCAL_PATH.SD_PATH + title + name + ".pdf";
        if (new File(url).exists()) {
            display(url, false);
        } else {
            downloadPdf(path, title + name);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText(title);
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

    /**
     * 打开pdf
     *
     * @param path
     * @param jumpToFirstPage
     */
    private void display(String path, boolean jumpToFirstPage) {

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
     * 重新加载按钮
     */
    public void reloadErrorBtn() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                initData();
            }
        });
    }
}
