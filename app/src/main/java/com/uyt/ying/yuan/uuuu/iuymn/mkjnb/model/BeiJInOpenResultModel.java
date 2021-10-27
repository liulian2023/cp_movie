package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class BeiJInOpenResultModel implements Serializable {
    String qishu;
    String shijian;
    String numList;
    boolean isGuanYa;
    String numListCopy;
    String sum;
    String markdx;
    String markds;
    List<String> marklh;

    public List<String> getMarklh() {
        return marklh;
    }

    public void setMarklh(List<String> marklh) {
        this.marklh = marklh;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getMarkdx() {
        return markdx;
    }

    public void setMarkdx(String markdx) {
        this.markdx = markdx;
    }

    public String getMarkds() {
        return markds;
    }

    public void setMarkds(String markds) {
        this.markds = markds;
    }

    public String getNumListCopy() {
        return numListCopy;
    }

    public void setNumListCopy(String numListCopy) {
        this.numListCopy = numListCopy;
    }

    public BeiJInOpenResultModel(String qishu, String shijian, String numList, boolean isGuanYa, String numListCopy,String sum,String markdx,String markds,List<String> marklh) {
        this.qishu = qishu;
        this.shijian = shijian;
        this.numList = numList;
        this.isGuanYa = isGuanYa;
        this.numListCopy = numListCopy;
        this.sum = sum;
        this.markdx = markdx;
        this.markds = markds;
        this.marklh = marklh;
    }

    public String getQishu() {

        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getNumList() {
        return numList;
    }

    public void setNumList(String numList) {
        this.numList = numList;
    }

    public boolean isGuanYa() {
        return isGuanYa;
    }

    public void setGuanYa(boolean guanYa) {
        isGuanYa = guanYa;
    }
}
