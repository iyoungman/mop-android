package com.youngman.mop.data.source.emergency;

import com.youngman.mop.data.Emergency;
import com.youngman.mop.data.Participant;
import com.youngman.mop.data.source.mapmemberadd.MapMemberAddRemoteDataSource;
import com.youngman.mop.net.api.RetrofitApiClient;
import com.youngman.mop.net.batch.RetrofitBatchApiClient;
import com.youngman.mop.net.batch.RetrofitBatchApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-10-14.
 */

public class EmergencyRemoteDataSource {

    private static EmergencyRemoteDataSource INSTANCE;

    public static EmergencyRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmergencyRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callEmergencyToOthers(Emergency emergency, EmergencyApiListener listener) {
        Call<Void> result = RetrofitBatchApiClient.getInstance().getRetrofitApiService().callEmergencyToOthers(emergency);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("알림 전송에 실패했습니다.");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public interface EmergencyApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
