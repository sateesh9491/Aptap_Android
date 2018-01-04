package com.app.aptap.net;

import com.app.aptap.model.UserDetails;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by aditya on 8/26/2017.
 */

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("user/otp")
    public Call<ResponseBody> getOTPAPI(@Query("countryName") String countryName, @Query("countryCode") Long countryCode, @Query("mobileNumber") String mobileNumber);


    @Headers("Content-Type: application/json")
    @GET("user/otp")
    public Call<ResponseBody> getRegUserInfo(@Query("countryName") String countryName, @Query("countryCode") Long countryCode, @Query("mobileNumber") String mobileNumber);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("create")
    public Call<ResponseBody> callAll(@Field(value = "typeofservice", encoded = true) String title, @Field(value =  "clientData", encoded = true) String clientData);

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("fetch")
    public Call<ResponseBody> callInterest(@Field(value = "typeofservice", encoded = true) String title, @Field(value =  "clientData", encoded = true) String clientData);


    @POST("/posts")
    @FormUrlEncoded
    Call<ResponseBody> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<ResponseBody> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<ResponseBody> getAnswers(@Query("tagged") String tags);


    @Headers("Content-Type: application/json")
    @GET("genQrVerification")
    public Call<UserDetails> getQrCode(@QueryMap Map<String, String> params);
}
