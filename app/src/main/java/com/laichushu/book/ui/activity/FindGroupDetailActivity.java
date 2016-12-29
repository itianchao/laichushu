package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.NewTopicList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findgroup.findgroupmain.FindGroupPagePresenter;
import com.laichushu.book.mvp.findgroup.findgroupmain.FindGroupPageView;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 小组详情
 * Created by wangtong on 2016/12/26.
 */

public class FindGroupDetailActivity extends MvpActivity2<FindGroupPagePresenter> implements FindGroupPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView backIv, headIv, searchIv, moreIv, topicIv;
    private TextView titleTv, linemarksTv, briefTv, createTimeTv, numberTv;
    private PullLoadMoreRecyclerView mRecyclerView;
    private RadioButton briefRbn, findRbn, recommendRbn;
    private LinearLayout briefLay;
    private GroupListModle.DataBean bean;
    private boolean isLeader;
    private boolean isJoin;

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
        topicIv = ((ImageView) inflate.findViewById(R.id.iv_title_the));//更多

        titleTv = ((TextView) inflate.findViewById(R.id.tv_title));//标题
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

        return inflate;
    }

    @Override
    protected void initData() {
        //==============================================设置数据
        titleTv.setText("小组主页");//标题
        bean = getIntent().getParcelableExtra("bean");//小组数据
        isLeader = bean.getLeaderId().equals(ConstantValue.USERID);//身份?
        //0　未加入　　1　申请加入　2　申请拒绝　3　正常　就是　申请通过　4　禁言
        isJoin = bean.getJoinStatus().equals("3") || bean.getJoinStatus().equals("4");//是否加入
        briefTv.setText(bean.getRemarks());//简介
        createTimeTv.setText(bean.getCreateDate());//创建时间
        linemarksTv.setText(bean.getMarkContent());//里程碑数据
        numberTv.setText(bean.getJoinNum() + "人");//人数
        GlideUitl.loadImg(mActivity, bean.getPhoto(), headIv);//头像
        GlideUitl.loadImg(mActivity, R.drawable.search_icon, searchIv);//搜索
        GlideUitl.loadImg2(mActivity, R.drawable.icon_more, moreIv);//更多
        GlideUitl.loadImg(mActivity, R.drawable.icon_comment, topicIv);//发话题
        //==============================================点击事件
        backIv.setOnClickListener(this);
        briefRbn.setOnClickListener(this);
        findRbn.setOnClickListener(this);
        recommendRbn.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 20);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.rbn_brief://简介
                briefLay.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.rbn_find://发现
                briefLay.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.rbn_recommend://推荐
                briefLay.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
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

                        } else {//成员：分享、加入小组or退出小组

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
            case R.id.iv_title_the://发话题

                break;
        }

    }

    @Override
    public void getDataFail(String msg) {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {

    }

    /**
     * @return 是否加入小组
     */
    public boolean isJoin() {
        return isJoin;
    }
}
