package com.youngman.mop.view.myclubs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.youngman.mop.R;
import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnMyClubsItemClickListener;
import com.youngman.mop.util.SignUtils;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvMyClubName;
    private Button btnMyClubDelete;
    private ImageView ivMyClub;
    private TextView tvMyClubRecentSchedule;

    private OnMyClubsItemClickListener onMyClubsItemClickListener;
    private RequestManager requestManager;


    public MyClubsViewHolder(Context context,
                             ViewGroup parent,
                             OnMyClubsItemClickListener onMyClubsItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_myclubs, parent, false));
        this.context = context;
        this.tvMyClubName = itemView.findViewById(R.id.tv_myclub_name);
        this.btnMyClubDelete = itemView.findViewById(R.id.btn_myclub_delete);
        this.ivMyClub = itemView.findViewById(R.id.iv_myclub);
        this.tvMyClubRecentSchedule = itemView.findViewById(R.id.tv_myclub_recent_schedule);
        this.onMyClubsItemClickListener = onMyClubsItemClickListener;
        this.requestManager = Glide.with(context);
    }

    public void onBind(Club club, int position) {

        setClubImage(club.getImageUri());
        tvMyClubName.setText(club.getName());
        tvMyClubRecentSchedule.setText(club.getSimpleTime());

        btnMyClubDelete.setOnClickListener(view -> {
            onMyClubsItemClickListener.onDeleteMyClubClick(SignUtils.readUserIdFromPref(context), position);
        });

        ivMyClub.setOnClickListener(view -> {
            onMyClubsItemClickListener.onStartMyClubClick(position);
        });

    }

    private void setClubImage(String imageUri) {
        RequestBuilder requestBuilder = requestManager.load(imageUri);
        RequestOptions requestOptions = new RequestOptions();
        requestBuilder.apply(requestOptions);
        ivMyClub.setImageResource(0);
        requestBuilder.into(ivMyClub);
    }

}
