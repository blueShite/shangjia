package com.huishengyuan.storemanage.NetWork;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by longhengyu on 2017/7/27.
 */

public class OkGoTools {
    //static修饰的静态变量在内存中一旦创建，便永久存在
    private static OkGoTools instance = new OkGoTools();
    private OkGoTools (){}
    public static OkGoTools getInstance() {
        return instance;
    }
    //    private static final String BaseUrl = "http://116.255.228.199:8010";
//    public static final String BaseUrl = "http://www.hsydining-hall.com";
    public static final String BaseUrl = "http://www.hsydining-hall.com";

    public void get(String path, Map<String,String> map, Context context, RequestCallBack callback){
        OkGo.<RequestStringBean>get(BaseUrl+path)
                .tag(context)
                .params(map)
                .execute(callback);
    }

    public void post(String path, Map<String,String> map, Context context, RequestCallBack callback){
        OkGo.<RequestStringBean>post(BaseUrl+path)
                .tag(context)
                .params(map)
                .execute(callback);
    }

}
