package com.huishengyuan.storemanage.Order.OrderDetail.ShowBarCode;

import android.os.Bundle;
import android.widget.ImageView;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowBarcodeActivity extends BaseActivity {

    @BindView(R.id.image_show_barcode)
    ImageView mImageShowBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_barcode);
        ButterKnife.bind(this);
        String imageUrl = getIntent().getStringExtra("BarCodeUrl");
        AppUtils.setImageUrl(imageUrl,this,mImageShowBarcode,-1);
    }

    @OnClick(R.id.image_showBarCode_back)
    public void onViewClicked() {
        finish();
    }
}
