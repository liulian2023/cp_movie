package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class NoticeMulBean implements MultiItemEntity {

    private int itemType; // 布局类型  1  系统   2 中奖
    private String content;
    private String name;
    private String lotplace;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getLotplace() {
        return lotplace;
    }

    public void setLotplace(String lotplace) {
        this.lotplace = lotplace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
