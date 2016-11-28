package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.UIUtil;

/**
 * 消息
 * Created by wangtong on 2016/10/17.
 */
public class MsgFragment extends MvpFragment2 implements View.OnClickListener {
    private TextView tvTitle;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_msg);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("消息");
    }

    @Override
    public void onClick(View v) {

    }
}
