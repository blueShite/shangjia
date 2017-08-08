package com.huishengyuan.storemanage.Order.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.huishengyuan.storemanage.Order.Bean.OrderListBean;
import com.huishengyuan.storemanage.Order.Interface.OrderInterface;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by longhengyu on 2017/8/4.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {


    private OrderInterface mInterface;
    private List<OrderListBean> mList;
    private Context mContext;

    public OrderAdapter(List<OrderListBean> list,Context context, OrderInterface anInterface) {
        mInterface = anInterface;
        mList = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        OrderListBean bean = mList.get(position);
        holder.mTextOrderPhone.setText(bean.getAcc_phone());
        holder.mTextOrderAddress.setText(bean.getAddress());
        holder.mTextOrderName.setText(bean.getAcc_name()+"的外卖订单");
        holder.mTextOrderFootName.setText(bean.getDish());
        AppUtils.setImageUrl(bean.getHeadimg(),mContext,holder.mImageOrderHeader,R.drawable.tab_info_active);
        holder.selfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickOrderItem(position);
            }
        });
        if(bean.getLOrderType().equals("-1")){
            holder.mButtonOrderSucess.setVisibility(View.VISIBLE);
        }else {
            holder.mButtonOrderSucess.setVisibility(View.GONE);
        }
        holder.mButtonOrderSucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onClickSucessBtn(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.button_order_sucess)
        Button mButtonOrderSucess;
        @BindView(R.id.text_order_phone)
        TextView mTextOrderPhone;
        @BindView(R.id.text_order_address)
        TextView mTextOrderAddress;
        @BindView(R.id.text_order_footName)
        TextView mTextOrderFootName;
        @BindView(R.id.text_order_name)
        TextView mTextOrderName;
        @BindView(R.id.image_order_header)
        CircleImageView mImageOrderHeader;
        private View selfView;

        public ViewHolder(View itemView) {
            super(itemView);
            selfView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
