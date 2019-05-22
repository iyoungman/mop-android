package com.youngman.mop.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.listener.OnClubListItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView ivClubImg;
    private TextView tvClubName;
    private TextView tvClubRegion;
    private TextView tvClubHobby;

    private OnClubListItemClickListener onClubListItemClickListener;

    public ClubListViewHolder(@NonNull Context context,
                              @NonNull ViewGroup parent,
                              @NonNull OnClubListItemClickListener onClubListItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_clublist, parent, false));
        this.context = context;
        this.ivClubImg = itemView.findViewById(R.id.iv_club_img);
        this.tvClubName = itemView.findViewById(R.id.tv_club_name);
        this.tvClubRegion = itemView.findViewById(R.id.tv_club_region);
        this.tvClubHobby = itemView.findViewById(R.id.tv_club_hobby);
        this.onClubListItemClickListener = onClubListItemClickListener;
    }

    public void onBind(@NonNull ClubDto clubDto, @NonNull final Integer position) {

//        ivClub
        tvClubName.setText(clubDto.getName());
        tvClubRegion.setText(clubDto.getRegion());
        tvClubHobby.setText(clubDto.getHobby());

        itemView.setOnClickListener(view -> {
            onClubListItemClickListener.onStartClubClick(position);
        });
    }

}
