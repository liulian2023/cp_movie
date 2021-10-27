package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChildBetAndDealAllPop implements Serializable {
    String name;//投注明细中 是彩票名  交易明细中是筛选栏分类名
    String img;
    String game;//
    String typoeId;//投注明细中 是彩票二级id  交易明细中是筛选栏分类typeId
    int status=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTypoeId() {
        return typoeId;
    }

    public void setTypoeId(String typoeId) {
        this.typoeId = typoeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ChildBetAndDealAllPop(String name, String img, String game, String typoeId) {
        this.name = name;
        this.img = img;
        this.game = game;
        this.typoeId = typoeId;
    }
}
