/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class MarksixLottery implements Serializable {
    private String message;
    private String status;
    private String totalsize;
    private List<marksixLotteryBean> marksixLotterylist;
    public class marksixLotteryBean implements Serializable{

        /**
         * lotteryNo : 48,23,29,35,26,18,36
         * hedx : 大
         * islottery : 1
         * typeqishu : 12019068
         * animalAll : 鼠,牛,羊,牛,狗,马,鼠
         * color : blue,red,red,red,blue,red,blue
         * iscustom : 0
         * lastModifiedDate : 1560864888000
         * type_id : 1
         * isDelete : 0
         * sum : 215
         * lotterytime : 1560864879000
         * ds : 双
         * sumds : 总和单
         * weidx : 尾大
         * createdDate : 1560605402000
         * sumdx : 总和大
         * dx : 大
         * lotteryqishu : 2019068
         * animal : 鼠,牛,羊,牛,狗,马,鼠
         * lastModifiedUser : ws
         * id : 42380
         * heds : 单
         * createdUser : sys
         */

        private String lotteryNo;
        private String hedx;
        private int islottery;
        private String typeqishu;
        private String animalAll;
        private String color;
        private int iscustom;
        private long lastModifiedDate;
        private int type_id;
        private int isDelete;
        private int sum;
        private long lotterytime;
        private String ds;
        private String sumds;
        private String weidx;
        private long createdDate;
        private String sumdx;
        private String dx;
        private String lotteryqishu;
        private String animal;
        private String lastModifiedUser;
        private long id;
        private String heds;
        private String createdUser;

        public String getLotteryNo() {
            return lotteryNo;
        }

        public void setLotteryNo(String lotteryNo) {
            this.lotteryNo = lotteryNo;
        }

        public String getHedx() {
            return hedx;
        }

        public void setHedx(String hedx) {
            this.hedx = hedx;
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

        public String getAnimalAll() {
            return animalAll;
        }

        public void setAnimalAll(String animalAll) {
            this.animalAll = animalAll;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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

        public long getLotterytime() {
            return lotterytime;
        }

        public void setLotterytime(long lotterytime) {
            this.lotterytime = lotterytime;
        }

        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }

        public String getSumds() {
            return sumds;
        }

        public void setSumds(String sumds) {
            this.sumds = sumds;
        }

        public String getWeidx() {
            return weidx;
        }

        public void setWeidx(String weidx) {
            this.weidx = weidx;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getSumdx() {
            return sumdx;
        }

        public void setSumdx(String sumdx) {
            this.sumdx = sumdx;
        }

        public String getDx() {
            return dx;
        }

        public void setDx(String dx) {
            this.dx = dx;
        }

        public String getLotteryqishu() {
            return lotteryqishu;
        }

        public void setLotteryqishu(String lotteryqishu) {
            this.lotteryqishu = lotteryqishu;
        }

        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
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

        public String getHeds() {
            return heds;
        }

        public void setHeds(String heds) {
            this.heds = heds;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
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

    public String getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(String totalsize) {
        this.totalsize = totalsize;
    }

    public List<marksixLotteryBean> getMarksixLotterylist() {
        return marksixLotterylist;
    }

    public void setMarksixLotterylist(List<marksixLotteryBean> marksixLotterylist) {
        this.marksixLotterylist = marksixLotterylist;
    }
}
