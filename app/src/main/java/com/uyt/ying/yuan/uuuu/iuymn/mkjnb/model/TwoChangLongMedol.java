package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class TwoChangLongMedol extends CommonModel {

    String type;
    String count;
    int game;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public TwoChangLongMedol(String type, String count, int game) {
        this.type = type;
        this.count = count;
        this.game = game;
    }
}
