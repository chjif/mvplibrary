package com.chjif.library.network;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 用来给每个接口加入基础参数
 * Created by cjf on 2018-07-19 16:09
 */

public class BaseParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        RequestBody body = oldRequest.body();
        if (body != null) {
            RequestBody newBody = null;
            if (body instanceof FormBody) {
                newBody = addParamsToFormBody((FormBody) body);
            } else if (body instanceof MultipartBody) {
                newBody = addParamsToMultipartBody((MultipartBody) body);
            }
            if (null != newBody) {
                Request newRequest = oldRequest.newBuilder()
                        .url(oldRequest.url())
                        .method(oldRequest.method(), newBody)
                        .build();
                return chain.proceed(newRequest);
            }

        }
        return chain.proceed(oldRequest);
    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("key", "value");
        builder.addFormDataPart("key", "value");
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }
        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "value");
        builder.add("key", "value");
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }
        return builder.build();
    }
}
