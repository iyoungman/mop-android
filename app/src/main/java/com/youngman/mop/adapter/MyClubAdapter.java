package com.youngman.mop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.adapter.holder.MyClubViewHolder;
import com.youngman.mop.listener.OnItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubAdapter extends RecyclerView.Adapter<MyClubViewHolder> implements MyClubAdapterContract.View, MyClubAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtos = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MyClubAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public void addItems(List<ClubDto> clubDtos) {
        this.clubDtos = clubDtos;
    }

    @Override
    public void deleteItem(int position) {
        clubDtos.remove(position);
    }

    @Override
    public ClubDto getClubDto(int position) {
        return clubDtos.get(position);
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clubDtos != null ? clubDtos.size() : 0;
    }

    @Override
    public MyClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyClubViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyClubViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(clubDtos.get(position), position);
    }
}
