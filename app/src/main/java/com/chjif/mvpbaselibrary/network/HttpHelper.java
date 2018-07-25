package com.chjif.mvpbaselibrary.network;

import com.chjif.library.network.BaseHttpHelper;

/**
 * Created by cjf on 2018-07-25 10:04
 */
public class HttpHelper {
    private String baseUrl = "http://www.kuaidi100.com";
    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (HttpHelper.class) {
                if (apiService == null) {
                    new HttpHelper();
                }
            }
        }
        return apiService;
    }

    private HttpHelper() {
        BaseHttpHelper baseApi = new BaseHttpHelper();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
