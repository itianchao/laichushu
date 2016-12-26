package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.group.main.GroupMainPresenter;
import com.laichushu.book.mvp.group.main.GroupMainView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

/**
 * 小组主页
 * Created by wangtong on 2016/12/26.
 */

public class GroupMainActivity extends MvpActivity2<GroupMainPresenter> implements GroupMainView, View.OnClickListener {

    /**
     * @return 控制器
     */
    @Override
    protected GroupMainPresenter createPresenter() {
        return new GroupMainPresenter(this);
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_groupmain);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView mineIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        ImageView searchIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        RadioButton fristRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_01);
        RadioButton scondRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_02);
        fristRbn.setOnClickListener(this);
        scondRbn.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        GlideUitl.loadImg2(this, R.drawable.search_icon, searchIv);
        GlideUitl.loadImg2(this, R.drawable.navigation_mine_normal, mineIv);
        titleTv.setText("小组");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://关闭
                finish();
                break;
            case R.id.iv_title_other://搜索
                break;
            case R.id.rbn_01://小组
                break;
            case R.id.rbn_02://最新话题
                break;
        }
    }
}
