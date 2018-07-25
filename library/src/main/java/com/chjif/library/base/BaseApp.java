package com.chjif.library.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by cjf on 2018-07-19 15:17
 */
public class BaseApp extends Application{
    private static Context mContext;//全局上下文对象
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
