package com.laichushu.book.ui.activity;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 小组 - 我的
 * Created by wangtong on 2016/12/28.
 */

public class FindGroupMineActivity extends MvpActivity2 {

    private View mineLv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupmine);
        mineLv = mSuccessView.findViewById(R.id.lv_mine);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }
}
