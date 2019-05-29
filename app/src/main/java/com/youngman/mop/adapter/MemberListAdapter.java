package com.youngman.mop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.MemberListAdapterContract;
import com.youngman.mop.adapter.holder.MemberListViewHolder;
import com.youngman.mop.listener.OnMemberItemClickListener;
import com.youngman.mop.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-30.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListViewHolder> implements MemberListAdapterContract.View, MemberListAdapterContract.Model {

    private Context context;
    private List<MemberDto> memberDtos = new ArrayList<>();
    private OnMemberItemClickListener onMemberItemClickListener;

    public MemberListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void setOnMemberItemClickListener(@NonNull OnMemberItemClickListener onMemberItemClickListener) {
        this.onMemberItemClickListener = onMemberItemClickListener;
    }

    @Override
    public void addItems(@NonNull List<MemberDto> memberDtos) {
        this.memberDtos = memberDtos;
    }

    @Override
    public void deleteItem(@NonNull Integer position) {
        memberDtos.remove(position);
    }

    @Override
    public MemberDto getItem(@NonNull Integer position) {
        return memberDtos.get(position);
    }

    @Override
    public int getItemCount() {
        return memberDtos != null ? memberDtos.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MemberListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberListViewHolder(context, parent, onMemberItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MemberListViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(memberDtos.get(position), position);
    }
}
