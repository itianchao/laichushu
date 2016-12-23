package com.laichushu.book.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.ui.fragment.FragmentFactory;
import com.laichushu.book.ui.fragment.HomeFragment;


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
}
