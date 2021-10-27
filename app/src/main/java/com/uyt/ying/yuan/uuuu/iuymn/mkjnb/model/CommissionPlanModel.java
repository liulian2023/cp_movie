package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class CommissionPlanModel {

    /**
     * count : 1
     * codeList : [{"happytenRate":8.5,"sscaiRate":8.5,"xuanwuRate":8.5,"farmRate":8.5,"lastModifiedUser":"sys","id":1331102918719442944,"createdUser":"sys","k3Rate":8.5,"lastModifiedDate":1605608103000,"isDelete":0,"isagent":1,"url":"http://10.6.46.34:8188/ohtreg?parent=66065091","happy8Rate":8.5,"isNotDelete":1,"createdDate":1606194641000,"user_id":1331102914273480704,"inviteCode":"66065091","raceRate":8.5,"sixRate":6,"danRate":8.5,"maintainStatus":0,"chessRate":0}]
     * message : 获取成功
     * status : success
     */

    private int count;
    private String message;
    private String status;
    /**
     * happytenRate : 8.5
     * sscaiRate : 8.5
     * xuanwuRate : 8.5
     * farmRate : 8.5
     * lastModifiedUser : sys
     * id : 1331102918719442944
     * createdUser : sys
     * k3Rate : 8.5
     * lastModifiedDate : 1605608103000
     * isDelete : 0
     * isagent : 1
     * url : http://10.6.46.34:8188/ohtreg?parent=66065091
     * happy8Rate : 8.5
     * isNotDelete : 1
     * createdDate : 1606194641000
     * user_id : 1331102914273480704
     * inviteCode : 66065091
     * raceRate : 8.5
     * sixRate : 6
     * danRate : 8.5
     * maintainStatus : 0
     * chessRate : 0
     */

    private List<CodeListBean> codeList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public List<CodeListBean> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<CodeListBean> codeList) {
        this.codeList = codeList;
    }

    public static class CodeListBean extends CommonModel {
        private int num;
        private BigDecimal happytenRate;
        private BigDecimal sscaiRate;
        private BigDecimal xuanwuRate;
        private BigDecimal farmRate;
        private String lastModifiedUser;
        private long id;
        private String createdUser;
        private BigDecimal k3Rate;
        private long lastModifiedDate;
        private int isDelete;
        private int isagent;
        private String url;
        private BigDecimal happy8Rate;
        private int isNotDelete;
        private long createdDate;
        private long user_id;
        private String inviteCode;
        private BigDecimal raceRate;
        private BigDecimal sixRate;
        private BigDecimal danRate;
        private int maintainStatus;
        private BigDecimal chessRate;
        private int inCheck =0;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getInCheck() {
            return inCheck;
        }

        public void setInCheck(int inCheck) {
            this.inCheck = inCheck;
        }
        public BigDecimal getHappytenRate() {
            return happytenRate;
        }

        public void setHappytenRate(BigDecimal happytenRate) {
            this.happytenRate = happytenRate;
        }

        public BigDecimal getSscaiRate() {
            return sscaiRate;
        }

        public void setSscaiRate(BigDecimal sscaiRate) {
            this.sscaiRate = sscaiRate;
        }

        public BigDecimal getXuanwuRate() {
            return xuanwuRate;
        }

        public void setXuanwuRate(BigDecimal xuanwuRate) {
            this.xuanwuRate = xuanwuRate;
        }

        public BigDecimal getFarmRate() {
            return farmRate;
        }

        public void setFarmRate(BigDecimal farmRate) {
            this.farmRate = farmRate;
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

        public BigDecimal getK3Rate() {
            return k3Rate;
        }

        public void setK3Rate(BigDecimal k3Rate) {
            this.k3Rate = k3Rate;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsagent() {
            return isagent;
        }

        public void setIsagent(int isagent) {
            this.isagent = isagent;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public BigDecimal getHappy8Rate() {
            return happy8Rate;
        }

        public void setHappy8Rate(BigDecimal happy8Rate) {
            this.happy8Rate = happy8Rate;
        }

        public int getIsNotDelete() {
            return isNotDelete;
        }

        public void setIsNotDelete(int isNotDelete) {
            this.isNotDelete = isNotDelete;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public BigDecimal getRaceRate() {
            return raceRate;
        }

        public void setRaceRate(BigDecimal raceRate) {
            this.raceRate = raceRate;
        }

        public BigDecimal getSixRate() {
            return sixRate;
        }

        public void setSixRate(BigDecimal sixRate) {
            this.sixRate = sixRate;
        }

        public BigDecimal getDanRate() {
            return danRate;
        }

        public void setDanRate(BigDecimal danRate) {
            this.danRate = danRate;
        }

        public int getMaintainStatus() {
            return maintainStatus;
        }

        public void setMaintainStatus(int maintainStatus) {
            this.maintainStatus = maintainStatus;
        }

        public BigDecimal getChessRate() {
            return chessRate;
        }

        public void setChessRate(BigDecimal chessRate) {
            this.chessRate = chessRate;
        }
    }
}
