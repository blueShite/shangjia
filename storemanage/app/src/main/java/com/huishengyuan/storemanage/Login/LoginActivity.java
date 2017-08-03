package com.huishengyuan.storemanage.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tab.TabActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        Intent intent = new Intent(LoginActivity.this, TabActivity.class);
        startActivity(intent);
        finish();
    }
}
