package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class TurntableResultModel {
    String remark;
    int drawableId;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public TurntableResultModel(String remark, int drawableId) {
        this.remark = remark;
        this.drawableId = drawableId;
    }

    public TurntableResultModel() {
    }
}
