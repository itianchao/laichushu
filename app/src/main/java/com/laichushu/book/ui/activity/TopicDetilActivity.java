package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.event.RefrushHomePageEvent;
import com.laichushu.book.event.RefrushTopicManageEvent;
import com.laichushu.book.event.RefrushUserPageEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicDetailPresenter;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicDetailView;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicdetailModel;
import com.laichushu.book.ui.adapter.TopicCommentDetaileAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 话题详情
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetilActivity extends MvpActivity2<TopicDetailPresenter> implements TopicDetailView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private MechanismTopicListModel.DataBean bean;
    private ImageView ivLike, ivShare, moreIv;
    private ImageView topicUserheadIv, permissionIv;
    private TextView topicAuthorTv;
    private TextView topicContentTv;
    private TextView topicTiemTv;
    private TextView topicNameTv;
    private TextView titleTv;
    private ImageView finishIv;
    private PullLoadMoreRecyclerView commentRyv;
    private int pageNo = 1;
    private TopicCommentDetaileAdapter mAdapter;
    private ArrayList<CommentDetailModle.DataBean> mData = new ArrayList<>();
    private String topicId;
    private EditText sendmsgEv;
    private TextView sendmsgIv;
    private String type;
    private HomeUseDyrResult.DataBean homeBean;
    private String tag;
    private int topicType;
    private ArrayList<String> permissionList1;
    private ArrayList<String> permissionList2;

    @Override
    protected TopicDetailPresenter createPresenter() {
        return new TopicDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_topicdetail);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        moreIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        ivShare = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        ivLike = (ImageView) mSuccessView.findViewById(R.id.iv_title_the);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        topicUserheadIv = (ImageView) mSuccessView.findViewById(R.id.iv_topic_userhead);
        permissionIv = (ImageView) mSuccessView.findViewById(R.id.iv_permission);
        topicAuthorTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_author);
        topicContentTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_content);
        topicTiemTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_time);
        topicNameTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_name);
        commentRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_comment);
        sendmsgEv = (EditText) mSuccessView.findViewById(R.id.et_sendmsg);
        sendmsgIv = (TextView) mSuccessView.findViewById(R.id.iv_sendmsg);
        commentRyv.setLinearLayout();
        commentRyv.setOnPullLoadMoreListener(this);
        commentRyv.setFooterViewText("加载中");
        mAdapter = new TopicCommentDetaileAdapter(this, mData, mvpPresenter);
        commentRyv.setAdapter(mAdapter);
        finishIv.setOnClickListener(this);
        sendmsgIv.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        permissionList1 = new ArrayList<>();
        permissionList2 = new ArrayList<>();
        permissionList1.add("设置为置顶");
        permissionList1.add("设置为推荐");
        permissionList1.add("删除话题");
        permissionList2.add("删除话题");
        titleTv.setText("话题详情");
        ivLike.setVisibility(View.VISIBLE);
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.activity_share);
        moreIv.setImageResource(R.drawable.icon_more2x);

        type = getIntent().getStringExtra("type");
        tag = getIntent().getStringExtra("tag");
        topicType = getIntent().getIntExtra("topicType", 0);
        if (("homepage").equals(type)) {
            homeBean = (HomeUseDyrResult.DataBean) getIntent().getSerializableExtra("topBean");
            topicId = homeBean.getId();
            topicNameTv.setText(homeBean.getTitle());
            topicAuthorTv.setText(homeBean.getCreatUserName());
            topicContentTv.setText(homeBean.getContent());
            topicTiemTv.setText(homeBean.getCreateDate());
            GlideUitl.loadRandImg(mActivity, homeBean.getCreaterPhoto(), topicUserheadIv);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (homeBean.isCollect()) {
                GlideUitl.loadImg(mActivity, R.drawable.icon_likedwhite2x, ivLike);
            } else {
                GlideUitl.loadImg(mActivity, R.drawable.icon_likewhite2x, ivLike);
            }
        } else {
            bean = getIntent().getParcelableExtra("bean");
            if (bean.isCollect()) {
                GlideUitl.loadImg(mActivity, R.drawable.icon_likedwhite2x, ivLike);
            } else {
                GlideUitl.loadImg(mActivity, R.drawable.icon_likewhite2x, ivLike);
            }
            topicId = bean.getId();
            topicNameTv.setText(bean.getTitle());
            topicAuthorTv.setText(bean.getCreatUserName());
            topicContentTv.setText(bean.getContent());
            topicTiemTv.setText(bean.getCreateDate());
            GlideUitl.loadRandImg(mActivity, bean.getCreaterPhoto(), topicUserheadIv);
        }
        mvpPresenter.loadCommentData(topicId, ConstantValue.TOPICCOMMENT_TYPE);

        if (topicType != 0 && topicType != 1 && (bean.getLeaderId().equals(ConstantValue.USERID) || bean.getCreatUserId().equals(ConstantValue.USERID))) {
            moreIv.setVisibility(View.VISIBLE);
            moreIv.setOnClickListener(this);
        } else {
            moreIv.setVisibility(View.GONE);
        }
    }

    /**
     * 评论列表
     *
     * @param model
     */
    @Override
    public void getDataSuccess(TopicdetailModel model) {
        UIUtil.postPullLoadMoreCompleted(commentRyv);
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (model.getData().size() != 0) {
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
                pageNo++;
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            reLoadDate();
        }
    }

    /**
     * 点赞
     *
     * @param model
     * @param type
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {
                ToastUtil.showToast("点赞成功！");
            } else {
                ToastUtil.showToast("取消点赞成功！");
            }

        } else {
//            ToastUtil.showToast("操作失败！");
            LoggerUtil.toJson(model);
        }
    }

    /**
     * 发消息
     *
     * @param model
     */
    @Override
    public void getSendDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            sendmsgEv.setText("");
            onRefresh();
        } else {
            ToastUtil.showToast("发送失败");
        }
    }

    /**
     * 收藏
     *
     * @param model
     * @param type
     */
    @Override
    public void getSaveCollectSuccess(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {
                ToastUtil.showToast("收藏成功！");
            } else {
                ToastUtil.showToast("取消收藏！");
            }

        } else {
            ToastUtil.showToast("操作失败！");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        reLoadDate();
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        ToastUtil.showToast("发送失败");
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 话题设置置顶 成功
     *
     * @param modle
     */
    @Override
    public void getTopicStickySuccess(RewardResult modle) {
        if (modle.isSuccess()) {
            ToastUtil.showToast("话题设置置顶成功");
        } else {
            ToastUtil.showToast("话题设置置顶失败");
        }
    }

    /**
     * 话题设置推荐 成功
     *
     * @param modle
     */
    @Override
    public void getTopicRecommendSuccess(RewardResult modle) {
        if (modle.isSuccess()) {
            ToastUtil.showToast("话题设置推荐成功");
        } else {
            ToastUtil.showToast("话题设置推荐失败");
        }
    }

    /**
     * 话题删除 成功
     *
     * @param modle
     */
    @Override
    public void getTopicDeleteSuccess(RewardResult modle) {
        if (modle.isSuccess()) {
            ToastUtil.showToast("话题删除成功");
        } else {
            ToastUtil.showToast("话题删除失败");
        }
    }

    /**
     * 话题设置置顶 失败
     * 话题设置推荐 失败
     * 话题删除 失败
     *
     * @param msg
     */
    @Override
    public void getDataFail3(String msg) {
        if (msg.contains("置顶")) {
            ToastUtil.showToast("话题设置置顶失败");
        } else if (msg.contains("推荐")) {
            ToastUtil.showToast("话题设置推荐失败");
        } else if (msg.contains("删除")) {
            ToastUtil.showToast("话题删除失败");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_another:
                //分享
                String linkUrl = Base64Utils.getStringUrl(topicId, ConstantValue.SHARE_TYPR_TOPIC);
                ShareUtil.showShare(mActivity, linkUrl, linkUrl, bean.getCreaterPhoto(), bean.getContent(), bean.getTitle());
                break;
            case R.id.iv_title_the:
                //收藏
                if (null == homeBean) {
                    ToastUtil.showToast("参数错误！");
                    return;
                }
                if (homeBean.isCollect()) {
                    mvpPresenter.loadCollectSaveDate(topicId, ConstantValue.COLLECTTOPIC_TYPE, "1");
                    homeBean.setCollect(false);
                    ivLike.setImageResource(R.drawable.icon_likewhite2x);
                } else {
                    mvpPresenter.loadCollectSaveDate(topicId, ConstantValue.COLLECTTOPIC_TYPE, "0");
                    homeBean.setCollect(true);
                    ivLike.setImageResource(R.drawable.icon_likedwhite2x);
                }
                break;
            case R.id.iv_sendmsg:
                if (type.equals("homepage")) {
                    mvpPresenter.topicDetailCommentSave(sendmsgEv, homeBean.getId(), ConstantValue.TOPICCOMMENT_TYPE);
                } else {
                    mvpPresenter.topicDetailCommentSave(sendmsgEv, bean.getId(), ConstantValue.TOPICCOMMENT_TYPE);
                }
                break;
            case R.id.iv_title_other://小组-话题-管理
                showPopWindow(v);
                break;
        }
    }

    /**
     * 小组话题管理 弹窗
     *
     * @param v
     */
    private void showPopWindow(View v) {
        TypePopWindow popWindow = null;
        final boolean isLeader = bean.getLeaderId().equals(ConstantValue.USERID);
        if (isLeader) {
            popWindow = new TypePopWindow(mActivity, permissionList1);
            popWindow.setHeight(UIUtil.dip2px(40) * 3);
        } else {
            popWindow = new TypePopWindow(mActivity, permissionList2);
            popWindow.setHeight(UIUtil.dip2px(40));
        }

        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int index) {
                if (isLeader) {
                    if (index == 0) {//置顶话题
                        mvpPresenter.topicSticky(bean.getId());
                    } else if (index == 1) {
                        mvpPresenter.topicRecommend(bean.getId());//推荐话题
                    } else {//删除话题
                        mvpPresenter.topicDelete(bean.getId());
                    }
                } else {
                    if (index == 0) {////删除话题
                        mvpPresenter.topicDelete(bean.getId());
                    }
                }
            }
        });
        popWindow.setWidth(UIUtil.dip2px(90));
        popWindow.showAsDropDown(v);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.loadCommentData(topicId, ConstantValue.TOPICCOMMENT_TYPE);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.loadCommentData(topicId, ConstantValue.TOPICCOMMENT_TYPE);
    }

    /**
     * 重新加载
     */
    public void reLoadDate() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadCommentData(topicId, ConstantValue.TOPICCOMMENT_TYPE);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        if (!TextUtils.isEmpty(tag)) {
            switch (tag) {
                case "home":
                    EventBus.getDefault().postSticky(new RefrushHomePageEvent(true));
                    break;
                case "user":
                    EventBus.getDefault().postSticky(new RefrushUserPageEvent(true));
                    break;
                case "top":
                    EventBus.getDefault().postSticky(new RefrushTopicManageEvent(true));
                    break;
            }
        }
    }
}
