package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class MakeMoneyModel extends CommonModel {
    int imgId;
    String type;
    String remark;

    public MakeMoneyModel(int imgId, String type, String remark) {
        this.imgId = imgId;
        this.type = type;
        this.remark = remark;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
