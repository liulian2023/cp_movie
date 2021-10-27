/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class XuanwuLottery implements Serializable {


    /**
     * totalsize : 413
     * xuanwuLotterylist : [{"lotteryNo":"2,11,4,3,7","game":9,"canManualLottery":false,"islottery":1,"typeqishu":"1190619035","iscustom":0,"lastModifiedDate":1560948694453,"marklh":Utils.getString(R.string.虎),"isDelete":0,"type_id":1,"sum":27,"lotterytime":1560948688000,"markdx":Utils.getString(R.string.小),"markds":Utils.getString(R.string.单),"createdDate":1560808801000,"dx":[Utils.getString(R.string.小),Utils.getString(R.string.和),Utils.getString(R.string.小),Utils.getString(R.string.小),Utils.getString(R.string.大)],"markwdx":Utils.getString(R.string.尾大),"lotteryqishu":"190619035","lastModifiedUser":"ws","id":136302,"createdUser":"sys"}]
     * message : 取的缓存
     * status : success
     */

    private int totalsize;
    private String message;
    private String status;
    private List<XuanwuLotterylistBean> xuanwuLotterylist;

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

    public List<XuanwuLotterylistBean> getXuanwuLotterylist() {
        return xuanwuLotterylist;
    }

    public void setXuanwuLotterylist(List<XuanwuLotterylistBean> xuanwuLotterylist) {
        this.xuanwuLotterylist = xuanwuLotterylist;
    }

    public static class XuanwuLotterylistBean implements Serializable{
        /**
         * lotteryNo : 2,11,4,3,7
         * game : 9
         * canManualLottery : false
         * islottery : 1
         * typeqishu : 1190619035
         * iscustom : 0
         * lastModifiedDate : 1560948694453
         * marklh : 虎
         * isDelete : 0
         * type_id : 1
         * sum : 27
         * lotterytime : 1560948688000
         * markdx : 小
         * markds : 单
         * createdDate : 1560808801000
         * dx : [Utils.getString(R.string.小),Utils.getString(R.string.和),Utils.getString(R.string.小),Utils.getString(R.string.小),Utils.getString(R.string.大)]
         * markwdx : 尾大
         * lotteryqishu : 190619035
         * lastModifiedUser : ws
         * id : 136302
         * createdUser : sys
         */

        private String lotteryNo;
        private int game;
    //    private boolean canManualLottery;
        private int islottery;
        private String typeqishu;
        private int iscustom;
        private long lastModifiedDate;
        private String marklh;
        private int isDelete;
        private int type_id;
        private int sum;
        private long lotterytime;
        private String markdx;
        private String markds;
        private long createdDate;
        private String markwdx;
        private String lotteryqishu;
        private String lastModifiedUser;
        private long id;
        private String createdUser;
        private List<String> dx;

        public String getLotteryNo() {
            return lotteryNo;
        }

        public void setLotteryNo(String lotteryNo) {
            this.lotteryNo = lotteryNo;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

       /* public boolean isCanManualLottery() {
            return canManualLottery;
        }

        public void setCanManualLottery(boolean canManualLottery) {
            this.canManualLottery = canManualLottery;
        }*/

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

        public int getIscustom() {
            return iscustom;
        }

        public void setIscustom(int iscustom) {
            this.iscustom = iscustom;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getMarklh() {
            return marklh;
        }

        public void setMarklh(String marklh) {
            this.marklh = marklh;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
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

        public String getMarkwdx() {
            return markwdx;
        }

        public void setMarkwdx(String markwdx) {
            this.markwdx = markwdx;
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

        public List<String> getDx() {
            return dx;
        }

        public void setDx(List<String> dx) {
            this.dx = dx;
        }
    }
}
