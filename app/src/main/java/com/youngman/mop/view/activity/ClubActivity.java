package com.youngman.mop.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.youngman.mop.R;
import com.youngman.mop.view.fragment.BoardFragment;
import com.youngman.mop.view.fragment.InfoFragment;
import com.youngman.mop.view.fragment.MapFragment;
import com.youngman.mop.view.fragment.ScheduleFragment;

public class ClubActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivTabInfo, ivTabMap, ivTabSchedule, ivTabBoard;
    private Long clubId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 0);
        initView();

        ivTabInfo.performClick();
    }

    private void initView() {
        context = getApplicationContext();
        ivTabInfo = (ImageView) findViewById(R.id.iv_tab_info);
        ivTabMap = (ImageView) findViewById(R.id.iv_tab_map);
        ivTabSchedule = (ImageView) findViewById(R.id.iv_tab_schedule);
        ivTabBoard = (ImageView) findViewById(R.id.iv_tab_board);

        ivTabInfo.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, InfoFragment.createFragment(clubId))
                    .commit();
        });

        ivTabMap.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, MapFragment.createFragment(clubId))
                    .commit();
        });

        ivTabSchedule.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ScheduleFragment.createFragment())
                    .commit();
        });

        ivTabBoard.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.ll_fragment_container, BoardFragment.createFragment())
                    .commit();
        });
    }


}
