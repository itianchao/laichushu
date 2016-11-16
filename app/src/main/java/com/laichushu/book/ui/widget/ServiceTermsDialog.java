package com.laichushu.book.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.laichushu.book.R;


/**
 * 服务条款
 */
public class ServiceTermsDialog extends AlertDialog {
    private Context mContext;
    private TextView tv_service_terms;

    public ServiceTermsDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    public ServiceTermsDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serviceterms_dialog);
        initView();
    }

    private void initView() {
        tv_service_terms = (TextView) findViewById(R.id.tv_service_terms);
    }

}