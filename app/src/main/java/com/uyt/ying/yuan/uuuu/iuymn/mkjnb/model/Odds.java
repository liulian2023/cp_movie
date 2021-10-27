package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class Odds implements Serializable {

    String rebate;
    String odds;

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public Odds(String rebate, String odds) {
        this.rebate = rebate;
        this.odds = odds;
    }
}
