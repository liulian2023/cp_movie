package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class RedRainEntity {

    /**
     * data : [{"sendDate":1604120400000,"endDate":1604120760000,"redId":1543},{"sendDate":1604124000000,"endDate":1604124600000,"redId":1544},{"sendDate":1604145600000,"endDate":1604146200000,"redId":1545}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    /**
     * sendDate : 1604120400000
     * endDate : 1604120760000
     * redId : 1543
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private long sendDate;
        private long endDate;
        private String redId;

        public long getSendDate() {
            return sendDate;
        }

        public void setSendDate(long sendDate) {
            this.sendDate = sendDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public String getRedId() {
            return redId;
        }

        public void setRedId(String redId) {
            this.redId = redId;
        }
    }
}
