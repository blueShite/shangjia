package com.huishengyuan.storemanage.Order.ScanBarCode;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.R;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

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
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Toasty.success(ScanBarCodeActivity.this,result).show();
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
}
