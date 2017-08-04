package com.huishengyuan.storemanage.FootList.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.huishengyuan.storemanage.R;

import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class FootListClassAdapter extends RecyclerView.Adapter<FootListClassAdapter.ViewHolder> {

    @Override
    public FootListClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footlist_classity,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FootListClassAdapter.ViewHolder holder, int position) {

        if(position==0){
            holder.nameText.setSelected(true);
        }else {
            holder.nameText.setSelected(false);
        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = (CheckedTextView)itemView.findViewById(R.id.text_classify_name);
        }
    }
}
