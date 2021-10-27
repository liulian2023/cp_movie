

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class YueBaoMedol extends CommonModel {
    //余额宝交易类型
    String remark;
    //时间
    String time;
    //交易金额
    String amount;
    //交易的type,用于判断是入账还是出账
    String amountType;

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public YueBaoMedol(String remark, String time, String amount, String amountType) {
        this.remark = remark;
        this.time = time;
        this.amount = amount;
        this.amountType = amountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
