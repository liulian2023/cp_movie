package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class QuotaModel extends CommonModel {
    String name;
    String amount;
    String imageUrl;
    int autoConvert;
    String game;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAutoConvert() {
        return autoConvert;
    }

    public void setAutoConvert(int autoConvert) {
        this.autoConvert = autoConvert;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuotaModel(String name, String amount, String imageUrl, int autoConvert) {
        this.name = name;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.autoConvert = autoConvert;
    }
}
