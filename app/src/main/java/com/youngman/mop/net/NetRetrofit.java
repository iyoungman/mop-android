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
public class NetRetrofit {

    private final String LOCAL_BASE_URL = "http://192.168.0.61:8092/";
    private final String DEV_BASE_URL = "http://54.180.67.243:8092/";

    private static final NetRetrofit ourInstance = new NetRetrofit();
    private NetRetrofitInterface netRetrofitInterface;


    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(createOkHttpClient())
            .build();

    public NetRetrofitInterface getNetRetrofitInterface() {
        if (netRetrofitInterface == null) {
            netRetrofitInterface = retrofit.create(NetRetrofitInterface.class);
        }
        return netRetrofitInterface;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}
