package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.laichushu.book.mvp.home.part.PartModel;
import com.laichushu.book.mvp.home.part.PartView;
import com.laichushu.book.ui.adapter.PartListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;
import com.laichushu.book.mvp.home.part.PartPresenter;

import java.util.ArrayList;

/**
 * 节
 * Created by wangtong on 2016/11/7.
 */
public class PartActivity extends MvpActivity2<PartPresenter> implements PartView, AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView finishIv;
    private String parentId;
    private ListView bookLv;
    private ArrayList<PartModel.DataBean> mPartdata = new ArrayList<>();
    private PartListAdapter partListAdapter;
    private TextView titleTv;
    private String type;
    private String articleId;
    private String title;

    @Override
    protected void initData() {
        finishIv.setOnClickListener(this);
        parentId = getIntent().getStringExtra("parentId");
        title = getIntent().getStringExtra("title");
        articleId = getIntent().getStringExtra("articleId");
        titleTv.setText(title);
        partListAdapter = new PartListAdapter(this, mPartdata);
        bookLv.setAdapter(partListAdapter);
        type = getIntent().getStringExtra("type");
        if (type.equals("1")) {
            mvpPresenter.loadPartListData(parentId);
        } else {
            mvpPresenter.loadPartListData2(parentId);
        }

        bookLv.setOnItemClickListener(this);
    }

    @Override
    protected PartPresenter createPresenter() {
        return new PartPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_part);
        finishIv = (ImageView) inflate.findViewById(R.id.iv_title_finish);
        titleTv = (TextView) inflate.findViewById(R.id.tv_title);
        bookLv = (ListView) inflate.findViewById(R.id.lv_book);
        return inflate;
    }

    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText(title);
    }

    @Override
    public void getDataSuccess(PartModel model) {
        if (model.isSuccess()) {
            if (null != model.getData()) {
                mPartdata = model.getData();
                partListAdapter.setmPartdata(mPartdata);
                partListAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.showToast(mActivity.getResources().getString(R.string.errMsg_empty));
            }
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refrushErrorView();
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO: 2016/11/8  跳转书籍
        Bundle bundle = new Bundle();
        String path = mPartdata.get(position).getContentUrlApp();
        String name = mPartdata.get(position).getName();
        bundle.putString("path", path);
        bundle.putString("title", name);
        bundle.putString("articleId", articleId);
        if (!TextUtils.isEmpty(path)) {
            UIUtil.openActivity(this, NopublishBookActivity.class, bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }
    /**
     * 重新加载
     */
    public void refrushErrorView() {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                if (type.equals("1")) {
                    mvpPresenter.loadPartListData(parentId);
                } else {
                    mvpPresenter.loadPartListData2(parentId);
                }
            }
        });
    }
}
