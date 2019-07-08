package com.youngman.mop.view.club;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youngman.mop.R;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.view.board.BoardFragment;
import com.youngman.mop.view.clubinfo.ClubInfoFragment;
import com.youngman.mop.view.map.MapActivity;
import com.youngman.mop.view.schedule.ScheduleFragment;
import com.youngman.mop.view.signup.SignUpActivity;

public class ClubActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout llMenuInfo;
    private LinearLayout llMenuSchedule;
    private LinearLayout llMenuBoard;
    private LinearLayout llMenuMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        init();
    }

    private void init() {
        context = getApplicationContext();
        llMenuInfo = (LinearLayout) findViewById(R.id.ll_menu_info);
        llMenuSchedule = (LinearLayout) findViewById(R.id.ll_menu_schedule);
        llMenuBoard = (LinearLayout) findViewById(R.id.ll_menu_board);
        llMenuMap = (LinearLayout) findViewById(R.id.ll_menu_map);

        Long clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 1);

        llMenuInfo.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ClubInfoFragment.createFragment(clubId))
                    .commit();
        });

        llMenuSchedule.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ScheduleFragment.createFragment(clubId))
                    .commit();
        });

        llMenuBoard.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.ll_fragment_container, BoardFragment.createFragment())
                    .commit();
        });

        llMenuMap.setOnClickListener(view -> {
            startMapActivity(clubId);
        });

        llMenuInfo.performClick();
    }

    private void startMapActivity(Long clubId) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BusProvider.getInstance().post(new ActivityResultEvent(requestCode, resultCode, data));
    }

}
