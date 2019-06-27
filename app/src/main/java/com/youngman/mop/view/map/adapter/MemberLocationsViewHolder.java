package com.youngman.mop.view.map.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Member;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnMemberItemClickListener;

/**
 * Created by YoungMan on 2019-06-28.
 */

public class MemberLocationsViewHolder extends RecyclerView.ViewHolder {

    private TextView tvMapMemberName;
    private OnMemberItemClickListener onMemberItemClickListener;


    public MemberLocationsViewHolder(Context context,
                                     ViewGroup parent,
                                     OnMemberItemClickListener onMemberItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_member_locations, parent, false));
        this.tvMapMemberName = itemView.findViewById(R.id.tv_map_member_name);
        this.onMemberItemClickListener = onMemberItemClickListener;
    }

    public void onBind(MemberLocation memberLocation, int position) {
        tvMapMemberName.setText(memberLocation.getEmail());

        itemView.setOnClickListener(view -> {
            onMemberItemClickListener.onStartMemberClick(position);
        });
    }
}