package com.youngman.mop.view.clublist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.view.clublist.adapter.ClubListAdapterContract;
import com.youngman.mop.view.clublist.adapter.ClubListViewHolder;
import com.youngman.mop.listener.OnClubListItemClickListener;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.model.dto.ClubDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubListAdapter extends RecyclerView.Adapter<ClubListViewHolder> implements ClubListAdapterContract.View, ClubListAdapterContract.Model {

    private Context context;
    private List<ClubDto> clubDtos = new ArrayList<>();
    private OnClubListItemClickListener onClubListItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;


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
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                if (isVisibleLastItem(rv)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    private boolean isVisibleLastItem(RecyclerView recyclerView) {
        return (!isLast && !isMoreLoading && (linearLayoutManager.getItemCount() - recyclerView.getChildCount())
                <= (linearLayoutManager.findFirstVisibleItemPosition() + 1));
    }

    @Override
    public void addItems(@NonNull List<ClubDto> clubDtos) {
        this.clubDtos.addAll(clubDtos);
    }

    @Override
    public ClubDto getItem(@NonNull Integer position) {
        return clubDtos.get(position);
    }

    @Override
    public int getItemCount() {
        return clubDtos != null ? clubDtos.size() : 0;
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
        holder.onBind(clubDtos.get(position), position);
    }

    @Override
    public void setMoreLoading(@NonNull Boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    @Override
    public void setIsLast(@NonNull Boolean isLast) {
        this.isLast = isLast;
    }
}
