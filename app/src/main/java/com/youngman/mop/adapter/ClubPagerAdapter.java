package com.youngman.mop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-27.
 */

public class ClubPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> clubFragments = new ArrayList<>();

    public ClubPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return clubFragments.get(position);
    }

    @Override
    public int getCount() {
        return clubFragments.size();
    }

    public void addFragment(Fragment fragment){
        clubFragments.add(fragment);
    }
}
