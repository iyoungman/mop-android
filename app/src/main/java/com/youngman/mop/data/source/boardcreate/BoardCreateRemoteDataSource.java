package com.youngman.mop.data.source.boardcreate;

import com.youngman.mop.data.BoardCreateRequest;
import com.youngman.mop.net.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-11-01.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCreateRemoteDataSource {

    private static BoardCreateRemoteDataSource INSTANCE;

    public static BoardCreateRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardCreateRemoteDataSource();
        }
        return INSTANCE;
    }

    public void callCreateBoard(BoardCreateRequest boardCreateRequest, BoardCreateApiListener listener) {
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callCreateBoard(boardCreateRequest);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("게시글 저장에 실패했습니다");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    public interface BoardCreateApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
