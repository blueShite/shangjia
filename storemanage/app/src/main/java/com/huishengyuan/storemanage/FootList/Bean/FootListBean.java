package com.huishengyuan.storemanage.FootList.Bean;

/**
 * Created by longhengyu on 2017/8/8.
 */

public class FootListBean {

    /**
     * menu_id : 541
     * price : 15.00
     * dish : 农家小炒肉盖饭
     * type_id : 1
     */

    private String menu_id;
    private String price;
    private String dish;
    private String type_id;
    private String litpic;
    private String state;

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
