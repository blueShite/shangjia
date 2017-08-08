package com.huishengyuan.storemanage.FootList.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.huishengyuan.storemanage.FootList.Bean.FootClassBean;
import com.huishengyuan.storemanage.FootList.Interface.FootListInterface;
import com.huishengyuan.storemanage.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class FootListClassAdapter extends RecyclerView.Adapter<FootListClassAdapter.ViewHolder> {

    private List<FootClassBean> mList;
    private FootListInterface mInterface;

    public FootListClassAdapter(List<FootClassBean> list,FootListInterface anInterface){
        mList = list;
        mInterface = anInterface;
    }

    @Override
    public FootListClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footlist_classity,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FootListClassAdapter.ViewHolder holder, final int position) {

        FootClassBean bean = mList.get(position);
        holder.nameText.setText(bean.getTypename());
        if(bean.isSelect()){
            holder.nameText.setSelected(true);
        }else {
            holder.nameText.setSelected(false);
        }
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickClassify(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView nameText;
        private View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            selfView = itemView;
            nameText = (CheckedTextView)itemView.findViewById(R.id.text_classify_name);
        }
    }
}
