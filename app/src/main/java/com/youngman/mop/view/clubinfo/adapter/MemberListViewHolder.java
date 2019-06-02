package com.youngman.mop.view.clubinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.model.dto.MemberDto;

/**
 * Created by YoungMan on 2019-05-30.
 */

public class MemberListViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvMemberName;
    private TextView tvMemberIntroduce;

    private OnMemberItemClickListener onMemberItemClickListener;


    public MemberListViewHolder(@NonNull Context context,
                                @NonNull ViewGroup parent,
                                @NonNull OnMemberItemClickListener onMemberItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_member_list, parent, false));
        this.context = context;
//        this.ivClubImg = itemView.findViewById(R.id.iv_club_img);
        this.tvMemberName = itemView.findViewById(R.id.tv_member_name);
        this.tvMemberIntroduce = itemView.findViewById(R.id.tv_member_introduce);
        this.onMemberItemClickListener = onMemberItemClickListener;
    }

    public void onBind(@NonNull MemberDto memberDto, @NonNull final Integer position) {

        tvMemberName.setText(memberDto.getName());
        tvMemberIntroduce.setText(memberDto.getIntroduce());

        itemView.setOnClickListener(view -> {
            onMemberItemClickListener.onStartMemberClick(position);
        });
    }
}