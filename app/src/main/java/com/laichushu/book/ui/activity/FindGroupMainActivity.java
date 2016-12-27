package com.laichushu.book.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.laichushu.book.R;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.mvp.findgroup.groupmain.GroupMainPresenter;
import com.laichushu.book.mvp.findgroup.groupmain.GroupMainView;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.ui.adapter.FindGroupListAdapter;
import com.laichushu.book.ui.adapter.FindGroupRecommenAdapter;
import com.laichushu.book.ui.adapter.MechanismTopicListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 发现 - 小组主页
 * Created by wangtong on 2016/12/26.
 */

public class FindGroupMainActivity extends MvpActivity2<GroupMainPresenter> implements GroupMainView, View.OnClickListener {

    private FrameLayout groupFay;
    private PullLoadMoreRecyclerView groupRyv;
    private PullLoadMoreRecyclerView recommendRyv;
    private PullLoadMoreRecyclerView topicRyv;
    private ArrayList<GroupListModle.DataBean> mGroupListdata = new ArrayList<>();
    private ArrayList<MechanismTopicListModel.DataBean> mTopicData = new ArrayList<>();
    private FindGroupListAdapter mGroupListAdapter;
    private FindGroupRecommenAdapter mRecommendAdapter;
    private MechanismTopicListAdapter mTopicAdapter;
    private boolean frist = false;
    private boolean second = false;
    private MyHandler mhandler = new MyHandler(this);

    /**
     * handler
     */
    static class MyHandler extends Handler {
        private WeakReference<FindGroupMainActivity> weakReference;

        MyHandler(FindGroupMainActivity FindGroupMainActivity) {
            weakReference = new WeakReference<FindGroupMainActivity>(FindGroupMainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FindGroupMainActivity handlerMemoryActivity = weakReference.get();
            if (handlerMemoryActivity != null) {
                if (handlerMemoryActivity.frist && handlerMemoryActivity.second) {
                    handlerMemoryActivity.refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                }
            }
        }
    }

    /**
     * @return 控制器
     */
    @Override
    protected GroupMainPresenter createPresenter() {
        return new GroupMainPresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_groupmain);
        //标题
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView mineIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        ImageView searchIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        //切换
        RadioButton fristRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);//小组
        RadioButton scondRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);//推荐话题
        //容器
        groupFay = (FrameLayout) mSuccessView.findViewById(R.id.fay_group);//小组容器
        //列表
        groupRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_group);//小组列表
        recommendRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_recommend_group);//小组推荐列表
        topicRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_topic);//话题推荐列表
        //点击事件
        fristRbn.setOnClickListener(this);
        scondRbn.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        //设置标题图片
        GlideUitl.loadImg2(this, R.drawable.search_icon, searchIv);
        GlideUitl.loadImg2(this, R.drawable.navigation_mine_normal, mineIv);
        //设置名称
        titleTv.setText("小组");
        //创建adapter
        mGroupListAdapter = new FindGroupListAdapter(mGroupListdata, this);
        mRecommendAdapter = new FindGroupRecommenAdapter(this);
        mTopicAdapter = new MechanismTopicListAdapter(mTopicData, this);
        //设置adapter
        groupRyv.setAdapter(mGroupListAdapter);
        recommendRyv.setAdapter(mRecommendAdapter);
        topicRyv.setAdapter(mTopicAdapter);

        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        mvpPresenter.loadGroupList();//获取小组列表
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                break;
            case R.id.iv_title_other://搜索
//                UIUtil.openActivity(mActivity,);
                break;
            case R.id.rbn_01://小组
                groupFay.setVisibility(View.VISIBLE);
                topicRyv.setVisibility(View.GONE);
                break;
            case R.id.rbn_02://最新话题
                topicRyv.setVisibility(View.VISIBLE);
                groupFay.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 获取小组列表数据 成功
     *
     * @param modle 成功模型
     */
    @Override
    public void getGroupListDataSuccess(GroupListModle modle) {
        if (modle.isSuccess()) {
            if (modle.getData() != null && modle.getData().isEmpty()) {
                mGroupListAdapter.setmData(modle.getData());
                frist = true;
                Message msg = new Message();
                mhandler.sendMessage(msg);
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    /**
     * 获取小组列表数据 失败
     *
     * @param msg 失败的信息
     */
    @Override
    public void getGroupListDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    /**
     * 获取话题列表数据 成功
     * @param model 成功模型
     */
    @Override
    public void getTopicListDataSuccess(MechanismTopicListModel model) {
        topicRyv.setPullLoadMoreCompleted();
        if (model.isSuccess()) {
//            if (pageNo == 1) {
//
//            }
//            if (model.getData() != null && !model.getData().isEmpty()) {
//                mData.addAll(model.getData());
//                pageNo++;
//                mAdapter.setmData(mData);
//                mAdapter.notifyDataSetChanged();
//            }
        } else {
            LoggerUtil.e(model.getErrMsg());
        }
    }

    /**
     * 获取话题列表数据 失败
     * @param msg
     */
    @Override
    public void getTopicListDataFail(String msg) {

    }
}