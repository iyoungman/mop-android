package com.youngman.mop.net.batch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youngman.mop.net.LoggingInterceptor;
import com.youngman.mop.net.api.RetrofitApiService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YoungMan on 2019-10-14.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrofitBatchApiClient {

    private final String LOCAL_BASE_URL = "http://172.30.1.49:8080/";
    private final String DEV_BASE_URL = "http://13.125.213.79:8090/";
    private static final RetrofitBatchApiClient INSTANCE = new RetrofitBatchApiClient();
    private RetrofitBatchApiService retrofitBatchApiService;

    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static RetrofitBatchApiClient getInstance() {
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

    public RetrofitBatchApiService getRetrofitApiService() {
        if (retrofitBatchApiService == null) {
            retrofitBatchApiService = retrofit.create(RetrofitBatchApiService.class);
        }
        return retrofitBatchApiService;
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
