package com.laichushu.book.mvp.findeditmypage;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.activity.FindEditMyPageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/24.
 */

public class FindEditMyPagePresenter extends BasePresenter<FindEditMyPageView> {
    private FindEditMyPageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public FindEditMyPagePresenter(FindEditMyPageView view) {
        attachView(view);
        mActivity = (FindEditMyPageActivity) view;
    }
    //显示私信

    /**
     * 发送私信对话框
     */
    public void openSendPerMsgDialog() {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_send_per_msg);
        final EditText edMsg = (EditText) customerView.findViewById(R.id.et_dialogMsg);

        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 请求私信接口
//                mvpPresenter.loadAddPerInfoDate(userId, edMsg);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle("发送私信")                                  // 为null时不显示title
                .withDialogColor("#94C3B7")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();

    }

    public void openTeamworkDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, R.style.DialogStyle);
        final AlertDialog alertDialog = dialogBuilder.create();
        View customerView = UIUtil.inflate(R.layout.item_pop_teamwork);
        Spinner spBook = (Spinner) customerView.findViewById(R.id.sp_bookList);
        EditText edContent = (EditText) customerView.findViewById(R.id.ed_remarksContent);
        Button btnContract = (Button) customerView.findViewById(R.id.bt_sendContract);


        alertDialog.setView(customerView);
        alertDialog.show();
        WindowManager m = mActivity.getWindowManager();
        Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setWindowAnimations(R.style.periodpopwindow_anim_style);
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }
}
