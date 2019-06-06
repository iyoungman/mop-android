package com.youngman.mop.view.myclubs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.data.Club;
import com.youngman.mop.listener.OnMyClubItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-05-01.
 */

public class MyClubsAdapter extends RecyclerView.Adapter<MyClubsViewHolder> implements MyClubsAdapterContract.View, MyClubsAdapterContract.Model {

    private Context context;
    private List<Club> myClubs = new ArrayList<>();
    private OnMyClubItemClickListener onMyClubItemClickListener;

    public MyClubsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void setOnMyClubItemClickListener(@NonNull OnMyClubItemClickListener onMyClubItemClickListener) {
        this.onMyClubItemClickListener = onMyClubItemClickListener;
    }

    @Override
    public void addItems(@NonNull List<com.youngman.mop.data.Club> myClubs) {
        this.myClubs = myClubs;
    }

    @Override
    public void deleteItem(@NonNull Integer position) {
        myClubs.remove(position);
    }

    @Override
    public Club getItem(@NonNull Integer position) {
        return myClubs.get(position);
    }

    @Override
    public int getItemCount() {
        return myClubs != null ? myClubs.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MyClubsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyClubsViewHolder(context, parent, onMyClubItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyClubsViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(myClubs.get(position), position);
    }
}
