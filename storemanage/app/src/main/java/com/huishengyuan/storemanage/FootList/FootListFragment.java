package com.huishengyuan.storemanage.FootList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huishengyuan.storemanage.FootList.Adapter.FootListClassAdapter;
import com.huishengyuan.storemanage.FootList.Adapter.FootListCommodityAdapter;
import com.huishengyuan.storemanage.FootList.FootDetail.FootDetailActivity;
import com.huishengyuan.storemanage.FootList.Interface.FootListInterface;
import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FootListFragment extends SupportFragment implements FootListInterface {

    @BindView(R.id.footlist_class_recycle)
    RecyclerView mFootlistClassRecycle;
    @BindView(R.id.footlist_commodity_recycler)
    RecyclerView mFootlistCommodityRecycler;
    @BindView(R.id.footlist_commodity_refresh)
    TwinklingRefreshLayout mFootlistCommodityRefresh;


    private View mView;
    private String page;
    private FootListClassAdapter mClassAdapter;
    private FootListCommodityAdapter mCommodityAdapter;


    public static FootListFragment newInstance() {
        Bundle args = new Bundle();
        FootListFragment fragment = new FootListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_foot_list, container, false);
        ButterKnife.bind(this, mView);
        page = "1";
        customView();
        return mView;
    }

    private void customView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mFootlistClassRecycle.setLayoutManager(linearLayoutManager);
        mClassAdapter = new FootListClassAdapter();
        mFootlistClassRecycle.setAdapter(mClassAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        mFootlistCommodityRecycler.setLayoutManager(linearLayoutManager1);
        mCommodityAdapter = new FootListCommodityAdapter(getContext(),this);
        mFootlistCommodityRecycler.setAdapter(mCommodityAdapter);

        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mFootlistCommodityRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mFootlistCommodityRefresh.setBottomView(loadingView);
        mFootlistCommodityRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mFootlistCommodityRefresh.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mFootlistCommodityRefresh.finishLoadmore();
            }
        });
    }

    @Override
    public void onClickCommodityItem(int poist) {
        Intent intent = new Intent(getActivity(), FootDetailActivity.class);
        startActivity(intent);
    }
}
