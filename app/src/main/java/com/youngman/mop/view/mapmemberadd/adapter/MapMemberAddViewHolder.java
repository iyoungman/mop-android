package com.youngman.mop.view.mapmemberadd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Participant;

/**
 * Created by YoungMan on 2019-07-26.
 */

public class MapMemberAddViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvMemberName;
    private TextView tvMemberParticipate;
    private CheckBox cbAddMember;

    public MapMemberAddViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_map_add_members, parent, false));
        this.context = context;
        this.tvMemberName = itemView.findViewById(R.id.tv_member_name);
        this.tvMemberParticipate = itemView.findViewById(R.id.tv_member_participate);
        this.cbAddMember = itemView.findViewById(R.id.cb_add_member);
    }

    public void onBind(Participant participant, int position) {
        tvMemberName.setText(participant.getName());
        tvMemberParticipate.setText(participant.isParticipate());
    }

    public CheckBox getCbAddMember() {
        return cbAddMember;
    }

    public boolean isChecked() {
        return cbAddMember.isChecked();
    }
}
