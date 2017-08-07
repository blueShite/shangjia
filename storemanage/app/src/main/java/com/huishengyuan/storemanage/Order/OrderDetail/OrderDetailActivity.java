package com.huishengyuan.storemanage.Order.OrderDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.Order.OrderDetail.Adapter.OrderDetailAdapter;
import com.huishengyuan.storemanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.order_detail_recycler)
    RecyclerView mOrderDetailRecycler;

    private OrderDetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        customView();
    }

    @OnClick(R.id.image_orderDetail_back)
    public void onViewClicked() {

        finish();
    }

    private void customView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderDetailActivity.this);
        mOrderDetailRecycler.setLayoutManager(layoutManager);
        mDetailAdapter = new OrderDetailAdapter();
        mOrderDetailRecycler.setAdapter(mDetailAdapter);

    }
}
