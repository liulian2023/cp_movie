package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyMedol implements Serializable {
     String passagewayName;//充值通道名称
     String personName;//收款人账号
     String bankNum;
     String bankName;
     BigDecimal max;
     BigDecimal mix;
     String bankId;//银行id
     int status=0;


    public int getStatus() {
        return status;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CompanyMedol(String passagewayName, String personName, String bankNum, String bankName, BigDecimal max, BigDecimal mix, String bankId) {
        this.passagewayName = passagewayName;
        this.personName = personName;
        this.bankNum = bankNum;
        this.bankName = bankName;
        this.max = max;
        this.mix = mix;
        this.bankId = bankId;
    }

    public BigDecimal getMax() {

        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMix() {
        return mix;
    }

    public void setMix(BigDecimal mix) {
        this.mix = mix;
    }

    public String getPassagewayName() {
        return passagewayName;
    }

    public void setPassagewayName(String passagewayName) {
        this.passagewayName = passagewayName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


}
