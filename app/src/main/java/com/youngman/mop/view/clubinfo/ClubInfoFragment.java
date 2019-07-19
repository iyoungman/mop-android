package com.youngman.mop.view.clubinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.otto.Subscribe;
import com.youngman.mop.R;
import com.youngman.mop.data.Club;
import com.youngman.mop.data.Member;
import com.youngman.mop.data.source.clubinfo.ClubInfoRepository;
import com.youngman.mop.lib.otto.ActivityResultEvent;
import com.youngman.mop.lib.otto.BusProvider;
import com.youngman.mop.util.CameraUtils;
import com.youngman.mop.util.LogUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.clubinfo.adapter.MembersAdapter;
import com.youngman.mop.view.clubinfo.presenter.ClubInfoContract;
import com.youngman.mop.view.clubinfo.presenter.ClubInfoPresenter;
import com.youngman.mop.view.memberinfo.MemberInfoActivity;

import java.io.File;

public class ClubInfoFragment extends Fragment implements ClubInfoContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private ImageView ivClubImg;
    private TextView tvClubIntroduce;
    private TextView tvClubCreateDate;
    private TextView tvClubRegion;
    private TextView tvClubHobby;
    private MembersAdapter membersAdapter;
    private ClubInfoContract.Presenter presenter;

    private Long clubId;


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
        tvClubIntroduce = (TextView) view.findViewById(R.id.tv_club_introduce);
        tvClubCreateDate = (TextView) view.findViewById(R.id.tv_club_create_date);
        tvClubRegion = (TextView) view.findViewById(R.id.tv_club_region);
        tvClubHobby = (TextView) view.findViewById(R.id.tv_club_hobby);

        clubId = getArguments().getLong("EXTRA_CLUB_ID");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        membersAdapter = new MembersAdapter(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        recyclerView.setAdapter(membersAdapter);

        presenter = new ClubInfoPresenter(this, ClubInfoRepository.getInstance());
        presenter.setMembersAdapterView(membersAdapter);
        presenter.setMembersAdapterModel(membersAdapter);
        presenter.callClubInfoByClubId(clubId);

        ivClubImg.setOnLongClickListener(v -> startToAlbum());

        BusProvider.getInstance().register(this);
    }

    @Override
    public void setClubInfo(Club club) {
        tvClubIntroduce.setText(club.getIntroduce());
        tvClubCreateDate.setText(club.getCreateDate());
        tvClubRegion.setText(club.getRegion());
        tvClubHobby.setText(club.getHobby());
    }

    private boolean startToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        getActivity().startActivityForResult(intent, 2222);
        return true;
    }

    @Subscribe
    public void onActivityResult(ActivityResultEvent activityResultEvent) {
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
        if (activityResultEvent.getRequestCode() == 2222 && activityResultEvent.getData() != null) {
            Uri imageUri = activityResultEvent.getData().getData();
            File imageFile = CameraUtils.getImageFromAlbum(context.getContentResolver(), imageUri);
            presenter.callUploadClubImage(clubId, imageFile);
        }
    }

    @Override
    public void setClubImage(String imageUri) {
        if (imageUri != null && getContext() != null) {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true);

            Glide.with(getContext())
                    .load(imageUri)
                    .apply(myOptions)
                    .into(ivClubImg);
        }
    }

    @Override
    public void startMemberInfoActivity(Member member) {
        Intent intent = new Intent(context, MemberInfoActivity.class);
        intent.putExtra("EXTRA_MEMBER", member);
        getActivity().startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }
}
