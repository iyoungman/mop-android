package com.youngman.mop.view.clubinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.data.Member;
import com.youngman.mop.listener.OnMemberItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-30.
 */

public class MembersAdapter extends RecyclerView.Adapter<MembersViewHolder> implements MembersAdapterContract.View, MembersAdapterContract.Model {

    private Context context;
    private List<Member> members = new ArrayList<>();
    private OnMemberItemClickListener onMemberItemClickListener;

    public MembersAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void setOnMemberItemClickListener(OnMemberItemClickListener onMemberItemClickListener) {
        this.onMemberItemClickListener = onMemberItemClickListener;
    }

    @Override
    public void addItems(List<Member> members) {
        this.members = members;
    }

    @Override
    public void deleteItem(int position) {
        members.remove(position);
    }

    @Override
    public Member getItem(int position) {
        return members.get(position);
    }

    @Override
    public int getItemCount() {
        return members != null ? members.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MembersViewHolder(context, parent, onMemberItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MembersViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(members.get(position), position);
    }
}
