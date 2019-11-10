package com.youngman.mop.view.map.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnBasicItemClickListener;

/**
 * Created by YoungMan on 2019-06-28.
 */

public class MemberLocationsViewHolder extends RecyclerView.ViewHolder {

    private TextView tvMapMemberName;
    private OnBasicItemClickListener onBasicItemClickListener;

    public MemberLocationsViewHolder(Context context, ViewGroup parent,
                                     OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_member_locations, parent, false));
        this.tvMapMemberName = itemView.findViewById(R.id.tv_map_member_name);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(MemberLocation memberLocation, int position) {
        String name = memberLocation.getLocationInfo().getName();
        tvMapMemberName.setText(name.substring(name.length() - 2, name.length()));

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}