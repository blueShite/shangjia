package com.huishengyuan.storemanage.FootList.FootDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huishengyuan.storemanage.Base.BaseActivity;
import com.huishengyuan.storemanage.FootList.FootDetail.Adapter.FootDetailAdapter;
import com.huishengyuan.storemanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FootDetailActivity extends BaseActivity {


    @BindView(R.id.foot_detail_recycler)
    RecyclerView mFootDetailRecycler;

    private FootDetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_detail);
        ButterKnife.bind(this);
        customView();
    }

    @OnClick(R.id.image_footDetail_back)
    public void onViewClicked() {
        finish();
    }

    private void customView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(FootDetailActivity.this);
        mFootDetailRecycler.setLayoutManager(layoutManager);
        mDetailAdapter = new FootDetailAdapter();
        mFootDetailRecycler.setAdapter(mDetailAdapter);

    }
}