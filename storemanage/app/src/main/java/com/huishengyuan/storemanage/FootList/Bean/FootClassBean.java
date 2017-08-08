package com.huishengyuan.storemanage.FootList.Bean;

/**
 * Created by longhengyu on 2017/8/8.
 */

public class FootClassBean {

    /**
     * id : 1
     * typename : 热菜
     */

    private String id;
    private String typename;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
