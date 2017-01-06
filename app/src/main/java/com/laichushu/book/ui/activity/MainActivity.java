package com.laichushu.book.ui.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.FindFragment;
import com.laichushu.book.ui.fragment.HomeFragment;
import com.laichushu.book.ui.fragment.MineFragment;
import com.laichushu.book.ui.fragment.MsgFragment;
import com.laichushu.book.ui.fragment.WriteFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;


/**
 * 主页
 */
public class MainActivity extends MvpActivity2 implements View.OnClickListener {
    private int position = 0;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private WriteFragment writeFragment;
    private MsgFragment msgFragment;
    private MineFragment mineFragment;
    private RadioButton homeRbn;
    private RadioButton findRbn;
    private RadioButton writeRbn;
    private RadioButton msgRbn;
    private RadioButton mindRbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTabSelection(position);
    }

    @Override
    protected void initData() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        },20);
        homeRbn.setOnClickListener(this);
        findRbn.setOnClickListener(this);
        writeRbn.setOnClickListener(this);
        msgRbn.setOnClickListener(this);
        mindRbn.setOnClickListener(this);
        setDefaultFragment();
        findRbn.setClickable(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_main);
        homeRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_home);
        findRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_find);
        writeRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_write);
        msgRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_msg);
        mindRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_mind);
        return mSuccessView;
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        setTabSelection(0);
    }

    /**
     * 切换
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbn_home:
                if (position != 0) {
                    position = 0;
                    setTabSelection(position);
                }
                break;
            case R.id.rbn_find:
                if (position != 1) {
                    position = 1;
                    setTabSelection(position);
                }
                break;
            case R.id.rbn_write:
                if (position != 2) {
                    position = 2;
                    setTabSelection(position);
                }
                break;
            case R.id.rbn_msg:
                if (position != 3) {
                    position = 3;
                    setTabSelection(position);
                }
                break;
            case R.id.rbn_mind:
                if (position != 4) {
                    position = 4;
                    setTabSelection(position);
                }
                break;
        }
    }

    /**
     * 清理 FragmentManager 中的 Fragment。
     * 解决在系统设置中更改权限后，App 被 kill 掉重启时的 Fragment 状态错误问题。
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void clearFragmentManagerInsideFragments(Activity activity) {
        if (activity instanceof FragmentActivity) {
            FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
            int count = manager.getBackStackEntryCount();
            List<Fragment> list = manager.getFragments();
            int fragmentCount = list == null ? 0 : list.size();
            if (list != null) {
                for (Fragment fragment : list) {
                    manager.beginTransaction().remove(fragment).commit();
                }
            }
        }
    }

    private void setTabSelection(int position) {
        //记录position
        this.position = position;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("homeModel", getIntent().getParcelableExtra("homeModel"));
                    bundle.putParcelable("homeHotModel", getIntent().getParcelableExtra("homeHotModel"));
                    bundle.putParcelable("homeAllModel", getIntent().getParcelableExtra("homeAllModel"));
                    homeFragment.setArguments(bundle);
                    transaction.add(R.id.layFrame, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("homeModel", getIntent().getParcelableExtra("homeModel"));
                    findFragment.setArguments(bundle);
                    transaction.add(R.id.layFrame, findFragment);
                } else {
                    transaction.show(findFragment);
                }
                break;
            case 2:
                if (writeFragment == null) {
                    writeFragment = new WriteFragment();
                    transaction.add(R.id.layFrame, writeFragment);
                } else {
                    transaction.show(writeFragment);
                }
                break;
            case 3:
                if (msgFragment == null) {
                    msgFragment = new MsgFragment();
                    transaction.add(R.id.layFrame, msgFragment);
                } else {
                    transaction.show(msgFragment);
                }
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.layFrame, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) transaction.hide(homeFragment);
        if (findFragment != null) transaction.hide(findFragment);
        if (writeFragment != null) transaction.hide(writeFragment);
        if (msgFragment != null) transaction.hide(msgFragment);
        if (mineFragment != null) transaction.hide(mineFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
    }
}
