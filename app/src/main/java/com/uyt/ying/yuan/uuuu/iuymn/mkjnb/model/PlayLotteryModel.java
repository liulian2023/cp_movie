package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class PlayLotteryModel extends CommonModel {
    private String imageUrl;
    private String name;
    int type_id;
    String isopenOffice;
    String isStart;
    long game;
    long id;
    boolean isLive;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getIsopenOffice() {
        return isopenOffice;
    }

    public void setIsopenOffice(String isopenOffice) {
        this.isopenOffice = isopenOffice;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
        this.game = game;
    }

    public PlayLotteryModel(String imageUrl, String name, int type_id, String isopenOffice, String isStart, long game,long id  ) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.type_id = type_id;
        this.isopenOffice = isopenOffice;
        this.isStart = isStart;
        this.game = game;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
