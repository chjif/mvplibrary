package com.chjif.library.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * 图片工具类
 * Created by cjf on 2018-07-12 15:14
 */
public class ImageUtil {
    /**
     * glide普通加载图片
     *
     * @param context
     * @param object
     * @param mImageView
     */
    public static void load(Context context, Object object, ImageView mImageView) {
        Glide.with(context).load(object).into(mImageView);
    }

    /**
     * glide普通图片显示
     * @param context
     * @param object
     * @param mImageView
     * @param placeDrawable 占位图
     * @param errorDrawable 异常图
     */
    public static void load(Context context, Object object, ImageView mImageView,
                                      Drawable placeDrawable, Drawable errorDrawable) {
        RequestOptions requestOptions = new RequestOptions();
        if (placeDrawable != null) {
            requestOptions.placeholder(placeDrawable);
        }
        if (errorDrawable != null) {
            requestOptions.error(errorDrawable);
        }
        Glide.with(context).load(object)
                .apply(requestOptions).into(mImageView);
    }

    /**
     * glide圆形图片显示
     *
     * @param context
     * @param object
     * @param mImageView
     */
    public static void loadWithCircle(Context context, Object object, ImageView mImageView) {
        Glide.with(context).load(object)
                .apply(new RequestOptions().circleCrop()).into(mImageView);
    }

    /**
     * glide圆形图片显示
     * @param context
     * @param object
     * @param mImageView
     * @param placeDrawable 占位图
     * @param errorDrawable 异常图
     */
    public static void loadWithCircle(Context context, Object object, ImageView mImageView,
                                      Drawable placeDrawable, Drawable errorDrawable) {
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        if (placeDrawable != null) {
            requestOptions.placeholder(placeDrawable);
        }
        if (errorDrawable != null) {
            requestOptions.error(errorDrawable);
        }
        Glide.with(context).load(object)
                .apply(requestOptions).into(mImageView);
    }

}
