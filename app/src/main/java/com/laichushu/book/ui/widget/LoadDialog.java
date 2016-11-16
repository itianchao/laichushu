package com.laichushu.book.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.utils.UIUtil;


public class LoadDialog extends Dialog {
    private TextView tv;

    public LoadDialog(Context context) {
        super(context, R.style.loading_dialog);
        initView();
    }

    private void initView() {
        View contentView = View.inflate(getContext(), R.layout.setting_dialog_layout, null);

        Window win = this.getWindow();

        WindowManager.LayoutParams shareDialogParams = this.getWindow().getAttributes();
        shareDialogParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        shareDialogParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置ShareDialog在屏幕中位于底部
        //shareDialogParams.x = (UIUtil.getScreenWidth()-shareDialogParams.width)/2;//设置x坐标
        //shareDialogParams.y = UIUtil.getScreenHeight()-shareDialogParams.height;//设置y坐标
        // 设置ShareDialog的屏幕内边距
        //win.getDecorView().setPadding(0, 0, 0, 0);

        win.setAttributes(shareDialogParams);

        setContentView(contentView);
//		ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.iv_route);
//		if (android.os.Build.VERSION.SDK_INT > 22) {//android 6.0替换clip的加载动画
//			Drawable drawable =  UIUtil.getResources().getDrawable(R.drawable.loading_anim_6);
//            mProgressBar.setIndeterminateDrawable(drawable);
//        }

        tv = (TextView) findViewById(R.id.tv);
        //win.setWindowAnimations(R.anim.alpha_in);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void setTitle(final CharSequence title) {
        UIUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(title);
            }
        });
    }

    /**
     * 延迟销毁对话框
     *
     * @param msg
     */
    public void postDelayDismiss(String msg) {
        if (msg != null || "".equals(msg)) setTitle(msg);
        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadDialog.this.dismiss();
            }
        }, 1500);
    }
}
