package com.youngman.mop.view.activity;

import android.content.Context;
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

    private InfoFragment infoFragment;
    private MapFragment mapFragment;
    private ScheduleFragment scheduleFragment;
    private BoardFragment boardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        initView();

        ivTabInfo.performClick();
    }

    private void initView() {
        context = getApplicationContext();
        ivTabInfo = (ImageView) findViewById(R.id.iv_tab_info);
        ivTabMap = (ImageView) findViewById(R.id.iv_tab_map);
        ivTabSchedule = (ImageView) findViewById(R.id.iv_tab_schedule);
        ivTabBoard = (ImageView) findViewById(R.id.iv_tab_board);

        infoFragment = new InfoFragment();
        mapFragment = new MapFragment();
        scheduleFragment = new ScheduleFragment();
        boardFragment = new BoardFragment();

        ivTabInfo.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, infoFragment).commit();
        });

        ivTabMap.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, mapFragment).commit();
        });

        ivTabSchedule.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, scheduleFragment).commit();
        });

        ivTabBoard.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, boardFragment).commit();
        });
    }


}
