package com.youngman.mop.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.youngman.mop.R;
import com.youngman.mop.adapter.MyClubAdapter;
import com.youngman.mop.contract.MyClubContract;
import com.youngman.mop.model.domain.MyClubModel;
import com.youngman.mop.model.dto.MyClubDto;
import com.youngman.mop.presenter.MyClubPresenter;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;

public class MyClubActivity extends AppCompatActivity implements MyClubContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private MyClubAdapter myClubAdapter;

    MyClubContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclub);
        initView();
        presenter = new MyClubPresenter(this);
        presenter.setMyClubAdapterView(myClubAdapter);
        presenter.setMyClubAdapterModel(myClubAdapter);
        presenter.callMyClubList(SignUtils.readUserIdFromPref(context));
    }

    private void initView() {
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_myclub);

        myClubAdapter = new MyClubAdapter(context);
        recyclerView.setAdapter(myClubAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startClubListActivity() {
        //동호회 목록으로 이동
    }

    @Override
    public void startClubActivity(@NonNull String clubId) {
        Intent intent = new Intent(context, ClubActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }
}
