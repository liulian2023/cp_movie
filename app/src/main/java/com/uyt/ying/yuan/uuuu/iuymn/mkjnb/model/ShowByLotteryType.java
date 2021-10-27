package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ShowByLotteryType implements Serializable {
    String name;
    String xiazhu;
    String zhongjiang;
    String yingkui;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXiazhu() {
        return xiazhu;
    }

    public void setXiazhu(String xiazhu) {
        this.xiazhu = xiazhu;
    }

    public String getZhongjiang() {
        return zhongjiang;
    }

    public void setZhongjiang(String zhongjiang) {
        this.zhongjiang = zhongjiang;
    }

    public String getYingkui() {
        return yingkui;
    }

    public void setYingkui(String yingkui) {
        this.yingkui = yingkui;
    }

    public ShowByLotteryType(String name, String xiazhu, String zhongjiang, String yingkui) {
        this.name = name;
        this.xiazhu = xiazhu;
        this.zhongjiang = zhongjiang;
        this.yingkui = yingkui;
    }
}
