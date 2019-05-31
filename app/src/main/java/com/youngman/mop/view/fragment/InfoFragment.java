package com.youngman.mop.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.adapter.ClubListAdapter;
import com.youngman.mop.adapter.MemberListAdapter;
import com.youngman.mop.adapter.MyClubListAdapter;
import com.youngman.mop.contract.InfoContract;
import com.youngman.mop.model.dto.ClubDto;
import com.youngman.mop.model.dto.MemberDto;
import com.youngman.mop.presenter.InfoPresenter;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.activity.MemberInfoActivity;

public class InfoFragment extends Fragment implements InfoContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubIntroduce;
    private TextView tvClubCreateDate;
    private TextView tvClubRegion;
    private TextView tvClubHobby;
    private MemberListAdapter memberListAdapter;

    InfoContract.Presenter presenter;


    public static InfoFragment createFragment(Long clubId) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        presenter = new InfoPresenter(this);
        presenter.setMemberListAdapterView(memberListAdapter);
        presenter.setMemberListAdapterModel(memberListAdapter);
        presenter.callClubInfoByClubId(getArguments().getLong("EXTRA_CLUB_ID"));

        return view;
    }

    private void initView(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_members);
        ivClubImg = (ImageView) view.findViewById(R.id.iv_club_img);
        tvClubName = (TextView) view.findViewById(R.id.tv_club_name);
        tvClubIntroduce = (TextView) view.findViewById(R.id.tv_club_introduce);
        tvClubCreateDate = (TextView) view.findViewById(R.id.tv_club_create_date);
        tvClubRegion = (TextView) view.findViewById(R.id.tv_club_region);
        tvClubHobby = (TextView) view.findViewById(R.id.tv_club_hobby);

        memberListAdapter = new MemberListAdapter(context);
        recyclerView.setAdapter(memberListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void setClubInfo(ClubDto clubDto) {
        tvClubName.setText(clubDto.getName());
//        tvClubIntroduce.setText(clubDto.getIntroduce());
        tvClubCreateDate.setText(clubDto.getCreateDate());
        tvClubRegion.setText(clubDto.getRegion());
//        tvClubHobby.setText(clubDto.getHobby());
    }

    @Override
    public void startMemberInfoActivity(@NonNull MemberDto memberDto) {
        Intent intent = new Intent(context, MemberInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }
}
