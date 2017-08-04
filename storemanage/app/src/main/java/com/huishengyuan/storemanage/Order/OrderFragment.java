package com.huishengyuan.storemanage.Order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.huishengyuan.storemanage.Order.Adapter.OrderAdapter;
import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends SupportFragment {

    @BindView(R.id.btn_order_title1)
    Button mBtnOrderTitle1;
    @BindView(R.id.btn_order_title2)
    Button mBtnOrderTitle2;
    @BindView(R.id.btn_order_title3)
    Button mBtnOrderTitle3;
    @BindView(R.id.btn_order_title4)
    Button mBtnOrderTitle4;
    @BindView(R.id.order_recycle)
    RecyclerView mOrderRecycle;
    @BindView(R.id.order_refresh)
    TwinklingRefreshLayout mOrderRefresh;

    private View mView;
    private String page;
    private OrderAdapter mOrderAdapter;

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, mView);
        page = "1";
        customView();
        return mView;
    }

    private void customView(){

        mBtnOrderTitle1.setSelected(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mOrderRecycle.setLayoutManager(layoutManager);
        mOrderAdapter = new OrderAdapter();
        mOrderRecycle.setAdapter(mOrderAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mOrderRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mOrderRefresh.setBottomView(loadingView);
        mOrderRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mOrderRefresh.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mOrderRefresh.finishLoadmore();
            }
        });
    }

    @OnClick({R.id.btn_order_title1, R.id.btn_order_title2, R.id.btn_order_title3, R.id.btn_order_title4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_order_title1:
                mBtnOrderTitle1.setSelected(true);
                mBtnOrderTitle2.setSelected(false);
                mBtnOrderTitle3.setSelected(false);
                mBtnOrderTitle4.setSelected(false);
                break;
            case R.id.btn_order_title2:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(true);
                mBtnOrderTitle3.setSelected(false);
                mBtnOrderTitle4.setSelected(false);
                break;
            case R.id.btn_order_title3:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(false);
                mBtnOrderTitle3.setSelected(true);
                mBtnOrderTitle4.setSelected(false);
                break;
            case R.id.btn_order_title4:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(false);
                mBtnOrderTitle3.setSelected(false);
                mBtnOrderTitle4.setSelected(true);
                break;
        }
    }
}
