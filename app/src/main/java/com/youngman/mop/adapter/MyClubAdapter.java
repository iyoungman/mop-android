package com.youngman.mop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.adapter.holder.MyClubViewHolder;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubAdapter extends RecyclerView.Adapter<MyClubViewHolder> implements MyClubAdapterContract.View, MyClubAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtoList = new ArrayList<>();
    private OnMyClubItemClickListener onMyClubItemClickListener;

    public MyClubAdapter(Context context) {
        this.context = context;
    }

    public void setOnMyClubItemClickListener(@NonNull OnMyClubItemClickListener onMyClubItemClickListener) {
        this.onMyClubItemClickListener = onMyClubItemClickListener;
    }

    @Override
    public void addItems(@NonNull List<ClubDto> clubDtoList) {
        this.clubDtoList = clubDtoList;
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
    public MyClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyClubViewHolder(context, parent, onMyClubItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyClubViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(clubDtoList.get(position), position);
    }
}
