package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class BetChoosePalyModel extends CommonModel{
    String type;
    int status=0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BetChoosePalyModel(String type) {
        this.type = type;
    }
}
