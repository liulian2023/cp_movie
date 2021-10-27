package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class WxPayMedol implements Serializable {
     String passagewayName;//充值通道名称(弃用)
     String personName;//收款人账号
     String bankNum;//收款账号
     String bankName;//充值通道名称
     BigDecimal max;//最小金额
     BigDecimal mix;//最大金额
     String bankId;//银行id
     int status=0;//checkBox选中状态 0 未选中 1选中
     String thirdPartyFlag;//为空则为第三方支付
     String payTypeImage;//thirdPartyFlag为空时显示的二维码图片
    String remark;//下方提示文字

    public String getThirdPartyFlag() {
        return thirdPartyFlag;
    }

    public String getPayTypeImage() {
        return payTypeImage;
    }

    public void setPayTypeImage(String payTypeImage) {
        this.payTypeImage = payTypeImage;
    }

    public WxPayMedol(String passagewayName, String personName, String bankNum, String bankName, BigDecimal max, BigDecimal mix, String bankId, String thirdPartyFlag, String payTypeImage,String remark) {
        this.passagewayName = passagewayName;
        this.personName = personName;
        this.bankNum = bankNum;
        this.bankName = bankName;
        this.max = max;
        this.mix = mix;
        this.bankId = bankId;
        this.thirdPartyFlag = thirdPartyFlag;
        this.payTypeImage = payTypeImage;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setThirdPartyFlag(String thirdPartyFlag) {
        this.thirdPartyFlag = thirdPartyFlag;
    }

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
