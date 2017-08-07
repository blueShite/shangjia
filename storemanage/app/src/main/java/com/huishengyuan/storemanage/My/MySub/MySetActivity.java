package com.huishengyuan.storemanage.My.MySub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.Login.LoginActivity;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_my_set_password,
              R.id.layout_my_set_about,
              R.id.layout_my_set_cache,
              R.id.button_my_set_out,
              R.id.image_mySet_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_my_set_password:
                break;
            case R.id.layout_my_set_about:
                break;
            case R.id.layout_my_set_cache:
                break;
            case R.id.button_my_set_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(MySetActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定退出登录吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface anInterface, int i) {
                        Intent intent = new Intent(MySetActivity.this, LoginActivity.class);
                        startActivity(intent);
                        LoginManage.getInstance().saveLoginBean(null);
                        ActivityCollector.finishAll();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            case R.id.image_mySet_back:
                finish();
                break;
        }
    }
}
