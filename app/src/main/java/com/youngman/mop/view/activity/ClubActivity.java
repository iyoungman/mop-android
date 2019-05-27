package com.youngman.mop.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youngman.mop.R;
import com.youngman.mop.adapter.ClubPagerAdapter;
import com.youngman.mop.view.CustomViewPager;

public class ClubActivity extends AppCompatActivity {

    private Context context;
    private CustomViewPager customViewPager;
    private ClubPagerAdapter clubPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
//        initView();
    }

    /*private void initView() {
        ClubPagerAdapter adapter = new ClubPagerAdapter(getChildFragmentManager());
        Fragment fragment1 = EmptyFragment.newInstance("this is page 1");
        Fragment fragment2 = EmptyFragment.newInstance("this is page 2");
        presenter1.setxxx(fragment1);
        presenter2.setxxx(fragment2);
        adapter.addFragement(fragment1);
        adapter.addFragement(fragment2);
        viewPager.setAdapter(adapter);
    }*/
}
