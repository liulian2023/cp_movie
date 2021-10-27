package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class IntegralModel {


    /**
     * data : [{"remark":Utils.getString(R.string.邀请获得的积分1),"inviteName":"1212a***","createdDate":1581674687000,"integral":1,"integralType":1}]
     * message : success
     * invitePoint : 1001.0
     * status : success
     */

    private String message;
    private double invitePoint;
    private String status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getInvitePoint() {
        return invitePoint;
    }

    public void setInvitePoint(double invitePoint) {
        this.invitePoint = invitePoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends CommonModel{
        /**
         * remark : 邀请获得的积分1
         * inviteName : 1212a***
         * createdDate : 1581674687000
         * integral : 1.0
         * integralType : 1
         */

        private String remark;
        private String inviteName;
        private long createdDate;
        private int integral;
        private int integralType;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getInviteName() {
            return inviteName;
        }

        public void setInviteName(String inviteName) {
            this.inviteName = inviteName;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIntegralType() {
            return integralType;
        }

        public void setIntegralType(int integralType) {
            this.integralType = integralType;
        }
    }
}
