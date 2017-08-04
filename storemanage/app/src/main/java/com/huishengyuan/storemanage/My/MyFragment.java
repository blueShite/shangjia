package com.huishengyuan.storemanage.My;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends SupportFragment {

    @BindView(R.id.text_my_todayPrice)
    TextView mTextMyTodayPrice;
    @BindView(R.id.text_my_weekPrice)
    TextView mTextMyWeekPrice;
    @BindView(R.id.text_my_monthPrice)
    TextView mTextMyMonthPrice;
    @BindView(R.id.text_my_todayNum)
    TextView mTextMyTodayNum;
    @BindView(R.id.text_my_weekNum)
    TextView mTextMyWeekNum;
    @BindView(R.id.text_my_monthNum)
    TextView mTextMyMonthNum;
    @BindView(R.id.my_refresh)
    TwinklingRefreshLayout mMyRefresh;

    private View mView;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, mView);
        customView();
        return mView;
    }

    private void customView() {

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mMyRefresh.setHeaderView(headerView);

        mMyRefresh.setEnableLoadmore(false);
        mMyRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {

                mMyRefresh.finishRefreshing();
            }
        });

    }
}
