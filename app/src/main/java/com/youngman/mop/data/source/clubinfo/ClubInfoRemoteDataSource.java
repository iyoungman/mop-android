package com.youngman.mop.data.source.clubinfo;

import com.youngman.mop.data.ClubInfoResponse;
import com.youngman.mop.net.RetrofitClient;

import java.io.File;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-06-06.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubInfoRemoteDataSource implements ClubInfoSource {

    private static ClubInfoRemoteDataSource INSTANCE;


    public static ClubInfoRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClubInfoRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callClubInfoByClubId(Long clubId, InfoApiListener listener) {
        Call<ClubInfoResponse> result = RetrofitClient.getInstance().getRetrofitApiService().callClubInfoById(clubId);
        result.enqueue(new Callback<ClubInfoResponse>() {
            @Override
            public void onResponse(Call<ClubInfoResponse> call, Response<ClubInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubInfoResponse clubInfoResponse = response.body();
                    listener.onSuccess(clubInfoResponse);
                    return;
                }
                listener.onFail("동호회 정보를 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<ClubInfoResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    @Override
    public void callUploadClubImage(File imageFile, UploadApiListener listener) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);
        Call<String> result = RetrofitClient.getInstance().getRetrofitApiService().callUploadClubImage(uploadFile);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("동호회 이미지 저장에 실패하였습니다");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

}
