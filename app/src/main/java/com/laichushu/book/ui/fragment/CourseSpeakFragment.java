package com.laichushu.book.ui.fragment;

import android.view.View;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

import java.io.File;

/**
 * 发现 - 课程 - 讲义
 * Created by wangtong on 2017/1/9.
 */

public class CourseSpeakFragment extends MvpFragment2 implements OnPageChangeListener {

    private PDFView mPdfView;
    private Integer pageNumber = 1;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_coursespeak);
        mPdfView = (PDFView) mSuccessView.findViewById(R.id.pdfView);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        String path = getArguments().getString("path");
        //下载成功
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        display(path, false);
    }
    private void display(String path, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        File file = new File(path);
        mPdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this).swipeVertical(true)
                .swipeVertical(false) //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                .load();
    }

    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }
}
