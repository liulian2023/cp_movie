package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class BeiJinOpenResultModel2 implements Serializable {
    String result;
    boolean isGuangYa;

    public BeiJinOpenResultModel2(String result, boolean isGuangYa) {
        this.result = result;
        this.isGuangYa = isGuangYa;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isGuangYa() {
        return isGuangYa;
    }

    public void setGuangYa(boolean guangYa) {
        isGuangYa = guangYa;
    }
}
