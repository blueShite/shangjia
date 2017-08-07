package com.huishengyuan.storemanage.Manage;

import com.alibaba.fastjson.JSON;
import com.huishengyuan.storemanage.Manage.Bean.LoginBean;
import com.huishengyuan.storemanage.Tools.MyApplication;
import com.huishengyuan.storemanage.Tools.utils.SharePrefUtil;

/**
 * Created by longhengyu on 2017/8/7.
 */

public class LoginManage {

    private static LoginManage instance = new LoginManage();
    private LoginManage (){}
    public static LoginManage getInstance() {
        return instance;
    }

    private String loginStr = "SaveLogin";

    public void saveLoginBean(LoginBean bean) {
        if(bean==null){
            SharePrefUtil.saveString(MyApplication.getContext(),loginStr,"");
            return;
        }
        String beanStr = JSON.toJSONString(bean);
        SharePrefUtil.saveString(MyApplication.getContext(),loginStr,beanStr);

    }

    public LoginBean getLoginBean(){

        String beanStr = SharePrefUtil.getString(MyApplication.getContext(),loginStr,"");
        if(beanStr.isEmpty()){
            return null;
        }
        LoginBean bean = JSON.parseObject(beanStr,LoginBean.class);
        return bean;
    }
}
