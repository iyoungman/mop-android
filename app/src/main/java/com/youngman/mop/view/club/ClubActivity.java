package com.youngman.mop.view.club;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.view.board.BoardFragment;
import com.youngman.mop.view.clubinfo.ClubInfoFragment;
import com.youngman.mop.view.map.MapActivity;
import com.youngman.mop.view.schedule.ScheduleFragment;

public class ClubActivity extends AppCompatActivity {

    private Context context;
    private Toolbar tbClub;
    private TextView tvClubName;
    private LinearLayout llMenuInfo;
    private LinearLayout llMenuSchedule;
    private LinearLayout llMenuBoard;
    private LinearLayout llMenuMap;
    private ImageView ivMenuInfo;
    private ImageView ivMenuSchedule;
    private ImageView ivMenuBoard;

    private static final String[] MENU_STRINGS = {
            "INFO", "SCHEDULE", "BOARD"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        init();
    }

    private void init() {
        context = getApplicationContext();
        tbClub = (Toolbar) findViewById(R.id.tb_club);
        setSupportActionBar(tbClub);
        tvClubName = (TextView) tbClub.findViewById(R.id.tv_club_name);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        llMenuInfo = (LinearLayout) findViewById(R.id.ll_menu_info);
        llMenuSchedule = (LinearLayout) findViewById(R.id.ll_menu_schedule);
        llMenuBoard = (LinearLayout) findViewById(R.id.ll_menu_board);
        llMenuMap = (LinearLayout) findViewById(R.id.ll_menu_map);
        ivMenuInfo = (ImageView) findViewById(R.id.iv_menu_info);
        ivMenuSchedule = (ImageView) findViewById(R.id.iv_menu_schedule);
        ivMenuBoard = (ImageView) findViewById(R.id.iv_menu_board);

        Long clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 1);
        String clubName = getIntent().getStringExtra("EXTRA_CLUB_NAME");
        tvClubName.setText(clubName);

        llMenuInfo.setOnClickListener(view -> {
            convertSelectedMenu(MENU_STRINGS[0]);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ClubInfoFragment.createFragment(clubId))
                    .commit();
        });

        llMenuSchedule.setOnClickListener(view -> {
            convertSelectedMenu(MENU_STRINGS[1]);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_fragment_container, ScheduleFragment.createFragment(clubId))
                    .commit();
        });

        llMenuBoard.setOnClickListener(view -> {
            convertSelectedMenu(MENU_STRINGS[2]);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.ll_fragment_container, BoardFragment.createFragment(clubId))
                    .commit();
        });

        llMenuMap.setOnClickListener(view -> {
            startMapActivity(clubId);
        });

        llMenuInfo.performClick();
    }

    private void convertSelectedMenu(String menu) {
        switch (menu) {
            case "INFO":
                ivMenuInfo.setImageResource(R.drawable.img_club_info_selected);
                ivMenuSchedule.setImageResource(R.drawable.img_club_schedule);
                ivMenuBoard.setImageResource(R.drawable.img_club_board);
                break;
            case "SCHEDULE":
                ivMenuInfo.setImageResource(R.drawable.img_club_info);
                ivMenuSchedule.setImageResource(R.drawable.img_club_schedule_selected);
                ivMenuBoard.setImageResource(R.drawable.img_club_board);
                break;
            case "BOARD":
                ivMenuInfo.setImageResource(R.drawable.img_club_info);
                ivMenuSchedule.setImageResource(R.drawable.img_club_schedule);
                ivMenuBoard.setImageResource(R.drawable.img_club_board_selected);
                break;
        }
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
