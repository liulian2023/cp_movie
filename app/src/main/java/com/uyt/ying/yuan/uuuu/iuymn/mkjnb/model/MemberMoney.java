/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class MemberMoney implements Serializable {


    /**
     * memberMoney : {"amount":750357.93,"commission":8503.53,"createdDate":1554787433000,"createdUser":"sys","growthIntegral":940243.01,"id":143,"isDelete":0,"lastModifiedDate":1564550173000,"lastModifiedUser":"sys","point":940243.01,"user_id":147,"user_name":"ookk333444","version":5277}
     * message : 获取资金信息成功
     * status : success
     */

    private MemberMoneyBean memberMoney;
    private String message;
    private String status;

    public MemberMoneyBean getMemberMoney() {
        return memberMoney;
    }

    public void setMemberMoney(MemberMoneyBean memberMoney) {
        this.memberMoney = memberMoney;
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

    public static class MemberMoneyBean {
        /**
         * amount : 750357.93
         * commission : 8503.53
         * createdDate : 1554787433000
         * createdUser : sys
         * growthIntegral : 940243.01
         * id : 143
         * isDelete : 0
         * lastModifiedDate : 1564550173000
         * lastModifiedUser : sys
         * point : 940243.01
         * user_id : 147
         * user_name : ookk333444
         * version : 5277
         */

        private double amount;
        private double commission;
        private long createdDate;
        private String createdUser;
        private double growthIntegral;
        private String id;
        private int isDelete;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private double point;
        private long user_id;
        private String user_name;
        private int version;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
            this.commission = commission;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public double getGrowthIntegral() {
            return growthIntegral;
        }

        public void setGrowthIntegral(double growthIntegral) {
            this.growthIntegral = growthIntegral;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
