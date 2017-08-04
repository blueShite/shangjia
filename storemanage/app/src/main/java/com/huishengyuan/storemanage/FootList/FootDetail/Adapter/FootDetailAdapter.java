package com.huishengyuan.storemanage.FootList.FootDetail.Adapter;

import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huishengyuan.storemanage.R;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class FootDetailAdapter extends RecyclerView.Adapter<FootDetailAdapter.ViewHolder> {

    private View headerView;

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return 0;
        }
        return 1;
    }

    @Override
    public FootDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0){
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_foot_detail_header,parent,false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot_detail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FootDetailAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            if(headerView!=null&&headerView==itemView){

            }

        }
    }
}
