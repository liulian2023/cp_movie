package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ManualRechargeEntity {
    String manualName;
    String availableQuota;

    public ManualRechargeEntity(String manualName, String availableQuota) {
        this.manualName = manualName;
        this.availableQuota = availableQuota;
    }

    public String getAvailableQuota() {
        return availableQuota;
    }

    public void setAvailableQuota(String availableQuota) {
        this.availableQuota = availableQuota;
    }

    public String getManualName() {
        return manualName;
    }

    public void setManualName(String manualName) {
        this.manualName = manualName;
    }

}
