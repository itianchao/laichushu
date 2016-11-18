package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

public class MyBookCastActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private GridView gdView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View bookCastView = UIUtil.inflate(R.layout.activity_my_book_cast);
        ivBack = ((ImageView) bookCastView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) bookCastView.findViewById(R.id.tv_title));
//        gdView= ((GridView) bookCastView.findViewById(R.id.gv_list));
        return bookCastView;
    }


    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("作品管理");
        ivBack.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }
}
