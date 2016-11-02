package com.sofacity.laichushu.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.ui.base.BaseFragment;
import com.sofacity.laichushu.ui.fragment.FragmentFactory;
import com.sofacity.laichushu.ui.fragment.HomeFragment;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bnb_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
        .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        BottomNavigationItem home = new BottomNavigationItem(R.drawable.navigation_home_normal, UIUtil.getString(R.string.home));
        BottomNavigationItem find = new BottomNavigationItem(R.drawable.navigation_find_normal, UIUtil.getString(R.string.find));
        BottomNavigationItem write = new BottomNavigationItem(R.drawable.navigation_write_normal, null);
        BottomNavigationItem msg = new BottomNavigationItem(R.drawable.navigation_message_normal, UIUtil.getString(R.string.message));
        BottomNavigationItem mine = new BottomNavigationItem(R.drawable.navigation_mine_normal,  UIUtil.getString(R.string.mine));

        bottomNavigationBar
                .addItem(home).setActiveColor(R.color.global)
                .addItem(find).setActiveColor(R.color.global)
                .addItem(write)
                .addItem(msg).setActiveColor(R.color.global)
                .addItem(mine).setActiveColor(R.color.global)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
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

    @Override
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

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

}
