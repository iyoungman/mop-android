package com.youngman.mop.view.clubs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.data.source.clubs.ClubsRepository;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.club.ClubActivity;
import com.youngman.mop.view.clubs.adapter.ClubsAdapter;
import com.youngman.mop.view.clubs.presenter.ClubsContract;
import com.youngman.mop.view.clubs.presenter.ClubsPresenter;
import com.youngman.mop.view.clubsearch.ClubSearchActivity;

public class ClubsActivity extends AppCompatActivity implements ClubsContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private EditText etSearchClubs;
    private ClubsAdapter clubsAdapter;
    private ClubsContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etSearchClubs = (EditText) findViewById(R.id.et_search_clubs);
        recyclerView = (RecyclerView) findViewById(R.id.rv_clubs);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        clubsAdapter = new ClubsAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        clubsAdapter.setLinearLayoutManager(linearLayoutManager);
        clubsAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(clubsAdapter);

        presenter = new ClubsPresenter(this, ClubsRepository.getInstance());
        presenter.setClubsAdapterView(clubsAdapter);
        presenter.setClubsAdapterModel(clubsAdapter);
        presenter.callClubsByUserInfo(PrefUtils.readUserIdFromPref(context), 1);

        etSearchClubs.setOnClickListener(view -> startClubSearchActivity());
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            presenter.callClubsByUserInfo(PrefUtils.readUserIdFromPref(context), calculatePageNo());
        }, 500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = clubsAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }

    @Override
    public void successCreateMyClub() {
        ToastUtils.showToast(context, "마이 동호회 저장 성공");
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startClubActivity(Long clubId) {
        Intent intent = new Intent(context, ClubActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    private void startClubSearchActivity() {
        Intent intent = new Intent(context, ClubSearchActivity.class);
        startActivity(intent);
    }
}
