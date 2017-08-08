package com.huishengyuan.storemanage.FootList.Interface;

import com.huishengyuan.storemanage.FootList.Bean.FootClassBean;
import com.huishengyuan.storemanage.FootList.Bean.FootListBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/8/4.
 */

public interface FootListInterface {


    void requestClassSucess(List<FootClassBean> list);

    void requestListSucess(List<FootListBean> list,String classId);

    void requestListError(String classId);

    void onClickClassify(int poist);

    void onClickCommodityItem(int poist);
}
