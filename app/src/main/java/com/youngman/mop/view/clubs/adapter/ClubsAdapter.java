package com.youngman.mop.view.clubs.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-03.
 */

public class ClubsAdapter extends RecyclerView.Adapter<ClubsViewHolder> implements ClubsAdapterContract.View, ClubsAdapterContract.Model {

    private Context context;
    private List<Club> clubs = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;


    public ClubsAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
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
    public void addItems(List<Club> clubs) {
        this.clubs.addAll(clubs);
    }

    @Override
    public Club getItem(int position) {
        return clubs.get(position);
    }

    @Override
    public int getItemCount() {
        return clubs != null ? clubs.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public ClubsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClubsViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(ClubsViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(clubs.get(position), position);
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }
}
