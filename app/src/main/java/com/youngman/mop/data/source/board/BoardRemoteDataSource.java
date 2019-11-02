package com.youngman.mop.data.source.board;

import com.youngman.mop.data.BoardPagingResponse;
import com.youngman.mop.net.api.RetrofitApiClient;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YoungMan on 2019-07-09.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRemoteDataSource implements BoardSource {

    private static BoardRemoteDataSource INSTANCE;

    public static BoardRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callBoardsByClub(Long clubId,
                                 int pageNo,
                                 ListApiListener listener) {

        Call<BoardPagingResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callPagingBoards(makeParams(clubId, pageNo));
        result.enqueue(new Callback<BoardPagingResponse>() {
            @Override
            public void onResponse(Call<BoardPagingResponse> call, Response<BoardPagingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BoardPagingResponse boardPagingResponse = response.body();
                    listener.onSuccess(boardPagingResponse);
                    return;
                }
                listener.onFail("목록을 받아오는데 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<BoardPagingResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private Map<String, Object> makeParams(Long clubId, int pageNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubId", clubId);
        params.put("pageNo", pageNo);

        return params;
    }
}