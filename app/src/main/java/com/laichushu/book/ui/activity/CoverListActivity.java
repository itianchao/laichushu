package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.effects.NewsPaper;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.mvp.coverdir.CoverDirModle;
import com.laichushu.book.mvp.coverdir.CoverDirPresenter;
import com.laichushu.book.mvp.coverdir.CoverDirView;
import com.laichushu.book.ui.adapter.CoverListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 模版列表
 * Created by wangtong on 2016/11/23.
 */

public class CoverListActivity extends MvpActivity2<CoverDirPresenter> implements CoverDirView, View.OnClickListener {

    private String type;
    private String bookname;
    private String url = "";
    private PullLoadMoreRecyclerView ryv_coverlist;
    private ArrayList<CoverDirModle.DataBean> mData = new ArrayList<>();
    private CoverListAdapter mAdapter;
    private ImageView finishIv;
    private TextView titleTv;
    private String title,bookType;

    @Override
    protected CoverDirPresenter createPresenter() {
        return new CoverDirPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_coverlist);
        finishIv = (ImageView)mSuccessView.findViewById(R.id.iv_title_finish);
        titleTv = (TextView)mSuccessView.findViewById(R.id.tv_title);
        ryv_coverlist = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_coverlist);
        ryv_coverlist.setGridLayout(3);
        ryv_coverlist.setPushRefreshEnable(false);
        ryv_coverlist.setPullRefreshEnable(false);

        finishIv.setOnClickListener(this);
        mAdapter = new CoverListAdapter(mData, this,mvpPresenter);
        ryv_coverlist.setAdapter(mAdapter);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        bookname = getIntent().getStringExtra("bookname");
        bookType = getIntent().getStringExtra("bookType");
        titleTv.setText(title);
        mvpPresenter.loadCoverListData(type);
    }

    /**
     * 请求 成功
     */
    @Override
    public void getDataSuccess(CoverDirModle modle) {
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mData.clear();
            if (null != modle.getData() && !modle.getData().isEmpty()) {
                mData.addAll(modle.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ToastUtil.showToast(modle.getErrMsg());
            reLoadData();
        }
    }

    /**
     * 获取创作后的图片
     * @param model
     */
    @Override
    public void getMakePreviewCover(PreviewCoverBean model) {

        if (model.isSuccess()) {
            url = model.getData();
            if (!TextUtils.isEmpty(url)){
                dismissProgressDialog();
                UIUtil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        bundle.putString("path",url);
                        bundle.putString("title",title);
                        bundle.putString("bookType",bookType);
                        UIUtil.openActivity(mActivity,PreviewCoverActivity.class,bundle);
                    }
                },1710);
            }
        }else {
            ToastUtil.showToast("预览失败");
            LoggerUtil.e(model.getErrMsg());
            dismissProgressDialog();
        }
    }

    /**
     * 请求 失败
     */
    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        reLoadData();
        ToastUtil.showToast("获取列表失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail2(String msg) {
        dismissProgressDialog();
        ToastUtil.showToast("预览失败");
        LoggerUtil.e(msg);
    }

    /**
     * 失败 重新加载
     */
    private void reLoadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadCoverListData(type);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
