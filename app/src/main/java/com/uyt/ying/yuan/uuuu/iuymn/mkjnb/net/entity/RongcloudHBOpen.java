package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户开活动红包返回的信息
 */
public class RongcloudHBOpen implements Serializable {
    private String status;
    private String message;
    private int isReceive;//0未领取;1已经领取完了

    /*趣约开始*/
    private BigDecimal price;//领取的金额
    private int lastNum;//还可以领取的次数

    /*专享开始*/
    private BigDecimal zxHBPrice;//领取的专享红包基恩
    private BigDecimal tzPrice;//专享昨日投注金额

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(int isReceive) {
        this.isReceive = isReceive;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getLastNum() {
        return lastNum;
    }

    public void setLastNum(int lastNum) {
        this.lastNum = lastNum;
    }

    public BigDecimal getZxHBPrice() {
        return zxHBPrice;
    }

    public void setZxHBPrice(BigDecimal zxHBPrice) {
        this.zxHBPrice = zxHBPrice;
    }

    public BigDecimal getTzPrice() {
        return tzPrice;
    }

    public void setTzPrice(BigDecimal tzPrice) {
        this.tzPrice = tzPrice;
    }
}
