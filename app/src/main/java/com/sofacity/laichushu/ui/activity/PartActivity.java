package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.part.PartModel;
import com.sofacity.laichushu.mvp.part.PartPresenter;
import com.sofacity.laichushu.mvp.part.PartView;
import com.sofacity.laichushu.ui.adapter.PartListAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 节
 * Created by wangtong on 2016/11/7.
 */
public class PartActivity extends MvpActivity<PartPresenter> implements PartView, AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView finishIv;
    private String parentId;
    private ListView bookLv;
    private ArrayList<PartModel.DataBean> mPartdata = new ArrayList<>();
    private PartListAdapter partListAdapter;
    private TextView titleTv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_part);
        finishIv = (ImageView)findViewById(R.id.iv_title_finish);
        titleTv = (TextView)findViewById(R.id.tv_title);
        bookLv = (ListView)findViewById(R.id.lv_book);
    }

    @Override
    protected void initData() {
        finishIv.setOnClickListener(this);
        parentId = getIntent().getStringExtra("parentId");
        String title = getIntent().getStringExtra("title");
        titleTv.setText(title);
        partListAdapter = new PartListAdapter(this, mPartdata);
        bookLv.setAdapter(partListAdapter);
        mvpPresenter.loadPartListData(parentId);
        bookLv.setOnItemClickListener(this);
    }

    @Override
    protected PartPresenter createPresenter() {
        return new PartPresenter(this);
    }

    @Override
    public void getDataSuccess(PartModel model) {
        if (model.isSuccess()) {
            if (model.getData() != null) {
                mPartdata = model.getData();
                partListAdapter.setmPartdata(mPartdata);
                partListAdapter.notifyDataSetChanged();
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
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
        String path = mPartdata.get(position).getContent();
        String name = mPartdata.get(position).getName();
        bundle.putString("path",path);
        bundle.putString("title",name);
        if (!TextUtils.isEmpty(path)){
            UIUtil.openActivity(this,NopublishBookActivity.class,bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }
}
