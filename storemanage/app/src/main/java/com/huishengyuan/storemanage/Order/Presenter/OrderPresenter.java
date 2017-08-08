package com.huishengyuan.storemanage.Order.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Base.BasePresenter;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.Order.Bean.OrderListBean;
import com.huishengyuan.storemanage.Order.Interface.OrderInterface;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longhengyu on 2017/8/7.
 */

public class OrderPresenter extends BasePresenter {

    private OrderInterface mInterface;

    public OrderPresenter(OrderInterface anInterface){
        mInterface = anInterface;
    }

    public void requestList(final String resId, final String dispatching){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("res_id",resId);
        map.put("dispatching",dispatching);
        OkGoTools.getInstance().post("/merchant-api/order_list.api.php",map,mContext,new RequestCallBack(mContext){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("订单列表:",response.body().getData());
                        List<OrderListBean> list = JSON.parseArray(response.body().getData(),OrderListBean.class);
                        for (OrderListBean bean:list){
                            bean.setLOrderType(dispatching);
                        }
                        mInterface.requestSucess(list);
                    }else {
                        mInterface.requestError();
                    }
                }else {
                    mInterface.requestError();
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
                dismissDialog();
                mInterface.requestError();
            }
        });
    }

}
