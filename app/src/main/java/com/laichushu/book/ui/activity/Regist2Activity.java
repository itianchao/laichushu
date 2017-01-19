package com.laichushu.book.ui.activity;

import android.content.res.Configuration;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.entry.login.LoginModel;
import com.laichushu.book.mvp.entry.regist2.RegistModel2;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.ui.widget.WheelView;
import com.laichushu.book.utils.DialogUtil;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;
import com.laichushu.book.mvp.entry.regist2.RegistPresenter2;
import com.laichushu.book.mvp.entry.regist2.RegistView2;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册页面
 * Created by wangtong on 2016/10/12.
 */
public class Regist2Activity extends MvpActivity<RegistPresenter2> implements RegistView2, View.OnClickListener {

    private TextView titleTv;
    private ImageView finishTv;
    private EditText nameEt;
    private TextView sexTv;
    private TextView phoneTv;
    private TextView addressTv;
    private EditText pwdEt;
    private EditText repwdEt;
    private Button finishBtn;
    private String curProCode = null;
    private Cache_JsonDao cache_jsonDao;
    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_regist2);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishTv = (ImageView) findViewById(R.id.iv_title_finish);
        nameEt = (EditText) findViewById(R.id.et_name);
        sexTv = (TextView) findViewById(R.id.tv_sex);
        addressTv = (TextView) findViewById(R.id.tv_address);
        phoneTv = (TextView) findViewById(R.id.tv_phone);
        pwdEt = (EditText) findViewById(R.id.et_pwd);
        repwdEt = (EditText) findViewById(R.id.et_re_pwd);
        finishBtn = (Button) findViewById(R.id.bt_finish);
    }

    @Override
    protected void initData() {
        titleTv.setText("填写信息");
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
        city_idList = city_idDao.queryBuilder().build().list();

        //获取屏幕高度
        screenHeight = UIUtil.getScreenHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

        sexTv.setOnClickListener(this);
        addressTv.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        String phone = mActivity.getIntent().getStringExtra("phone");
        phoneTv.setText(phone);
    }

    @Override
    protected RegistPresenter2 createPresenter() {
        return new RegistPresenter2(this);
    }

    @Override
    public void getDataSuccess(RegistModel2 model) {
        hideLoading();
        if (model.isSuccess()) {
            //登陆成功跳转页面
            ToastUtil.showToast("注册成功");
            UIUtil.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showProgressDialog("登陆中...");
                    String phone = phoneTv.getText().toString().trim();
                    String pwd = pwdEt.getText().toString().trim();
                    mvpPresenter.loginData(phone, pwd);
                }
            }, 1710);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("请检查网络");
        Logger.e("网络失败原因：", msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 登录接口成功
     *
     * @param model
     */
    @Override
    public void getLoginSuccess(LoginModel model) {
        hideLoading();
        if (model.isSuccess()) {
            String username = phoneTv.getText().toString().trim();
            String password = pwdEt.getText().toString().trim();
            String userId = model.getUserId();
            String token = model.getToken();
            String type = "1";
            mvpPresenter.lastLogin(username, password, userId, token, type);
        } else {
            String errMsg = model.getErrMsg();
            if (errMsg.contains(UIUtil.getString(R.string.errMsg2))) {
                DialogUtil.showDialog();
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg3))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg3));
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg4))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg4));
            } else if (errMsg.contains(UIUtil.getString(R.string.errMsg5))) {
                ToastUtil.showToast(UIUtil.getString(R.string.errMsg5));
            } else {
                ToastUtil.showToast(errMsg);
            }
            UIUtil.postStartActivity(mActivity, LoginActivity.class);
        }
    }

    /**
     * 登录接口失败
     *
     * @param msg
     */
    @Override
    public void getLoginFail(String msg) {
        hideLoading();
        ToastUtil.showToast("登录失败");
        UIUtil.postStartActivity(mActivity, LoginActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.bt_finish:
                String name = nameEt.getText().toString().trim();
                String sex = sexTv.getText().toString().trim();
                String phonenum = phoneTv.getText().toString().trim();
                String pwd = pwdEt.getText().toString().trim();
                String repwd = repwdEt.getText().toString().trim();
                if (mvpPresenter.check(name, sex, curProCode, pwd, repwd)) {
                    //请求网络
                    showLoading();
                    mvpPresenter.regist(phonenum, name, sex, curProCode, pwd);
                }
                break;
            case R.id.tv_sex:
                mvpPresenter.getSex(sexTv);
                break;
            case R.id.tv_address:
                //选择地址
                initAreaSelector();
                break;
        }
    }
    //-----------省市选择器---------------

    /**
     * 获取所有的省份
     *
     * @return
     */
    private ArrayList<String> getProvonce() {
        ArrayList<String> proDate = new ArrayList<>();
        proDate.clear();
        for (int i = 0; i < city_idList.size(); i++) {
            if (!proDate.contains(city_idList.get(i).getProvince())) {
                proDate.add(city_idList.get(i).getProvince());
            }
        }
        return proDate;
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
     * 通过省份code获取省份名字
     *
     * @param proCode
     * @return
     */
    private String getProvinceByProcode(String proCode) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (proCode.equals(city_idList.get(i).getProCode())) {
                result = city_idList.get(i).getProvince();
            }
        }
        return result;
    }

    /**
     * @param proCode
     * @return 根据proCode查询城市
     */
    private ArrayList<String> getCity(String proCode) {
        ArrayList<String> cityDate = new ArrayList<>();
        cityDate.clear();
        for (int i = 0; i < city_idList.size(); i++) {
            if (proCode.equals(city_idList.get(i).getProCode())) {
                cityDate.add(city_idList.get(i).getCity());
            }
        }
        return cityDate;
    }

    public void initAreaSelector() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, R.style.DialogStyle);
        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View customerView = inflater.inflate(R.layout.item_pop_myview, null);
        WheelView wvProvince = ((WheelView) customerView.findViewById(R.id.wv_province));
        final WheelView wvCity = ((WheelView) customerView.findViewById(R.id.wv_city));
        TextView tvCancel = ((TextView) customerView.findViewById(R.id.tv_cancel));
        TextView tvSubmit = ((TextView) customerView.findViewById(R.id.tv_submit));
        wvProvince.setTextSize(R.dimen.editTop);
        wvProvince.setSelectTextColor("#969696");
        wvProvince.setLineColor("#969696");
        wvCity.setSelectTextColor("#969696");
        wvCity.setLineColor("#969696");
        wvCity.setPadd(15);
        wvCity.setTextSize(R.dimen.editTop);
        wvProvince.setPadd(15);
        wvProvince.setOffset(2);
        wvProvince.setSeletion(0);
        wvCity.setOffset(2);
        wvCity.setSeletion(0);
        wvProvince.setItems(getProvonce());
        wvCity.setItems(getCity("01"));
        final String[] curProvince = {"北京市"};
        wvProvince.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int position, String item) {
                curProvince[0] = item;
                wvCity.setItems(getCity(getProCodeByProvince(item)));
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交数据
                addressTv.setText(curProvince[0]);
                curProCode = getProCodeByProvince(curProvince[0]);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(customerView);
        alertDialog.show();
        WindowManager m = getWindowManager();
        Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.getWindow().setLayout(display.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setWindowAnimations(R.style.timepopwindow_anim_style);
        alertDialog.show();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks whether a hardware keyboard is available
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            ToastUtil.showToast("软键盘弹起");
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            ToastUtil.showToast("软键盘关闭");
            pwdEt.clearFocus();
            repwdEt.clearFocus();
        }

    }
}
