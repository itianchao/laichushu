package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindCourseCommResult;
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
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 发现 - 小组主页
 * Created by wangtong on 2016/12/26.
 */

public class FindGroupMainActivity extends MvpActivity2<GroupMainPresenter> implements GroupMainView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private FrameLayout groupFay;
    private PullLoadMoreRecyclerView groupRyv;
    private PullLoadMoreRecyclerView recommendRyv;
    private PullLoadMoreRecyclerView topicRyv;
    private ArrayList<GroupListModle.DataBean> mGroupListdata = new ArrayList<>();
    private ArrayList<MechanismTopicListModel.DataBean> mTopicData = new ArrayList<>();
    private ArrayList<GroupListModle.DataBean> mRecommendData = new ArrayList<>();
    private FindGroupListAdapter mGroupListAdapter;
    private FindGroupRecommenAdapter mRecommendAdapter;
    private MechanismTopicListAdapter mTopicAdapter;
    private boolean frist = false;
    private boolean second = false;
    private MyHandler mhandler = new MyHandler(this);
    private int pageNo = 1;
    private ImageView emptyIv;

    /**
     * handler
     */
    static class MyHandler extends Handler {
        private WeakReference<FindGroupMainActivity> weakReference;

        MyHandler(FindGroupMainActivity FindGroupMainActivity) {
            weakReference = new WeakReference<>(FindGroupMainActivity);
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
        TextView createGroupTv = (TextView) mSuccessView.findViewById(R.id.tv_create_group);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView mineIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        ImageView searchIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        emptyIv = (ImageView) mSuccessView.findViewById(R.id.iv_empty);
        //切换
        RadioButton fristRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);//小组
        RadioButton scondRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);//推荐话题
        //容器
        groupFay = (FrameLayout) mSuccessView.findViewById(R.id.fay_group);//小组容器
        //列表
        groupRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_group);//小组列表
        recommendRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_recommend_group);//小组推荐列表
        topicRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_topic);//话题推荐列表
        groupRyv.setLinearLayout();
        recommendRyv.setGridLayout(4);
        topicRyv.setLinearLayout();
        groupRyv.setPushRefreshEnable(false);
        groupRyv.setPullRefreshEnable(false);
        recommendRyv.setPushRefreshEnable(false);
        recommendRyv.setPullRefreshEnable(false);
        topicRyv.setOnPullLoadMoreListener(this);
        //点击事件
        fristRbn.setOnClickListener(this);
        scondRbn.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        mineIv.setOnClickListener(this);
        createGroupTv.setOnClickListener(this);
        //设置标题图片
        GlideUitl.loadImg2(this, R.drawable.search_icon, searchIv);
        GlideUitl.loadImg2(this, R.drawable.navigation_mine_normal, mineIv);
        //设置名称
        titleTv.setText("小组");
        //创建adapter
        mGroupListAdapter = new FindGroupListAdapter(mGroupListdata, this);
        mRecommendAdapter = new FindGroupRecommenAdapter(mRecommendData, this);
        mTopicAdapter = new MechanismTopicListAdapter(mTopicData, this,4);
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
        mvpPresenter.loadNewTopicList();//获取最新话题
        mRecommendData = getIntent().getParcelableArrayListExtra("recommend");
        mRecommendAdapter.setmRecommendData(mRecommendData);
    }

    /**
     * 点击事件
     *
     * @param v 被点的控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                break;
            case R.id.iv_title_another://搜索
                UIUtil.openActivity(mActivity, FindGroupSearchActivity.class);
                break;
            case R.id.iv_title_other://我的
                UIUtil.openActivity(mActivity, FindGroupMineActivity.class);
                break;
            case R.id.rbn_01://小组
                groupFay.setVisibility(View.VISIBLE);
                topicRyv.setVisibility(View.GONE);
                break;
            case R.id.rbn_02://最新话题
                groupFay.setVisibility(View.GONE);
                if (mTopicData.isEmpty()) {
                    emptyIv.setVisibility(View.VISIBLE);
                    topicRyv.setVisibility(View.GONE);
                } else {
                    topicRyv.setVisibility(View.VISIBLE);
                    emptyIv.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_create_group://创建小组
                Bundle bundle = new Bundle();
                bundle.putInt("type",1);
                UIUtil.openActivity(mActivity, FindGroupCreateNewActivity.class,bundle);
                break;
        }
    }

    /**
     * 获取小组列表数据 成功
     *
     * @param modle 数据模型
     */
    @Override
    public void getGroupListDataSuccess(GroupListModle modle) {
        UIUtil.postPullLoadMoreCompleted(topicRyv);
        if (modle.isSuccess()) {
            frist = true;
            if (modle.getData() != null && !modle.getData().isEmpty()) {
                mGroupListdata = modle.getData();
                mGroupListAdapter.setmData(mGroupListdata);
                Message msg = new Message();
                mhandler.sendMessage(msg);
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ErrorReloadData();
        }
    }

    /**
     * 获取小组列表数据 失败
     *
     * @param msg 错误的信息
     */
    @Override
    public void getGroupListDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        ErrorReloadData();
    }

    /**
     * 获取最新话题列表数据 成功
     *
     * @param modle 数据模型
     */
    @Override
    public void getNewTopicListDataSuccess(MechanismTopicListModel modle) {
        UIUtil.postPullLoadMoreCompleted(topicRyv);
        if (modle.isSuccess()) {
            second = true;
            if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                Message msg = new Message();
                mhandler.sendMessage(msg);
                mTopicData.clear();
            }
            if (modle.getData() != null && !modle.getData().isEmpty()) {
                pageNo++;
                mvpPresenter.getParamet().setPageNo(pageNo + "");
            }
            if (modle.getData().isEmpty()) {
                ToastUtil.showToast("没用更多数据");
            }
            mTopicData.addAll(modle.getData());
            mTopicAdapter.setmData(mTopicData);
        } else {
            if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                ErrorReloadData();
            } else {
                ToastUtil.showToast("加载失败");
            }
        }
    }

    /**
     * 获取最新话题列表数据 失败
     *
     * @param msg 错误的信息
     */
    @Override
    public void getNewTopicDataFail(String msg) {
        if (mvpPresenter.getParamet().getPageNo().equals("1")) {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ErrorReloadData();
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        } else {
            ToastUtil.showToast("加载失败");
        }
    }

    public void ErrorReloadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                if (!frist) {
                    mvpPresenter.loadGroupList();
                }
                if (!second) {
                    mvpPresenter.loadNewTopicList();
                }
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.loadNewTopicList();
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.loadNewTopicList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {//创建小组后
            Bundle bundle = data.getExtras();
            String str = bundle.getString("back");
            if (str.equals("updata")) {//刷新小组列表
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                frist = false;
                mGroupListdata.clear();
                mvpPresenter.loadGroupList();
            }
        } else if (resultCode == 3) {//发话题后
            Bundle bundle = data.getExtras();
            String str = bundle.getString("refrush");
            if (str.equals("refrush")) {//刷新话题列表
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                second = false;
                mTopicData.clear();
                pageNo = 1;
                mvpPresenter.getParamet().setPageNo(pageNo + "");
                mvpPresenter.loadNewTopicList();
            }
        }else if (resultCode == 4){//更新小组人数
            Bundle bundle = data.getExtras();
            int argsMember = bundle.getInt("argsMember");
            int index = bundle.getInt("index");
            if (index>=0){
                mGroupListdata.get(index).setJoinNum(mGroupListdata.get(index).getJoinNum()+argsMember);
                mGroupListAdapter.setmData(mGroupListdata);
            }else if (index<0){
                mRecommendData.get(-index-1).setJoinNum(mRecommendData.get(index).getJoinNum()+argsMember);
                mRecommendAdapter.setmData(mRecommendData);
            }
        }
    }
}