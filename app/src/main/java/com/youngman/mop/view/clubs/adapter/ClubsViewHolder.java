package com.youngman.mop.view.clubs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youngman.mop.R;
import com.youngman.mop.data.Club;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubRegion;
    private TextView tvClubHobby;
    private TextView tvClubIntroduce;
    private TextView tvClubJoin;
    private LinearLayout llClubInfo;
    private LinearLayout llClubJoin;
    private OnClubsItemClickListener onClubsItemClickListener;


    public ClubsViewHolder(Context context,
                           ViewGroup parent,
                           OnClubsItemClickListener onClubsItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_clubs, parent, false));
        this.context = context;
        this.ivClubImg = itemView.findViewById(R.id.iv_club_img);
        this.tvClubName = itemView.findViewById(R.id.tv_club_name);
        this.tvClubRegion = itemView.findViewById(R.id.tv_club_region);
        this.tvClubHobby = itemView.findViewById(R.id.tv_club_hobby);
        this.tvClubIntroduce = itemView.findViewById(R.id.tv_club_introduce);
        this.tvClubJoin = itemView.findViewById(R.id.tv_club_join);
        this.llClubInfo = itemView.findViewById(R.id.ll_club_info);
        this.llClubJoin = itemView.findViewById(R.id.ll_club_join);
        this.onClubsItemClickListener = onClubsItemClickListener;
    }

    public void onBind(Club club, int position) {
        setClubImage(club.getImageUri());
        tvClubName.setText(club.getName());
        tvClubRegion.setText(club.getRegion());
        tvClubHobby.setText(club.getHobby());
        tvClubIntroduce.setText(club.getIntroduce());

        llClubInfo.setOnClickListener(v -> isPossibleStart(club.getClubId()));
        llClubJoin.setOnClickListener(v -> isPossibleJoin(club.getClubId()));
    }

    private void setClubImage(String imageUri) {
        if (imageUri != null) {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true);

            Glide.with(context)
                    .load(imageUri)
                    .apply(myOptions)
                    .into(ivClubImg);
        }
    }

    private void isPossibleStart(Long clubId) {
        List<Long> myClubIds = PrefUtils.readMyClubIdsFromPref(context);

        if (!myClubIds.contains(clubId)) {
            ToastUtils.showToast(context, "동호회의 회원이 아닙니다");
            return;
        }
        onClubsItemClickListener.onStartClubClick(clubId);
    }

    private void isPossibleJoin(Long clubId) {
        List<Long> myClubIds = PrefUtils.readMyClubIdsFromPref(context);

        if (myClubIds.contains(clubId)) {
            ToastUtils.showToast(context, "이미 가입된 동호회 입니다");
            return;
        }
        String email = PrefUtils.readMemberEmailFromPref(context);
        onClubsItemClickListener.onJoinClubClick(email, clubId);
    }

}
