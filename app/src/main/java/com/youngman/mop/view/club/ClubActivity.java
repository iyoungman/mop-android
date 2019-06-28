package com.youngman.mop.view.club;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.youngman.mop.R;
import com.youngman.mop.view.board.BoardFragment;
import com.youngman.mop.view.clubinfo.ClubInfoFragment;
import com.youngman.mop.view.schedule.ScheduleFragment;

public class ClubActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivTabInfo, ivTabSchedule, ivTabBoard;
    private Long clubId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 0);
        init();

        ivTabInfo.performClick();
    }

    private void init() {
        context = getApplicationContext();
        ivTabInfo = (ImageView) findViewById(R.id.iv_tab_info);
        ivTabSchedule = (ImageView) findViewById(R.id.iv_tab_schedule);
        ivTabBoard = (ImageView) findViewById(R.id.iv_tab_board);

        ivTabInfo.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ClubInfoFragment.createFragment(clubId))
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
