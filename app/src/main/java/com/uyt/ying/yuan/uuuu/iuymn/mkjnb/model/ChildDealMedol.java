package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChildDealMedol implements Serializable {
    String nickName;
    BigDecimal amout;
    String time;
    Long type;
    String typeName;
    String remark;
    String what;
    String status;


    public String getNickName() {
        return nickName;
    }




    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ChildDealMedol(String nickName, BigDecimal amout, String time, Long type, String typeName, String remark,String what,String status) {

        this.nickName = nickName;
        this.amout = amout;
        this.time = time;
        this.type = type;
        this.typeName = typeName;
        this.remark = remark;
        this.what = what;
        this.status = status;
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

    public void setStatus(String outStatus) {
        this.status = outStatus;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
