package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class FarmOpenResultModel implements Serializable {
    String qishu;
    String shijian;
    String numList;
    String hezhi;
    String daxiao;
    String danshuang;
    String weida;
    String marklh;
    boolean isGuanYa;
    List<String> longHuList;
    String numListCopy;

    public List<String> getLongHuList() {
        return longHuList;
    }

    public void setLongHuList(List<String> longHuList) {
        this.longHuList = longHuList;
    }

    public String getNumListCopy() {
        return numListCopy;
    }

    public void setNumListCopy(String numListCopy) {
        this.numListCopy = numListCopy;
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

    public String getHezhi() {
        return hezhi;
    }

    public void setHezhi(String hezhi) {
        this.hezhi = hezhi;
    }

    public String getDaxiao() {
        return daxiao;
    }

    public void setDaxiao(String daxiao) {
        this.daxiao = daxiao;
    }

    public String getDanshuang() {
        return danshuang;
    }

    public void setDanshuang(String danshuang) {
        this.danshuang = danshuang;
    }

    public String getWeida() {
        return weida;
    }

    public void setWeida(String weida) {
        this.weida = weida;
    }

    public String getMarklh() {
        return marklh;
    }

    public void setMarklh(String marklh) {
        this.marklh = marklh;
    }

    public boolean isGuanYa() {
        return isGuanYa;
    }

    public void setGuanYa(boolean guanYa) {
        isGuanYa = guanYa;
    }

    public FarmOpenResultModel(String qishu, String shijian, String numList, String hezhi, String daxiao, String danshuang, String weida, String marklh, boolean isGuanYa, List<String> longHuList, String numListCopy) {
        this.qishu = qishu;
        this.shijian = shijian;
        this.numList = numList;
        this.hezhi = hezhi;
        this.daxiao = daxiao;
        this.danshuang = danshuang;
        this.weida = weida;
        this.marklh = marklh;
        this.isGuanYa = isGuanYa;
        this.longHuList = longHuList;
        this.numListCopy = numListCopy;
    }
}
