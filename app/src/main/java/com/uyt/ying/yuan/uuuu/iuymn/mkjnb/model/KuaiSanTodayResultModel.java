package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class KuaiSanTodayResultModel extends CommonModel {
    String qishu;
    String lotteryNo;
    String remark;//大小
    String time;

    public KuaiSanTodayResultModel(String qishu, String lotteryNo, String remark,String time) {
        this.qishu = qishu;
        this.lotteryNo = lotteryNo;
        this.remark = remark;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQishu() {

        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
