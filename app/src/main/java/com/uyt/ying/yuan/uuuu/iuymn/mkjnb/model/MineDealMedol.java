package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MineDealMedol implements Serializable {
    BigDecimal amout;
    String time;
    Long type;
    String remark;
    String what;
    String status;
    String typeName;
    String bankkName;

    public String getBankkName() {
        return bankkName;
    }

    public void setBankkName(String bankkName) {
        this.bankkName = bankkName;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public MineDealMedol(BigDecimal amout, String time, Long type, String remark, String what, String status, String typeName) {
        this.amout = amout;
        this.time = time;
        this.type = type;
        this.remark = remark;
        this.what = what;
        this.status = status;
        this.typeName = typeName;
    }
}
