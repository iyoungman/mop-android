package com.youngman.mop.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.listener.OnItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvMyClubName;
    private Button btnMyClubDelete;
    private ImageView ivMyClub;
    private TextView tvMyClubRecentSchedule;

    private OnItemClickListener onItemClickListener;

    public MyClubViewHolder(Context context, ViewGroup parent, OnItemClickListener onItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_myclub, parent, false));
        this.context = context;
        this.tvMyClubName = itemView.findViewById(R.id.tv_myclub_name);
        this.btnMyClubDelete = itemView.findViewById(R.id.btn_myclub_delete);
        this.ivMyClub = itemView.findViewById(R.id.iv_myclub);
        this.tvMyClubRecentSchedule = itemView.findViewById(R.id.tv_myclub_recent_schedule);
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(ClubDto clubDto, final int position) {

        btnMyClubDelete.setOnClickListener(view -> {
            onItemClickListener.onDeleteMyClubClick(position);
        });

    }
}
