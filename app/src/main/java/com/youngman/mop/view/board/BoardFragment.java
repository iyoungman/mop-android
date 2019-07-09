package com.youngman.mop.view.board;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youngman.mop.R;
import com.youngman.mop.data.source.board.BoardRepository;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.board.adpater.BoardAdapter;
import com.youngman.mop.view.board.presenter.BoardContract;
import com.youngman.mop.view.board.presenter.BoardPresenter;

public class BoardFragment extends Fragment implements BoardContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private BoardAdapter boardAdapter;
    private BoardContract.Presenter presenter;

    private Long clubId;


    public static BoardFragment createFragment(Long clubId) {
        BoardFragment fragment = new BoardFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_boards);

        clubId = getArguments().getLong("EXTRA_CLUB_ID");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        boardAdapter = new BoardAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        boardAdapter.setLinearLayoutManager(linearLayoutManager);
        boardAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(boardAdapter);

        presenter = new BoardPresenter(this, BoardRepository.getInstance());
        presenter.setBoardAdapterView(boardAdapter);
        presenter.setBoardAdapterModel(boardAdapter);
        presenter.callBoardByClub(clubId, 1);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            presenter.callBoardByClub(clubId, calculatePageNo());
        },500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = boardAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startBoardDetailActivity(Long boardId) {

    }
}
