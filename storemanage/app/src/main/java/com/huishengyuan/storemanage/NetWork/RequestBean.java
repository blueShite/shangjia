package com.huishengyuan.storemanage.NetWork;

/**
 * Created by longhengyu on 2017/7/27.
 */

public class RequestBean<T> {
    private boolean res;
    private String mes;
    private T data;

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
