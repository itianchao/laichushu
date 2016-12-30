package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findgroup.findgroupmain.FindGroupPagePresenter;
import com.laichushu.book.mvp.findgroup.findgroupmain.FindGroupPageView;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.ui.adapter.MechanismTopicListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 发现 - 小组详情
 * Created by wangtong on 2016/12/26.
 */

public class FindGroupDetailActivity extends MvpActivity2<FindGroupPagePresenter> implements FindGroupPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView backIv, headIv, searchIv, moreIv, topicIv, emptyIv;
    private TextView titleTv, groupNameTv, linemarksTv, briefTv, createTimeTv, numberTv;
    private PullLoadMoreRecyclerView mRecyclerView;
    public RadioButton briefRbn, findRbn, recommendRbn;
    private LinearLayout briefLay;
    private GroupListModle.DataBean bean;
    private boolean isLeader;
    private boolean isJoin;
    private boolean frist = false;
    private boolean second = false;
    private int position = 0;
    private int pageNo1 = 1;
    private int pageNo2 = 1;
    private ArrayList<MechanismTopicListModel.DataBean> mGroupTopicList = new ArrayList<>();//小组
    private ArrayList<MechanismTopicListModel.DataBean> mSuggestTopicList = new ArrayList<>();//推荐
    private MyHandler mhandler = new MyHandler(this);
    private MechanismTopicListAdapter mTopicAdapter;

    /**
     * handler
     */
    static class MyHandler extends Handler {
        private WeakReference<FindGroupDetailActivity> weakReference;

        MyHandler(FindGroupDetailActivity mActivity) {
            weakReference = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FindGroupDetailActivity handlerMemoryActivity = weakReference.get();
            if (handlerMemoryActivity != null) {
                if (handlerMemoryActivity.frist && handlerMemoryActivity.second) {
                    handlerMemoryActivity.refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                }
            }
        }
    }

    @Override
    protected FindGroupPagePresenter createPresenter() {
        return new FindGroupPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_findgroupmain);
        backIv = ((ImageView) inflate.findViewById(R.id.iv_title_finish));//返回按钮
        headIv = ((ImageView) inflate.findViewById(R.id.iv_head));//小组头像
        searchIv = ((ImageView) inflate.findViewById(R.id.iv_title_another));//搜索
        moreIv = ((ImageView) inflate.findViewById(R.id.iv_title_other));//更多
        topicIv = ((ImageView) inflate.findViewById(R.id.iv_title_the));//发话题
        emptyIv = ((ImageView) inflate.findViewById(R.id.iv_empty));//发话题
        titleTv = ((TextView) inflate.findViewById(R.id.tv_title));//标题
        groupNameTv = ((TextView) inflate.findViewById(R.id.tv_userRealName));//标题
        linemarksTv = ((TextView) inflate.findViewById(R.id.tv_linemarks));//里程碑内容
        createTimeTv = ((TextView) inflate.findViewById(R.id.tv_create_time));//创建时间
        briefTv = ((TextView) inflate.findViewById(R.id.tv_brief));//简介内容
        numberTv = ((TextView) inflate.findViewById(R.id.tv_number));//小组人数
        briefLay = ((LinearLayout) inflate.findViewById(R.id.lay_brief));//简介容器
        mRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_list);//话题 or 推荐容器
        mRecyclerView.setLinearLayout();
        mRecyclerView.setOnPullLoadMoreListener(this);
        briefRbn = (RadioButton) inflate.findViewById(R.id.rbn_brief);
        findRbn = (RadioButton) inflate.findViewById(R.id.rbn_find);
        recommendRbn = (RadioButton) inflate.findViewById(R.id.rbn_recommend);
        mTopicAdapter = new MechanismTopicListAdapter(mGroupTopicList, this);
        mRecyclerView.setAdapter(mTopicAdapter);
        return inflate;
    }

    @Override
    protected void initData() {
        //==============================================设置数据
        titleTv.setText("小组主页");//标题
        bean = getIntent().getParcelableExtra("bean");//小组数据
        groupNameTv.setText(bean.getName());//设置组名
        isLeader = bean.getLeaderId().equals(ConstantValue.USERID);//身份?
        //0　未加入　　1　申请加入　2　申请拒绝　3　正常　就是　申请通过　4　禁言
        isJoin = bean.getJoinStatus().equals("3") || bean.getJoinStatus().equals("4");//是否加入
        briefTv.setText(bean.getRemarks());//简介
        createTimeTv.setText(bean.getCreateDate());//创建时间
        linemarksTv.setText(bean.getMarkContent());//里程碑数据
        numberTv.setText(bean.getJoinNum() + "人");//人数
        GlideUitl.loadImg(mActivity, bean.getPhoto(), headIv);//头像
        GlideUitl.loadImg(mActivity, R.drawable.search_icon,searchIv);//搜索
        GlideUitl.loadImg2(mActivity, R.drawable.icon_more, moreIv);//更多
        GlideUitl.loadImg(mActivity, R.drawable.icon_comment,topicIv);//发话题
        //==============================================点击事件
        backIv.setOnClickListener(this);
        briefRbn.setOnClickListener(this);
        findRbn.setOnClickListener(this);
        recommendRbn.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
        topicIv.setOnClickListener(this);
        numberTv.setOnClickListener(this);
        mvpPresenter.getGroupTopicList(bean.getId());
        mvpPresenter.getGroupSuggestTopicList(bean.getId());
    }

    @Override
    public void onClick(View v) {
        final Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.rbn_brief://简介
                position = 0;
                briefLay.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                emptyIv.setVisibility(View.GONE);
                break;
            case R.id.rbn_find://话题
                if (position != 1) {
                    position = 1;
                    briefLay.setVisibility(View.GONE);
                    if (mGroupTopicList.isEmpty()) {
                        emptyIv.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        emptyIv.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                }
                break;
            case R.id.rbn_recommend://推荐
                if (position != 2) {
                    position = 2;
                    briefLay.setVisibility(View.GONE);
                    if (mSuggestTopicList.isEmpty()) {
                        emptyIv.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        emptyIv.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.iv_title_another://搜索：话题

                break;
            case R.id.iv_title_other://更多
                TypePopWindow popWindow = null;
                if (isLeader) {//组长：待处理申请、成员管理、修改资料、分享、解散
                    popWindow = new TypePopWindow(mActivity, mvpPresenter.getLeaderList());
                } else {//成员：分享、退出
                    popWindow = new TypePopWindow(mActivity, mvpPresenter.getMemberList());
                }
                popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        if (isLeader) {//组长：待处理申请、成员管理、修改资料、分享、解散
                            switch(position){
                                case 0://待处理申请
                                    bundle.putInt("type", 2);
                                    bundle.putString("title","小组成员("+bean.getJoinNum()+")");
                                    bundle.putString("teamId",bean.getId());
                                    UIUtil.openActivity(mActivity, FindGroupMemberListActivity.class, bundle);
                                    break;
                                case 1://成员管理
                                    bundle.putInt("type", 3);
                                    bundle.putString("title","成员管理");
                                    bundle.putString("teamId",bean.getId());
                                    UIUtil.openActivity(mActivity, FindGroupMemberListActivity.class, bundle);
                                    break;
                                case 2://修改资料

                                    break;
                                case 3://分享
                                    break;
                                case 4://解散
                                    break;
                            }
                        } else {//成员：分享、加入小组or退出小组
                            if (position == 0){//分享

                            }else {//加入小组or退出小组

                            }
                        }
                    }
                });
                popWindow.setWidth(UIUtil.dip2px(90));
                if (isLeader) {//组长
                    popWindow.setHeight(UIUtil.dip2px(40) * mvpPresenter.getLeaderList().size());
                } else {//成员
                    popWindow.setHeight(UIUtil.dip2px(40) * mvpPresenter.getMemberList().size());
                }
                popWindow.showAsDropDown(v);
                break;
            case R.id.iv_title_the://发表话题
                bundle.putString("type", "2");
                bundle.putString("partyId", bean.getId());
                UIUtil.openActivity(mActivity, HomePublishTopicActivity.class, bundle);
                break;
            case R.id.tv_number://跳转成员列表
                bundle.putInt("type", 1);
                bundle.putString("title","小组成员("+bean.getJoinNum()+")");
                bundle.putString("teamId",bean.getId());
                UIUtil.openActivity(mActivity, FindGroupMemberListActivity.class, bundle);
                break;
        }
    }

    /**
     * 获取小组话题列表 成功
     *
     * @param model
     */
    @Override
    public void getGroupTopicListDataSuccess(MechanismTopicListModel model) {
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        if (model.isSuccess()) {
            frist = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            if ((model.getData() != null)) {//判null
                if (model.getData().isEmpty()) {//判空
                    if (!mvpPresenter.getParamet1().getPageNo().equals("1")) {//如果是第一页
                        ToastUtil.showToast("没有更多数据啦");
                    }
                } else {
                    pageNo1++;
                    mvpPresenter.getParamet1().setPageNo(pageNo1 + "");
                    mGroupTopicList.addAll(model.getData());
                    mTopicAdapter.setmData(mGroupTopicList);
                }
            }
        } else {
            if (mvpPresenter.getParamet1().getPageNo().equals("1")) {//如果是第一页
                frist = false;
                refreshPage(LoadingPager.PageState.STATE_ERROR);
            } else {
                ErrorReloadData();
            }
        }
        briefRbn.setEnabled(true);
        recommendRbn.setEnabled(true);
    }

    /**
     * 获取小组话题列表 失败
     *
     * @param msg
     */
    @Override
    public void getGroupTopicListDataFail(String msg) {
        LoggerUtil.e(msg);
        UIUtil.postPullLoadMoreCompleted(mRecyclerView);
        if (mvpPresenter.getParamet1().getPageNo().equals("1")) {//如果是第一页
            frist = false;
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            ErrorReloadData();
        } else {
            ToastUtil.showToast("获取小组话题列表失败");
        }
        briefRbn.setEnabled(true);
        recommendRbn.setEnabled(true);
    }

    /**
     * 获取推荐话题列表 成功
     *
     * @param model
     */
    @Override
    public void getGroupSuggestTopicListSuccess(MechanismTopicListModel model) {
        if (model.isSuccess()) {
            second = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            if ((model.getData() != null)) {//判null
                if (model.getData().isEmpty()) {//判空
                    if (!mvpPresenter.getParamet2().getPageNo().equals("1")) {//如果是第一页
                        ToastUtil.showToast("没有更多数据啦");
                    }
                } else {
                    pageNo2++;
                    mvpPresenter.getParamet2().setPageNo(pageNo1 + "");
                    mSuggestTopicList.addAll(model.getData());
                    mTopicAdapter.setmData(mSuggestTopicList);
                }
            }
        } else {
            if (mvpPresenter.getParamet2().getPageNo().equals("1")) {//如果是第一页
                frist = false;
                refreshPage(LoadingPager.PageState.STATE_ERROR);
            } else {
                ErrorReloadData();
            }
        }
        briefRbn.setEnabled(true);
        findRbn.setEnabled(true);
    }

    /**
     * 获取推荐话题列表 失败
     *
     * @param msg
     */
    @Override
    public void getGroupSuggestTopicListFail(String msg) {
        LoggerUtil.e(msg);
        if (mvpPresenter.getParamet1().getPageNo().equals("1")) {//如果是第一页
            second = false;
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        } else {
            ToastUtil.showToast("获取小组推荐话题列表失败");
            ErrorReloadData();
        }
        briefRbn.setEnabled(true);
        findRbn.setEnabled(true);
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        if (position == 1) {
            pageNo1 = 1;
            mGroupTopicList.clear();
            mvpPresenter.getParamet1().setPageNo(pageNo1 + "");
            mvpPresenter.getGroupTopicList(bean.getId());
        } else {
            pageNo2 = 1;
            mSuggestTopicList.clear();
            mvpPresenter.getParamet2().setPageNo(pageNo2 + "");
            mvpPresenter.getGroupSuggestTopicList(bean.getId());
        }
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        if (position == 1) {
            mvpPresenter.getGroupTopicList(bean.getId());
        } else {
            mvpPresenter.getGroupSuggestTopicList(bean.getId());
        }
    }

    /**
     * @return 是否加入小组
     */
    public boolean isJoin() {
        return isJoin;
    }


    /**
     * 发表话题后返回刷新
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {
            Bundle bundle = data.getExtras();
            String str = bundle.getString("back");
            if (str.equals("updata")) {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mGroupTopicList.clear();
                mSuggestTopicList.clear();
                frist = false;
                second = false;
                pageNo1 = 1;
                pageNo2 = 1;
                mvpPresenter.getParamet1().setPageNo(pageNo1 + "");
                mvpPresenter.getParamet2().setPageNo(pageNo2 + "");
                mvpPresenter.getGroupTopicList(bean.getId());
                mvpPresenter.getGroupSuggestTopicList(bean.getId());
                Intent intent = new Intent();
                intent.putExtra("refrush", "refrush");
                setResult(3, intent);
            }
        }
    }

    public void ErrorReloadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                if (!frist) {
                    mvpPresenter.getGroupTopicList(bean.getId());
                }
                if (!second) {
                    mvpPresenter.getGroupSuggestTopicList(bean.getId());
                }
            }
        });
    }
}
