package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.bean.netbean.ReSavaComment_Paramet;
import com.laichushu.book.event.RefurshCommentListEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.R;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 发送回复评论
 * Created by wangtong on 2016/11/4.
 */
public class CommentSendActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText commentEt;
    private String userId = ConstantValue.USERID;
    private String commentId;
    private String sourceType = ConstantValue.COMMENTBOOK_TYPE;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commentsend);
        initTitleBar("发布评论");
        commentEt = (EditText)findViewById(R.id.et_comment);
        commentEt.setOnEditorActionListener(this);
    }

    @Override
    protected void initData() {
        commentId = getIntent().getStringExtra("commentId");
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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_finish:
                RefurshCommentListEvent event = new RefurshCommentListEvent(true);
                EventBus.getDefault().postSticky(event);
                finish();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String conent = commentEt.getText().toString();
            if (!TextUtils.isEmpty(conent)){
                ReSavaComment_Paramet paramet = new ReSavaComment_Paramet(commentId,userId,conent,sourceType);
                showProgressDialog();
                addSubscription(apiStores.saveComment(paramet), new ApiCallback<CommentDetailModle>() {
                    @Override
                    public void onSuccess(CommentDetailModle model) {
                        dismissProgressDialog();
                        if (model.isSuccess()) {
                            ToastUtil.showToast("发送成功");
                            RefurshCommentListEvent event = new RefurshCommentListEvent(true);
                            EventBus.getDefault().postSticky(event);
                            UIUtil.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            },1700);
                        }else {
                            String errorMsg = model.getErrMsg();
                            if (errorMsg.contains("该用户已经评分了")){
                                ToastUtil.showToast(errorMsg);
                            }else {
                                ToastUtil.showToast("发送失败");
                            }
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast("请检查网络");
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
                commentEt.setText("");
                UIUtil.hideKeyboard(commentEt);
            }else {
                ToastUtil.showToast("请输入评论内容");
            }
        }
        return false;
    }

}
