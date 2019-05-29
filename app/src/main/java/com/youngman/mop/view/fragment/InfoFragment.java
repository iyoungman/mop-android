package com.youngman.mop.view.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.contract.InfoContract;
import com.youngman.mop.model.dto.ClubDto;
import com.youngman.mop.model.dto.InfoDto;
import com.youngman.mop.model.dto.MemberDto;
import com.youngman.mop.presenter.InfoPresenter;
import com.youngman.mop.util.ToastUtils;

import java.util.List;

public class InfoFragment extends Fragment implements InfoContract.View {

    private Context context;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubIntroduce;
    private TextView tvClubCreateDate;
    private TextView tvClubRegion;
    private TextView tvClubHobby;

    InfoContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);
        presenter = new InfoPresenter(this);
        initView(view);

        return view;
    }

    private void initView(View view) {
        context = view.getContext();
        ivClubImg = (ImageView) view.findViewById(R.id.iv_club_img);
        tvClubName = (TextView) view.findViewById(R.id.tv_club_name);
        tvClubIntroduce = (TextView) view.findViewById(R.id.tv_club_introduce);
        tvClubCreateDate = (TextView) view.findViewById(R.id.tv_club_create_date);
        tvClubRegion = (TextView) view.findViewById(R.id.tv_club_region);
        tvClubHobby = (TextView) view.findViewById(R.id.tv_club_hobby);
    }

    @Override
    public void setClubInfo(InfoDto infoDto) {

        ClubDto clubDto = infoDto.getClubDto();
        tvClubName.setText(clubDto.getName());
        tvClubIntroduce.setText(clubDto.getIntroduce());
        tvClubCreateDate.setText(clubDto.getCreateDate());
        tvClubRegion.setText(clubDto.getRegion());
        tvClubHobby.setText(clubDto.getHobby());

        List<MemberDto> memberDtos = infoDto.getMemberDtoList();

    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        ToastUtils.showToast(context, message);
    }
}
