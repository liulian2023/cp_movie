package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class OpenNoMulBean implements MultiItemEntity {

    private int itemType=0; // 布局类型

    private String name;   //开奖号码数目

    private int game;  // 1-9的彩种

    private String animal;  //六合彩 的生肖

    private String color;   //六合彩的颜色

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
