package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class MabualRechargeCenterEntity {
    String rechargeAccount;
    String czFdRate;

    public MabualRechargeCenterEntity(String rechargeAccount, String czFdRate) {
        this.rechargeAccount = rechargeAccount;
        this.czFdRate = czFdRate;
    }

    public String getCzFdRate() {
        return czFdRate;
    }

    public void setCzFdRate(String czFdRate) {
        this.czFdRate = czFdRate;
    }

    public String getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(String rechargeAccount) {
        this.rechargeAccount = rechargeAccount;
    }


}
