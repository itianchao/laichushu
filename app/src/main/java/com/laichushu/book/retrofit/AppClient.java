package com.laichushu.book.retrofit;

import com.laichushu.book.BuildConfig;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client 网络框架
 */
public class AppClient {
    public static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                    .addHeader("gis", SharePrefManager.getToken())
                                    .addHeader("diu", SharePrefManager.getUserId())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .cache(new Cache(UIUtil.getContext().getCacheDir(), 20 * 1024 * 1024));
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
