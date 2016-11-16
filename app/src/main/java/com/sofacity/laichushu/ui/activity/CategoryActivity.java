package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.homecategory.CategoryModle;
import com.sofacity.laichushu.mvp.homecategory.CategoryPresenter;
import com.sofacity.laichushu.mvp.homecategory.CategoryView;
import com.sofacity.laichushu.ui.adapter.CategoryChildAdapter;
import com.sofacity.laichushu.ui.adapter.CategoryParentAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.ui.base.MvpActivity2;
import com.sofacity.laichushu.ui.widget.LoadingPager;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 首页分类
 * Created by wangtong on 2016/11/10.
 */

public class CategoryActivity extends MvpActivity2<CategoryPresenter> implements CategoryView, View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<CategoryModle.DataBean> mParentData = new ArrayList<>();
    private ArrayList<CategoryModle.DataBean.ChildBean> mChildDate = new ArrayList<>();
    private TextView titleTv;
    private ImageView finishIv;
    private ListView parentLv;
    private TextView nameTv;
    private GridView childGv;
    private CategoryParentAdapter categoryParentAdapter;
    private CategoryChildAdapter categoryChildAdapter;

    @Override
    protected void initData() {
        mvpPresenter.loadCategoryData();
        categoryParentAdapter = new CategoryParentAdapter(mParentData);
        parentLv.setAdapter(categoryParentAdapter);
        parentLv.setOnItemClickListener(this);
        categoryChildAdapter = new CategoryChildAdapter(mChildDate);
        childGv.setAdapter(categoryChildAdapter);
        childGv.setOnItemClickListener(this);
    }

    @Override
    public void getDataSuccess(CategoryModle model) {
        if (model.isSuccess()) {
            mParentData = model.getData();
            checkItem(0);
            categoryParentAdapter.setmParentData(mParentData);
            categoryParentAdapter.notifyDataSetChanged();
            onItemClick(parentLv,parentLv,0,0);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
        ToastUtil.showToast("网络失败");
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View successView = UIUtil.inflate(R.layout.activity_category);
        titleTv = (TextView) successView.findViewById(R.id.tv_title);
        finishIv = (ImageView) successView.findViewById(R.id.iv_title_finish);
        parentLv = (ListView) successView.findViewById(R.id.lv_parent);
        nameTv = (TextView) successView.findViewById(R.id.tv_name);
        childGv = (GridView) successView.findViewById(R.id.gv_child);
        titleTv.setText("分类");
        finishIv.setOnClickListener(this);
        return successView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.lv_parent:
                String name = "全部" + mParentData.get(position).getName();
                nameTv.setText(name);
                checkItem(position);
                mChildDate.clear();
                mChildDate.addAll(mParentData.get(position).getChild());
                categoryParentAdapter.setmParentData(mParentData);
                categoryParentAdapter.notifyDataSetChanged();
                categoryChildAdapter.setmParentData(mChildDate);
                categoryChildAdapter.notifyDataSetChanged();
                break;
            case R.id.gv_child:
                String categoryId = mChildDate.get(position).getId();
                String title = mChildDate.get(position).getName();
                Bundle bundle = new Bundle();
                bundle.putString("categoryId",categoryId);
                bundle.putString("title",title);
                UIUtil.openActivity(this,CategoryListActivity.class,bundle);
                break;
        }
    }

    public void checkItem(int position) {
        for (int i = 0; i < mParentData.size(); i++) {
            CategoryModle.DataBean dataBean = mParentData.get(i);
            if (i == position) {
                dataBean.setPressd(true);
            } else {
                dataBean.setPressd(false);
            }
        }
    }
}
