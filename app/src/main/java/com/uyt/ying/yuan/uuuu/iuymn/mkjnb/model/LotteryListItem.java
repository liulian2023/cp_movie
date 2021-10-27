package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class LotteryListItem {

    private long cpId;
    private long game;
    private int type_id;
    private String name;
    private String imgUrl;
    private boolean isLive;
    private boolean isXian;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCpId() {
        return cpId;
    }

    public void setCpId(long cpId) {
        this.cpId = cpId;
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
        this.game = game;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }
}
