package com.sofacity.laichushu.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.ui.fragment.FragmentFactory;
import com.sofacity.laichushu.ui.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements  View.OnClickListener {
    private ArrayList<Fragment> fragments;
    private int position;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        fragments = getFragments();
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

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentFactory.getFragment(0));
        fragments.add(FragmentFactory.getFragment(1));
        fragments.add(FragmentFactory.getFragment(2));
        fragments.add(FragmentFactory.getFragment(3));
        fragments.add(FragmentFactory.getFragment(4));
        return fragments;
    }

    /**
     * 替换fragment
     * @param position
     */
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }

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
}
