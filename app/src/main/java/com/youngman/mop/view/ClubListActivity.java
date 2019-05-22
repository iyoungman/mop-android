package com.youngman.mop.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.youngman.mop.R;
import com.youngman.mop.adapter.ClubListAdapter;
import com.youngman.mop.contract.ClubListContract;
import com.youngman.mop.presenter.ClubListPresenter;
import com.youngman.mop.util.EmptyCheckUtils;
import com.youngman.mop.util.SignUtils;
import com.youngman.mop.util.ToastUtils;

public class ClubListActivity extends AppCompatActivity implements ClubListContract.View {

    private Context context;
    private EditText etSearchClubList;
    private Button btnSearchClubList;
    private RecyclerView recyclerView;
    private ClubListAdapter clubListAdapter;

    ClubListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clublist);
        initView();
        presenter = new ClubListPresenter(this);
        presenter.callClubListByUserInfo(SignUtils.readUserIdFromPref(context));
    }

    private void initView() {
        context = getApplicationContext();
        etSearchClubList = (EditText) findViewById(R.id.et_search_clublist);
        btnSearchClubList = (Button) findViewById(R.id.btn_search_clublist);
        recyclerView = (RecyclerView) findViewById(R.id.rv_clublist);

        clubListAdapter = new ClubListAdapter(context);
        recyclerView.setAdapter(clubListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        btnSearchClubList.setOnClickListener(view -> {
            if (isNotEmpty())
                presenter.callClubListBySearch(etSearchClubList.getText().toString());
        });
    }

    private boolean isNotEmpty() {
        if (EmptyCheckUtils.isEmpty(etSearchClubList.getText().toString())) {
            showErrorMessage("검색어를 입력해주세요");
            return false;
        }
        return true;
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }
}
