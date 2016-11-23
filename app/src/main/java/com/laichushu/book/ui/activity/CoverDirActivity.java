package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.otherbean.CoverDirBean;
import com.laichushu.book.ui.adapter.CoverDirAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
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
    private ArrayList<CoverDirBean> mData = new ArrayList();
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_coverdir);
        titleTv = (TextView)mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView)mSuccessView.findViewById(R.id.iv_title_finish);
        mRecyclerView = (PullLoadMoreRecyclerView)mSuccessView.findViewById(R.id.ryv_coverdir);
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
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        if (isInit) {
            mData.add(new CoverDirBean("模版一",R.drawable.img_default));
            mData.add(new CoverDirBean("模版二",R.drawable.img_default));
            mData.add(new CoverDirBean("模版三",R.drawable.img_default));
            mData.add(new CoverDirBean("模版四",R.drawable.img_default));
        }
        isInit = false;
        mRecyclerView.setAdapter(new CoverDirAdapter(mData,this));

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
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
}
