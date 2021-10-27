package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class RechargeCenterEntity {
    String mix;
    String max;
    String thirdPartyFlag;
    String useCzPriceManualInput;;//0否；1是(充值金额手动输入)
    List<String> czPrices;//充值金额列表(将0筛选后的结果)
    String czFdRate;

    public String getCzFdRate() {
        return czFdRate;
    }

    public void setCzFdRate(String czFdRate) {
        this.czFdRate = czFdRate;
    }

    public String getThirdPartyFlag() {
        return thirdPartyFlag;
    }

    public void setThirdPartyFlag(String thirdPartyFlag) {
        this.thirdPartyFlag = thirdPartyFlag;
    }

    public String getMix() {
        return mix;
    }

    public void setMix(String mix) {
        this.mix = mix;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getUseCzPriceManualInput() {
        return useCzPriceManualInput;
    }

    public void setUseCzPriceManualInput(String useCzPriceManualInput) {
        this.useCzPriceManualInput = useCzPriceManualInput;
    }

    public  List<String>  getCzPrices() {
        return czPrices;
    }

    public void setCzPrices( List<String>  czPrices) {
        this.czPrices = czPrices;
    }


    public RechargeCenterEntity(String mix, String max, String thirdPartyFlag, String useCzPriceManualInput, List<String> czPrices, String czFdRate) {
        this.mix = mix;
        this.max = max;
        this.thirdPartyFlag = thirdPartyFlag;
        this.useCzPriceManualInput = useCzPriceManualInput;
        this.czPrices = czPrices;
        this.czFdRate = czFdRate;
    }
}
