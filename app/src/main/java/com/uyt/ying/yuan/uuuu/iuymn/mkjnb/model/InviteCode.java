package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class InviteCode implements Serializable {
    String inviteCode;//邀请码
    String createdDate;//创建时间
    String num;//注册人数
    String codeUrl;
    String k3Rate;
    String happytenRate;
    String sscaiRate;
    String happy8Rate;
    String xuanwuRate;
    String farmRate;
    String raceRate;
    String sixRate;
    String danRate;




    public InviteCode(String inviteCode, String createdDate, String num, String codeUrl, String k3Rate, String happytenRate, String sscaiRate, String happy8Rate, String xuanwuRate, String farmRate, String raceRate, String sixRate, String danRate) {
        this.inviteCode = inviteCode;
        this.createdDate = createdDate;
        this.num = num;
        this.codeUrl = codeUrl;
        this.k3Rate = k3Rate;
        this.happytenRate = happytenRate;
        this.sscaiRate = sscaiRate;
        this.happy8Rate = happy8Rate;
        this.xuanwuRate = xuanwuRate;
        this.farmRate = farmRate;
        this.raceRate = raceRate;
        this.sixRate = sixRate;
        this.danRate = danRate;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getK3Rate() {
        return k3Rate;
    }

    public void setK3Rate(String k3Rate) {
        this.k3Rate = k3Rate;
    }

    public String getHappytenRate() {
        return happytenRate;
    }

    public void setHappytenRate(String happytenRate) {
        this.happytenRate = happytenRate;
    }

    public String getSscaiRate() {
        return sscaiRate;
    }

    public void setSscaiRate(String sscaiRate) {
        this.sscaiRate = sscaiRate;
    }

    public String getHappy8Rate() {
        return happy8Rate;
    }

    public void setHappy8Rate(String happy8Rate) {
        this.happy8Rate = happy8Rate;
    }

    public String getXuanwuRate() {
        return xuanwuRate;
    }

    public void setXuanwuRate(String xuanwuRate) {
        this.xuanwuRate = xuanwuRate;
    }

    public String getFarmRate() {
        return farmRate;
    }

    public void setFarmRate(String farmRate) {
        this.farmRate = farmRate;
    }

    public String getRaceRate() {
        return raceRate;
    }

    public void setRaceRate(String raceRate) {
        this.raceRate = raceRate;
    }

    public String getSixRate() {
        return sixRate;
    }

    public void setSixRate(String sixRate) {
        this.sixRate = sixRate;
    }

    public String getDanRate() {
        return danRate;
    }

    public void setDanRate(String danRate) {
        this.danRate = danRate;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
