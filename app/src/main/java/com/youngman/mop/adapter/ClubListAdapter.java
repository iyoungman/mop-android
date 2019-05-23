package com.youngman.mop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.adapter.contract.ClubListAdapterContract;
import com.youngman.mop.adapter.contract.MyClubAdapterContract;
import com.youngman.mop.adapter.holder.ClubListViewHolder;
import com.youngman.mop.adapter.holder.MyClubViewHolder;
import com.youngman.mop.listener.OnClubListItemClickListener;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.listener.OnMyClubItemClickListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListAdapter extends RecyclerView.Adapter<ClubListViewHolder> implements ClubListAdapterContract.View, ClubListAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtoList = new ArrayList<>();
    private OnClubListItemClickListener onClubListItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;

    public ClubListAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setOnClubListItemClickListener(@NonNull OnClubListItemClickListener onClubListItemClickListener) {
        this.onClubListItemClickListener = onClubListItemClickListener;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public void setRecyclerView(RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isVisibleLastItem(recyclerView)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    private boolean isVisibleLastItem(RecyclerView recyclerView) {
        return  !isMoreLoading && (linearLayoutManager.getItemCount() - recyclerView.getChildCount())
                <= (linearLayoutManager.findFirstVisibleItemPosition() + 1);
    }

    /*public void addInitItems(@NonNull List<ClubDto> initClubDtoList) {
        clubDtoList.clear();
        clubDtoList.addAll(initClubDtoList);
    }*/

    /*public void addMoreItems(@NonNull List<ClubDto> moreClubDtoList) {
        clubDtoList.addAll(moreClubDtoList);
    }

    public void notifyAdapterRange() {
        notifyItemRangeChanged(0, clubDtoList.size() - 1);
    }*/

   /* public void notiftAdapterDataSet() {
        notifyDataSetChanged();
    }*/

    @Override
    public void addItems(@NonNull List<ClubDto> clubDtoList) {
        this.clubDtoList.addAll(clubDtoList);
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

    @Override
    public void setMoreLoading(@NonNull Boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }
}
