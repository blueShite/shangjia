package com.huishengyuan.storemanage.FootList.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huishengyuan.storemanage.FootList.Bean.FootListBean;
import com.huishengyuan.storemanage.FootList.Interface.FootListInterface;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class FootListCommodityAdapter extends RecyclerView.Adapter<FootListCommodityAdapter.ViewHolder> {


    private Context mContext;
    private FootListInterface mDetailInterface;
    private List<FootListBean> mList;

    public FootListCommodityAdapter(List<FootListBean> list,Context context, FootListInterface detailInterface) {
        mContext = context;
        mDetailInterface = detailInterface;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footlist_commodity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        FootListBean bean = mList.get(position);
        holder.mTextFootlistName.setText(bean.getDish());
        holder.mTextFootlistPrice.setText("¥"+bean.getPrice());
        AppUtils.setImageUrl(bean.getLitpic(),mContext,holder.mImageFootlistCommodity,-1);
        if(bean.getState().equals("1")){
            holder.mTextFootlistSub.setText("正在销售");
        }else {
            holder.mTextFootlistSub.setText("未上架");
        }
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDetailInterface.onClickCommodityItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_footlist_commodity)
        ImageView mImageFootlistCommodity;
        @BindView(R.id.text_footlist_name)
        TextView mTextFootlistName;
        @BindView(R.id.text_footlist_price)
        TextView mTextFootlistPrice;
        @BindView(R.id.text_footlist_sub)
        TextView mTextFootlistSub;

        View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            selfView = itemView;
            ButterKnife.bind(this, itemView);

        }
    }
}
