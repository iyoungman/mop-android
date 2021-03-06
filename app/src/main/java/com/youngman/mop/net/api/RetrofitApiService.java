package com.youngman.mop.net.api;

import com.youngman.mop.data.BoardCreateRequest;
import com.youngman.mop.data.BoardPagingResponse;
import com.youngman.mop.data.Club;
import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.data.ClubPagingResponse;
import com.youngman.mop.data.ClubSignCountResponse;
import com.youngman.mop.data.Participant;
import com.youngman.mop.data.Schedule;
import com.youngman.mop.data.SignIn;
import com.youngman.mop.data.SignInResponse;
import com.youngman.mop.data.SignUp;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by YoungMan on 2019-04-28.
 */

public interface RetrofitApiService {

    @GET("mop/auth/check")
    Call<Boolean> callIsValidToken(@Header("token") String token);



    @POST("mop/member/signin")
    Call<SignInResponse> callSingIn(@Body SignIn signIn);

    @POST("mop/member/signup")
    Call<Void> callSingUp(@Body SignUp signUp);

    @PUT("mop/member")
    Call<Void> callUpdateMember();

    @DELETE("mop/member")
    Call<Void> callDeleteMember(@Query("email") String email);



    @POST("mop/myclub")
    Call<Void> callCreateMyClub(@Body Map<String, Object> body);

    @GET("mop/myclub")
    Call<List<Club>> callMyClubsByMemberEmail(@Query("email") String email);

    @DELETE("mop/myclub")
    Call<Void> callDeleteMyClub(@QueryMap Map<String, Object> params);



    @POST("mop/club")
    Call<Void> callCreateClub();

    @GET("mop/club/chair")
    Call<Boolean> callIsClubChair(@QueryMap Map<String, Object> params);

    @GET("mop/club/member")
    Call<ClubPagingResponse> callPagingClubsByMember(@QueryMap Map<String, Object> params);

    @PUT("mop/club")
    Call<Void> callUpdateClub();

    @DELETE("mop/club")
    Call<Void> callDeleteClub();

    @GET("mop/club/info")
    Call<ClubInfoResponse> callClubInfoById(@Query("clubId") Long clubId);

    @Multipart
    @POST("mop/club/image")
    Call<Map<String, String>> callUploadClubImage(@Part("clubId") Long clubId, @Part MultipartBody.Part image);



    @POST("mop/schedule")
    Call<Void> callCreateSchedule(@Body Schedule schedule);

    @GET("mop/schedule/monthly")
    Call<Map<String, Schedule>> callSchedules(@QueryMap Map<String, Object> params);



    @POST("mop/participant")
    Call<Integer> callChangeParticipant(@Body Map<String, Object> body);

    @GET("mop/participant")
    Call<List<Participant>> callParticipants(@QueryMap Map<String, Object> params);

    @GET("mop/participant/count")
    Call<Integer> callParticipantCount(@QueryMap Map<String, Object> params);


    @POST("mop/board")
    Call<Void> callCreateBoard(@Body BoardCreateRequest boardCreateRequest);

    @GET("mop/board")
    Call<BoardPagingResponse> callPagingBoards(@QueryMap Map<String, Object> params);


}
