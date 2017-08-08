package com.huishengyuan.storemanage.FootList.FootDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.CustomView.AddFootAlert;
import com.huishengyuan.storemanage.FootList.FootDetail.Adapter.FootDetailAdapter;
import com.huishengyuan.storemanage.FootList.FootDetail.Bean.FootDetailBean;
import com.huishengyuan.storemanage.FootList.FootDetail.Interface.FootDetailInterface;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.R;
import com.huishengyuan.storemanage.Tools.utils.StringUtils;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class FootDetailActivity extends BaseActivity implements FootDetailInterface {


    @BindView(R.id.foot_detail_recycler)
    RecyclerView mFootDetailRecycler;

    private FootDetailAdapter mDetailAdapter;
    private String footId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_detail);
        ButterKnife.bind(this);
        customView();
        requestDetail();
    }

    @OnClick(R.id.image_footDetail_back)
    public void onViewClicked() {
        finish();
    }

    private void customView(){

        footId = getIntent().getStringExtra("footId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(FootDetailActivity.this);
        mFootDetailRecycler.setLayoutManager(layoutManager);


    }

    @Override
    public void onClickAddFootBtn() {
        new AddFootAlert(FootDetailActivity.this)
                .builder()
                .onClickSubmit(new AddFootAlert.OnClickAddFootInterface() {
                    @Override
                    public void addFoot(String numStr) {
                        if(StringUtils.isEmpty(numStr)){
                            return;
                        }
                        requestAddFoot(footId,numStr,"1");
                    }
                })
                .onClickCancel(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    public void requestDetail(){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("menu_id",footId);
        OkGoTools.getInstance().post("/merchant-api/menu_xq.api.php",map,this,new RequestCallBack(this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("餐单详情:",response.body().getData());
                        FootDetailBean detailBean = JSON.parseArray(response.body().getData(),FootDetailBean.class).get(0);
                        mDetailAdapter = new FootDetailAdapter(detailBean,FootDetailActivity.this,FootDetailActivity.this);
                        mFootDetailRecycler.setAdapter(mDetailAdapter);
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

    private void requestAddFoot(String menuId,String num,String state){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("menu_id",menuId);
        map.put("num",num);
        map.put("state",state);
        OkGoTools.getInstance().post("/merchant-api/updata_stste.api.php",map,this,new RequestCallBack(this){
            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Toasty.success(FootDetailActivity.this,"上架成功").show();
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