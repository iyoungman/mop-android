package com.youngman.mop.net.batch;

import com.youngman.mop.data.ClubSignCountResponse;
import com.youngman.mop.data.Emergency;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by YoungMan on 2019-10-14.
 */

public interface RetrofitBatchApiService {

    @POST("mop/notification/emergency")
    Call<Void> callEmergencyToOthers(@Body Emergency emergency);

    @GET("mop/clubstatistics")
    Call<ClubSignCountResponse> callSignCount(@Query("clubId") Long clubId);
}
