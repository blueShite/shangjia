package com.huishengyuan.storemanage.Order.Bean;

/**
 * Created by longhengyu on 2017/8/7.
 */

public class OrderListBean {


    /**
     * address : 嘻嘻嘻嘻他们呵呵
     * add_time : 2017.07.11 14:57:21
     * id : 1707111457216066
     * acc_phone : 13676917233
     * diner_time : 2:30
     * delivery : 2.00
     */

    private String address;
    private String add_time;
    private String id;
    private String acc_phone;
    private String diner_time;
    private String delivery;
    private String dish;
    private String headimg;
    private String acc_name;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcc_phone() {
        return acc_phone;
    }

    public void setAcc_phone(String acc_phone) {
        this.acc_phone = acc_phone;
    }

    public String getDiner_time() {
        return diner_time;
    }

    public void setDiner_time(String diner_time) {
        this.diner_time = diner_time;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
