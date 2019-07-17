package com.youngman.mop.view.board.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youngman.mop.R;
import com.youngman.mop.data.Board;
import com.youngman.mop.listener.OnBasicItemClickListener;

/**
 * Created by YoungMan on 2019-07-09.
 */

public class BoardViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvBoardType;
    private TextView tvBoardTitle;
    private TextView tvBoardWriter;
    private TextView tvBoardDate;
    private OnBasicItemClickListener onBasicItemClickListener;


    public BoardViewHolder(Context context,
                           ViewGroup parent,
                           OnBasicItemClickListener onBasicItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_boards, parent, false));
        this.context = context;
        this.tvBoardType = itemView.findViewById(R.id.tv_board_type);
        this.tvBoardTitle = itemView.findViewById(R.id.tv_board_title);
        this.tvBoardWriter = itemView.findViewById(R.id.tv_board_writer);
        this.tvBoardDate = itemView.findViewById(R.id.tv_board_date);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(Board board, int position) {
        tvBoardType.setText(board.getStrBoardType());
        tvBoardTitle.setText(board.getTitle());
        tvBoardWriter.setText(board.getWriter());
        tvBoardDate.setText(board.getSimpleTime());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }

}
