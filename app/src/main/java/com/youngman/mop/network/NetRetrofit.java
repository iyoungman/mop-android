package com.youngman.mop.network;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YoungMan on 2019-04-28.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NetRetrofit {

    private static final NetRetrofit ourInstance = new NetRetrofit();
    private NetRetrofitInterface netRetrofitInterface;

    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public NetRetrofitInterface getNetRetrofitInterface(){
        if(netRetrofitInterface ==null){
            netRetrofitInterface = retrofit.create(NetRetrofitInterface.class);
        }
        return netRetrofitInterface;
    }
}
