package com.chjif.library.network;

import com.chjif.library.base.BaseApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by cjf on 2018-07-19 14:09
 */
public class BaseHttpHelper {
    private final int WRITE_TIME_OUT = 30;
    private final int READ_TIME_OUT = 30;
    private final int CONNECT_TIME_OUT = 30;
    //缓存大小
    private long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
    private OkHttpClient defaultClient = null;
    private Retrofit mRetrofit = null;

    public BaseHttpHelper() {
        init();
    }


    private void init() {
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        //缓存目录
        String cacheFile = BaseApp.getContext().getCacheDir() + "/http";
        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
        defaultClient = new OkHttpClient.Builder()
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                //无网的情况
                .addInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                //设置重连次数
                .addInterceptor(new RetryIntercepter(3))
                //有网的情况
                .addNetworkInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                //添加固定参数
                //.addInterceptor(new BaseParamsInterceptor())
                .cache(cache)
                .build();
    }

    /**
     * 设置retrofit
     *
     * @param url
     * @return
     */
    public Retrofit getRetrofit(String url) {
        mRetrofit = new Retrofit.Builder().baseUrl(url)
                .client(defaultClient)
                //数据解析器支持返回格式为String
                .addConverterFactory(ScalarsConverterFactory.create())
                //数据解析器支持返回格式为json实体
                .addConverterFactory(GsonConverterFactory.create())
                //适配Rxjava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }

}
