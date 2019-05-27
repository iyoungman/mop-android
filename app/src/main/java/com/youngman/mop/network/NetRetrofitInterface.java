package com.youngman.mop.network;

import com.youngman.mop.model.domain.ClubListModel;
import com.youngman.mop.model.domain.ClubModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by YoungMan on 2019-04-28.
 */

@SuppressWarnings("SpellCheckingInspection")
public interface NetRetrofitInterface {

    @POST("mop/member/signin")
    Call<Boolean> callSingIn(@Body Map<String, String> signInParams);

    @POST("mop/member")
    Call<Boolean> callSingUp(@Body Map<String, String> signUpParams);

    @PUT("mop/member")
    Call<Void> callUpdateMember();

    @DELETE("mop/member")
    Call<Void> callDeleteMember(@Query("email") String email);


    @POST("mop/myclub")
    Call<Void> callCreateMyClub();

    @GET("mop/myclub")
    Call<List<ClubModel>> callMyClubsByMemberEmail(@Query("email") String email);

    @DELETE("mop/myclub")
    Call<Void> callDeleteMyClub(@QueryMap Map deleteMyClubParams);


    @POST("mop/club")
    Call<Void> callCreateClub();

    @GET("mop/club/search")
    Call<List<ClubModel>> callPagingClubsBySearch(@Query("searchClub") String searchClub);

    /*@GET("mop/club/member")
    Call<List<ClubModel>> callPagingClubsByMember(@QueryMap Map<String, Object> pagingClubsByMemberParams);*/
    @GET("mop/club/test")
    Call<ClubListModel> callPagingClubsByMember(@QueryMap Map<String, Object> pagingClubsByMemberParams);

    @PUT("mop/club")
    Call<Void> callUpdateClub();

    @DELETE("mop/club")
    Call<Void> callDeleteClub();


    @POST("mop/schedule")
    Call<Void> callCreateSchedule();
}
