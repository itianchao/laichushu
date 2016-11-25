package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.write.WritePresenter;
import com.laichushu.book.mvp.write.WriteView;
import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.adapter.WriteBookAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
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
    private boolean isLoad = true;

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
        mRecyclerView.setPushRefreshEnable(false);
        mRecyclerView.setPullRefreshEnable(false);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        if (isLoad) {//只执行一次
            mvpPresenter.getArticleBookList();
        }else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
        writeBookAdapter = new WriteBookAdapter(mData, mActivity, mvpPresenter);
        mRecyclerView.setAdapter(writeBookAdapter);
    }

    @Override
    protected WritePresenter createPresenter() {
        return new WritePresenter(this);
    }

    @Override
    public void getDataSuccess(HomeHotModel model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mData.addAll(model.getData());
            writeBookAdapter.setmData(mData);
            writeBookAdapter.notifyDataSetChanged();
            isLoad = false;
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    @Override
    public void deleteNewBook(RewardResult model, int position) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            mData.remove(position);
            writeBookAdapter.setmData(mData);
            writeBookAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showToast("删除失败");
        }
    }

    @Override
    public void articleVote(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("投稿成功");
        } else {
            ToastUtil.showToast("投稿失败");
        }
    }

    @Override
    public void publishNewBook(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发表成功");
        } else {
            ToastUtil.showToast("发表失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail2(String msg) {
        ToastUtil.showToast("删除失败");
        LoggerUtil.e(msg);
        hideLoading();
    }

    @Override
    public void getDataFail3(String msg) {
        ToastUtil.showToast("投稿失败");
        LoggerUtil.e(msg);
        hideLoading();
    }

    @Override
    public void getDataFail4(String msg) {
        ToastUtil.showToast("发表失败");
        LoggerUtil.e(msg);
        hideLoading();
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
            mData.clear();
            mvpPresenter.getArticleBookList();
        }
    }
}
