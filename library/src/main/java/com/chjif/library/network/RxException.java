package com.chjif.library.network;


import android.net.ParseException;

import com.chjif.library.util.LoggerUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Created by cjf on 2018-07-25 11:34
 */
public class RxException<T extends Throwable> implements Consumer<T> {

    private static final String TAG = "RxException";

    private static final String SOCKETTIMEOUTEXCEPTION = "连接超时，请检查您的网络状态，稍后重试";
    private static final String HTTPEXCEPTION = "服务器异常，请检查您的网络状态";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "UNKNOWN HOST异常，请检查您的网络状态";
    private static final String UNKNOWNEXCEPTION = "UNKNOWN异常，请检查您的网络状态";
    private static final String PARESEEXCEPTION = "解析异常";
    private static final String SSLHANDSHAKEEXCEPTION = "证书验证失败";

    private Consumer<? super Throwable> onError;

    public RxException(Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    public RxException() {

    }

    /**
     * Consume the given value.
     *
     * @param t the value
     * @throws Exception on error
     */
    @Override
    public void accept(T t) throws Exception {
        if (t instanceof SocketTimeoutException) {
            LoggerUtil.e(TAG, "onError: SocketTimeoutException----" + SOCKETTIMEOUTEXCEPTION);
            onError.accept(new Throwable(SOCKETTIMEOUTEXCEPTION));
        } else if (t instanceof HttpException) {
            LoggerUtil.e(TAG, "onError: HttpException----" + SOCKETTIMEOUTEXCEPTION);
            onError.accept(new Throwable(SOCKETTIMEOUTEXCEPTION));
        } else if (t instanceof ConnectException) {
            LoggerUtil.e(TAG, "onError: ConnectException-----" + CONNECTEXCEPTION);
            onError.accept(new Throwable(CONNECTEXCEPTION));
        } else if (t instanceof UnknownHostException) {
            LoggerUtil.e(TAG, "onError: UnknownHostException-----" + UNKNOWNHOSTEXCEPTION);
            onError.accept(new Throwable(UNKNOWNHOSTEXCEPTION));
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            LoggerUtil.e(TAG, "onError: PARESEEXCEPTION-----" + PARESEEXCEPTION);
            onError.accept(new Throwable(PARESEEXCEPTION));
        }else if (t instanceof javax.net.ssl.SSLHandshakeException) {
            LoggerUtil.e(TAG, "onError: SSLHANDSHAKEEXCEPTION-----" + SSLHANDSHAKEEXCEPTION);
            onError.accept(new Throwable(SSLHANDSHAKEEXCEPTION));
        }
        else {
            LoggerUtil.e(TAG, "onError:----" + t.getMessage());
//            ToastUtil.show(UNKNOWNEXCEPTION);
            onError.accept(t);
        }
    }
}
