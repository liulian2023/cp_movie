package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class SscOpenResultModel implements Serializable {
    String qishu;
    String shijian;
    String numList;
    String hezhi;
    String daxiao;
    String danshuang;
    String longhu;
    String markqs;
    String markzs;
    String markhs;
    String markwdx;
    boolean isGuanYa;
    String numListCopy;//点击大小 单双 冠亚和按钮时需要用到的值

    public String getNumListCopy() {
        return numListCopy;
    }

    public void setNumListCopy(String numListCopy) {
        this.numListCopy = numListCopy;
    }
/*
    String markhs;    //后三变前三
    String markqs;    //前三变中三
    String markzs;
 */

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

    public String getLonghu() {
        return longhu;
    }

    public void setLonghu(String longhu) {
        this.longhu = longhu;
    }

    public String getMarkqs() {
        return markqs;
    }

    public void setMarkqs(String markqs) {
        this.markqs = markqs;
    }

    public String getMarkzs() {
        return markzs;
    }

    public void setMarkzs(String markzs) {
        this.markzs = markzs;
    }

    public String getMarkhs() {
        return markhs;
    }

    public void setMarkhs(String markhs) {
        this.markhs = markhs;
    }

    public String getMarkwdx() {
        return markwdx;
    }

    public void setMarkwdx(String markwdx) {
        this.markwdx = markwdx;
    }

    public boolean isGuanYa() {
        return isGuanYa;
    }

    public void setGuanYa(boolean guanYa) {
        isGuanYa = guanYa;
    }

    public SscOpenResultModel(String qishu, String shijian, String numList, String hezhi, String daxiao, String danshuang, String longhu, String markqs, String markzs, String markhs, String markwdx, boolean isGuanYa,String numListCopy) {
        this.qishu = qishu;
        this.shijian = shijian;
        this.numList = numList;
        this.hezhi = hezhi;
        this.daxiao = daxiao;
        this.danshuang = danshuang;
        this.longhu = longhu;
        this.markqs = markqs;
        this.markzs = markzs;
        this.markhs = markhs;
        this.markwdx = markwdx;
        this.isGuanYa = isGuanYa;
        this.numListCopy = numListCopy;
    }

    public SscOpenResultModel(String qishu, String shijian, String numList, String hezhi, String daxiao, String danshuang, String longhu, String markqs, String markzs, String markhs, boolean isGuanYa,String numListCopy) {
        this.qishu = qishu;
        this.shijian = shijian;
        this.numList = numList;
        this.hezhi = hezhi;
        this.daxiao = daxiao;
        this.danshuang = danshuang;
        this.longhu = longhu;
        this.markqs = markqs;
        this.markzs = markzs;
        this.markhs = markhs;
        this.isGuanYa = isGuanYa;
        this.numListCopy = numListCopy;
    }
}
