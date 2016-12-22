package com.laichushu.book.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PersonInfoResultReward;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.event.RefrushMineEvent;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.fragment.MineFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.WheelView;
import com.laichushu.book.utils.DateUtil;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.pickerview.OptionsPopupWindow;
import com.pickerview.TimePopupWindow;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 个人中心--编辑个人信息
 * 2016年11月24日11:25:54
 */
public class EditMyselfeInforActivity extends MvpActivity2 implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView ivBack, ivHead;
    private TextView tvTitle, tvRight, tvIdCard, tvSex, edBirthday, tvCity;
    private EditText edNickName, edSign;
    private PersonalCentreResult resultData = new PersonalCentreResult();
    private RelativeLayout rlIdCard, rlHead, rlArea, rlNickName;
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private File photoFile;
    private String curNickName = null, curProCode = null, photoUrl = null;
    //时间选择器
    private TimePopupWindow timePopupWindow;
    private OptionsPopupWindow sexPopupWindow;
    private Cache_JsonDao cache_jsonDao;
    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    private MineFragment mf;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_edit_myselfe_infor);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        ivHead = ((ImageView) inflate.findViewById(R.id.iv_editHead));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        tvIdCard = ((TextView) inflate.findViewById(R.id.tv_idCardContent));
        edNickName = ((EditText) inflate.findViewById(R.id.ed_nickNameContent));
        tvSex = ((TextView) inflate.findViewById(R.id.ed_sexContent));
        tvCity = ((TextView) inflate.findViewById(R.id.tv_areaContent));
        edSign = ((EditText) inflate.findViewById(R.id.ed_signatureContent));
        edBirthday = ((TextView) inflate.findViewById(R.id.ed_birthdayContent));
        rlIdCard = ((RelativeLayout) inflate.findViewById(R.id.rl_idCard));
        rlHead = ((RelativeLayout) inflate.findViewById(R.id.rl_editHeadImg));
        rlArea = ((RelativeLayout) inflate.findViewById(R.id.rl_area));
        rlNickName = ((RelativeLayout) inflate.findViewById(R.id.rl_nickName));
        mf = new MineFragment();
        //initListener;
        ivBack.setOnClickListener(this);
        rlHead.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        edBirthday.setOnClickListener(this);
        rlIdCard.setOnClickListener(this);
        rlArea.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
        city_idList = city_idDao.queryBuilder().build().list();
        initPersonInfor();
        tvTitle.setText("编辑个人信息");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        edBirthday.setInputType(InputType.TYPE_NULL);
        edNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edNickName.setCursorVisible(true);
                    edNickName.setSelection(curNickName.length());
                    edNickName.setBackgroundResource(R.drawable.shape_rectangle_fill_hint);
                    edNickName.setGravity(Gravity.CENTER);

                } else {
                    disFocus();
                }
            }
        });

        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                disFocus();
                if (!judgeAttrbute()) {
                    return;
                }
                ArrayMap<String, RequestBody> params = new ArrayMap<>();
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), SharePrefManager.getUserId().toString());
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), edNickName.getText().toString());
                RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), (tvSex.getText().toString().trim()).equals("男") ? "1" : "2");
                RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), curProCode);
                RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), edSign.getText().toString());
                RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"), edBirthday.getText().toString());
                RequestBody requestBody7 = null;
                if (photoFile != null) {
                    requestBody7 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(photoFile));
                    photoUrl = null;
                }
                params.put("userId", requestBody1);
                params.put("nickName", requestBody2);
                params.put("sex", requestBody3);
                params.put("city", requestBody4);
                params.put("sign", requestBody5);
                params.put("birthday", requestBody6);
                addSubscription(apiStores.getUpdateDetails(params, requestBody7), new ApiCallback<PersonInfoResultReward>() {
                    @Override
                    public void onSuccess(PersonInfoResultReward result) {
                        if (result.isSuccess()) {
                            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                            ToastUtil.showToast("修改成功！");
                            mActivity.finish();
                            updateDate(result);
                        } else {
                            refreshPage(LoadingPager.PageState.STATE_ERROR);
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        refreshPage(LoadingPager.PageState.STATE_ERROR);
                    }

                    @Override
                    public void onFinish() {

                    }
                });

                break;
            case R.id.ed_sexContent:
                disFocus();
                setSexPopWindow();
                break;
            case R.id.ed_birthdayContent:
                disFocus();
                setTimePopupwindow();
                break;
            case R.id.rl_area:
                disFocus();
                initAreaSelector();
                break;
            case R.id.rl_idCard:
                disFocus();
                Bundle bundle = new Bundle();
                bundle.putSerializable("idcard", resultData);
                UIUtil.openActivity(this, IndentityAuthenActivity.class, bundle);
                break;
            case R.id.rl_editHeadImg:
                disFocus();
                openAlertDialog();
                break;
        }

    }


    /**
     * 初始化数据
     */
    private void initPersonInfor() {
        resultData = (PersonalCentreResult) getIntent().getSerializableExtra("result");
        if (resultData != null) {
            GlideUitl.loadRandImg(mActivity, resultData.getPhoto(), ivHead);
            photoUrl = resultData.getPhoto();
            edNickName.setText(resultData.getNickName());
            curNickName = resultData.getNickName();
            if (TextUtils.isEmpty(resultData.getSex())) {
                tvSex.setText("男");
            } else {
                if (resultData.getSex().equals("1")) {
                    tvSex.setText("男");
                } else if (resultData.getSex().equals("2")) {
                    tvSex.setText("女");
                }
            }
            if (!TextUtils.isEmpty(resultData.getBirthday())) {
                edBirthday.setText(resultData.getBirthday().toString());
            }
            if (!TextUtils.isEmpty(resultData.getCity())) {
                curProCode = resultData.getCity();
                tvCity.setText(getProvinceByProcode(resultData.getCity()));
            }
            if (!TextUtils.isEmpty(resultData.getAtteStatus())) {
                switch (Integer.parseInt(resultData.getAtteStatus())) {
                    case 1:
                        tvIdCard.setText("未认证");
                        break;
                    case 2:
                        tvIdCard.setText("认证中");
                        rlIdCard.setClickable(false);
                        break;
                    case 3:
                        tvIdCard.setText("认证失败");
                        break;
                    case 4:
                        tvIdCard.setText("认证通过");
                        rlIdCard.setClickable(false);
                        break;
                }
            }

            edSign.setText(resultData.getSign());
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast("获取信息失败！");
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }

    }

    /**
     * 判断是否录入为空
     */
    private boolean judgeAttrbute() {

        if (TextUtils.isEmpty(edNickName.getText())) {
            ToastUtil.showToast("请输入昵称!");
            return false;
        }
        if (TextUtils.isEmpty(tvSex.getText())) {
            ToastUtil.showToast("请选择输入性别!");
            return false;
        }
        if (TextUtils.isEmpty(curProCode)) {
            ToastUtil.showToast("请输入您所在城市!");
            return false;
        }
        if (TextUtils.isEmpty(edBirthday.getText())) {
            ToastUtil.showToast("请输入出生日期!");
            return false;
        }
        if (TextUtils.isEmpty(edNickName.getText())) {
            ToastUtil.showToast("请输入个性签名!");
            return false;
        }

        return true;
    }

    /**
     * 设置性别
     */
    public void setSexPopWindow() {
        final ArrayList<String> sexData = new ArrayList<>();
        sexData.clear();
        sexData.add("男");
        sexData.add("女");
        sexPopupWindow = new OptionsPopupWindow(this);
        sexPopupWindow.setPicker(sexData);
        sexPopupWindow.setCyclic(false);
        sexPopupWindow.setTitleBackgroundColor(mActivity.getResources().getColor(R.color.auditing));
        sexPopupWindow.setTitle("性别");
        sexPopupWindow.setSelectOptions(Gravity.CENTER_HORIZONTAL);
        sexPopupWindow.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvSex.setText(sexData.get(options1));
            }
        });
        sexPopupWindow.showAtLocation(tvSex, Gravity.BOTTOM, 0, 0);
    }

    //选择日期
    private void setTimePopupwindow() {
        timePopupWindow = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
        timePopupWindow.setRange(1900, Calendar.getInstance().get(Calendar.YEAR));
        timePopupWindow.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                edBirthday.setText(DateUtil.getFormatDateTime(date));
            }
        });
        timePopupWindow.showAtLocation(edBirthday, Gravity.BOTTOM, 0, 0, null);
    }


    /**
     * 通知fragment更新数据
     */
    private void updateDate(PersonInfoResultReward result) {
        //更新本地数据
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        cache_jsonDao = daoSession.getCache_JsonDao();
        QueryBuilder<Cache_Json> userQueryBuilder = cache_jsonDao.queryBuilder();
        QueryBuilder<Cache_Json> builder = userQueryBuilder.where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails"));
        Query<Cache_Json> build = builder.build();
        List<Cache_Json> cache_jsons = build.list();
        Cache_Json cache_json = cache_jsons.get(0);
        PersonalCentreResult json = new Gson().fromJson(cache_json.getJson(), PersonalCentreResult.class);
        json.setNickName(result.getData().getNickName());
        json.setSex(result.getData().getSex().equals("1") ? "男" : "女");
        json.setBirthday(result.getData().getBirthday() + "");
        json.setCity(result.getData().getCity());
        json.setSign(result.getData().getSign());
        if (photoUrl == null) {
            json.setPhoto(result.getData().getPhoto());
        } else {
            json.setPhoto(photoUrl);
        }

        cache_json.setJson(new Gson().toJson(json));
        cache_jsonDao.update(cache_json);
        //sharePerfance存储
        SharePrefManager.setNickName(result.getData().getNickName());

        EventBus.getDefault().postSticky(new RefrushMineEvent(true));
    }


    /**
     * 打开相册
     */
    public void openAlertDialog() {

        Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                , 1                                                         // 指定选择数量。
                , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。

    }

    /**
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> imagesPath = Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                GlideUitl.loadRandImg(mActivity, path, ivHead);
                //压缩图片
                photoFile = new File(path);
            }
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
                tvCity.setText(curProvince[0]);
                curProCode = getProCodeByProvince(curProvince[0]);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(customerView);
        alertDialog.show();
        WindowManager m = getWindowManager();
        Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.getWindow().setLayout(display.getWidth(), display.getHeight() / 2);
        alertDialog.getWindow().setWindowAnimations(R.style.timepopwindow_anim_style);
        alertDialog.show();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    public void disFocus() {
        if (TextUtils.isEmpty(edNickName.getText().toString())) {
            edNickName.setText(curNickName);
        }
        rlNickName.requestFocus();
        edNickName.clearFocus();
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        edNickName.setCursorVisible(false);
        imm.hideSoftInputFromWindow(edNickName.getWindowToken(), 0);
        edNickName.setBackgroundResource(R.drawable.shape_kitkat_undobarfocused);
        edNickName.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
    }

}
