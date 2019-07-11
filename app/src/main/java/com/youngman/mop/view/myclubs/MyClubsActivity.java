package com.youngman.mop.view.myclubs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.youngman.mop.R;
import com.youngman.mop.data.source.myclubs.MyClubsRepository;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.club.ClubActivity;
import com.youngman.mop.view.clubs.ClubsActivity;
import com.youngman.mop.view.myclubs.adapter.MyClubsAdapter;
import com.youngman.mop.view.myclubs.presenter.MyClubsContract;
import com.youngman.mop.view.myclubs.presenter.MyClubsPresenter;

public class MyClubsActivity extends AppCompatActivity implements MyClubsContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private Button btnStartClubList;
    private MyClubsAdapter myClubsAdapter;
    private MyClubsContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclubs);
        init();
    }

    private void init() {
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_myclubs);
        btnStartClubList = (Button) findViewById(R.id.btn_start_clubs);

        myClubsAdapter = new MyClubsAdapter(context);
        recyclerView.setAdapter(myClubsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        presenter = new MyClubsPresenter(this, MyClubsRepository.getInstance());
        presenter.setMyClubAdapterView(myClubsAdapter);
        presenter.setMyClubAdapterModel(myClubsAdapter);
        presenter.callMyClubList(SignUtils.readUserIdFromPref(context));

        btnStartClubList.setOnClickListener(view -> {
            startClubsActivity();
        });
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    public void startClubsActivity() {
        Intent intent = new Intent(context, ClubsActivity.class);
        startActivity(intent);
    }

    @Override
    public void startClubActivity(Long clubId, String clubName) {
        Intent intent = new Intent(context, ClubActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        intent.putExtra("EXTRA_CLUB_NAME", clubName);
        startActivity(intent);
    }
}
