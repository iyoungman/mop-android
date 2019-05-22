package com.youngman.mop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.ClubListAdapterContract;
import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.adapter.holder.ClubListViewHolder;
import com.youngman.mop.adapter.holder.MyClubViewHolder;
import com.youngman.mop.listener.OnClubListItemClickListener;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListAdapter extends RecyclerView.Adapter<ClubListViewHolder> implements ClubListAdapterContract.View, ClubListAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtoList = new ArrayList<>();
    private OnClubListItemClickListener onClubListItemClickListener;

    public ClubListAdapter(Context context) {
        this.context = context;
    }

    public void setOnClubListItemClickListener(@NonNull OnClubListItemClickListener onClubListItemClickListener) {
        this.onClubListItemClickListener = onClubListItemClickListener;
    }

    @Override
    public void addItems(@NonNull List<ClubDto> clubDtoList) {
        this.clubDtoList = clubDtoList;
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
    public ClubListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClubListViewHolder(context, parent, onClubListItemClickListener);
    }

    @Override
    public void onBindViewHolder(final ClubListViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(clubDtoList.get(position), position);
    }
}
