package com.youngman.mop.view.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youngman.mop.R;

public class ScheduleFragment extends Fragment {

    public static ScheduleFragment createFragment() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
//        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_schedule, container, false);

        return view;
    }
}
