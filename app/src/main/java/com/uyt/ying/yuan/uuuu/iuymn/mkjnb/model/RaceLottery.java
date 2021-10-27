/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class RaceLottery implements Serializable {
    private String message;
    private String status;
    private String totalSize;
    private List<RaceLotteryBean> raceLotterylist;
    public class RaceLotteryBean implements Serializable {


        /**
         * lotteryNo : 7,3,6,9,4,8,5,2,1,10
         * islottery : 1
         * typeqishu : 1735243
         * lastModifiedDate : 1561305362000
         * iscustom : 0
         * marklh : [Utils.getString(R.string.虎),Utils.getString(R.string.龙),Utils.getString(R.string.龙),Utils.getString(R.string.龙),Utils.getString(R.string.虎)]
         * type_id : 1
         * isDelete : 0
         * sum : 10
         * remark : 小
         * lotterytime : 1561305357000
         * markdx : 小
         * ds : 双
         * markds : 双
         * createdDate : 1561154401000
         * lotteryqishu : 735243
         * lastModifiedUser : ws
         * id : 155844
         * createdUser : sys
         */

        private String lotteryNo;
        private int islottery;
        private String typeqishu;
        private long lastModifiedDate;
        private int iscustom;
        private int type_id;
        private int isDelete;
        private int sum;
        private String remark;
        private long lotterytime;
        private String markdx;
        private String ds;
        private String markds;
        private long createdDate;
        private String lotteryqishu;
        private String lastModifiedUser;
        private long id;
        private String createdUser;
        private List<String> marklh;

        public String getLotteryNo() {
            return lotteryNo;
        }

        public void setLotteryNo(String lotteryNo) {
            this.lotteryNo = lotteryNo;
        }

        public int getIslottery() {
            return islottery;
        }

        public void setIslottery(int islottery) {
            this.islottery = islottery;
        }

        public String getTypeqishu() {
            return typeqishu;
        }

        public void setTypeqishu(String typeqishu) {
            this.typeqishu = typeqishu;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public int getIscustom() {
            return iscustom;
        }

        public void setIscustom(int iscustom) {
            this.iscustom = iscustom;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getLotterytime() {
            return lotterytime;
        }

        public void setLotterytime(long lotterytime) {
            this.lotterytime = lotterytime;
        }

        public String getMarkdx() {
            return markdx;
        }

        public void setMarkdx(String markdx) {
            this.markdx = markdx;
        }

        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }

        public String getMarkds() {
            return markds;
        }

        public void setMarkds(String markds) {
            this.markds = markds;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getLotteryqishu() {
            return lotteryqishu;
        }

        public void setLotteryqishu(String lotteryqishu) {
            this.lotteryqishu = lotteryqishu;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public List<String> getMarklh() {
            return marklh;
        }

        public void setMarklh(List<String> marklh) {
            this.marklh = marklh;
        }
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

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public List<RaceLotteryBean> getRaceLotterylist() {
        return raceLotterylist;
    }

    public void setRaceLotterylist(List<RaceLotteryBean> raceLotterylist) {
        this.raceLotterylist = raceLotterylist;
    }
}
