package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberModle;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberPresenter;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberView;
import com.laichushu.book.ui.adapter.FindGroupMemberAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ListUtil;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 小组 - 成员管理列表or成员列表or待审核成员列表
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMemberListActivity extends MvpActivity2<FindGroupMemberPresenter> implements FindGroupMemberView, View.OnClickListener, TextWatcher {

    private PullLoadMoreRecyclerView mRecyclerView;
    private EditText searchEt;
    private ImageView backIv,emptyIv;
    private TextView titleTv;
    private String title;
    private FindGroupMemberAdapter mAdapter;
    private ArrayList<FindGroupMemberModle.DataBean> mData = new ArrayList<>();
    private int type;
    private LinearLayout searchLay;
    private String teamId;
    private int argsMember = 0;
    @Override
    protected FindGroupMemberPresenter createPresenter() {
        return new FindGroupMemberPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupmemberlist);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        backIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        searchLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_search);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_rember);
        searchEt = (EditText) mSuccessView.findViewById(R.id.et_search);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        teamId = getIntent().getStringExtra("teamId");
        titleTv.setText(title);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setPushRefreshEnable(false);
        mRecyclerView.setPullRefreshEnable(false);
        mAdapter = new FindGroupMemberAdapter(this, mData, type, mvpPresenter);
        mRecyclerView.setAdapter(mAdapter);
        backIv.setOnClickListener(this);
        searchEt.addTextChangedListener(this);
        if (type == 2){
            mvpPresenter.getGroupApplyMemberList(teamId);//审核成员列表
        }else {
            mvpPresenter.getGroupMemberList(teamId);//成员列表
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    /**
     * 获取成员列表 or 待审核成员列表 成功
     *
     * @param modle 数据模型
     */
    @Override
    public void getDataSuccess(FindGroupMemberModle modle) {
        if (modle.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (modle.getData() != null && !modle.getData().isEmpty()) {
                mData = modle.getData();
                for (int i = mData.size() - 1; i >= 0; i--) {
                    if (mData.get(i).getUserId().equals(ConstantValue.USERID)) {
                        mData.get(i).setShowFollow(true);//判断
                    }
                    if (i>=1){
                        if (mData.get(i).getRole().equals("1")) {
                            ListUtil.indexExChange(mData,i,0);
                        }
                    }
                }
                mAdapter.setmData(mData);
            }else {
                emptyIv.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                searchLay.setVisibility(View.GONE);
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            loadErrorData();
        }
    }

    /**
     * 获取成员列表 or 待审核成员列表 失败
     *
     * @param msg 错误信息
     */
    @Override
    public void getDataFali(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        loadErrorData();
    }

    /**
     * 显示进度条
     */
    @Override
    public void showProgress() {
        showProgressDialog();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    /**
     * 申请处理 成功
     * @param model
     * @param position
     * @param result
     */
    @Override
    public void getApplyMemberSuccess(RewardResult model, int position, String result) {
        if (model.isSuccess()) {
            if (result.equals("0")){//拒绝
                mData.get(position).setStatus("2");
            }else {//加入
                mData.get(position).setStatus("3");
                argsMember++;
            }
            mAdapter.setmData(mData);
        }else {
            ToastUtil.showToast("审核处理失败");
            LoggerUtil.e(model.getErrMsg());
        }
    }

    /**
     * 申请处理 失败
     * @param msg
     */
    @Override
    public void getApplyMemberFail(String msg) {
        ToastUtil.showToast("审核处理失败");
        LoggerUtil.e(msg);
    }

    /**
     * 删除成员 成功
     * @param model
     * @param position
     */
    @Override
    public void deleteGroupMemberSuccess(RewardResult model, int position) {
        if (model.isSuccess()) {
            mData.remove(position);
            mAdapter.setmData(mData);
            argsMember--;
        }else {
            ToastUtil.showToast("删除失败");
            LoggerUtil.e(model.getErrMsg());
        }
    }

    /**
     * 删除成员 失败
     * @param msg
     */
    @Override
    public void deleteGroupMemberFail(String msg) {
        ToastUtil.showToast("删除失败");
        LoggerUtil.e(msg);
    }

    /**
     * 关注/取消关注 成功
     * @param model
     * @param isFocus
     * @param position
     */
    @Override
    public void getAddFollowDataSuccess(HomeFocusResult model, boolean isFocus, int position) {
        if (model.isSuccess()) {
            if (isFocus) {
                mData.get(position).setFollow(true);
            }else {
                mData.get(position).setFollow(false);
            }
            mAdapter.setmData(mData);
        }else {
            if (isFocus) {
                ToastUtil.showToast("关注失败");
            }else {
                ToastUtil.showToast("取消关注失败");
            }
        }
    }

    /**
     * 关注/取消关注 失败
     * @param msg
     * @param isFocus
     */
    @Override
    public void getAddFollowDataFail(String msg, boolean isFocus) {
        if (isFocus) {
            ToastUtil.showToast("关注失败");
        }else {
            ToastUtil.showToast("取消关注失败");
        }
    }


    public void loadErrorData(){
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                if (type == 2){
                    mvpPresenter.getGroupApplyMemberList(teamId);//审核
                }else {
                    mvpPresenter.getGroupMemberList(teamId);//成员
                }
            }
        });
    }
    //====================================================================================
    //EditView 监听搜索
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    private ArrayList<FindGroupMemberModle.DataBean> searchData = new ArrayList<>();
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchData.clear();
        for (FindGroupMemberModle.DataBean bean : mData) {
            if (bean.getName().contains(s)) {
                searchData.add(bean);
            }
        }
        if (TextUtils.isEmpty(searchEt.getText().toString())){
            mAdapter.setmData(mData);
        }else {
            mAdapter.setmData(searchData);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("argsMember",argsMember);
        setResult(4,data);
        super.finish();
    }
}
