package com.huishengyuan.storemanage.FootList.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Base.BasePresenter;
import com.huishengyuan.storemanage.FootList.Bean.FootClassBean;
import com.huishengyuan.storemanage.FootList.Bean.FootListBean;
import com.huishengyuan.storemanage.FootList.FootDetail.Interface.FootDetailInterface;
import com.huishengyuan.storemanage.FootList.Interface.FootListInterface;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longhengyu on 2017/8/8.
 */

public class FootListPresenter extends BasePresenter {

    private FootListInterface mInterface;

    public FootListPresenter(FootListInterface anInterface){
        mInterface = anInterface;
    }

    public void requestClassList(final String resId){
        Map<String,String> map = new HashMap<>();
        map.put("res_id",resId);
        OkGoTools.getInstance().post("/merchant-api/type_list.api.php",map,mContext,new RequestCallBack(mContext){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("分类数据--------",response.body().getData());
                        List<FootClassBean> list = JSON.parseArray(response.body().getData(),FootClassBean.class);
                        list.get(0).setSelect(true);
                        mInterface.requestClassSucess(list);
                    }
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
            }
        });
    }

    public void requestFootList(String resId, final String classifyId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("res_id",resId);
        map.put("id",classifyId);
        OkGoTools.getInstance().post("/merchant-api/menu_list.api.php",map,mContext,new RequestCallBack(mContext){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("餐单列表数据--------",response.body().getData());
                        List<FootListBean> list = JSON.parseArray(response.body().getData(),FootListBean.class);
                        mInterface.requestListSucess(list,classifyId);
                    }else {
                        mInterface.requestListError(classifyId);
                    }
                }else {
                    mInterface.requestListError(classifyId);
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
                dismissDialog();
                mInterface.requestListError(classifyId);
            }
        });
    }


}
