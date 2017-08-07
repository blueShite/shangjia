package com.huishengyuan.storemanage.NetWork;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;

import es.dmoral.toasty.Toasty;

/**
 * Created by longhengyu on 2017/7/27.
 */

public class RequestCallBack extends AbsCallback<RequestStringBean> {

    public Context mContext;
    public RequestCallBack(Context context){
        mContext = context;
    }

    @Override
    public void onSuccess(Response<RequestStringBean> response) {
        if(!response.isSuccessful()){
            Toasty.error(mContext,"请求失败").show();
        }else {
            if(!response.body().isRes()){
                Toasty.error(mContext,response.body().getMes()).show();
            }
        }
    }

    @Override
    public void onError(Response<RequestStringBean> response) {
        super.onError(response);
        Toasty.error(mContext,"请求失败!").show();
    }

    @Override
    public RequestStringBean convertResponse(okhttp3.Response response) throws Throwable {

        StringConvert convert = new StringConvert();
        String s = convert.convertResponse(response);
        RequestStringBean bean = JSON.parseObject(s,RequestStringBean.class);
        response.close();
        return bean;
    }
}
