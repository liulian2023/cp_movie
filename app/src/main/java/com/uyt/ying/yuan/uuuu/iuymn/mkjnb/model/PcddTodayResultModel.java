package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class PcddTodayResultModel extends CommonModel {
    String qishu;
    String time;
    String lotteryNo;
    String hezhi;
    String daxiao;
    String xingtai;

    public PcddTodayResultModel(String qishu, String time, String lotteryNo, String hezhi, String daxiao, String xingtai) {
        this.qishu = qishu;
        this.time = time;
        this.lotteryNo = lotteryNo;
        this.hezhi = hezhi;
        this.daxiao = daxiao;
        this.xingtai = xingtai;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getHezhi() {
        return hezhi;
    }

    public void setHezhi(String hezhi) {
        this.hezhi = hezhi;
    }

    public String getXingtai() {
        return xingtai;
    }

    public void setXingtai(String xingtai) {
        this.xingtai = xingtai;
    }

    public String getDaxiao() {
        return daxiao;
    }

    public void setDaxiao(String daxiao) {
        this.daxiao = daxiao;
    }


}
