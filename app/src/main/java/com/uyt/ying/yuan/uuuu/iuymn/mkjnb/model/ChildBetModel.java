package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChildBetModel implements Serializable {
    String lotteryNo;//上期开奖号码
    BigDecimal amount ;//投注金额
    String createdDate;//投注时间
    BigDecimal bonus;//中奖金额
    String nikeName;//用户名
    String lotteryqishu;//彩票期数
    String perordercode;//注单号
    String rulename ;//投注类别
    String remark;////状态(中奖 未中 等待开奖)
    String groupname ;//投注类别
    String typename;//彩票类型

    public ChildBetModel(String lotteryNo, BigDecimal amount, String createdDate, BigDecimal bonus, String nikeName, String lotteryqishu, String perordercode, String rulename, String remark, String groupname, String typename) {
        this.lotteryNo = lotteryNo;
        this.amount = amount;
        this.createdDate = createdDate;
        this.bonus = bonus;
        this.nikeName = nikeName;
        this.lotteryqishu = lotteryqishu;
        this.perordercode = perordercode;
        this.rulename = rulename;
        this.remark = remark;
        this.groupname = groupname;
        this.typename = typename;
    }

    public String getLotteryNo() {

        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getLotteryqishu() {
        return lotteryqishu;
    }

    public void setLotteryqishu(String lotteryqishu) {
        this.lotteryqishu = lotteryqishu;
    }

    public String getPerordercode() {
        return perordercode;
    }

    public void setPerordercode(String perordercode) {
        this.perordercode = perordercode;
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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
