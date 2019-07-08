package com.youngman.mop.view.clubinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Club;
import com.youngman.mop.data.Member;
import com.youngman.mop.data.source.clubinfo.ClubInfoRepository;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapter;
import com.youngman.mop.view.clubinfo.presenter.ClubInfoContract;
import com.youngman.mop.view.clubinfo.presenter.ClubInfoPresenter;
import com.youngman.mop.view.memberinfo.MemberInfoActivity;

public class ClubInfoFragment extends Fragment implements ClubInfoContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubIntroduce;
    private TextView tvClubCreateDate;
    private TextView tvClubRegion;
    private TextView tvClubHobby;
    private MembersAdapter membersAdapter;
    private ClubInfoContract.Presenter presenter;


    public static ClubInfoFragment createFragment(Long clubId) {
        ClubInfoFragment fragment = new ClubInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("EXTRA_CLUB_ID", clubId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_club_info, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_members);
        ivClubImg = (ImageView) view.findViewById(R.id.iv_club_img);
        tvClubName = (TextView) view.findViewById(R.id.tv_club_name);
        tvClubIntroduce = (TextView) view.findViewById(R.id.tv_club_introduce);
        tvClubCreateDate = (TextView) view.findViewById(R.id.tv_club_create_date);
        tvClubRegion = (TextView) view.findViewById(R.id.tv_club_region);
        tvClubHobby = (TextView) view.findViewById(R.id.tv_club_hobby);

        membersAdapter = new MembersAdapter(context);
        recyclerView.setAdapter(membersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        presenter = new ClubInfoPresenter(this, ClubInfoRepository.getInstance());
        presenter.setMembersAdapterView(membersAdapter);
        presenter.setMembersAdapterModel(membersAdapter);
        presenter.callClubInfoByClubId(getArguments().getLong("EXTRA_CLUB_ID"));
    }

    @Override
    public void setClubInfo(Club club) {
        tvClubName.setText(club.getName());
        tvClubIntroduce.setText(club.getIntroduce());
        tvClubCreateDate.setText(club.getCreateDate());
        tvClubRegion.setText(club.getRegion());
        tvClubHobby.setText(club.getHobby());
    }

    @Override
    public void startMemberInfoActivity(Member member) {
        Intent intent = new Intent(context, MemberInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }
}
