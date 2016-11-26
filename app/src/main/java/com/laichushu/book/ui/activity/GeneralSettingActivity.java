package com.laichushu.book.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.AppManager;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

public class GeneralSettingActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvAnother;
    private RelativeLayout rlAbout, rlSignOut;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_general_setting);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        rlAbout = ((RelativeLayout) inflate.findViewById(R.id.rl_aboutApp));
        rlSignOut = ((RelativeLayout) inflate.findViewById(R.id.rl_signOut));
        tvAnother = ((TextView) inflate.findViewById(R.id.tv_title_right));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("通用设置");
        tvAnother.setText("保存");
        tvAnother.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlSignOut.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rl_aboutApp:
                //关于来出书
                UIUtil.openActivity(mActivity, AboutAppDetails.class);
                break;
            case R.id.rl_signOut:
                //退出
                SharePrefManager.setLoginInfo("");
                UIUtil.openActivity(mActivity, LoginActivity.class);
                AppManager.getInstance().killAllActivity();
//                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.tv_title_right:
                //保存
                SharePrefManager.setCharacterNum("");
                SharePrefManager.setMsgState("");
                this.finish();
                break;
        }
    }
}
