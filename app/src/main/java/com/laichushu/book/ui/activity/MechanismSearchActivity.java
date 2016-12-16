package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.mechanismsearch.MechanismSearchModle;
import com.laichushu.book.mvp.mechanismsearch.MechanismSearchPresenter;
import com.laichushu.book.mvp.mechanismsearch.MechanismSearchView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.utils.UIUtil;

/**
 * 机构搜索页
 * Created by wt on 2016/12/15.
 */

public class MechanismSearchActivity extends MvpActivity2<MechanismSearchPresenter> implements MechanismSearchView, View.OnClickListener {

    private ImageView finishIV;
    private EditText searchEt;

    @Override
    protected MechanismSearchPresenter createPresenter() {
        return new MechanismSearchPresenter(this);
    }

    /**
     * 成功页面
     *
     * @return
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismsearch);
        finishIV = (ImageView) mSuccessView.findViewById(R.id.iv_finish);//返回
        searchEt = (EditText) mSuccessView.findViewById(R.id.et_search);//搜索
        finishIV.setOnClickListener(this);
        searchEt.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    public void getDataSuccess(MechanismSearchModle model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    /**
     * 显示进度条
     */
    @Override
    public void showLoading() {
        showProgressDialog();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish:
                finish();
                break;
            case R.id.et_search:
                break;
        }
    }
}
