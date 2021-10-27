package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MineBetModel extends CommonModel implements Serializable   {

    String typeName;//彩票名
    String lotteryqishu;//期数
    String createdDate;//时间
    BigDecimal bonus;//中奖金额
    BigDecimal amount;//投注金额
    String groupname;//投注详情(前)
    String rulename;//投注详情(后)
    String remark;//类型
    String perordercode;//点击进入详情时的请求参数

    public String getTypeName() {
        return typeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public MineBetModel() {
    }

    public MineBetModel(String typeName, String lotteryqishu, String createdDate, BigDecimal bonus, BigDecimal amount, String groupname, String rulename, String remark, String perordercode) {

        this.typeName = typeName;
        this.lotteryqishu = lotteryqishu;
        this.createdDate = createdDate;
        this.bonus = bonus;
        this.amount = amount;
        this.groupname = groupname;
        this.rulename = rulename;
        this.remark = remark;
        this.perordercode = perordercode;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLotteryqishu() {
        return lotteryqishu;
    }

    public void setLotteryqishu(String lotteryqishu) {
        this.lotteryqishu = lotteryqishu;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPerordercode() {
        return perordercode;
    }

    public void setPerordercode(String perordercode) {
        this.perordercode = perordercode;
    }

}
