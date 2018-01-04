package com.app.aptap.net;

import android.util.Log;
import android.widget.TextView;

import com.app.aptap.action.APICallback;
import com.app.aptap.model.CheckLoginStatus;
import com.app.aptap.model.ResponseRegisterAPI;
import com.app.aptap.util.Constants;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ke41342 on 08-10-2017.
 */

public class APICall {

    public static String TAG = APICall.class.getName();

    private Call<ResponseBody> callAPI = null;

    public APICall() {
    }

    public void callAPIFunction(ApiInterface apiInterface, String typeOfService, CheckLoginStatus checkLoginStatus, final APICallback apiCallback) {

        Gson gson = new Gson();
        String json = "[" + gson.toJson(checkLoginStatus) + "]";
        Log.d(TAG, new StringBuilder().append("callAPIFunction:json:-").append(json).toString());

        callAPI = apiInterface.callAll(typeOfService, json);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("callAll:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("createUser:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });
    }


    public void callAllAPI(ApiInterface apiInterface, String typeOfService, String requestData, final APICallback apiCallback) {

        Log.d(TAG, new StringBuilder().append("callAllAPI:json:-").append(requestData).toString());

        callAPI = apiInterface.callAll(typeOfService, requestData);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("callAllAPI:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("callAllAPI:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });
    }

    public void callInterestAPI(ApiInterface apiInterface, String typeOfService, String requestData, final APICallback apiCallback) {

        Log.d(TAG, new StringBuilder().append("callInterestAPI:json:-").append(requestData).toString());

        callAPI = apiInterface.callInterest(typeOfService, requestData);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("callAllAPI:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("callAllAPI:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });
    }

    public void getOTPAPI(ApiInterface apiInterface, String countryName, Long countryCode, String mobileNumber, final APICallback apiCallback) {
        callAPI = apiInterface.getOTPAPI(countryName, countryCode, mobileNumber);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("getOTPAPI:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("getOTPAPI:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });

    }


    public void getRegUserInfoAPI(ApiInterface apiInterface, String countryName, Long countryCode, String mobileNumber, final APICallback apiCallback) {
        callAPI = apiInterface.getRegUserInfo(countryName, countryCode, mobileNumber);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("getOTPAPI:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("getOTPAPI:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });

    }



}
