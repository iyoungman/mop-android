package com.youngman.mop.view.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youngman.mop.R;

public class MapFragment extends Fragment {

    public static InfoFragment createFragment() {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
//        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

        return view;
    }
}
