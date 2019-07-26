package com.youngman.mop.view.mapmemberadd;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.LinearLayout;

import com.youngman.mop.R;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.mapmemberadd.adapter.MapMemberAddAdapter;
import com.youngman.mop.view.mapmemberadd.presenter.MapMemberAddContract;
import com.youngman.mop.view.mapmemberadd.presenter.MapMemberAddPresenter;

public class MapMemberAddActivity extends Activity implements MapMemberAddContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayout llInviteMembers;
    private MapMemberAddAdapter mapMemberAddAdapter;
    private MapMemberAddContract.Presenter presenter;

    private Long scheduleId = 2L;
    private Long clubId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_map_member_add);
        init();
    }

    private void init() {
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.rv_map_add_members);
        llInviteMembers = (LinearLayout) findViewById(R.id.ll_invite_members);

        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", -1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mapMemberAddAdapter = new MapMemberAddAdapter(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        mapMemberAddAdapter.setLinearLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mapMemberAddAdapter);

        presenter = new MapMemberAddPresenter(this);
        presenter.setMapMemberAddAdapterModel(mapMemberAddAdapter);
        presenter.setMapMemberAddAdapterView(mapMemberAddAdapter);
        presenter.callParticipants(scheduleId, clubId);

        llInviteMembers.setOnClickListener(v -> inviteSelectedMembers());
    }

    private void inviteSelectedMembers() {

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }
}
