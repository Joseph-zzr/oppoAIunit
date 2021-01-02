package com.coderpig.oppoaiunit.http;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 工具类
 */

public class RetrofitService {
    private volatile static RetrofitService apiRetrofit;
    private API.WAZApi apiService;

    //单例模式 双检锁
    public static RetrofitService getInstance(){
        if (apiRetrofit == null){
            synchronized (RetrofitService.class){
                if (apiRetrofit == null){
                    apiRetrofit = new RetrofitService();
                }
            }
        }
        return apiRetrofit;
    }

    //获得API对象
    public API.WAZApi getApiService() {
        return apiService;
    }

    private RetrofitService() {
        //配置okhttp并设置时间，日志信息和cookies
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        //关联okhttp并加上rxjava和gson的配置和baseurl
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .client(okHttpClient)
                //添加Gson解析工具
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //添加rxjava处理工具
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(API.WAZApi.class);
    }
}
