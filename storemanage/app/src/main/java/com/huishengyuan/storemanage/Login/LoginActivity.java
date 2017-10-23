package com.huishengyuan.storemanage.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.Manage.Bean.LoginBean;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tab.TabActivity;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_login_password)
    EditText mEditLoginPassword;
    @BindView(R.id.edit_login_account)
    EditText mEditLoginAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {

        requestLogin(mEditLoginAccount.getText().toString(),mEditLoginPassword.getText().toString());
    }

    private void requestLogin(String account,String password){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("user_name",account);
        map.put("pass_word",password);
        OkGoTools.getInstance().post("/merchant-api/login.api.php",map,LoginActivity.this,new RequestCallBack(LoginActivity.this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("登录数据",response.body().getData());
                        LoginBean loginBean = JSON.parseArray(response.body().getData(),LoginBean.class).get(0);
                        LoginManage.getInstance().saveLoginBean(loginBean);
                        Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                        startActivity(intent);
                        finish();
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
}
