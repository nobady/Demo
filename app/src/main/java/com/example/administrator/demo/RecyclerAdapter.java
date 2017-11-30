package com.example.administrator.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/28.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {


    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.contentTv.setText("woshiyigedade"+position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

        private final TextView contentTv;

        public RecyclerHolder(View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.tv_content);
        }
    }
}
