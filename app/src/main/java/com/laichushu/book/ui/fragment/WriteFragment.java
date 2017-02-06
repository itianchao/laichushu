package com.laichushu.book.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.MyTabStrip;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.SignStateResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.event.RefrushMerPermissionEvent;
import com.laichushu.book.event.RefrushWriteFragmentEvent;
import com.laichushu.book.event.RefurshWriteFragment;
import com.laichushu.book.event.RefurshWriteFragmentEvent;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.write.WritePresenter;
import com.laichushu.book.mvp.write.WriteView;
import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.adapter.WriteBookAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.SpinerPopWindow;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 写书
 * Created by wangtong on 2016/10/17.
 */
public class WriteFragment extends MvpFragment2<WritePresenter> implements WriteView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private PullLoadMoreRecyclerView mRecyclerView;
    private WriteBookAdapter writeBookAdapter;
    private ArrayList<HomeHotModel.DataBean> mData = new ArrayList<>();
    private ArrayList<MyTabStrip> mStrip = new ArrayList<>();
    private int img[] = {R.drawable.icon_draft2x, R.drawable.icon_material2x, R.drawable.icon_delete2x, R.drawable.icon_publishl2x, R.drawable.icon_submission2x, R.drawable.icon_sign2x};
    private String title[] = {"编辑目录", "编辑素材", "删除", "发表", "投稿", "签约状态"};
    private boolean isLoad = true;
    //-----
    private SpinerPopWindow mSpinerPopWindow, mSpinerPopWindow2;
    private SignStateResult model = new SignStateResult();
    final List<String> list = new ArrayList<>();//出版社
    final List<String> editList = new ArrayList<>();//编辑
    private String pressId, editId;
    private int currentPos;
    private AlertDialog alertDialog = null;
    private int PAGE_NO = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_write);
        mSuccessView.findViewById(R.id.iv_title_finish).setVisibility(View.INVISIBLE);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        LinearLayout addNewBookLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_add_newbook);
        titleTv.setText("来写书");
        addNewBookLay.setOnClickListener(this);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_book);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerView.setPushRefreshEnable(false);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        //初始化strip列表
        mStrip.clear();
        for (int i = 0; i < 6; i++) {
            mStrip.add(new MyTabStrip(title[i], img[i]));
        }
        writeBookAdapter = new WriteBookAdapter(mData, mActivity, mvpPresenter, mStrip);
        mRecyclerView.setAdapter(writeBookAdapter);

        if (isLoad) {//只执行一次
            mvpPresenter.getArticleBookList();
        } else {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }
    }

    @Override
    protected WritePresenter createPresenter() {
        return new WritePresenter(this);
    }

    @Override
    public void getDataSuccess(HomeHotModel model) {
        if (model.isSuccess()) {
            if (null != model.getData()) {
                mData.clear();
                mData.addAll(model.getData());
                writeBookAdapter.setmData(mData);
                PAGE_NO++;
            } else {
                ToastUtil.showToast(R.string.errMsg_empty);
            }
            mRecyclerView.setPullLoadMoreCompleted();
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            isLoad = false;
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            refurshData();
        }
    }

    /**
     * 修改签约状态
     *
     * @param model
     * @param articleId
     */
    @Override
    public void getSignEditorDataSuccess(RewardResult model, String articleId) {
        if (model.isSuccess()) {
            ToastUtil.showToast("修改成功");
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getArticleId().equals(articleId)){
                    //修改出版中
                    mData.get(i).setStatus("3");
                    writeBookAdapter.setmData(mData);
                    return;
                }
            }
        } else {
            ToastUtil.showToast("修改失败");
        }
    }

    @Override
    public void deleteNewBook(RewardResult model, int position) {
        if (model.isSuccess()) {
            ToastUtil.showToast("删除成功");
            mData.remove(position);
            writeBookAdapter.setmData(mData);
            updateDB();
        } else {
            ToastUtil.showToast("删除失败");
        }
    }

    @Override
    public void articleVote(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("投稿成功");
        } else {
//            if (model.getErrMsg().contains("已经投稿")) {
//                ToastUtil.showToast("投稿失败，此出版社已经投稿了");
//            } else {
//                ToastUtil.showToast("投稿失败");
//            }
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getSignStateDeteSuccess(final SignStateResult model, final String articleId) {
        writeBookAdapter.getFinalItemView().setEnabled(true);
        if (model.isSuccess()) {
            this.model = model;
            if (alertDialog == null) {
                alertDialog = new AlertDialog.Builder(mActivity, R.style.DialogStyle).create();
            }
            if (alertDialog.isShowing()) {
                return;
            }
            View customerView = UIUtil.inflate(R.layout.dialog_signstate);
            final Spinner spPress = (Spinner) customerView.findViewById(R.id.sp_press);
            final Spinner spEdit = (Spinner) customerView.findViewById(R.id.sp_edit);
            final TextView tvCancle = (TextView) customerView.findViewById(R.id.tv_cancel);
            final TextView tvSubmit = (TextView) customerView.findViewById(R.id.tv_submit);

            list.clear();
            for (int i = 0; i < model.getData().size(); i++) {
                list.add(model.getData().get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, R.layout.spiner_item_layout, list);
            adapter.setDropDownViewResource(R.layout.spiner_item_layout);
            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mActivity, R.layout.spiner_item_layout, editList);
            adapter2.setDropDownViewResource(R.layout.spiner_item_layout);
            spPress.setAdapter(adapter);
            spEdit.setAdapter(adapter2);
            spPress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    editList.clear();
                    for (int i = 0; i < model.getData().get(position).getEditors().size(); i++) {
                        editList.add(model.getData().get(position).getEditors().get(i).getNickName());
                    }
                    pressId = model.getData().get(position).getId();

                    if (null != model.getData().get(position).getEditors() && model.getData().get(position).getEditors().size() > 0) {
                        editId = model.getData().get(position).getEditors().get(0).getId();
                    } else {
                        editId = null;
                    }

                    currentPos = position;
                    adapter2.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    editId = model.getData().get(currentPos).getEditors().get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            tvCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(pressId) && !TextUtils.isEmpty(editId)) {
                        mvpPresenter.getSignEditorDeta(pressId, articleId, editId);
                    } else {
                        ToastUtil.showToast("修改失败");
                    }
                    alertDialog.dismiss();
                }
            });
            alertDialog.setView(customerView);
            alertDialog.show();
            WindowManager m = mActivity.getWindowManager();
            Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().setLayout(display.getWidth() - UIUtil.dip2px(60), display.getHeight() / 2 - UIUtil.dip2px(100));
            alertDialog.getWindow().setWindowAnimations(R.style.periodpopwindow_anim_style);
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }

        } else {
            ToastUtil.showToast("获取数据失败");
        }

    }

    /**
     * 更新权限
     *
     * @param model
     */
    @Override
    public void updateBookPermission(RewardResult model, String permission, int position) {
        if (model.isSuccess()) {
            HomeHotModel.DataBean dataBean = mData.get(position);
            dataBean.setPermission(permission);
            mData.set(position, dataBean);
            ToastUtil.showToast("设置权限成功");
        } else {
            ToastUtil.showToast("设置权限失败");
        }
    }

    @Override
    public void publishNewBook(RewardResult model, int index, String type) {
        if (model.isSuccess()) {
            if (type.equals("1")) {
                mData.get(index).setExpressStatus("0");
                mData.get(index).setEdit(true);
                mData.get(index).setDelete(true);
            } else {
                mData.get(index).setExpressStatus("1");
                mData.get(index).setEdit(false);
                mData.get(index).setDelete(false);
            }
            writeBookAdapter.setmData(mData);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        refurshData();
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
        switch (msg) {
            case "发表":
                ToastUtil.showToast("发表失败");
                break;
            case "更新权限":
                ToastUtil.showToast("设置权限失败");
                break;
            case "签约状态":
                ToastUtil.showToast("设置签约失败");
                break;
        }
        writeBookAdapter.getFinalItemView().setEnabled(true);
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

    public void refurshData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mData.clear();
                mvpPresenter.getBookList_paramet().setPageNo(PAGE_NO+"");
                mvpPresenter.getArticleBookList();
            }
        });
    }

    private void showSpinWindow(TextView tv) {

    }

    private void updateDB() {
        Cache_JsonDao cache_jsonDao = BaseApplication.getDaoSession(getActivity()).getCache_JsonDao();
        List<Cache_Json> cache_jsons = cache_jsonDao.queryBuilder()
                .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).build().list();
        Cache_Json cache_json = cache_jsons.get(0);
        PersonalCentreResult result = new Gson().fromJson(cache_json.getJson(), PersonalCentreResult.class);
        if (result.getArticleCount() != null) {
            result.setArticleCount(Integer.parseInt(result.getArticleCount()) - 1 + "");
        } else {
            result.setArticleCount("0");
        }
        cache_json.setJson(new Gson().toJson(result));
        cache_jsonDao.update(cache_json);
    }

    @Override
    public void onRefresh() {
        PAGE_NO=1;
        refreshPage(LoadingPager.PageState.STATE_LOADING);
        mData.clear();
        mvpPresenter.getBookList_paramet().setPageNo(PAGE_NO+"");
        mvpPresenter.getArticleBookList();
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getBookList_paramet().setPageNo(PAGE_NO+"");
        mvpPresenter.getArticleBookList();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 投稿后刷新页面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshWriteFragment event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh()) {
            onRefresh();
        }
    }

    /**
     * 修改详情页后刷新数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushWriteFragmentEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh()) {
            onRefresh();
        }
    }

    /**
     * 修改 素材权限
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushMerPermissionEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.getIndex() != -1) {
            HomeHotModel.DataBean dataBean = mData.get(event.getIndex());
            dataBean.setMaterialPermission(event.getMaterialPermission());
            mData.set(event.getIndex(), dataBean);
        }
    }

}
