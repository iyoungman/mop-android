package com.youngman.mop.net;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YoungMan on 2019-04-28.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NetRetrofit {

    private final String LOCAL_BASE_URL = "http://192.168.0.104:8092/";
    private final String DEV_BASE_URL = "http://54.180.67.243:8092/";

    private static final NetRetrofit ourInstance = new NetRetrofit();
    private NetRetrofitInterface netRetrofitInterface;

    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public NetRetrofitInterface getNetRetrofitInterface() {
        if (netRetrofitInterface == null) {
            netRetrofitInterface = retrofit.create(NetRetrofitInterface.class);
        }
        return netRetrofitInterface;
    }
}
