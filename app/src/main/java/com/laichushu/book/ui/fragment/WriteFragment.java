package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.write.WriteModle;
import com.laichushu.book.mvp.write.WritePresenter;
import com.laichushu.book.mvp.write.WriteView;
import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.adapter.WriteBookAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 写书
 * Created by wangtong on 2016/10/17.
 */
public class WriteFragment extends MvpFragment2<WritePresenter> implements WriteView, View.OnClickListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private WriteBookAdapter writeBookAdapter;
    private ArrayList<HomeHotModel.DataBean> mData = new ArrayList<>();

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_write);
        EventBus.getDefault().register(this);
        mSuccessView.findViewById(R.id.iv_title_finish).setVisibility(View.INVISIBLE);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        LinearLayout addNewBookLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_add_newbook);
        titleTv.setText("来写书");
        addNewBookLay.setOnClickListener(this);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_book);
        mRecyclerView.setLinearLayout();
        writeBookAdapter = new WriteBookAdapter(mData, mActivity);
        mRecyclerView.setAdapter(writeBookAdapter);

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
    public void getDataSuccess(HomeHotModel model) {

    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_add_newbook://创建新书
                UIUtil.openActivity(mActivity, CreateNewBookActivity.class);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshWriteFragmentEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh()) {
            // TODO: 2016/11/17 刷新
            mvpPresenter.getArticleBookList();
        }
    }
}
