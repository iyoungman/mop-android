package com.youngman.mop.view.clubinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Member;
import com.youngman.mop.listener.OnBasicItemClickListener;

/**
 * Created by YoungMan on 2019-05-30.
 */

public class MembersViewHolder extends RecyclerView.ViewHolder {

    private TextView tvMemberName;
    private TextView tvMemberIntroduce;
    private OnBasicItemClickListener onBasicItemClickListener;


    public MembersViewHolder(Context context,
                             ViewGroup parent,
                             OnBasicItemClickListener onBasicItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_members, parent, false));
//        this.ivClubImg = itemView.findViewById(R.id.iv_club_img);
        this.tvMemberName = itemView.findViewById(R.id.tv_member_name);
        this.tvMemberIntroduce = itemView.findViewById(R.id.tv_member_introduce);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(Member member, int position) {
        tvMemberName.setText(member.getName());
        tvMemberIntroduce.setText(member.getIntroduce());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}
