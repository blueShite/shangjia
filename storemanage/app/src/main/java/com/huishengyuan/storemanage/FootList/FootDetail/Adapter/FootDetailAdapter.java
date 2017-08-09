package com.huishengyuan.storemanage.FootList.FootDetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huishengyuan.storemanage.FootList.FootDetail.Bean.FootDetailBean;
import com.huishengyuan.storemanage.FootList.FootDetail.Interface.FootDetailInterface;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class FootDetailAdapter extends RecyclerView.Adapter<FootDetailAdapter.ViewHolder> {



    private View headerView;
    private FootDetailInterface mInterface;
    private Context mContext;
    private FootDetailBean mDetailBean;

    public FootDetailAdapter(FootDetailBean detailBean, Context context, FootDetailInterface detailInterface) {
        mInterface = detailInterface;
        mDetailBean = detailBean;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_foot_detail_header, parent, false);
            ViewHolder viewHolder = new ViewHolder(headerView);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            if(mDetailBean.getState().equals("1")){
                holder.mButtonFootDetailAdd.setText("下架");
            }else {
                holder.mButtonFootDetailAdd.setText("上架");
            }
            holder.mButtonFootDetailAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterface.onClickAddFootBtn();
                }
            });
            AppUtils.setImageUrl(mDetailBean.getLitpic(), mContext, holder.mImageFootDetailHeader,-1);
            holder.mTextFootDetailName.setText(mDetailBean.getDish());
            holder.mTextFootDetailPrice.setText("¥"+mDetailBean.getPrice());
            holder.mTextFootDetailMiaoshu.setText(mDetailBean.getTact());
            return;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_foot_detail_name)
        TextView mTextFootDetailName;
        @BindView(R.id.text_foot_detail_price)
        TextView mTextFootDetailPrice;
        @BindView(R.id.button_foot_detail_add)
        Button mButtonFootDetailAdd;
        @BindView(R.id.text_foot_detail_miaoshu)
        TextView mTextFootDetailMiaoshu;
        @BindView(R.id.image_foot_detail_header)
        ImageView mImageFootDetailHeader;

        TextView itemNameText;
        TextView itemRemarkText;

        public ViewHolder(View itemView) {
            super(itemView);
            if (headerView != null && headerView == itemView) {
                ButterKnife.bind(this, itemView);
                return;
            }
            itemNameText = (TextView) itemView.findViewById(R.id.text_foot_detail_item_name);
            itemRemarkText = (TextView) itemView.findViewById(R.id.text_foot_detail_item_remark);
        }
    }
}
