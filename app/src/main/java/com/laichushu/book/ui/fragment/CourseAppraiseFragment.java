package com.laichushu.book.ui.fragment;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.home.allcomment.AllCommentPresenter;
import com.laichushu.book.mvp.home.allcomment.AllCommentView;
import com.laichushu.book.mvp.home.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.ui.activity.FindClassVideoDetailActivity;
import com.laichushu.book.ui.adapter.CommentAllAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 发现 - 课程- 评论
 * Created by wangtong on 2017/1/9.
 */

public class CourseAppraiseFragment extends MvpFragment2<AllCommentPresenter> implements AllCommentView, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {

    private PullLoadMoreRecyclerView commentRyv;
    private RatingBar numRb;
    private EditText commentEt;
    private int lessonId;
    private CommentAllAdapter mAdapter;
    private int pageNo = 1;
    private ArrayList<ArticleCommentModle.DataBean> mData = new ArrayList<>();
    private boolean isComment;
    private LinearLayout commentLay;

    @Override
    protected AllCommentPresenter createPresenter() {
        return new AllCommentPresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_courseappraise);
        commentRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_comment);
        commentLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_comment);
        commentEt = (EditText) mSuccessView.findViewById(R.id.et_comment);
        numRb = (RatingBar) mSuccessView.findViewById(R.id.ratbar_num);
        commentRyv.setLinearLayout();
        commentRyv.setOnPullLoadMoreListener(this);
        commentEt.setOnEditorActionListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        mAdapter = new CommentAllAdapter(mActivity, mData, mvpPresenter);
        commentRyv.setAdapter(mAdapter);
        lessonId = getArguments().getInt("lessonId");
        isComment = getArguments().getBoolean("isComment");
        if (!isComment) {
            commentLay.setVisibility(View.GONE);
        } else {
            commentLay.setVisibility(View.VISIBLE);
        }
        mvpPresenter.loadAllCommentData(lessonId);
    }

    @Override
    public void onRefresh() {
        mData.clear();
        pageNo = 1;
        mvpPresenter.getParamet2().setPageNo(pageNo + "");
        mvpPresenter.loadAllCommentData(lessonId);
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.loadAllCommentData(lessonId);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String conent = commentEt.getText().toString();
            if (!TextUtils.isEmpty(conent)) {
                mvpPresenter.loadSendCommentData(lessonId, conent, (int) numRb.getRating() + "");
                commentEt.setText("");
                UIUtil.hideKeyboard(commentEt);
            } else {
                ToastUtil.showToast("请输入评论内容");
            }
        }
        return false;
    }

    @Override
    public void getDataSuccess(ArticleCommentModle model) {
        hideLoading();
        UIUtil.postPullLoadMoreCompleted(commentRyv);
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (!model.getData().isEmpty()) {
                pageNo++;
                mvpPresenter.getParamet2().setPageNo(pageNo + "");
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            reloadErrorBtn(model.getErrorMsg());
        }
    }

    @Override
    public void getSendDataSuccess(SendCommentMoudle model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            onRefresh();
            isComment = false;//能不能评论 false不能评论
            ((FindClassVideoDetailActivity) getActivity()).getMdata().setComment(true);
            commentLay.setVisibility(View.GONE);
        } else {
            String errorMsg = model.getErrMsg();
            if (errorMsg.contains("该用户已经评分了")) {
                ToastUtil.showToast(errorMsg);
            } else {
                reloadErrorBtn(model.getErrMsg());
            }
        }
    }

    @Override
    public void SaveScoreLikeData(RewardResult model, String type, int position) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//点赞
                Logger.e("点赞");
                mData.get(position).setIsLike(true);
                mData.get(position).setLikeNum(mData.get(position).getLikeNum() + 1);
            } else {//取消赞
                Logger.e("取消赞");
                mData.get(position).setIsLike(false);
                mData.get(position).setLikeNum(mData.get(position).getLikeNum() - 1);
            }
            mAdapter.setmData(mData);
        } else {
            reloadErrorBtn(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        UIUtil.postPullLoadMoreCompleted(commentRyv);
        reloadErrorBtn(msg);
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
     * 重新加载按钮
     */
    public void reloadErrorBtn(String msg) {
        LoggerUtil.e(msg);
        if (pageNo == 1 && msg.contains("全部评论")) {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
                @Override
                public void reLoadData() {
                    refreshPage(LoadingPager.PageState.STATE_LOADING);
                    mvpPresenter.loadAllCommentData(lessonId);
                }
            });
        } else {
            if (msg.contains("全部评论")) {
                ToastUtil.showToast("加载失败");
            } else if (msg.contains("点赞")) {
                ToastUtil.showToast("点赞失败");
            } else if (msg.contains("发送")) {
                ToastUtil.showToast("发送失败");
            }

        }
    }
}
