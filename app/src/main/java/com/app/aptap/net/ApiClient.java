package com.app.aptap.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aditya on 8/26/2017.
 */

public class ApiClient {

    //public static final String BASE_URL = "http://35.197.148.98:8080/aptap-services/remote/rest/aptap/";

    public static final String BASE_URL = "http://35.197.148.98:8080/aptap-services-boot/rest/aptap/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
