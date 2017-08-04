package com.huishengyuan.storemanage.Order.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huishengyuan.storemanage.R;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {



    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
