package com.huishengyuan.storemanage.Order.Interface;

import com.huishengyuan.storemanage.Order.Bean.OrderListBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/8/7.
 */

public interface OrderInterface {

    void requestSucess(List<OrderListBean> list);
    void requestError();
    void onClickOrderItem(int poist);
}
