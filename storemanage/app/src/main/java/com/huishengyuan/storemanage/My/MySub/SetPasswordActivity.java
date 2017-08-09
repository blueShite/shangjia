package com.huishengyuan.storemanage.My.MySub;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.R;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SetPasswordActivity extends BaseActivity {

    @BindView(R.id.edit_set_pass_new)
    EditText mEditSetPassNew;
    @BindView(R.id.edit_set_pass_queren)
    EditText mEditSetPassQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_setPassWord_back, R.id.button_set_pass_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_setPassWord_back:
                finish();
                break;
            case R.id.button_set_pass_submit:
                if(mEditSetPassNew.getText().length()<1||mEditSetPassQueren.getText().length()<1){
                    Toasty.error(SetPasswordActivity.this,"请输入新密码").show();
                    return;
                }
                if(!mEditSetPassQueren.getText().toString().equals(mEditSetPassNew.getText().toString())){
                    Toasty.error(SetPasswordActivity.this,"两次输入密码不一致").show();
                    return;
                }
                requestSetPassWord(mEditSetPassNew.getText().toString());
                break;
        }
    }

    private void requestSetPassWord(String passWord){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("pass_word",passWord);
        map.put("res_id", LoginManage.getInstance().getLoginBean().getRes_id());
        OkGoTools.getInstance().post("/merchant-api/updata_pwd.api.php",map,this,new RequestCallBack(this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Toasty.success(SetPasswordActivity.this,"修改密码成功").show();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
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
