package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class ManualRechargeData implements Serializable {
    List<ManualEntity> rechargeBankList;

    public List<ManualEntity> getRechargeBankList() {
        return rechargeBankList;
    }

    public void setRechargeBankList(List<ManualEntity> rechargeBankList) {
        this.rechargeBankList = rechargeBankList;
    }

    @Override
    public String toString() {
        return "ManualRechargeData{" +
                "rechargeBankList=" + rechargeBankList +
                '}';
    }
}
