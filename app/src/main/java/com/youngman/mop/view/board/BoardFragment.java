package com.youngman.mop.view.board;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;
import com.youngman.mop.R;
import com.youngman.mop.data.source.board.BoardRepository;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.board.adpater.BoardAdapter;
import com.youngman.mop.view.board.presenter.BoardContract;
import com.youngman.mop.view.board.presenter.BoardPresenter;
import com.youngman.mop.view.boardcreate.BoardCreateActivity;
import com.youngman.mop.view.schedulecreate.ScheduleCreateActivity;

public class BoardFragment extends Fragment implements BoardContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private BoardAdapter boardAdapter;
    private BoardContract.Presenter presenter;
    private FloatingActionButton fab;

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
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

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
        fab.setOnClickListener(v -> startBoardCreateActivity());
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            presenter.callBoardByClub(clubId, calculatePageNo());
        }, 500);
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

    private void startBoardCreateActivity() {
        Intent intent = new Intent(getActivity(), BoardCreateActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        getActivity().startActivityForResult(intent, 2222);
    }

    @Subscribe
    public void onActivityResult(ActivityResultEvent activityResultEvent) {
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
        if (activityResultEvent.getRequestCode() == 2222 && activityResultEvent.getResultCode() == 2222) {
            presenter.callBoardByClub(clubId, 1);
        }
    }

    @Override
    public void startBoardDetailActivity(Long boardId) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }
}
