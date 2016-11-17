package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.write.WriteModle;
import com.laichushu.book.mvp.write.WritePresenter;
import com.laichushu.book.mvp.write.WriteView;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 写书
 * Created by wangtong on 2016/10/17.
 */
public class WriteFragment extends MvpFragment2<WritePresenter> implements WriteView, View.OnClickListener {

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_write);
        mSuccessView.findViewById(R.id.iv_title_finish).setVisibility(View.INVISIBLE);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        LinearLayout addNewBookLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_add_newbook);
        titleTv.setText("来写书");
        addNewBookLay.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    protected WritePresenter createPresenter() {
        return new WritePresenter(this);
    }


    @Override
    public void getDataSuccess(WriteModle model) {

    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lay_add_newbook:
                break;
        }
    }
}
