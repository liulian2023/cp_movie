package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MineIntegralMedol implements Serializable {
//    String remark;
    BigDecimal price;
    String createDate;
    Long type;

//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public MineIntegralMedol(/*String remark,*/ BigDecimal price, String createDate, Long type) {

//        this.remark = remark;
        this.price = price;
        this.createDate = createDate;
        this.type = type;
    }
}
