/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class LastLottery implements Serializable {
    private String message;
    private String status;
    private String totalSize;
    private List<GameLotteryBean> gameLotterylist;
        public class GameLotteryBean implements Serializable{


            /**
             * lotteryNo : 2,3,5
             * islottery : 1
             * typeqishu : 1190616041
             * lastModifiedDate : 1560694270000
             * iscustom : 0
             * type_id : 1
             * isDelete : 0
             * remark : 小
             * lotterytime : 1560694160000
             * ds : 双
             * createdDate : 1560549601000
             * lotteryqishu : 190616041
             * lastModifiedUser : ws
             * id : 192982
             * createdUser : sys
             */

            private String lotteryNo;
      //      private String canManualLottery;
            private int islottery;
            private String typeqishu;
            private long lastModifiedDate;
            private int iscustom;
            private int type_id;
            private int isDelete;
            private String remark;
            private long lotterytime;
            private String ds;
            private long createdDate;
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

           /* public String getCanManualLottery() {
                return canManualLottery;
            }

            public void setCanManualLottery(String canManualLottery) {
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

            public String getDs() {
                return ds;
            }

            public void setDs(String ds) {
                this.ds = ds;
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

    public List<GameLotteryBean> getGameLotterylist() {
        return gameLotterylist;
    }

    public void setGameLotterylist(List<GameLotteryBean> gameLotterylist) {
        this.gameLotterylist = gameLotterylist;
    }
}
