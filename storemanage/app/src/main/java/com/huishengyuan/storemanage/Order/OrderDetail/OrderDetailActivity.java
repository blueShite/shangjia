package com.huishengyuan.storemanage.Order.OrderDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.Order.OrderDetail.Adapter.OrderDetailAdapter;
import com.huishengyuan.storemanage.Order.OrderDetail.Bean.OrderDetailBean;
import com.huishengyuan.storemanage.Order.OrderDetail.Interface.OrderDetailInterface;
import com.huishengyuan.storemanage.Order.OrderDetail.ShowBarCode.ShowBarcodeActivity;
import com.huishengyuan.storemanage.R;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements OrderDetailInterface {

    @BindView(R.id.order_detail_recycler)
    RecyclerView mOrderDetailRecycler;

    private OrderDetailAdapter mDetailAdapter;
    private String orderId;
    private OrderDetailBean mDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        customView();
        requestOrderDetail();
    }

    @OnClick(R.id.image_orderDetail_back)
    public void onViewClicked() {

        finish();
    }

    private void customView(){

        orderId = getIntent().getStringExtra("orderId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderDetailActivity.this);
        mOrderDetailRecycler.setLayoutManager(layoutManager);


    }

    private void requestOrderDetail(){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("id",orderId);
        OkGoTools.getInstance().post("/merchant-api/getorder_xq.api.php",map,OrderDetailActivity.this,new RequestCallBack(OrderDetailActivity.this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("订单详情数据:",response.body().getData());
                        mDetailBean = JSON.parseObject(response.body().getData(),OrderDetailBean.class);
                        mDetailAdapter = new OrderDetailAdapter(mDetailBean,OrderDetailActivity.this,OrderDetailActivity.this);
                        mOrderDetailRecycler.setAdapter(mDetailAdapter);
                    }
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
                dismissDialog();
            }
        });
    }

    @Override
    public void onClickBarCode() {
        Intent intent = new Intent(this, ShowBarcodeActivity.class);
        intent.putExtra("BarCodeUrl",mDetailBean.getCodepic());
        startActivity(intent);
    }
}
