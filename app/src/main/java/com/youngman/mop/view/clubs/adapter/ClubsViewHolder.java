package com.youngman.mop.view.clubs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnClubsItemClickListener;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubRegion;
    private TextView tvClubHobby;

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
        this.onClubsItemClickListener = onClubsItemClickListener;
    }

    public void onBind(Club club, int position) {
        tvClubName.setText(club.getName());
        tvClubRegion.setText(club.getRegion());
        tvClubHobby.setText(club.getHobby());//        ivClub

        itemView.setOnClickListener(view -> {
            onClubsItemClickListener.onStartClubClick(position);
        });
    }

}
