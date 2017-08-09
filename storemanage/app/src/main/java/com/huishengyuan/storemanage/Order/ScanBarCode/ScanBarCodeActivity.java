package com.huishengyuan.storemanage.Order.ScanBarCode;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.R;
import com.lzy.okgo.model.Response;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


public class ScanBarCodeActivity extends BaseActivity {

    CaptureFragment captureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_bar_code);
        ButterKnife.bind(this);
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, final String result) {

            captureFragment.onStop();
            requestOrderSucess(result);
        }

        @Override
        public void onAnalyzeFailed() {
            Toasty.error(ScanBarCodeActivity.this,"扫码失败!").show();
        }
    };

    @OnClick(R.id.image_scanBarCode_back)
    public void onViewClicked() {
        finish();
    }

    private void requestOrderSucess(String orderId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("id",orderId);
        OkGoTools.getInstance().post("/merchant-api/order_alter.api.php",map,ScanBarCodeActivity.this,new RequestCallBack(ScanBarCodeActivity.this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()) {
                    if(response.body().isRes()){
                        Toasty.success(ScanBarCodeActivity.this,"确认制作完成").show();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }else {
                        captureFragment = new CaptureFragment();
                        // 为二维码扫描界面设置定制化界面
                        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
                        captureFragment.setAnalyzeCallback(analyzeCallback);
                        /**
                         * 替换我们的扫描控件
                         */
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
                    }
                }else {
                    captureFragment = new CaptureFragment();
                    // 为二维码扫描界面设置定制化界面
                    CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
                    captureFragment.setAnalyzeCallback(analyzeCallback);
                    /**
                     * 替换我们的扫描控件
                     */
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
                dismissDialog();
                captureFragment = new CaptureFragment();
                // 为二维码扫描界面设置定制化界面
                CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
                captureFragment.setAnalyzeCallback(analyzeCallback);
                /**
                 * 替换我们的扫描控件
                 */
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
            }
        });

    }
}
