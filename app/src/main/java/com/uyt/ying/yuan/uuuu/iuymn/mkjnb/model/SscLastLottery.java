

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class SscLastLottery implements Serializable {

    /**
     * totalsize : 13097
     * sscaiLotterylist : [{"lotteryNo":"5,4,8,9,4","islottery":1,"typeqishu":"121907090137","markqs":Utils.getString(R.string.半顺),"lastModifiedDate":1562609825000,"iscustom":1,"marklh":Utils.getString(R.string.龙),"type_id":12,"isDelete":0,"sum":30,"markzs":Utils.getString(R.string.半顺),"remark":Utils.getString(R.string.大),"lotterytime":1562609820000,"markdx":Utils.getString(R.string.大),"ds":Utils.getString(R.string.双),"markds":Utils.getString(R.string.双),"createdDate":1562536854000,"markhs":Utils.getString(R.string.半顺),"lotteryqishu":"1907090137","lastModifiedUser":"sys","id":316150,"createdUser":"sys"}]
     * message : 操作成功
     * status : success
     */

    private int totalsize;
    private String message;
    private String status;
    private List<SscaiLotterylistBean> sscaiLotterylist;

    public int getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(int totalsize) {
        this.totalsize = totalsize;
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

    public List<SscaiLotterylistBean> getSscaiLotterylist() {
        return sscaiLotterylist;
    }

    public void setSscaiLotterylist(List<SscaiLotterylistBean> sscaiLotterylist) {
        this.sscaiLotterylist = sscaiLotterylist;
    }

    public static class SscaiLotterylistBean implements Serializable{
        /**
         * lotteryNo : 5,4,8,9,4
         * islottery : 1
         * typeqishu : 121907090137
         * markqs : 半顺
         * lastModifiedDate : 1562609825000
         * iscustom : 1
         * marklh : 龙
         * type_id : 12
         * isDelete : 0
         * sum : 30
         * markzs : 半顺
         * remark : 大
         * lotterytime : 1562609820000
         * markdx : 大
         * ds : 双
         * markds : 双
         * createdDate : 1562536854000
         * markhs : 半顺
         * lotteryqishu : 1907090137
         * lastModifiedUser : sys
         * id : 316150
         * createdUser : sys
         */

        private String lotteryNo;
        private int islottery;
        private String typeqishu;
        private String markqs;
        private long lastModifiedDate;
        private int iscustom;
        private String marklh;
        private int type_id;
        private int isDelete;
        private int sum;
        private String markzs;
        private String remark;
        private long lotterytime;
        private String markdx;
        private String ds;
        private String markds;
        private long createdDate;
        private String markhs;
        private String lotteryqishu;
        private String lastModifiedUser;
        private long id;
        private String createdUser;

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

        public String getMarkqs() {
            return markqs;
        }

        public void setMarkqs(String markqs) {
            this.markqs = markqs;
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

        public String getMarklh() {
            return marklh;
        }

        public void setMarklh(String marklh) {
            this.marklh = marklh;
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

        public String getMarkzs() {
            return markzs;
        }

        public void setMarkzs(String markzs) {
            this.markzs = markzs;
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

        public String getMarkhs() {
            return markhs;
        }

        public void setMarkhs(String markhs) {
            this.markhs = markhs;
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
    }
}
