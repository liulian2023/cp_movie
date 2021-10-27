package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ChipModel extends CommonModel{
    String amount;
    int drawableId;
    boolean isCheck=false;//0未选中 1 选中
    boolean isCustom=false;//是否是自定义按钮
    boolean isCurrent = false;//是否是当前筹码

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public ChipModel(String amount, int drawableId) {
        this.amount = amount;
        this.drawableId = drawableId;
    }
}
