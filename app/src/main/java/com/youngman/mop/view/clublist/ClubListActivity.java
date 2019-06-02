package com.youngman.mop.view.clublist;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.view.clublist.adapter.ClubListAdapter;
import com.youngman.mop.view.clublist.presenter.ClubListContract;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.view.clublist.presenter.ClubListPresenter;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.clubsearch.ClubSearchActivity;
import com.youngman.mop.view.ClubActivity;

public class ClubListActivity extends AppCompatActivity implements ClubListContract.View, OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private EditText etSearchClubList;
    private Button btnSearchClubList;
    private ClubListAdapter clubListAdapter;
    private ClubListContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);
        initView();
        presenter = new ClubListPresenter(this);
        presenter.setClubListAdapterView(clubListAdapter);
        presenter.setClubListAdapterModel(clubListAdapter);
        presenter.callClubListByUserInfo(SignUtils.readUserIdFromPref(context), 1);
    }

    private void initView() {
        context = getApplicationContext();
        etSearchClubList = (EditText) findViewById(R.id.et_search_clublist);
        btnSearchClubList = (Button) findViewById(R.id.btn_search_clublist);
        recyclerView = (RecyclerView) findViewById(R.id.rv_clublist);
        initMoreRecyclerView();

        etSearchClubList.setOnClickListener(view -> {
            startClubSearchActivity();
        });

        /*btnSearchClubList.setOnClickListener(view -> {
            if (isNotEmpty())
                presenter.callPagingClubsBySearch(etSearchClubList.getText().toString());
        });*/
    }

    private void initMoreRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        clubListAdapter = new ClubListAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        clubListAdapter.setLinearLayoutManager(linearLayoutManager);
        clubListAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(clubListAdapter);
    }

    /*private boolean isNotEmpty() {
        if (EmptyCheckUtils.isEmpty(etSearchClubList.getText().toString())) {
            showErrorMessage("검색어를 입력해주세요");
            return false;
        }
        return true;
    }*/

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            presenter.callClubListByUserInfo(SignUtils.readUserIdFromPref(context), calculatePageNo());
        }, 500);
    }

    private int calculatePageNo() {
        final int pageSize = 24;
        int itemCount = clubListAdapter.getItemCount();
        return (itemCount == 0) ? 0 : (itemCount / pageSize) + 1;
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startClubActivity(@NonNull Long clubId) {
        Intent intent = new Intent(context, ClubActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    private void startClubSearchActivity() {
        Intent intent = new Intent(context, ClubSearchActivity.class);
        startActivity(intent);
    }
}
