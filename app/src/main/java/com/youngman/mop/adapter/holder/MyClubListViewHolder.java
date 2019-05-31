package com.youngman.mop.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;
import com.youngman.mop.util.SignUtils;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubListViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvMyClubName;
    private Button btnMyClubDelete;
    private ImageView ivMyClub;
    private TextView tvMyClubRecentSchedule;

    private OnMyClubItemClickListener onMyClubItemClickListener;

    public MyClubListViewHolder(@NonNull Context context,
                                @NonNull ViewGroup parent,
                                @NonNull OnMyClubItemClickListener onMyClubItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_myclub_list, parent, false));
        this.context = context;
        this.tvMyClubName = itemView.findViewById(R.id.tv_myclub_name);
        this.btnMyClubDelete = itemView.findViewById(R.id.btn_myclub_delete);
        this.ivMyClub = itemView.findViewById(R.id.iv_myclub);
        this.tvMyClubRecentSchedule = itemView.findViewById(R.id.tv_myclub_recent_schedule);
        this.onMyClubItemClickListener = onMyClubItemClickListener;
    }

    public void onBind(@NonNull ClubDto clubDto, @NonNull final Integer position) {

        tvMyClubName.setText(clubDto.getName());
        tvMyClubRecentSchedule.setText(clubDto.getUpComingMeetingDate());

        btnMyClubDelete.setOnClickListener(view -> {
            onMyClubItemClickListener.onDeleteMyClubClick(SignUtils.readUserIdFromPref(context), position);
        });

        ivMyClub.setOnClickListener(view -> {
            onMyClubItemClickListener.onStartMyClubClick(position);
        });

    }

}
