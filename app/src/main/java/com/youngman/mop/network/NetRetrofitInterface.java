package com.youngman.mop.network;

import com.youngman.mop.model.domain.SignInModel;
import com.youngman.mop.model.domain.SignUpModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface NetRetrofitInterface {

    @POST("singup")
    Call<Boolean> singUp(@Body SignUpModel signUpModel);

    @POST("singin")
    Call<Boolean> singIn(@Body SignInModel signInModel);

    @POST("singIn")
    Call<Boolean> getMyInfo(@Body String userId);
}
