package com.youngman.mop.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private final String LOCAL_BASE_URL = "http://172.30.1.6:8092/";
    private final String DEV_BASE_URL = "http://13.125.46.192:8092/";

    private static final RetrofitClient INSTANCE = new RetrofitClient();
    private RetrofitApiService retrofitApiService;

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static RetrofitClient getInstance() {
        return INSTANCE;
    }

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(DEV_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
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
