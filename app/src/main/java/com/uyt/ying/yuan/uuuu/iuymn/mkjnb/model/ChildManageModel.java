package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChildManageModel implements Serializable {
    String name;
    String level;
    String childNUm;
    String loginDate;
    String registerDate;
    String agentLevel;
    String user_id;
    BigDecimal k3Rate;
    BigDecimal sscaiRate;
    BigDecimal happytenRate;
    BigDecimal happy8Rate;
    BigDecimal xuanwuRate;
    BigDecimal farmRate;
    BigDecimal raceRate;
    BigDecimal sixRate;
    BigDecimal danRate;
    BigDecimal commission;
    BigDecimal amount;
    String orderBy;


    public ChildManageModel(String name, String level, String childNUm,
                            String loginDate, String registerDate, String agentLevel,
                            String user_id, BigDecimal k3Rate, BigDecimal sscaiRate,
                            BigDecimal happytenRate, BigDecimal happy8Rate, BigDecimal
                            xuanwuRate, BigDecimal farmRate, BigDecimal raceRate,
                            BigDecimal sixRate, BigDecimal danRate, BigDecimal commission, BigDecimal amount,String orderBy)
    {
        this.name = name;
        this.level = level;
        this.childNUm = childNUm;
        this.loginDate = loginDate;
        this.registerDate = registerDate;
        this.agentLevel = agentLevel;
        this.user_id = user_id;
        this.k3Rate = k3Rate;
        this.sscaiRate = sscaiRate;
        this.happytenRate = happytenRate;
        this.happy8Rate = happy8Rate;
        this.xuanwuRate = xuanwuRate;
        this.farmRate = farmRate;
        this.raceRate = raceRate;
        this.sixRate = sixRate;
        this.danRate = danRate;
        this.commission = commission;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getChildNUm() {
        return childNUm;
    }

    public void setChildNUm(String childNUm) {
        this.childNUm = childNUm;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getK3Rate() {
        return k3Rate;
    }

    public void setK3Rate(BigDecimal k3Rate) {
        this.k3Rate = k3Rate;
    }

    public BigDecimal getSscaiRate() {
        return sscaiRate;
    }

    public void setSscaiRate(BigDecimal sscaiRate) {
        this.sscaiRate = sscaiRate;
    }

    public BigDecimal getHappytenRate() {
        return happytenRate;
    }

    public void setHappytenRate(BigDecimal happytenRate) {
        this.happytenRate = happytenRate;
    }

    public BigDecimal getHappy8Rate() {
        return happy8Rate;
    }

    public void setHappy8Rate(BigDecimal happy8Rate) {
        this.happy8Rate = happy8Rate;
    }

    public BigDecimal getXuanwuRate() {
        return xuanwuRate;
    }

    public void setXuanwuRate(BigDecimal xuanwuRate) {
        this.xuanwuRate = xuanwuRate;
    }

    public BigDecimal getFarmRate() {
        return farmRate;
    }

    public void setFarmRate(BigDecimal farmRate) {
        this.farmRate = farmRate;
    }

    public BigDecimal getRaceRate() {
        return raceRate;
    }

    public void setRaceRate(BigDecimal raceRate) {
        this.raceRate = raceRate;
    }

    public BigDecimal getSixRate() {
        return sixRate;
    }

    public void setSixRate(BigDecimal sixRate) {
        this.sixRate = sixRate;
    }

    public BigDecimal getDanRate() {
        return danRate;
    }

    public void setDanRate(BigDecimal danRate) {
        this.danRate = danRate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
