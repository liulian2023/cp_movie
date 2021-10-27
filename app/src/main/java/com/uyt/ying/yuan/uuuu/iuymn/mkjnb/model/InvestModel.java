package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info.RechargeBank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InvestModel implements Serializable {
    String imgUrl;
    String type;
    BigDecimal max;
    BigDecimal mix;
    List<RechargeBank> rechargeBankList;
    List<ManualEntity> manualEntityList;

    public InvestModel(String imgUrl, String type, BigDecimal max, BigDecimal mix, List<RechargeBank> rechargeBankList) {
        this.imgUrl = imgUrl;
        this.type = type;
        this.max = max;
        this.mix = mix;
        this.rechargeBankList = rechargeBankList;
    }

    public InvestModel(String type,List<ManualEntity> manualEntityList) {
        this.type = type;
        this.manualEntityList = manualEntityList;
    }
    public List<ManualEntity> getManualEntityList() {
        return manualEntityList;
    }

    public void setManualEntityList(List<ManualEntity> manualEntityList) {
        this.manualEntityList = manualEntityList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<RechargeBank> getRechargeBankList() {
        return rechargeBankList;
    }

    public void setRechargeBankList(List<RechargeBank> rechargeBankList) {
        this.rechargeBankList = rechargeBankList;
    }
}
