package com.huishengyuan.storemanage.Splash;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import com.huishengyuan.storemanage.Login.LoginActivity;
import com.huishengyuan.storemanage.Manage.Bean.LoginBean;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tab.TabActivity;
import com.squareup.picasso.Picasso;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import qiu.niorgai.StatusBarCompat;

@RuntimePermissions
public class SplashActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(SplashActivity.this, true);
        mImageView = (ImageView)findViewById(R.id.image_splash);
        Picasso.with(SplashActivity.this).load(R.drawable.splash).fit().centerCrop().into(mImageView);
        SplashActivityPermissionsDispatcher.onPermissionsAllGrantedWithCheck(this);
    }

    private void enterHomeActivity(){

        LoginBean loginBean = LoginManage.getInstance().getLoginBean();
        if(loginBean==null||loginBean.getRes_id().isEmpty()){
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        Intent intent = new Intent(SplashActivity.this,TabActivity.class);
        startActivity(intent);
        finish();

    }

    @NeedsPermission({Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE})
    public void onPermissionsAllGranted() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    @OnPermissionDenied({Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE})
    public void showPermissionsDenied(){

        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("权限提示");
        builder.setMessage("我们需要获取一些基本权限，否则您将无法正常使用惠生园app");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {
                System.exit(0);
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

}
