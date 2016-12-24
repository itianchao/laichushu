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
import com.laichushu.book.ui.fragment.FragmentFactory;
import com.laichushu.book.ui.fragment.HomeFragment;

import java.util.List;


/**
 * 主页
 */
public class MainActivity extends BaseActivity implements  View.OnClickListener {
    private int position;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        RadioButton homeRbn = (RadioButton) findViewById(R.id.rbn_home);
        RadioButton findRbn = (RadioButton) findViewById(R.id.rbn_find);
        RadioButton writeRbn = (RadioButton) findViewById(R.id.rbn_write);
        RadioButton msgRbn = (RadioButton) findViewById(R.id.rbn_msg);
        RadioButton mindRbn = (RadioButton) findViewById(R.id.rbn_mind);
        homeRbn.setOnClickListener(this);
        findRbn.setOnClickListener(this);
        writeRbn.setOnClickListener(this);
        msgRbn.setOnClickListener(this);
        mindRbn.setOnClickListener(this);
        setDefaultFragment();
    }

    /** * 设置默认的 */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        HomeFragment fragment = (HomeFragment) FragmentFactory.getFragment(0);
        Bundle bundle = new Bundle();
        bundle.putParcelable("homeModel",getIntent().getParcelableExtra("homeModel"));
        bundle.putParcelable("homeHotModel",getIntent().getParcelableExtra("homeHotModel"));
        bundle.putParcelable("homeAllModel",getIntent().getParcelableExtra("homeAllModel"));
        transaction.remove(fragment);
        fragment.setArguments(bundle);
        transaction.replace(R.id.layFrame, fragment,"home");
        transaction.commit();
        position = 0;
    }

    /**
     * 替换fragment
     * @param position
     */
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = FragmentFactory.getFragment(position);
        ft.replace(R.id.layFrame, fragment);
        ft.commit();
    }

    /**
     * 切换
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rbn_home:
                if (position!=0){
                    position = 0;
                    onTabSelected(position);
                }
                break;
            case R.id.rbn_find:
                if (position!=1){
                    position = 1;
                    onTabSelected(position);
                }
                break;
            case R.id.rbn_write:
                if (position!=2){
                    position = 2;
                    onTabSelected(position);
                }
                break;
            case R.id.rbn_msg:
                if (position!=3){
                    position = 3;
                    onTabSelected(position);
                }
                break;
            case R.id.rbn_mind:
                if (position!=4){
                    position = 4;
                    onTabSelected(position);
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
}
