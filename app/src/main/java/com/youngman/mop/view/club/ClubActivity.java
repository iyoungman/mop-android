package com.youngman.mop.view.club;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.board.BoardFragment;
import com.youngman.mop.view.club.presenter.ClubContract;
import com.youngman.mop.view.club.presenter.ClubPresenter;
import com.youngman.mop.view.clubinfo.ClubInfoFragment;
import com.youngman.mop.view.map.MapActivity;
import com.youngman.mop.view.schedule.ScheduleFragment;

public class ClubActivity extends AppCompatActivity implements ClubContract.View, NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private Toolbar tbClub;
    private ImageView ivSideMenu;
    private TextView tvClubName;
    private LinearLayout llMenuInfo;
    private LinearLayout llMenuSchedule;
    private LinearLayout llMenuBoard;
    private LinearLayout llMenuMap;
    private ImageView ivMenuInfo;
    private ImageView ivMenuSchedule;
    private ImageView ivMenuBoard;
    private DrawerLayout dlSideMenu;
    private ClubContract.Presenter presenter;

    private Long clubId;
    private boolean isClubChair;

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
        ivSideMenu = (ImageView) findViewById(R.id.iv_side_menu);
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
        dlSideMenu = (DrawerLayout) findViewById(R.id.dl_side_menu);

        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", -1);
        String clubName = getIntent().getStringExtra("EXTRA_CLUB_NAME");
        tvClubName.setText(clubName);

        presenter = new ClubPresenter(this);
        presenter.callIsClubChair(clubId, PrefUtils.readMemberEmailFrom(context));

        ivSideMenu.setOnClickListener(v -> dlSideMenu.openDrawer(GravityCompat.START));

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

        llMenuMap.setOnClickListener(view -> startMapActivity());

        llMenuInfo.performClick();
    }

    @Override
    public void setIsClubChair(boolean isClubChair) {
        this.isClubChair = isClubChair;
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
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

    private void startMapActivity() {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("EXTRA_CLUB_ID", clubId);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_add_map_member:
                startSideMenuBy("MapMemberAddActivity");

            case R.id.menu_club_statistics:
                startSideMenuBy("ClubStatisticsActivity");
        }
        dlSideMenu.closeDrawer(GravityCompat.START);
        return false;
    }

    private void startSideMenuBy(String activityName) {
        if (!isClubChair) {
            ToastUtils.showToast(context, "동호회장 권한이 없습니다");
            return;
        }
        try {
            Class<?> cls = Class.forName(activityName);
            Intent intent = new Intent(context, cls);
            intent.putExtra("EXTRA_CLUB_ID", clubId);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            ToastUtils.showToast(context, "Side Menu 시작에 실패하였습니다");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BusProvider.getInstance().post(new ActivityResultEvent(requestCode, resultCode, data));
    }

}
