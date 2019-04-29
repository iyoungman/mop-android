package com.youngman.mop.network;

import com.youngman.mop.model.domain.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface NetRetrofitInterface {

    @POST("singup")
    Call<Void> singup(@Body UserModel userModel);
}
