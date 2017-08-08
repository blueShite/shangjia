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
import com.huishengyuan.storemanage.FootList.Bean.FootClassBean;
import com.huishengyuan.storemanage.FootList.Bean.FootListBean;
import com.huishengyuan.storemanage.FootList.FootDetail.FootDetailActivity;
import com.huishengyuan.storemanage.FootList.Interface.FootListInterface;
import com.huishengyuan.storemanage.FootList.Presenter.FootListPresenter;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

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
    private FootListClassAdapter mClassAdapter;
    private FootListCommodityAdapter mCommodityAdapter;
    private FootListPresenter mPresenter = new FootListPresenter(this);
    private List<FootClassBean> mClassList = new ArrayList<>();
    private List<FootListBean> mList = new ArrayList<>();
    private String selectClassId;


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
        customView();
        selectClassId = "-1";
        mPresenter.requestClassList(LoginManage.getInstance().getLoginBean().getRes_id());
        return mView;
    }

    private void customView(){

        mPresenter.setContext(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mFootlistClassRecycle.setLayoutManager(linearLayoutManager);
        mClassAdapter = new FootListClassAdapter(mClassList,this);
        mFootlistClassRecycle.setAdapter(mClassAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        mFootlistCommodityRecycler.setLayoutManager(linearLayoutManager1);
        mCommodityAdapter = new FootListCommodityAdapter(mList,getContext(),this);
        mFootlistCommodityRecycler.setAdapter(mCommodityAdapter);

        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mFootlistCommodityRefresh.setHeaderView(headerView);
        mFootlistCommodityRefresh.setEnableLoadmore(false);
        mFootlistCommodityRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mPresenter.requestFootList(LoginManage.getInstance().getLoginBean().getRes_id(),selectClassId);
            }
        });
    }

    @Override
    public void requestClassSucess(List<FootClassBean> list) {
        mClassList.clear();
        mClassList.addAll(list);
        mClassAdapter.notifyDataSetChanged();
        selectClassId = mClassList.get(0).getId();
        mPresenter.requestFootList(LoginManage.getInstance().getLoginBean().getRes_id(),mClassList.get(0).getId());

    }

    @Override
    public void requestListSucess(List<FootListBean> list,String classId) {
        mFootlistCommodityRefresh.finishRefreshing();
        selectClassId = classId;
        mList.clear();
        mList.addAll(list);
        mCommodityAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestListError(String classId) {
        mFootlistCommodityRefresh.finishRefreshing();
        selectClassId = classId;
        mList.clear();
        mCommodityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickClassify(int poist) {

        for (int i=0;i<mClassList.size();i++){
            FootClassBean bean = mClassList.get(i);
            if(i==poist){
                bean.setSelect(true);
            }else {
                bean.setSelect(false);
            }
        }
        mClassAdapter.notifyDataSetChanged();
        selectClassId = mClassList.get(poist).getId();
        mPresenter.requestFootList(LoginManage.getInstance().getLoginBean().getRes_id(),selectClassId);

    }

    @Override
    public void onClickCommodityItem(int poist) {
        Intent intent = new Intent(getActivity(), FootDetailActivity.class);
        intent.putExtra("footId",mList.get(poist).getMenu_id());
        startActivity(intent);
    }
}
