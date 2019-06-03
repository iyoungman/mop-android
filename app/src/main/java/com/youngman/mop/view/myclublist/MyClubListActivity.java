package com.youngman.mop.view.myclublist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.youngman.mop.R;
import com.youngman.mop.view.myclublist.adapter.MyClubListAdapter;
import com.youngman.mop.view.myclublist.presenter.MyClubListContract;
import com.youngman.mop.view.myclublist.presenter.MyClubListPresenter;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.club.ClubActivity;
import com.youngman.mop.view.clublist.ClubListActivity;

public class MyClubListActivity extends AppCompatActivity implements MyClubListContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private Button btnStartClubList;
    private MyClubListAdapter myClubListAdapter;
    private MyClubListContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclub_list);
        initView();
        presenter = new MyClubListPresenter(this);
        presenter.setMyClubAdapterView(myClubListAdapter);
        presenter.setMyClubAdapterModel(myClubListAdapter);
        presenter.callMyClubList(SignUtils.readUserIdFromPref(context));
    }

    private void initView() {
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_myclublist);
        btnStartClubList = (Button) findViewById(R.id.btn_start_clublist);

        myClubListAdapter = new MyClubListAdapter(context);
        recyclerView.setAdapter(myClubListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        btnStartClubList.setOnClickListener(view -> {
            startClubListActivity();
        });
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    public void startClubListActivity() {
        Intent intent = new Intent(context, ClubListActivity.class);
        startActivity(intent);
    }

    @Override
    public void startClubActivity(@NonNull Long clubId) {
        Intent intent = new Intent(context, ClubActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }
}
