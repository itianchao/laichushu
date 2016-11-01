package com.sofacity.laichushu.retrofit;

import com.sofacity.laichushu.bean.netbean.ForgetPwd_Paramet;
import com.sofacity.laichushu.bean.netbean.Login_Paramet;
import com.sofacity.laichushu.bean.netbean.RegistValid_Paramet;
import com.sofacity.laichushu.bean.netbean.Regist_Paramet;
import com.sofacity.laichushu.mvp.forgetpwd.ForgetPwdModel;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.login.LoginModel;
import com.sofacity.laichushu.mvp.regist.RegistModel;
import com.sofacity.laichushu.mvp.regist2.RegistModel2;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 接口url
 * Created by wangtong
 * on 2016/10/11
 */
public interface ApiStores {
    //baseUrl
//    String API_SERVER_URL = "http://60.205.141.21:8099/";
    String API_SERVER_URL = "http://192.168.147.115:8082/book-app/";//张峰
//    String API_SERVER_URL = "http://192.168.147.101:8082/book-app/";//张永生

    //登录
    @POST("login/do")
    Observable<LoginModel> loginLoadData(@Body Login_Paramet paramet);
    //校验手机号和验证码
    @POST("register/valid")
    Observable<RegistModel> registCode(@Body RegistValid_Paramet paramet);
    //注册
    @POST("register/do")
    Observable<RegistModel2> registData(@Body Regist_Paramet paramet);
    //重置密码
    @POST("user/resetPwd")
    Observable<ForgetPwdModel> forgetPwdData(@Body ForgetPwd_Paramet paramet);
    //home轮播图
    @POST("carousel/list")
    Observable<HomeModel> homeCarouselData();
    //home最热
    @POST("suggest/list")
    Observable<HomeHotModel> homeHotData();
}
