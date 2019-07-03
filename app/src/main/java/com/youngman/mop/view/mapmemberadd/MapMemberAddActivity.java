package com.youngman.mop.view.mapmemberadd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youngman.mop.R;

public class MapMemberAddActivity extends AppCompatActivity {

    private Long clubId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_member_add);
        init();

    }

    private void init() {
        clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 0);
    }
}
