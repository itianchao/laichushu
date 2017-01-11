package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.FindClassDocFragment;
import com.laichushu.book.ui.fragment.FindClassMineFragment;
import com.laichushu.book.ui.fragment.FindClassVideoFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;


public class FindCoursePageActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack,searchIv;
    private TextView tvTitle;
    private RadioButton videoRbn, docRbn, mineRbn;
    private int position = -1;
    private FindClassVideoFragment videoFragment;
    private FindClassDocFragment docFragment;
    private FindClassMineFragment mineFragment;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_find_course_page);
        ivBack = ((ImageView) mSuccessView.findViewById(R.id.iv_title_finish));
        searchIv = ((ImageView) mSuccessView.findViewById(R.id.iv_title_other));
        tvTitle = ((TextView) mSuccessView.findViewById(R.id.tv_title));
        videoRbn = ((RadioButton) mSuccessView.findViewById(R.id.rb_video));//视频
        docRbn = ((RadioButton) mSuccessView.findViewById(R.id.rb_doc));//文档
        mineRbn = ((RadioButton) mSuccessView.findViewById(R.id.rb_mine));//我的
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("课程");
        GlideUitl.loadImg(mActivity,R.drawable.search_icon,searchIv);
        ivBack.setOnClickListener(this);
        videoRbn.setOnClickListener(this);
        docRbn.setOnClickListener(this);
        mineRbn.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
        onClick(videoRbn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rb_video://视频呢
                if (position != 0) {
                    position = 0;
                    searchIv.setVisibility(View.VISIBLE);
                    setTabSelection(position);
                }
                break;
            case R.id.rb_doc://文档
                if (position != 1) {
                    position = 1;
                    searchIv.setVisibility(View.VISIBLE);
                    setTabSelection(position);
                }
                break;
            case R.id.rb_mine://我的
                if (position != 2) {
                    position = 2;
                    searchIv.setVisibility(View.GONE);
                    setTabSelection(position);
                }
                break;
            case R.id.iv_title_other://搜索
                Bundle bundle = new Bundle();
                bundle.putInt("type",position);
                UIUtil.openActivity(mActivity,FindClassSearchActivity.class,bundle);
        }
    }

    private void setTabSelection(int position) {
        //记录position
        this.position = position;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position) {
            case 0:
                if (videoFragment == null) {
                    videoFragment = new FindClassVideoFragment();
                    transaction.add(R.id.fay_content, videoFragment);
                } else {
                    transaction.show(videoFragment);
                }
                break;
            case 1:
                if (docFragment == null) {
                    docFragment = new FindClassDocFragment();
                    transaction.add(R.id.fay_content, docFragment);
                } else {
                    transaction.show(docFragment);
                }
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new FindClassMineFragment();
                    transaction.add(R.id.fay_content, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (videoFragment != null) transaction.hide(videoFragment);
        if (docFragment != null) transaction.hide(docFragment);
        if (mineFragment != null) transaction.hide(mineFragment);
    }
}
