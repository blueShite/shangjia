package com.huishengyuan.storemanage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.lzy.okgo.model.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkGoTools.getInstance().post("/api/getDieCircleTop.api.php",null,MainActivity.this,new RequestCallBack(MainActivity.this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                Log.e("打印出来的数据",response.body().getData());
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
            }
        });
    }
}
