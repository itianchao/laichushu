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
        initProvinceDate();
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

    public void initProvinceDate() {
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

        city_idDao.insert(new City_Id(null, "吉林省", "长春市", "07", "01"));
        city_idDao.insert(new City_Id(null, "吉林省", "吉林市", "07", "02"));
        city_idDao.insert(new City_Id(null, "吉林省", "四平市", "07", "03"));
        city_idDao.insert(new City_Id(null, "吉林省", "辽源市", "07", "04"));
        city_idDao.insert(new City_Id(null, "吉林省", "通化市", "07", "05"));
        city_idDao.insert(new City_Id(null, "吉林省", "白山市", "07", "06"));
        city_idDao.insert(new City_Id(null, "吉林省", "松原市", "07", "07"));
        city_idDao.insert(new City_Id(null, "吉林省", "白城市", "07", "08"));
        city_idDao.insert(new City_Id(null, "吉林省", "延边朝鲜族自治州", "07", "09"));

        city_idDao.insert(new City_Id(null, "黑龙江省", "哈尔滨市", "08", "01"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "齐齐哈尔市", "08", "02"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "鸡西市", "08", "03"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "鹤岗市", "08", "04"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "双鸭山市", "08", "05"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "大庆市", "08", "06"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "伊春市", "08", "07"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "七台河市", "08", "08"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "牡丹江市", "08", "09"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "黑河市", "08", "10"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "绥化市", "08", "11"));
        city_idDao.insert(new City_Id(null, "黑龙江省", "大兴安岭地区", "08", "12"));

        city_idDao.insert(new City_Id(null, "上海市", "上海市", "09", "01"));


        city_idDao.insert(new City_Id(null, "江苏省", "南京市", "10", "01"));
        city_idDao.insert(new City_Id(null, "江苏省", "无锡市", "10", "02"));
        city_idDao.insert(new City_Id(null, "江苏省", "徐州市", "10", "03"));
        city_idDao.insert(new City_Id(null, "江苏省", "常州市", "10", "04"));
        city_idDao.insert(new City_Id(null, "江苏省", "苏州市", "10", "05"));
        city_idDao.insert(new City_Id(null, "江苏省", "南通市", "10", "06"));
        city_idDao.insert(new City_Id(null, "江苏省", "连云港市", "10", "07"));
        city_idDao.insert(new City_Id(null, "江苏省", "淮安市", "10", "08"));
        city_idDao.insert(new City_Id(null, "江苏省", "盐城市", "10", "09"));
        city_idDao.insert(new City_Id(null, "江苏省", "扬州市", "10", "10"));
        city_idDao.insert(new City_Id(null, "江苏省", "镇江市", "10", "11"));
        city_idDao.insert(new City_Id(null, "江苏省", "泰州市", "10", "12"));
        city_idDao.insert(new City_Id(null, "江苏省", "宿迁市", "10", "13"));

        city_idDao.insert(new City_Id(null, "浙江省", "杭州市", "11", "01"));
        city_idDao.insert(new City_Id(null, "浙江省", "宁波市", "11", "02"));
        city_idDao.insert(new City_Id(null, "浙江省", "温州市", "11", "03"));
        city_idDao.insert(new City_Id(null, "浙江省", "嘉兴市", "11", "04"));
        city_idDao.insert(new City_Id(null, "浙江省", "湖州市", "11", "05"));
        city_idDao.insert(new City_Id(null, "浙江省", "绍兴市", "11", "06"));
        city_idDao.insert(new City_Id(null, "浙江省", "金华市", "11", "07"));
        city_idDao.insert(new City_Id(null, "浙江省", "衢州市", "11", "08"));
        city_idDao.insert(new City_Id(null, "浙江省", "舟山市", "11", "09"));
        city_idDao.insert(new City_Id(null, "浙江省", "台州市", "11", "10"));
        city_idDao.insert(new City_Id(null, "浙江省", "丽水市", "11", "11"));

        city_idDao.insert(new City_Id(null, "安徽省", "合肥市", " 12", "01"));
        city_idDao.insert(new City_Id(null, "安徽省", "芜湖市", "12", "02"));
        city_idDao.insert(new City_Id(null, "安徽省", "蚌埠市", "12", "03"));
        city_idDao.insert(new City_Id(null, "安徽省", "淮南市", "12", "04"));
        city_idDao.insert(new City_Id(null, "安徽省", "马鞍山市", "12", "05"));
        city_idDao.insert(new City_Id(null, "安徽省", "淮北市", "12", "06"));
        city_idDao.insert(new City_Id(null, "安徽省", "铜陵市", "12", "07"));
        city_idDao.insert(new City_Id(null, "安徽省", "安庆市", "12", "08"));
        city_idDao.insert(new City_Id(null, "安徽省", "黄山市", "12", "09"));
        city_idDao.insert(new City_Id(null, "安徽省", "滁州市", "12", "10"));
        city_idDao.insert(new City_Id(null, "安徽省", "阜阳市", "12", "11"));
        city_idDao.insert(new City_Id(null, "安徽省", "宿州市", "12", "12"));
        city_idDao.insert(new City_Id(null, "安徽省", "六安市", "12", "13"));
        city_idDao.insert(new City_Id(null, "安徽省", "宣城市", "12", "14"));
        city_idDao.insert(new City_Id(null, "安徽省", "巢湖市", "12", "15"));
        city_idDao.insert(new City_Id(null, "安徽省", "池州市", "12", "16"));


        city_idDao.insert(new City_Id(null, "福建省", "龙岩", "13", "07"));
        city_idDao.insert(new City_Id(null, "福建省", "福州市", "13", "01"));
        city_idDao.insert(new City_Id(null, "福建省", "厦门市", "13", "02"));
        city_idDao.insert(new City_Id(null, "福建省", "宁德市", "13", "03"));
        city_idDao.insert(new City_Id(null, "福建省", "莆田市", "13", "04"));
        city_idDao.insert(new City_Id(null, "福建省", "泉州市", "13", "05"));
        city_idDao.insert(new City_Id(null, "福建省", "漳州市", "13", "06"));
        city_idDao.insert(new City_Id(null, "福建省", "三明市", "13", "08"));
        city_idDao.insert(new City_Id(null, "福建省", "南平市", "13", "09"));
        city_idDao.insert(new City_Id(null, "江西省", "南昌市", "14", "01"));
        city_idDao.insert(new City_Id(null, "江西省", "萍乡市", "14", "03"));
        city_idDao.insert(new City_Id(null, "江西省", "九江市", "14", "04"));
        city_idDao.insert(new City_Id(null, "江西省", "新余市", "14", "05"));
        city_idDao.insert(new City_Id(null, "江西省", "鹰潭市", "14", "06"));
        city_idDao.insert(new City_Id(null, "江西省", "赣州市", "14", "07"));
        city_idDao.insert(new City_Id(null, "江西省", "宜春市", "14", "08"));
        city_idDao.insert(new City_Id(null, "江西省", "上饶市", "14", "09"));
        city_idDao.insert(new City_Id(null, "江西省", "吉安市", "14", "10"));
        city_idDao.insert(new City_Id(null, "江西省", "抚州市", "14", "11"));
        city_idDao.insert(new City_Id(null, "山东省", "济南市", "15", "01"));
        city_idDao.insert(new City_Id(null, "山东省", "青岛市", "15", "02"));
        city_idDao.insert(new City_Id(null, "山东省", "淄博市", "15", "03"));
        city_idDao.insert(new City_Id(null, "山东省", "枣庄市", "15", "04"));
        city_idDao.insert(new City_Id(null, "山东省", "东营市", "15", "05"));
        city_idDao.insert(new City_Id(null, "山东省", "烟台市", "15", "06"));
        city_idDao.insert(new City_Id(null, "山东省", "潍坊市", "15", "07"));
        city_idDao.insert(new City_Id(null, "山东省", "济宁市", "15", "08"));
        city_idDao.insert(new City_Id(null, "山东省", "泰安市", "15", "09"));
        city_idDao.insert(new City_Id(null, "山东省", "威海市", "15", "10"));
        city_idDao.insert(new City_Id(null, "山东省", "日照市", "15", "11"));
        city_idDao.insert(new City_Id(null, "山东省", "莱芜市", "15", "12"));
        city_idDao.insert(new City_Id(null, "山东省", "临沂市", "15", "13"));
        city_idDao.insert(new City_Id(null, "山东省", "德州市", "15", "14"));
        city_idDao.insert(new City_Id(null, "山东省", "聊城市", "15", "15"));
        city_idDao.insert(new City_Id(null, "河南省", "郑州市", "16", "01"));
        city_idDao.insert(new City_Id(null, "河南省", "开封市", "16", "02"));
        city_idDao.insert(new City_Id(null, "河南省", "洛阳市", "16", "03"));
        city_idDao.insert(new City_Id(null, "河南省", "安阳市", "16", "05"));
        city_idDao.insert(new City_Id(null, "河南省", "鹤壁市", "16", "06"));
        city_idDao.insert(new City_Id(null, "河南省", "新乡市", "16", "07"));
        city_idDao.insert(new City_Id(null, "河南省", "焦作市", "16", "08"));
        city_idDao.insert(new City_Id(null, "河南省", "濮阳市", "16", "09"));
        city_idDao.insert(new City_Id(null, "河南省", "许昌市", "16", "10"));
        city_idDao.insert(new City_Id(null, "河南省", "漯河市", "16", "11"));
        city_idDao.insert(new City_Id(null, "河南省", "南阳市", "16", "13"));
        city_idDao.insert(new City_Id(null, "河南省", "商丘市", "16", "14"));
        city_idDao.insert(new City_Id(null, "河南省", "信阳市", "16", "15"));
        city_idDao.insert(new City_Id(null, "河南省", "周口市", "16", "16"));
        city_idDao.insert(new City_Id(null, "湖北省", "武汉市", "17", "01"));
        city_idDao.insert(new City_Id(null, "湖北省", "黄石市", "17", "02"));
        city_idDao.insert(new City_Id(null, "湖北省", "十堰市", "17", "03"));
        city_idDao.insert(new City_Id(null, "湖北省", "宜昌市", "17", "04"));
        city_idDao.insert(new City_Id(null, "湖北省", "襄樊市", "17", "05"));
        city_idDao.insert(new City_Id(null, "湖北省", "鄂州市", "17", "06"));
        city_idDao.insert(new City_Id(null, "湖北省", "荆门市", "17", "07"));
        city_idDao.insert(new City_Id(null, "湖北省", "孝感市", "17", "08"));
        city_idDao.insert(new City_Id(null, "湖北省", "荆州市", "17", "09"));
        city_idDao.insert(new City_Id(null, "湖北省", "黄冈市", "17", "10"));
        city_idDao.insert(new City_Id(null, "湖北省", "咸宁市", "17", "11"));
        city_idDao.insert(new City_Id(null, "湖北省", "随州市", "17", "12"));
        city_idDao.insert(new City_Id(null, "湖南省", "长沙市", "18", "01"));
        city_idDao.insert(new City_Id(null, "湖南省", "株洲市", "18", "02"));
        city_idDao.insert(new City_Id(null, "湖南省", "湘潭市", "18", "03"));
        city_idDao.insert(new City_Id(null, "湖南省", "衡阳市", "18", "04"));
        city_idDao.insert(new City_Id(null, "湖南省", "邵阳市", "18", "05"));
        city_idDao.insert(new City_Id(null, "湖南省", "岳阳市", "18", "06"));
        city_idDao.insert(new City_Id(null, "湖南省", "常德市", "18", "07"));
        city_idDao.insert(new City_Id(null, "湖南省", "益阳市", "18", "09"));
        city_idDao.insert(new City_Id(null, "湖南省", "郴州市", "18", "10"));
        city_idDao.insert(new City_Id(null, "湖南省", "永州市", "18", "11"));
        city_idDao.insert(new City_Id(null, "湖南省", "怀化市", "18", "12"));
        city_idDao.insert(new City_Id(null, "广东省", "广州市", "19", "01"));
        city_idDao.insert(new City_Id(null, "广东省", "韶关市", "19", "02"));
        city_idDao.insert(new City_Id(null, "广东省", "深圳市", "19", "03"));
        city_idDao.insert(new City_Id(null, "广东省", "珠海市", "19", "04"));
        city_idDao.insert(new City_Id(null, "广东省", "汕头市", "19", "05"));
        city_idDao.insert(new City_Id(null, "广东省", "佛山市", "19", "06"));
        city_idDao.insert(new City_Id(null, "广东省", "江门市", "19", "07"));
        city_idDao.insert(new City_Id(null, "广东省", "湛江市", "19", "08"));
        city_idDao.insert(new City_Id(null, "广东省", "茂名市", "19", "09"));
        city_idDao.insert(new City_Id(null, "广东省", "肇庆市", "19", "10"));
        city_idDao.insert(new City_Id(null, "广东省", "惠州市", "19", "11"));
        city_idDao.insert(new City_Id(null, "广东省", "梅州市", "19", "12"));
        city_idDao.insert(new City_Id(null, "广东省", "汕尾市", "19", "13"));
        city_idDao.insert(new City_Id(null, "广东省", "河源市", "19", "14"));
        city_idDao.insert(new City_Id(null, "广东省", "阳江市", "19", "15"));
        city_idDao.insert(new City_Id(null, "广东省", "清远市", "19", "16"));
        city_idDao.insert(new City_Id(null, "广东省", "东莞市", "19", "17"));
        city_idDao.insert(new City_Id(null, "广东省", "中山市", "19", "18"));
        city_idDao.insert(new City_Id(null, "广东省", "潮州市", "19", "19"));
        city_idDao.insert(new City_Id(null, "广东省", "揭阳市", "19", "20"));
        city_idDao.insert(new City_Id(null, "广东省", "云浮市", "19", "21"));
        city_idDao.insert(new City_Id(null, "广西省", "南宁市", "20", "01"));
        city_idDao.insert(new City_Id(null, "广西省", "柳州市", "20", "02"));
        city_idDao.insert(new City_Id(null, "广西省", "桂林市", "20", "03"));
        city_idDao.insert(new City_Id(null, "广西省", "梧州市", "20", "04"));
        city_idDao.insert(new City_Id(null, "广西省", "北海市", "20", "05"));
        city_idDao.insert(new City_Id(null, "广西省", "钦州市", "20", "07"));
        city_idDao.insert(new City_Id(null, "广西省", "贵港市", "20", "08"));
        city_idDao.insert(new City_Id(null, "广西省", "玉林市", "20", "09"));
        city_idDao.insert(new City_Id(null, "广西省", "崇左市", "20", "10"));
        city_idDao.insert(new City_Id(null, "广西省", "来宾市", "20", "11"));
        city_idDao.insert(new City_Id(null, "广西省", "贺州市", "20", "12"));
        city_idDao.insert(new City_Id(null, "广西省", "百色市", "20", "13"));
        city_idDao.insert(new City_Id(null, "广西省", "河池市", "20", "14"));
        city_idDao.insert(new City_Id(null, "海南省", "海口市", "21", "02"));
        city_idDao.insert(new City_Id(null, "海南省", "三亚市", "21", "03"));
        city_idDao.insert(new City_Id(null, "重庆市", "重庆市", "22", "01"));
        city_idDao.insert(new City_Id(null, "四川省", "成都市", "23", "01"));
        city_idDao.insert(new City_Id(null, "四川省", "自贡市", "23", "02"));
        city_idDao.insert(new City_Id(null, "四川省", "泸州市", "23", "04"));
        city_idDao.insert(new City_Id(null, "四川省", "德阳市", "23", "05"));
        city_idDao.insert(new City_Id(null, "四川省", "绵阳市", "23", "06"));
        city_idDao.insert(new City_Id(null, "四川省", "广元市", "23", "07"));
        city_idDao.insert(new City_Id(null, "四川省", "遂宁市", "23", "08"));
        city_idDao.insert(new City_Id(null, "四川省", "内江市", "23", "09"));
        city_idDao.insert(new City_Id(null, "四川省", "乐山市", "23", "10"));
        city_idDao.insert(new City_Id(null, "四川省", "南充市", "23", "11"));
        city_idDao.insert(new City_Id(null, "四川省", "宜宾市", "23", "12"));
        city_idDao.insert(new City_Id(null, "四川省", "广安市", "23", "13"));
        city_idDao.insert(new City_Id(null, "贵州省", "贵阳市", "24", "01"));
        city_idDao.insert(new City_Id(null, "贵州省", "遵义市", "24", "03"));
        city_idDao.insert(new City_Id(null, "云南省", "昆明市", "25", "01"));
        city_idDao.insert(new City_Id(null, "云南省", "曲靖市", "25", "02"));
        city_idDao.insert(new City_Id(null, "云南省", "玉溪市", "25", "03"));
        city_idDao.insert(new City_Id(null, "云南省", "思茅市", "25", "08"));
        city_idDao.insert(new City_Id(null, "西藏省", "拉萨市", "26", "01"));
        city_idDao.insert(new City_Id(null, "陕西省", "西安市", "27", "01"));
        city_idDao.insert(new City_Id(null, "陕西省", "铜川市", "27", "02"));
        city_idDao.insert(new City_Id(null, "陕西省", "宝鸡市", "27", "03"));
        city_idDao.insert(new City_Id(null, "陕西省", "咸阳市", "27", "04"));
        city_idDao.insert(new City_Id(null, "陕西省", "渭南市", "27", "05"));
        city_idDao.insert(new City_Id(null, "台湾省", "台湾省", "34", "01"));
        city_idDao.insert(new City_Id(null, "陕西省", "延安市", "27", "06"));
        city_idDao.insert(new City_Id(null, "陕西省", "汉中市", "27", "07"));
        city_idDao.insert(new City_Id(null, "甘肃省", "兰州市", "28", "01"));
        city_idDao.insert(new City_Id(null, "甘肃省", "金昌市", "28", "03"));
        city_idDao.insert(new City_Id(null, "甘肃省", "白银市", "28", "04"));
        city_idDao.insert(new City_Id(null, "甘肃省", "天水市", "28", "05"));
        city_idDao.insert(new City_Id(null, "青海省", "西宁市", "29", "01"));
        city_idDao.insert(new City_Id(null, "宁夏省", "银川市", "30", "01"));
        city_idDao.insert(new City_Id(null, "宁夏省", "吴忠市", "30", "03"));
        city_idDao.insert(new City_Id(null, "宁夏省", "中卫市", "30", "05"));
        city_idDao.insert(new City_Id(null, "新疆省", "哈密地区", "31", "04"));
        city_idDao.insert(new City_Id(null, "陕西省", "安康地区", "27", "08"));
        city_idDao.insert(new City_Id(null, "陕西省", "商洛地区", "27", "09"));
        city_idDao.insert(new City_Id(null, "陕西省", "榆林地区", "27", "10"));
        city_idDao.insert(new City_Id(null, "江西省", "景德镇市", "14", "02"));
        city_idDao.insert(new City_Id(null, "新疆省", "塔城地区", "31", "13"));
        city_idDao.insert(new City_Id(null, "新疆省", "喀什地区", "31", "10"));
        city_idDao.insert(new City_Id(null, "新疆省", "和田地区", "31", "11"));
        city_idDao.insert(new City_Id(null, "贵州省", "六盘水市", "24", "02"));
        city_idDao.insert(new City_Id(null, "四川省", "达川地区", "23", "14"));
        city_idDao.insert(new City_Id(null, "四川省", "雅安地区", "23", "15"));
        city_idDao.insert(new City_Id(null, "广西省", "防城港市", "20", "06"));
        city_idDao.insert(new City_Id(null, "湖南省", "娄底地区", "18", "13"));
        city_idDao.insert(new City_Id(null, "湖南省", "张家界市", "18", "08"));
        city_idDao.insert(new City_Id(null, "山东省", "滨州地区", "15", "16"));
        city_idDao.insert(new City_Id(null, "山东省", "菏泽地区", "15", "17"));
        city_idDao.insert(new City_Id(null, "河南省", "平顶山市", "16", "04"));
        city_idDao.insert(new City_Id(null, "河南省", "三门峡市", "16", "12"));
        city_idDao.insert(new City_Id(null, "甘肃省", "酒泉地区", "28", "06"));
        city_idDao.insert(new City_Id(null, "甘肃省", "张掖地区", "28", "07"));
        city_idDao.insert(new City_Id(null, "甘肃省", "武威地区", "28", "08"));
        city_idDao.insert(new City_Id(null, "甘肃省", "定西地区", "28", "09"));
        city_idDao.insert(new City_Id(null, "甘肃省", "陇南地区", "28", "10"));
        city_idDao.insert(new City_Id(null, "甘肃省", "平凉地区", "28", "11"));
        city_idDao.insert(new City_Id(null, "甘肃省", "庆阳地区", "28", "12"));
        city_idDao.insert(new City_Id(null, "贵州省", "毕节地区", "24", "06"));
        city_idDao.insert(new City_Id(null, "贵州省", "安顺地区", "24", "07"));
        city_idDao.insert(new City_Id(null, "四川省", "攀枝花市", "23", "03"));
        city_idDao.insert(new City_Id(null, "贵州省", "铜仁地区", "24", "04"));
        city_idDao.insert(new City_Id(null, "云南省", "昭通地区", "25", "04"));
        city_idDao.insert(new City_Id(null, "云南省", "保山地区", "25", "11"));
        city_idDao.insert(new City_Id(null, "西藏省", "昌都地区", "26", "02"));
        city_idDao.insert(new City_Id(null, "西藏省", "山南地区", "26", "03"));
        city_idDao.insert(new City_Id(null, "西藏省", "那曲地区", "26", "05"));
        city_idDao.insert(new City_Id(null, "西藏省", "阿里地区", "26", "06"));
        city_idDao.insert(new City_Id(null, "西藏省", "林芝地区", "26", "07"));
        city_idDao.insert(new City_Id(null, "云南省", "丽江地区", "25", "13"));
        city_idDao.insert(new City_Id(null, "甘肃省", "嘉峪关市", "28", "02"));
        city_idDao.insert(new City_Id(null, "云南省", "临沧地区", "25", "16"));
        city_idDao.insert(new City_Id(null, "四川省", "巴中地区", "23", "19"));
        city_idDao.insert(new City_Id(null, "四川省", "眉山地区", "23", "20"));
        city_idDao.insert(new City_Id(null, "四川省", "资阳地区", "23", "21"));
        city_idDao.insert(new City_Id(null, "宁夏省", "石嘴山市", "30", "02"));
        city_idDao.insert(new City_Id(null, "宁夏省", "固原地区", "30", "04"));
        city_idDao.insert(new City_Id(null, "青海省", "海东地区", "29", "02"));
        city_idDao.insert(new City_Id(null, "新疆省", "乌鲁木齐市", "31", "01"));
        city_idDao.insert(new City_Id(null, "新疆省", "克拉玛依市", "31", "02"));
        city_idDao.insert(new City_Id(null, "新疆省", "吐鲁番地区", "31", "03"));
        city_idDao.insert(new City_Id(null, "西藏省", "日喀则地区", "26", "04"));
        city_idDao.insert(new City_Id(null, "新疆省", "阿克苏地区", "31", "08"));
        city_idDao.insert(new City_Id(null, "新疆省", "阿勒泰地区", "31", "14"));
        city_idDao.insert(new City_Id(null, "河南省", "驻马店地区", "16", "17"));
        city_idDao.insert(new City_Id(null, "四川省", "甘孜藏族自治州", "23", "17"));
        city_idDao.insert(new City_Id(null, "四川省", "凉山彝族自治州", "23", "18"));
        city_idDao.insert(new City_Id(null, "青海省", "海北藏族自治州", "29", "03"));
        city_idDao.insert(new City_Id(null, "青海省", "黄南藏族自治州", "29", "04"));
        city_idDao.insert(new City_Id(null, "青海省", "海南藏族自治州", "29", "05"));
        city_idDao.insert(new City_Id(null, "青海省", "果洛藏族自治州", "29", "06"));
        city_idDao.insert(new City_Id(null, "青海省", "玉树藏族自治州", "29", "07"));
        city_idDao.insert(new City_Id(null, "新疆省", "昌吉回族自治州", "31", "05"));
        city_idDao.insert(new City_Id(null, "新疆省", "省直辖行政单位", "31", "15"));
        city_idDao.insert(new City_Id(null, "香港  ", "香港特别行政区", "32", "01"));
        city_idDao.insert(new City_Id(null, "澳门  ", "澳门特别行政区", "33", "01"));
        city_idDao.insert(new City_Id(null, "云南省", "大理白族自治州", "25", "10"));
        city_idDao.insert(new City_Id(null, "甘肃省", "临夏回族自治州", "28", "13"));
        city_idDao.insert(new City_Id(null, "甘肃省", "甘南藏族自治州", "28", "14"));
        city_idDao.insert(new City_Id(null, "河南省", "省直辖行政单位", "16", "18"));
        city_idDao.insert(new City_Id(null, "湖北省", "省直辖行政单位", "17", "14"));
        city_idDao.insert(new City_Id(null, "海南省", "省直辖行政单位", "21", "01"));
        city_idDao.insert(new City_Id(null, "云南省", "楚雄彝族自治州", "25", "05"));
        city_idDao.insert(new City_Id(null, "云南省", "迪庆藏族自治州", "25", "15"));
        city_idDao.insert(new City_Id(null, "新疆省", "伊犁哈萨克自治州", "31", "12"));
        city_idDao.insert(new City_Id(null, "云南省", "怒江傈僳族自治州", "25", "14"));
        city_idDao.insert(new City_Id(null, "四川省", "阿坝藏族羌族自治州", "23", "16"));
        city_idDao.insert(new City_Id(null, "新疆省", "博尔塔拉蒙古自治州", "31", "06"));
        city_idDao.insert(new City_Id(null, "云南省", "西双版纳傣族自治州", "25", "09"));
        city_idDao.insert(new City_Id(null, "新疆省", "巴音郭楞蒙古自治州", "31", "07"));
        city_idDao.insert(new City_Id(null, "云南省", "文山壮族苗族自治州", "25", "07"));
        city_idDao.insert(new City_Id(null, "青海省", "海西蒙古族藏族自治州", "29", "08"));
        city_idDao.insert(new City_Id(null, "云南省", "德宏傣族景颇族自治州", "25", "12"));
        city_idDao.insert(new City_Id(null, "贵州省", "黔东南苗族侗族自治州", "24", "08"));
        city_idDao.insert(new City_Id(null, "贵州省", "黔南布依族苗族自治州", "24", "09"));
        city_idDao.insert(new City_Id(null, "云南省", "红河哈尼族彝族自治州", "25", "06"));
        city_idDao.insert(new City_Id(null, "湖北省", "恩施土家族苗族自治州", "17", "13"));
        city_idDao.insert(new City_Id(null, "湖南省", "湘西土家族苗族自治州", "18", "14"));
        city_idDao.insert(new City_Id(null, "新疆省", "克孜勒苏柯尔克孜自治州", "31", "09"));
        city_idDao.insert(new City_Id(null, "贵州省", "黔西南布依族苗族自治州", "24", "05"));
    }
}
