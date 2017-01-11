package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        String title = getIntent().getStringExtra("title");
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv.setText(title);
        String url = ConstantValue.LOCAL_PATH.SD_PATH + name + ".pdf";
        if (new File(url).exists()) {
            display(path, false);
        } else {
            downloadPdf(path, name);
        }

    }

    /**
     * 下载文件
     *
     * @param path
     * @param name
     */
    private void downloadPdf(String path, final String name) {
        Call<ResponseBody> call = apiStores.downloadFile(path);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), name, "pdf");
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

    /**
     * 写入文件
     *
     * @param body
     * @param articleId
     * @return
     */
    private boolean writeResponseBodyToDisk(ResponseBody body, String articleId, String suffix) {
        try {
            // todo change the file location/name according to your needs
            String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + "." + suffix;

            File futureStudioIconFile = new File(path);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                reloadErrorBtn();
                futureStudioIconFile.delete();
                return false;
            } finally {
                reloadErrorBtn();
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            reloadErrorBtn();
            String path = ConstantValue.LOCAL_PATH.SD_PATH + articleId + "." + suffix;
            File futureStudioIconFile = new File(path);
            futureStudioIconFile.delete();
            return false;
        }
    }
}
