package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class InviteRedModel extends CommonModel {
    public   int peoperNum;
    public    boolean isOpened;
    public  boolean canOpen;
    public   int redCount;//一次领取几个红包
    public  float rightProgress;
    public   float leftProgress;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isCanOpen() {
        return canOpen;
    }

    public void setCanOpen(boolean canOpen) {
        this.canOpen = canOpen;
    }

    public int getRedCount() {
        return redCount;
    }

    public void setRedCount(int redCount) {
        this.redCount = redCount;
    }

    public float getRightProgress() {
        return rightProgress;
    }

    public void setRightProgress(float rightProgress) {
        this.rightProgress = rightProgress;
    }

    public float getLeftProgress() {
        return leftProgress;
    }

    public void setLeftProgress(float leftProgress) {
        this.leftProgress = leftProgress;
    }

    public int getPeoperNum() {
        return peoperNum;
    }

    public void setPeoperNum(int peoperNum) {
        this.peoperNum = peoperNum;
    }
}
