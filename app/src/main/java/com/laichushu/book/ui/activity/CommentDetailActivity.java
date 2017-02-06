package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshBookCommentListEvent;
import com.laichushu.book.event.RefurshCommentListEvent;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailView;
import com.laichushu.book.ui.adapter.CommentDetaileAdapter;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailPersenter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 评论详情
 * Created by wangtong on 2016/11/4.
 */
public class CommentDetailActivity extends MvpActivity<CommentDetailPersenter> implements CommentDetailView, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {
    private String pageNo = "1";
    private ImageView headIv;
    private TextView nameTv;
    private TextView contentTv;
    private TextView timeTv;
    private ImageView likeIv;
    private TextView likeTv;
    private TextView numberTv;
    private TextView gradeNameTv;
    private ImageView inIv;
    private ImageView gradeDetailsIv;
    private EditText edComment;
    private RelativeLayout rlComment;
    private ArrayList<CommentDetailModle.DataBean> mData = new ArrayList<>();
    private CommentDetaileAdapter mAdapter;
    private PullLoadMoreRecyclerView commentRyv;
    private String commentId;
    private ArticleCommentModle.DataBean dataBean;
    private String type, tag;
    private ImageView commentIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commentdetail);
        commentRyv = (PullLoadMoreRecyclerView) findViewById(R.id.ryv_comment);
        commentRyv.setLinearLayout();
        commentRyv.setOnPullLoadMoreListener(this);
        commentRyv.setFooterViewText("加载中");
        gradeDetailsIv = (ImageView) findViewById(R.id.iv_gradeDetail);
        gradeNameTv = (TextView) findViewById(R.id.tv_gradeName);
        headIv = (ImageView) findViewById(R.id.iv_comment_head);
        nameTv = (TextView) findViewById(R.id.tv_comment_name);
        contentTv = (TextView) findViewById(R.id.tv_comment_content);
        timeTv = (TextView) findViewById(R.id.tv_comment_time);
        likeIv = (ImageView) findViewById(R.id.iv_comment_like);
        likeTv = (TextView) findViewById(R.id.tv_comment_like);
        numberTv = (TextView) findViewById(R.id.tv_comment_number);
        inIv = (ImageView) findViewById(R.id.iv_comment_in);
        commentIv = (ImageView) findViewById(R.id.iv_comment);
        rlComment = (RelativeLayout) findViewById(R.id.rl_commentItem);
        edComment = (EditText) findViewById(R.id.et_comment);
        mAdapter = new CommentDetaileAdapter(this, mData);
        commentRyv.setAdapter(mAdapter);
        headIv.setOnClickListener(this);
    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        titleTv.setText(title);
        finishIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dataBean = getIntent().getParcelableExtra("bean");
        type = getIntent().getStringExtra("type");
        tag = getIntent().getStringExtra("tag");
        commentId = dataBean.getSourceId();
        if (null == tag) {
            initTitleBar("评论详情");
        } else if (tag.equals("replay")) {
            initTitleBar("回复评论");
        }
        //评论者等级
        if (null != dataBean.getLevelType()) {
            gradeDetailsIv.setVisibility(View.VISIBLE);
            gradeNameTv.setVisibility(View.VISIBLE);
            switch (dataBean.getLevelType()) {
                case "1":
                    gradeNameTv.setText("金牌作家");
                    GlideUitl.loadImg(mActivity, R.drawable.icon_gold_medal2x, gradeDetailsIv);
                    break;
                case "2":
                    gradeNameTv.setText("银牌作家");
                    GlideUitl.loadImg(mActivity, R.drawable.icon_silver_medal2x, gradeDetailsIv);
                    break;
                case "3":
                    gradeNameTv.setText("铜牌作家");
                    GlideUitl.loadImg(mActivity, R.drawable.icon_copper_medal2x, gradeDetailsIv);
                    break;
            }
        } else {
            gradeDetailsIv.setVisibility(View.GONE);
            gradeNameTv.setVisibility(View.GONE);

        }

        onRefresh();
        GlideUitl.loadRandImg(this, dataBean.getPhoto(), headIv);//头像
        nameTv.setText(dataBean.getNickName());//用户名
        contentTv.setText(dataBean.getContent());//评论内容
        timeTv.setText(dataBean.getCommentTime());//创建时间
        likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
        numberTv.setText(dataBean.getReplyNum() + "");//回复人数
        rlComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("commentId", commentId);
                UIUtil.openActivity(CommentDetailActivity.this, CommentSendActivity.class, bundle);
            }
        });
        inIv.setVisibility(View.INVISIBLE);
        if (dataBean.isIsLike()) {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
        } else {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
        }
        likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isIsLike()) {
                    mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "1");
                } else {
                    mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "0");
                }
            }
        });
    }

    @Override
    public void getDataSuccess(CommentDetailModle model) {
        hideLoading();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                commentRyv.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (mvpPresenter.getParamet().getPageNo().equals("1")) {
                mData.clear();
            }
            if (model.getData().size() != 0) {
                pageNo = Integer.parseInt(pageNo) + 1 + "";
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.showToast("请检查网络");
        commentRyv.setPullLoadMoreCompleted();
        Logger.e(msg);
    }

    @Override
    public void SaveScoreLikeData(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//点赞
                Logger.e("点赞");
                GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                dataBean.setIsLike(true);
                dataBean.setLikeNum(dataBean.getLikeNum() + 1);
                likeTv.setText(dataBean.getLikeNum() + "");
            } else {//取消赞
                Logger.e("取消赞");
                GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                dataBean.setIsLike(false);
                dataBean.setLikeNum(dataBean.getLikeNum() - 1);
                likeTv.setText(dataBean.getLikeNum() + "");
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
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
    protected CommentDetailPersenter createPresenter() {
        return new CommentDetailPersenter(this);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = "1";
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.loadCommentData(commentId);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.loadCommentData(commentId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_comment_head:
                //跳转用户主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", dataBean.getUserId());
                if (SharePrefManager.getUserId().equals(dataBean.getUserId())) {
                    UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                } else {
                    UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finish() {
        super.finish();
        RefurshBookCommentListEvent event = new RefurshBookCommentListEvent(true, -1);
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 发送评论刷新
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshCommentListEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            onRefresh();
            dataBean.setReplyNum(Integer.parseInt(numberTv.getText().toString()) + 1);
            numberTv.setText(dataBean.getReplyNum() + "");//回复人数
        }
    }
}
