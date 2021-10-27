package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class TurntableEntity {

    /**
     * effectivePerson : 5
     * lastNum : 4
     * data : 邀请转盘抽奖成功
     * zjList : [{"num":1,"remark":Utils.getString(R.string.18元现金券),"qzIndex":0}]
     * message : 邀请转盘抽奖成功
     * status : success
     */

    private String effectivePerson;
    private String lastNum;
    private String data;
    private String message;
    private String status;
    /**
     * num : 1
     * remark : 18元现金券
     * qzIndex : 0
     */

    private List<ZjListBean> zjList;

    public String getEffectivePerson() {
        return effectivePerson;
    }

    public void setEffectivePerson(String effectivePerson) {
        this.effectivePerson = effectivePerson;
    }

    public String getLastNum() {
        return lastNum;
    }

    public void setLastNum(String lastNum) {
        this.lastNum = lastNum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ZjListBean> getZjList() {
        return zjList;
    }

    public void setZjList(List<ZjListBean> zjList) {
        this.zjList = zjList;
    }

    public static class ZjListBean {
        private String num;
        private String remark;
        private int qzIndex;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getQzIndex() {
            return qzIndex;
        }

        public void setQzIndex(int qzIndex) {
            this.qzIndex = qzIndex;
        }
    }
}
