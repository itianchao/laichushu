package com.laichushu.book.ui.activity;


import android.text.TextUtils;
import android.widget.ImageView;

import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.bean.netbean.ProvinceCityBean;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.utils.SharePrefManager;
import com.nineoldandroids.animation.ObjectAnimator;
import com.laichushu.book.R;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 闪屏页
 * Created by wangtong on 2016/10/11.
 */
public class SplashActivity extends BaseActivity {
    //跳转页面
    private Class loadActivity;
    //启动页图片
    private ImageView splashIv;
    private List<PreviewCoverBean> proData = new ArrayList<>();
    private City_IdDao city_idDao;


    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_splash);
        splashIv = (ImageView) findViewById(R.id.iv_splash);
    }

    //动画
    @Override
    protected void initData() {
        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(splashIv, "alpha", 0, 0.25f, 0.5f, 0.75f, 1);
        mAnimator.setDuration(1000);
        mAnimator.start();
        //
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadActivity();
    }

    private void loadActivity() {
//        如果保存了帐号和密码，跳转主页面
        if (!TextUtils.isEmpty(SharePrefManager.getLoginInfo())) {
            loadActivity = InitActivity.class;
        } else {
            //如果第一次登录则跳转引导页
            if (SharePrefManager.getFristLogin()) {
                loadActivity = GuideActivity.class;
                //如果不是第一次登录则跳转登录页
            } else {
                loadActivity = LoginActivity.class;
            }
        }
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                UIUtil.openActivity(mActivity, loadActivity);
                finish();
            }
        }, 2000);
//        String articleId = "74";
//        Bundle bundle = new Bundle();
//        bundle.putString("articleId",articleId);
//        UIUtil.openActivity(this,CreatNewDraftActivity.class);
    }

    public List<ProvinceCityBean> initProvinceDate() {
        city_idDao.insert(new City_Id(null, "北京市", "北京市", "01", "01"));

        city_idDao.insert(new City_Id(null, "天津市", "天津市", "02", "01"));

        city_idDao.insert(new City_Id(null, "河北省", "石家庄市", "03", "01"));
        city_idDao.insert(new City_Id(null, "河北省", "唐山市", "03", "02"));
        city_idDao.insert(new City_Id(null, "河北省", "秦皇岛市", "03", "03"));
        city_idDao.insert(new City_Id(null, "河北省", "邯郸市", "03", "04"));
        city_idDao.insert(new City_Id(null, "河北省", "邢台市", "03", "05"));
        city_idDao.insert(new City_Id(null, "河北省", "张家口市", "03", "06"));
        city_idDao.insert(new City_Id(null, "河北省", "承德市", "03", "07"));
        city_idDao.insert(new City_Id(null, "河北省", "沧州市", "03", "08"));
        city_idDao.insert(new City_Id(null, "河北省", "廊坊市", "03", "09"));
        city_idDao.insert(new City_Id(null, "河北省", "沧州市", "03", "10"));
        city_idDao.insert(new City_Id(null, "河北省", "衡水市", "03", "11"));

        city_idDao.insert(new City_Id(null, "山西省", "太原市", "04", "01"));
        city_idDao.insert(new City_Id(null, "山西省", "大同市", "04", "02"));
        city_idDao.insert(new City_Id(null, "山西省", "阳泉市", "04", "03"));
        city_idDao.insert(new City_Id(null, "山西省", "长治市", "04", "04"));
        city_idDao.insert(new City_Id(null, "山西省", "晋城市", "04", "05"));
        city_idDao.insert(new City_Id(null, "山西省", "朔州市", "04", "06"));
        city_idDao.insert(new City_Id(null, "山西省", "忻州市", "04", "07"));
        city_idDao.insert(new City_Id(null, "山西省", "吕梁市", "04", "09"));
        city_idDao.insert(new City_Id(null, "山西省", "临汾市", "04", "10"));
        city_idDao.insert(new City_Id(null, "山西省", "运城市", "04", "11"));

        city_idDao.insert(new City_Id(null, "内蒙古", "呼和浩特市", "05", "01"));
        city_idDao.insert(new City_Id(null, "内蒙古", "包头市", "05", "02"));
        city_idDao.insert(new City_Id(null, "内蒙古", "乌海市", "05", "03"));
        city_idDao.insert(new City_Id(null, "内蒙古", "赤峰市", "05", "04"));
        city_idDao.insert(new City_Id(null, "内蒙古", "呼伦贝尔市", "05", "05"));
        city_idDao.insert(new City_Id(null, "内蒙古", "兴安盟", "05", "06"));
        city_idDao.insert(new City_Id(null, "内蒙古", "通辽市", "05", "07"));
        city_idDao.insert(new City_Id(null, "内蒙古", "锡林郭勒盟", "05", "08"));
        city_idDao.insert(new City_Id(null, "内蒙古", "乌兰察布盟", "05", "09"));
        city_idDao.insert(new City_Id(null, "内蒙古", "伊克昭盟", "05", "10"));
        city_idDao.insert(new City_Id(null, "内蒙古", "巴彦淖尔盟", "05", "11"));
        city_idDao.insert(new City_Id(null, "内蒙古", "阿拉善盟", "05", "12"));

        city_idDao.insert(new City_Id(null, "辽宁省", "沈阳市", "06", "01"));
        city_idDao.insert(new City_Id(null, "辽宁省", "大连市", "06", "02"));
        city_idDao.insert(new City_Id(null, "辽宁省", "鞍山市", "06", "03"));
        city_idDao.insert(new City_Id(null, "辽宁省", "抚顺市", "06", "04"));
        city_idDao.insert(new City_Id(null, "辽宁省", "本溪市", "06", "05"));
        city_idDao.insert(new City_Id(null, "辽宁省", "丹东市", "06", "06"));
        city_idDao.insert(new City_Id(null, "辽宁省", "锦州市", "06", "07"));
        city_idDao.insert(new City_Id(null, "辽宁省", "营口市", "06", "08"));
        city_idDao.insert(new City_Id(null, "辽宁省", "阜新市", "06", "09"));
        city_idDao.insert(new City_Id(null, "辽宁省", "辽阳市", "06", "10"));
        city_idDao.insert(new City_Id(null, "辽宁省", "盘锦市", "06", "11"));
        city_idDao.insert(new City_Id(null, "辽宁省", "铁岭市", "06", "12"));
        city_idDao.insert(new City_Id(null, "辽宁省", "朝阳市", "06", "13"));
        city_idDao.insert(new City_Id(null, "辽宁省", "葫芦岛市", "06", "14"));

        city_idDao.insert(new City_Id(null, "辽宁省", "长春市", "06", "12"));
        city_idDao.insert(new City_Id(null, "辽宁省", "葫芦岛市", "06", "12"));

        return null;
    }
}
