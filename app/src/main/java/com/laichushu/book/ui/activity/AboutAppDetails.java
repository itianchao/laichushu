package com.laichushu.book.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.AppManager;
import com.laichushu.book.utils.BitmapUtil;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

public class AboutAppDetails extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack,appIcon;
    private TextView tvTitle;

    @Override
    protected BasePresenter createPresenter() {

        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_about_app_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        appIcon = ((ImageView) inflate.findViewById(R.id.iv_appIcon));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("关于来出书");
        tvTitle.setVisibility(View.VISIBLE);
        GlideUitl.loadCornersImg(mActivity,R.drawable.app_icon_2,appIcon,R.drawable.app_icon_2);
        ivBack.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }
}
