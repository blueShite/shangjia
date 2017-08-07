package com.huishengyuan.storemanage.Order.OrderDetail.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huishengyuan.storemanage.R;

/**
 * Created by longhengyu on 2017/8/7.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private View headerView;
    private View footView;

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        if(position==4){
            return 2;
        }
        return 1;
    }

    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order_detail_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }
        if(viewType==2){
            footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order_detail_foot,parent,false);
            ViewHolder viewHolder = new ViewHolder(footView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderDetailAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            if(headerView!=null&&headerView==itemView){
                return;
            }
            if(footView!=null&&footView==itemView){
                return;
            }

        }
    }
}
