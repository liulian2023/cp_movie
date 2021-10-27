package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommonModel;

public class OpenRedBeen extends CommonModel {
    String  amount;
    boolean isOpened=false;
    int drawableId;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
