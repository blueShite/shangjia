package com.huishengyuan.storemanage.FootList.FootDetail.Bean;

/**
 * Created by longhengyu on 2017/8/8.
 */

public class FootDetailBean {

    /**
     * tact : 1.蒜切片、青椒及干辣椒去蒂去籽切环圈状待用;
     2.炒锅烧热倒油，下肉片煸炒，炒至表皮焦香出油将肉片盛出待用;
     3.锅内留底油，下蒜片、青椒煸炒，煸炒至椒皮稍微起皱起皮，少许盐炒均匀下干辣椒煸香。
     4.将五花肉片下锅与青椒一同翻炒均匀，锅内喷少许料酒，调入白糖、盐适量，少许老抽翻炒均匀即可(如有高汤可加2汤勺，没有视情况加少许水)。
     * price : 0.00
     * litpic : /images/menu_img/20161021/20161021220039.png
     * state : 1
     * res_name : 一餐厅一窗口
     * ban : 1、猪肉含有丰富的优质蛋白质和必需的脂肪酸，并提供血红素（有机铁）和促进铁吸收的半胱氨酸，能改善缺铁性贫血。
     2、辣椒具有开胃、加快新陈代谢、杀抑胃腹中的细菌等作用；富含的维C，可以控制心脏病及冠状动脉硬化，降低胆固醇；含有较多抗氧化物质，可预防癌症及其他慢性疾病。
     * dish : 农家小炒肉盖饭
     */

    private String tact;
    private String price;
    private String litpic;
    private String state;
    private String res_name;
    private String ban;
    private String dish;

    public String getTact() {
        return tact;
    }

    public void setTact(String tact) {
        this.tact = tact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }
}
