package com.sofacity.laichushu.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.adapter.GuideAdapter;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.UIUtil;

import java.util.ArrayList;

/**
 * 引导页面
 * Created by wangtong on 2016/10/11.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener
{
    private Button btn_start;
    private ImageView iv_red_point;
    private GuideAdapter adapter;
    private ArrayList<Integer> imageList;
    private LinearLayout ll_container;
    private int range;
    private ViewPager vp_guide;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_start = (Button) findViewById(R.id.btn_start);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
    }

    @Override
    protected void initData() {
        //添加引导页
        imageList = new ArrayList<>();
        imageList.add(R.drawable.img_guide1);
        imageList.add(R.drawable.img_guide2);
        imageList.add(R.drawable.img_guide3);
        adapter = new GuideAdapter(imageList,mActivity);
        vp_guide.setAdapter(adapter);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置不能再次进入引导页
                SharePrefManager.setFristLogin(false);
                UIUtil.openActivity(mActivity, LoginActivity.class);
                finish();
            }
        });
        vp_guide.setOnPageChangeListener(this);
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                range = ll_container.getChildAt(1).getLeft() - ll_container.getChildAt(0).getLeft();
            }
        });
        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.shape_point_hollow);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i>0){
                params.leftMargin = UIUtil.px2dip(10);
            }
            imageView.setLayoutParams(params);
            ll_container.addView(imageView);
        }
    }

//滑动监听事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = (int) ((position+positionOffset)*range);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = move;
        iv_red_point.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        if(position == imageList.size()-1){
            btn_start.setVisibility(View.VISIBLE);
        }else {
            btn_start.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
