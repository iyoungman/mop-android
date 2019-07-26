package com.youngman.mop.view.mapmemberadd.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youngman.mop.data.Club;
import com.youngman.mop.data.Participant;
import com.youngman.mop.listener.OnLoadMoreListener;
import com.youngman.mop.view.clubs.adapter.ClubsAdapterContract;
import com.youngman.mop.view.clubs.adapter.ClubsViewHolder;
import com.youngman.mop.view.clubs.adapter.OnClubsItemClickListener;
import com.youngman.mop.view.mapmemberadd.presenter.MapMemberAddContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-07-26.
 */

public class MapMemberAddAdapter extends RecyclerView.Adapter<MapMemberAddViewHolder> implements MapMemberAddAdapterContract.View, MapMemberAddAdapterContract.Model {

    private Context context;
    private List<Participant> participants = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    public MapMemberAddAdapter(Context context) {
        this.context = context;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void addItems(List<Participant> participants) {
        this.participants.addAll(participants);
    }

    @Override
    public Participant getItem(int position) {
        return participants.get(position);
    }

    @Override
    public int getItemCount() {
        return participants != null ? participants.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public MapMemberAddViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MapMemberAddViewHolder(parent.getContext(), parent);
    }

    @Override
    public void onBindViewHolder(MapMemberAddViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(participants.get(position), position);
    }
}
