package com.youngman.mop.view.board.adpater;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.data.Board;
import com.youngman.mop.listener.OnBasicItemClickListener;
import com.youngman.mop.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-07-09.
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder> implements BoardAdapterContract.View, BoardAdapterContract.Model {

    private Context context;
    private List<Board> boards = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    private boolean isMoreLoading = false;
    private boolean isLast = false;


    public BoardAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
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
    public void addItems(List<Board> boards) {
        this.boards.addAll(boards);
    }

    @Override
    public Board getItem(int position) {
        return boards.get(position);
    }

    @Override
    public int getItemCount() {
        return boards != null ? boards.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoardViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(boards.get(position), position);
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
