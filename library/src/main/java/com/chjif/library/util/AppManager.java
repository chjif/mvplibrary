package com.chjif.library.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * activity堆栈式管理
 * Created by cjf on 2018-08-07 10:29
 */
public class AppManager {
    private static AppManager instance = new AppManager();

    /**
     * activity控制
     */
    public Map<String, WeakReference<Activity>> openedActivitys = new LinkedHashMap<String, WeakReference<Activity>>();// 已经打开的activity

    private AppManager() {
    }

    /**
     * 单例模式 创建单一实例
     *
     * @return
     */
    public static AppManager getInstance() {
        return instance;
    }

    /**
     * 保存activity
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!openedActivitys.keySet().contains(activity.getClass().getSimpleName())) {
            openedActivitys.put(activity.getClass().getSimpleName(),
                    new WeakReference<Activity>(activity));
        }
    }

    /**
     * 获取对应的activity
     * @param className
     * @return
     */
    public Activity getActivity(String className) {
        if (!openedActivitys.isEmpty()) {
            WeakReference<Activity> weakReference = openedActivitys
                    .get(className);
            if (weakReference == null) {
                return null;
            }
            Activity activity = weakReference.get();
            return activity;
        }
        return null;
    }
    /**
     * 关闭某activity
     * @param className
     */
    public void finishActivity(String className) {
        if (!openedActivitys.isEmpty()) {
            WeakReference<Activity> weakReference = openedActivitys
                    .get(className);
            if (weakReference == null) {
                return;
            }
            Activity activity = weakReference.get();
            openedActivitys.remove(className); // 从集合中移除
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 移除保存的activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        openedActivitys.remove(activity.getClass().getSimpleName());
    }
    /**
     * 关闭所有打开的activity
     */
    public void finishAllActivitys() {
        if (!openedActivitys.isEmpty()) {
            for (String clsssName : openedActivitys.keySet()) {
                WeakReference<Activity> weakReference = openedActivitys
                        .get(clsssName);
                Activity activity = weakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
            openedActivitys.clear();
        }
    }
}
