package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;

public class InviteRedEntity {

    /**
     * data : {"receive":0,"redPriceYesterday":0,"canReceive":0,"redNum":3,"totalNum":0,"totalPrice":0,"totalInvitePoint":0,"peopleCount":1,"inviteCode":"04773504","invitePoint":0,"effectivePeople":0,"inviteAddress":"http://192.168.50.100:8188/ohtreg?parent=04773504"}
     * message : success
     * status : success
     */

    private DataBean data;
    private String message;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * receive : 0
         * redPriceYesterday : 0.0
         * canReceive : 0
         * redNum : 3
         * totalNum : 0
         * totalPrice : 0.0
         * totalInvitePoint : 0.0
         * peopleCount : 1
         * inviteCode : 04773504
         * invitePoint : 0.0
         * effectivePeople : 0
         * inviteAddress : http://192.168.50.100:8188/ohtreg?parent=04773504
         */

        private int receive;
        private BigDecimal redPriceYesterday;
        private int canReceive;
        private int redNum;
        private int totalNum;
        private BigDecimal totalPrice;
        private BigDecimal totalInvitePoint;
        private int peopleCount;
        private String inviteCode;
        private BigDecimal invitePoint;
        private int effectivePeople;
        private String inviteAddress;

        public int getReceive() {
            return receive;
        }

        public void setReceive(int receive) {
            this.receive = receive;
        }

        public BigDecimal getRedPriceYesterday() {
            return redPriceYesterday;
        }

        public void setRedPriceYesterday(BigDecimal redPriceYesterday) {
            this.redPriceYesterday = redPriceYesterday;
        }

        public int getCanReceive() {
            return canReceive;
        }

        public void setCanReceive(int canReceive) {
            this.canReceive = canReceive;
        }

        public int getRedNum() {
            return redNum;
        }

        public void setRedNum(int redNum) {
            this.redNum = redNum;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }

        public BigDecimal getTotalInvitePoint() {
            return totalInvitePoint;
        }

        public void setTotalInvitePoint(BigDecimal totalInvitePoint) {
            this.totalInvitePoint = totalInvitePoint;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public BigDecimal getInvitePoint() {
            return invitePoint;
        }

        public void setInvitePoint(BigDecimal invitePoint) {
            this.invitePoint = invitePoint;
        }

        public int getEffectivePeople() {
            return effectivePeople;
        }

        public void setEffectivePeople(int effectivePeople) {
            this.effectivePeople = effectivePeople;
        }

        public String getInviteAddress() {
            return inviteAddress;
        }

        public void setInviteAddress(String inviteAddress) {
            this.inviteAddress = inviteAddress;
        }
    }
}
