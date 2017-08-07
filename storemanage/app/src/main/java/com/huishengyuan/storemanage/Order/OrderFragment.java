package com.huishengyuan.storemanage.Order;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.huishengyuan.storemanage.Manage.Bean.LoginBean;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.Order.Adapter.OrderAdapter;
import com.huishengyuan.storemanage.Order.Bean.OrderListBean;
import com.huishengyuan.storemanage.Order.Interface.OrderInterface;
import com.huishengyuan.storemanage.Order.OrderDetail.OrderDetailActivity;
import com.huishengyuan.storemanage.Order.Presenter.OrderPresenter;
import com.huishengyuan.storemanage.Order.ScanBarCode.ScanBarCodeActivity;
import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends SupportFragment implements OrderInterface {

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
    @BindView(R.id.image_order_scaner)
    ImageView mImageOrderScaner;

    private View mView;
    private OrderAdapter mOrderAdapter;
    private OrderPresenter mPresenter = new OrderPresenter(this);
    private String orderStatus;
    private List<OrderListBean> mList = new ArrayList<>();

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
        customView();
        orderStatus = "-1";
        return mView;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.requestList(LoginManage.getInstance().getLoginBean().getRes_id(),orderStatus);
    }

    private void customView() {

        mPresenter.setContext(getContext());
        mBtnOrderTitle1.setSelected(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mOrderRecycle.setLayoutManager(layoutManager);
        mOrderAdapter = new OrderAdapter(mList,getContext(),this);
        mOrderRecycle.setAdapter(mOrderAdapter);

        mImageOrderScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScanBarCodeActivity.class);
                startActivity(intent);
            }
        });

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mOrderRefresh.setHeaderView(headerView);

        mOrderRefresh.setEnableLoadmore(false);
        mOrderRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mOrderRefresh.finishRefreshing();
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
                orderStatus = "-1";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getRes_id(),orderStatus);
                break;
            case R.id.btn_order_title2:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(true);
                mBtnOrderTitle3.setSelected(false);
                mBtnOrderTitle4.setSelected(false);
                orderStatus = "1";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getRes_id(),orderStatus);
                break;
            case R.id.btn_order_title3:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(false);
                mBtnOrderTitle3.setSelected(true);
                mBtnOrderTitle4.setSelected(false);
                orderStatus = "2";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getRes_id(),orderStatus);
                break;
            case R.id.btn_order_title4:
                mBtnOrderTitle1.setSelected(false);
                mBtnOrderTitle2.setSelected(false);
                mBtnOrderTitle3.setSelected(false);
                mBtnOrderTitle4.setSelected(true);
                orderStatus = "3";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getRes_id(),orderStatus);
                break;
        }
    }

    @Override
    public void requestSucess(List<OrderListBean> list) {
        mList.clear();
        mList.addAll(list);
        mOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestError() {
        mList.clear();
        mOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickOrderItem(int poist) {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        startActivity(intent);
    }
}
