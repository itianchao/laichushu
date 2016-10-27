package com.sofacity.laichushu.retrofit;

import com.sofacity.laichushu.bean.netbean.Login_Paramet;
import com.sofacity.laichushu.mvp.login.LoginModel;

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
    String API_SERVER_URL = "http://60.205.141.21:8099/";

    //登录
    @POST("mobile/login")
    Observable<LoginModel> loginLoadData(@Body Login_Paramet paramet);

}
