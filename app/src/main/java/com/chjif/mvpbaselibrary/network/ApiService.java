package com.chjif.mvpbaselibrary.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cjf on 2018-07-23 13:54
 */
public interface ApiService {
    String versionCode = "";

    @GET(versionCode + "/query")
    Observable<String> getExpress(@Query("type") String type, @Query("postid") String postid);

    @FormUrlEncoded
    @POST(versionCode + "/query")
    Observable<String> postExpress(@Field("type") String type, @Field("postid") String postid);

    //上传图片
//    @POST("file/up")
//    @Multipart
//    Observable<String> upload(@Part("image") String image);
}
