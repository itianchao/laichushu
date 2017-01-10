package com.laichushu.book.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.home.homelist.HomeModel;
import com.laichushu.book.mvp.entry.init.InitPresenter;
import com.laichushu.book.mvp.entry.init.InitView;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 初始化 数据
 * Created by wangtong on 2016/11/2.
 */
public class InitActivity extends MvpActivity<InitPresenter> implements InitView, View.OnClickListener {

    private ProgressBar loadingPb;
    private LinearLayout errorLay;
    private Button errorBtn;
    private boolean frist = false;
    private boolean second = false;
    private boolean thried = false;
    private boolean four = false;

    private HomeModel homeModel;
    private HomeHotModel homeHotModel;
    private HomeHotModel homeAllModel;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    /**
     * handler
     */
    private MyHandler mhandler = new MyHandler(this);

    static class MyHandler extends Handler {
        private WeakReference<InitActivity> weakReference;

        MyHandler(InitActivity mActivity) {
            weakReference = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InitActivity mActivity = weakReference.get();
            if (mActivity != null) {
                if (mActivity.frist && mActivity.second && mActivity.thried && mActivity.four) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("homeModel", mActivity.homeModel);
                    bundle.putParcelable("homeHotModel", mActivity.homeHotModel);
                    bundle.putParcelable("homeAllModel", mActivity.homeAllModel);
                    UIUtil.openActivity(mActivity, MainActivity.class, bundle);
                    mActivity.finish();
                }
            }
        }
    }


    private InitActivity.UpdateReceiver mUpdateReceiver;
    //获取对应的城市code
    private Cache_JsonDao cache_jsonDao;
    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    private String cityId = null, city, province;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_init);
        loadingPb = (ProgressBar) findViewById(R.id.pb_loading);
        errorLay = (LinearLayout) findViewById(R.id.lay_error);
        errorBtn = (Button) findViewById(R.id.error_btn_retry);
        errorBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //百度定位
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        initLocation();
        //开启定位
        mLocationClient.start();
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        registerPlayerReceiver();
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        cache_jsonDao = daoSession.getCache_JsonDao();
        List<Cache_Json> cache_jsons = cache_jsonDao.queryBuilder()
                .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).list();
        if (cache_jsons.isEmpty()) {
            mvpPresenter.loadMineData();//个人中心
        } else {
            four = true;
        }
        mvpPresenter.loadHomeCarouseData();//首页轮播图
        mvpPresenter.loadHomeHotData();//热门推荐
        mvpPresenter.loadHomeAllData("1");//全部图书
    }

    /**
     * 首页轮播图 成功返回数据
     *
     * @param model
     */
    @Override
    public void getDataSuccess(HomeModel model) {
        if (model.isSuccess()) {
            this.homeModel = model;
            frist = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 热门推荐 成功返回数据
     *
     * @param model
     */
    @Override
    public void getHotDataSuccess(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeHotModel = model;
            second = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 全部图书 成功返回数据
     *
     * @param model
     */
    @Override
    public void getAllData(HomeHotModel model) {
        if (model.isSuccess()) {
            this.homeAllModel = model;
            thried = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    /**
     * 个人中心 成功返回数据
     *
     * @param model
     */
    @Override
    public void loadMineDataSuccess(PersonalCentreResult model) {
        if (model.getSuccess()) {
            //本地存储--->已存在更新
            cache_jsonDao.insert(new Cache_Json(null, "PersonalDetails", new Gson().toJson(model)));
            four = true;
            Message msg = new Message();
            mhandler.sendMessage(msg);
            //sharePerfance存储
            SharePrefManager.setNickName(model.getNickName());
        } else {
            ToastUtil.showToast(model.getErrMsg());
            getDataFail(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        errorLay.setVisibility(View.VISIBLE);
        loadingPb.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        errorLay.setVisibility(View.GONE);
        loadingPb.setVisibility(View.VISIBLE);
    }

    @Override
    protected InitPresenter createPresenter() {
        return new InitPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.error_btn_retry:
                if (!frist) {
                    mvpPresenter.loadHomeCarouseData();
                }
                if (!second) {
                    mvpPresenter.loadHomeHotData();
                }
                if (!thried) {
                    mvpPresenter.loadHomeAllData("1");
                }
                if (!four) {
                    mvpPresenter.loadMineData();
                }
                break;
        }
    }

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConstantValue.ACTION_UPDATE_DATA_MINEINFO.equals(action)) {
                //更新信息
                mvpPresenter.loadMineData();
            }
        }
    }

    private void registerPlayerReceiver() {
        if (mUpdateReceiver == null) {
            mUpdateReceiver = new InitActivity.UpdateReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addCategory(mActivity.getPackageName());
            filter.addAction(ConstantValue.ACTION_UPDATE_DATA_MINEINFO);
            mActivity.registerReceiver(mUpdateReceiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUpdateReceiver);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            SharePrefManager.setAddressCity(location.getCity());
            SharePrefManager.setAddressProvince(location.getProvince() + location);
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
            mLocationClient.stop();
            getCityId();
        }

    }

    private void getCityId() {
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
        city_idList = city_idDao.queryBuilder().build().list();
        city = getProCodeByProvince(SharePrefManager.getAddressProvince());
        province = getCityCodeByCity(SharePrefManager.getAddressCity());
        if (TextUtils.isEmpty(city)) {
            city = "01";
        }
        if (TextUtils.isEmpty(province)) {
            province = "01";
        }
        cityId = city + province;
        SharePrefManager.setCityId(cityId);
    }

    /**
     * 通过省份名字获取省份代号
     *
     * @param province
     * @return
     */
    private String getProCodeByProvince(String province) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (province.equals(city_idList.get(i).getProvince())) {
                result = city_idList.get(i).getProCode();
            }
        }
        return result;
    }

    /**
     * 通过城市名字获取城市代号
     *
     * @param city
     * @return
     */
    private String getCityCodeByCity(String city) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (city.equals(city_idList.get(i).getCity())) {
                result = city_idList.get(i).getCityCode();
            }
        }
        return result;
    }
}
