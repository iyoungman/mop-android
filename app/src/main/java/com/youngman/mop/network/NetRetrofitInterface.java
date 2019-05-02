package com.youngman.mop.network;

import com.youngman.mop.model.domain.ClubModel;
import com.youngman.mop.model.domain.MyClubModel;
import com.youngman.mop.model.domain.SignInModel;
import com.youngman.mop.model.domain.SignUpModel;
import com.youngman.mop.model.dto.MyClubDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface NetRetrofitInterface {

    @POST("mop/singin")
    Call<Boolean> callSingIn(@Body Map<String, String> signInParams);

    @POST("mop/singup")
    Call<Boolean> callSingUp(@Body Map<String, String> signUpParams);

    @POST("mop/myclub")
    Call<List<ClubModel>> callClubList(@Body String userId);

    @DELETE("mop/myclub/{id}")
    Call<Boolean> deleteMyClub(@Path("id") String id);
}
