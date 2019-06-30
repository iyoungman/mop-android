package com.youngman.mop.net;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YoungMan on 2019-04-28.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrofitClient {

    private final String LOCAL_BASE_URL = "http://192.168.0.87:8092/";
    private final String DEV_BASE_URL = "http://54.180.67.243:8092/";

    private static final RetrofitClient INSTANCE = new RetrofitClient();
    private RetrofitApiService retrofitApiService;


    public static RetrofitClient getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitApiService getRetrofitApiService() {
        if (retrofitApiService == null) {
            retrofitApiService = retrofit.create(RetrofitApiService.class);
        }
        return retrofitApiService;
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
