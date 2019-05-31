package com.youngman.mop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.MyClubListAdapterContract;
import com.youngman.mop.adapter.holder.MyClubListViewHolder;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubListAdapter extends RecyclerView.Adapter<MyClubListViewHolder> implements MyClubListAdapterContract.View, MyClubListAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtoList = new ArrayList<>();
    private OnMyClubItemClickListener onMyClubItemClickListener;

    public MyClubListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void setOnMyClubItemClickListener(@NonNull OnMyClubItemClickListener onMyClubItemClickListener) {
        this.onMyClubItemClickListener = onMyClubItemClickListener;
    }

    @Override
    public void addItems(@NonNull List<ClubDto> clubDtos) {
        this.clubDtoList = clubDtos;
    }

    @Override
    public void deleteItem(@NonNull Integer position) {
        clubDtoList.remove(position);
    }

    @Override
    public ClubDto getItem(@NonNull Integer position) {
        return clubDtoList.get(position);
    }

    @Override
    public int getItemCount() {
        return clubDtoList != null ? clubDtoList.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MyClubListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyClubListViewHolder(context, parent, onMyClubItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyClubListViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(clubDtoList.get(position), position);
    }
}
