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
import com.youngman.mop.listener.OnClubListItemClickListener;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubRegion;
    private TextView tvClubHobby;

    private OnClubListItemClickListener onClubListItemClickListener;


    public ClubsViewHolder(@NonNull Context context,
                           @NonNull ViewGroup parent,
                           @NonNull OnClubListItemClickListener onClubListItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_clubs, parent, false));
        this.context = context;
        this.ivClubImg = itemView.findViewById(R.id.iv_club_img);
        this.tvClubName = itemView.findViewById(R.id.tv_club_name);
        this.tvClubRegion = itemView.findViewById(R.id.tv_club_region);
        this.tvClubHobby = itemView.findViewById(R.id.tv_club_hobby);
        this.onClubListItemClickListener = onClubListItemClickListener;
    }

    public void onBind(@NonNull Club club, @NonNull final Integer position) {

//        ivClub
        tvClubName.setText(club.getName());
        tvClubRegion.setText(club.getRegion());
        tvClubHobby.setText(club.getHobby());

        itemView.setOnClickListener(view -> {
            onClubListItemClickListener.onStartClubClick(position);
        });
    }

}
