package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info.RechargeBank;

import java.io.Serializable;
import java.util.List;

public class RechargeBankData implements Serializable {
    List<RechargeBank> rechargeBankList;

    public void setRechargeBankList(List<RechargeBank> rechargeBankList) {
        this.rechargeBankList = rechargeBankList;
    }

    public List<RechargeBank> getRechargeBankList() {
        return rechargeBankList;
    }

    @Override
    public String toString() {
        return "RechargeBankData{" +
                "rechargeBankList=" + rechargeBankList +
                '}';
    }
}
