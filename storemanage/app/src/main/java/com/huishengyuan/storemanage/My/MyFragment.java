package com.huishengyuan.storemanage.My;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Manage.LoginManage;
import com.huishengyuan.storemanage.My.MySub.MySetActivity;
import com.huishengyuan.storemanage.My.bean.MyBean;
import com.huishengyuan.storemanage.NetWork.OkGoTools;
import com.huishengyuan.storemanage.NetWork.RequestCallBack;
import com.huishengyuan.storemanage.NetWork.RequestStringBean;
import com.huishengyuan.storemanage.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        requestMyMessage(LoginManage.getInstance().getLoginBean().getRes_id());
    }

    private void customView() {

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mMyRefresh.setHeaderView(headerView);

        mMyRefresh.setEnableLoadmore(false);
        mMyRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {

                mMyRefresh.finishRefreshing();
            }
        });

    }

    @OnClick(R.id.image_my_set)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), MySetActivity.class);
        startActivity(intent);
    }

    private void requestMyMessage(String resId){

        Map<String,String> map = new HashMap<>();
        map.put("id",resId);
        OkGoTools.getInstance().post("/merchant-api/my_list.api.php",map,getContext(),new RequestCallBack(getContext()){

            @Override
            public void onSuccess(Response<RequestStringBean> response) {
                super.onSuccess(response);
                if(response.isSuccessful()){
                    if(response.body().isRes()){
                        Log.e("个人信息:",response.body().getData());
                        MyBean bean = JSON.parseObject(response.body().getData(),MyBean.class);
                        mTextMyTodayPrice.setText(bean.getToday_j());
                        mTextMyWeekPrice.setText(bean.getWeek_j());
                        mTextMyMonthPrice.setText(bean.getMoon_j());
                        mTextMyTodayNum.setText(bean.getToday_d()+"");
                        mTextMyWeekNum.setText(bean.getWeek_d()+"");
                        mTextMyMonthNum.setText(bean.getMoon_d()+"");
                    }
                }
            }

            @Override
            public void onError(Response<RequestStringBean> response) {
                super.onError(response);
            }
        });

    }


}
