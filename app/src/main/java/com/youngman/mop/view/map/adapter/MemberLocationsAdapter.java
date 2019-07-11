package com.youngman.mop.view.map.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.lib.realtimedb.MemberLocation;
import com.youngman.mop.listener.OnBasicItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-28.
 */

public class MemberLocationsAdapter extends RecyclerView.Adapter<MemberLocationsViewHolder> implements MemberLocationsAdapterContract.View, MemberLocationsAdapterContract.Model {

    private Context context;
    private List<MemberLocation> otherLocations = new ArrayList<>();
    private OnBasicItemClickListener onBasicItemClickListener;


    public MemberLocationsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    @Override
    public void addItems(List<MemberLocation> otherLocations) {
        this.otherLocations = otherLocations;
    }

    @Override
    public void deleteItem(int position) {
        otherLocations.remove(position);
    }

    @Override
    public MemberLocation getItem(int position) {
        return otherLocations.get(position);
    }

    @Override
    public int getItemCount() {
        return otherLocations != null ? otherLocations.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MemberLocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberLocationsViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(MemberLocationsViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(otherLocations.get(position), position);
    }
}