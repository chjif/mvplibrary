package com.chjif.library.network;

import com.chjif.library.base.BaseApplication;
import com.chjif.library.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKhttp的拦截器，用于上传初始化和缓存处理
 * 注意的是：okhttp只会对get请求进行缓存，post请求是不会进行缓存，这也是有道理的，
 * 因为get请求的数据一般是比较持久的，而post一般是交互操作，没太大意义进行缓存。
 * Created by cjf on 2018-07-20 16:22
 */
public class CachingControlInterceptor {
    private static final int TIMEOUT_CONNECT = 0; //0秒
    //缓存有效期
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24; //24小时

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            String cache = chain.request().header("cache");

            Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");

            if (cacheControl != null) {
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + cache)
                        .removeHeader("Pragma")
                        .build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    /**
     * max-age:告知缓存多长时间，在没有超过缓存时间的情况下，请求会返回缓存内的数据，在超出max-age的情况下向服务端发起新的请求，请求失败的情况下返回缓存数据（测试中已验证），否则向服务端重新发起请求。
     * max-stale:指示客户机可以接收超出max-age时间的响应消息，max-stale在请求设置中有效，在响应设置中无效（测试中已验证，且参见博客https://www.jianshu.com/p/db197279f053）。
     * 因此max-age和max-stale在请求中同时使用的情况下，缓存的时间可以为max-age和max-stale的和。
     */
    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected(BaseApplication.getContext())) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, max-stale=" + TIMEOUT_DISCONNECT)
                        .build();
            }
            return chain.proceed(request);

        }
    };

}