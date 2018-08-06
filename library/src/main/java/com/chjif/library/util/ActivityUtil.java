package com.chjif.library.util;

import android.app.Activity;
import android.content.Intent;


/**
 * Activity相关操作
 * Created by cjf on 2016/5/9.
 */
public class ActivityUtil {

    /**
     * 类的跳转
     * @param activity
     * @param cls
     */
    public static void toAnotherActivity(Activity activity, Class<?> cls) {
            Intent intent = new Intent(activity, cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivity(intent);
    }

    /**
     * 类的跳转
     * @param activity
     * @param cls
     */
    public static void toClearTopActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    /**
     * 类的跳转
     * @param activity
     * @param intent
     */
    public static void toAnotherActivity(Activity activity, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }
    /**
     * 类的跳转
     * @param activity
     * @param intent
     * @param requestCode
     */
    public static void toAnotherActivityForResult(Activity activity, Intent intent, int requestCode) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivityForResult(intent,requestCode);
    }
}
