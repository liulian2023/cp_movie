package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class RegisterEntity {

    /**
     * memberInfo : {"createdDate":1589179525322,"createdIp":"192.168.8.145","createdUser":"sys","id":108,"isDelete":0,"isTest":0,"isagent":0,"lastModifiedDate":1589179526072,"lastModifiedUser":"sys","maintainCount":0,"maintainStatus":0,"nickname":"lloooo","parent_id":1,"password":"1","phone":"****5903",CommonStr.GRADE:0,"rebate":0.3,"registerArea":"","registerIp":"192.168.8.145","sex":2,"source":2,"userNickName":"03cl93z6"}
     * memberAgent : {"k3Rate":9,"lastModifiedDate":1536130753000,"max":8,"isDelete":0,"happytenRate":9,"isagent":1,"sscaiRate":9,"remark":Utils.getString(R.string.公司自身最高返点),"happy8Rate":9,"isNotDelete":1,"createdDate":1536039956000,"xuanwuRate":9,"min":0,"farmRate":9,"user_id":"","inviteCode":"","raceRate":9,"sixRate":6,"lastModifiedUser":"sys","danRate":9,"id":1,"createdUser":"sss","chessRate":0,"islimit":1}
     * message : 登入成功
     * tokenInfo : 66496013ed8f4c8881b10201bb157414
     * token : 66496013ed8f4c8881b10201bb157414
     * status : success
     */

    private MemberInfoBean memberInfo;
    private MemberAgentBean memberAgent;
    private String message;
    private String tokenInfo;
    private String token;
    private String status;

    public MemberInfoBean getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfoBean memberInfo) {
        this.memberInfo = memberInfo;
    }

    public MemberAgentBean getMemberAgent() {
        return memberAgent;
    }

    public void setMemberAgent(MemberAgentBean memberAgent) {
        this.memberAgent = memberAgent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MemberInfoBean {
        /**
         * createdDate : 1589179525322
         * createdIp : 192.168.8.145
         * createdUser : sys
         * id : 108
         * isDelete : 0
         * isTest : 0
         * isagent : 0
         * lastModifiedDate : 1589179526072
         * lastModifiedUser : sys
         * maintainCount : 0
         * maintainStatus : 0
         * nickname : lloooo
         * parent_id : 1
         * password : 1
         * phone : ****5903
         * pointGrade : 0
         * rebate : 0.3
         * registerArea :
         * registerIp : 192.168.8.145
         * sex : 2
         * source : 2
         * userNickName : 03cl93z6
         */

        private long createdDate;
        private String createdIp;
        private String createdUser;
        private long id;
        private int isDelete;
        private int isTest;
        private int isagent;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int maintainCount;
        private int maintainStatus;
        private String nickname;
        private long parent_id;
        private String password;
        private String phone;
        private int pointGrade;
        private double rebate;
        private String registerArea;
        private String registerIp;
        private int sex;
        private int source;
        private String userNickName;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedIp() {
            return createdIp;
        }

        public void setCreatedIp(String createdIp) {
            this.createdIp = createdIp;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsTest() {
            return isTest;
        }

        public void setIsTest(int isTest) {
            this.isTest = isTest;
        }

        public int getIsagent() {
            return isagent;
        }

        public void setIsagent(int isagent) {
            this.isagent = isagent;
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

        public int getMaintainCount() {
            return maintainCount;
        }

        public void setMaintainCount(int maintainCount) {
            this.maintainCount = maintainCount;
        }

        public int getMaintainStatus() {
            return maintainStatus;
        }

        public void setMaintainStatus(int maintainStatus) {
            this.maintainStatus = maintainStatus;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getParent_id() {
            return parent_id;
        }

        public void setParent_id(long parent_id) {
            this.parent_id = parent_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPointGrade() {
            return pointGrade;
        }

        public void setPointGrade(int pointGrade) {
            this.pointGrade = pointGrade;
        }

        public double getRebate() {
            return rebate;
        }

        public void setRebate(double rebate) {
            this.rebate = rebate;
        }

        public String getRegisterArea() {
            return registerArea;
        }

        public void setRegisterArea(String registerArea) {
            this.registerArea = registerArea;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }
    }

    public static class MemberAgentBean {
        /**
         * k3Rate : 9
         * lastModifiedDate : 1536130753000
         * max : 8
         * isDelete : 0
         * happytenRate : 9
         * isagent : 1
         * sscaiRate : 9
         * remark : 公司自身最高返点
         * happy8Rate : 9
         * isNotDelete : 1
         * createdDate : 1536039956000
         * xuanwuRate : 9
         * min : 0
         * farmRate : 9
         * user_id :
         * inviteCode :
         * raceRate : 9
         * sixRate : 6
         * lastModifiedUser : sys
         * danRate : 9
         * id : 1
         * createdUser : sss
         * chessRate : 0
         * islimit : 1
         */

        private String k3Rate;
        private long lastModifiedDate;
        private String max;
        private int isDelete;
        private String happytenRate;
        private int isagent;
        private String sscaiRate;
        private String remark;
        private String happy8Rate;
        private int isNotDelete;
        private long createdDate;
        private String xuanwuRate;
        private String min;
        private String farmRate;
        private String user_id;
        private String inviteCode;
        private String raceRate;
        private String sixRate;
        private String lastModifiedUser;
        private String danRate;
        private long id;
        private String createdUser;
        private String chessRate;
        private int islimit;

        public String getK3Rate() {
            return k3Rate;
        }

        public void setK3Rate(String k3Rate) {
            this.k3Rate = k3Rate;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getHappytenRate() {
            return happytenRate;
        }

        public void setHappytenRate(String happytenRate) {
            this.happytenRate = happytenRate;
        }

        public int getIsagent() {
            return isagent;
        }

        public void setIsagent(int isagent) {
            this.isagent = isagent;
        }

        public String getSscaiRate() {
            return sscaiRate;
        }

        public void setSscaiRate(String sscaiRate) {
            this.sscaiRate = sscaiRate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHappy8Rate() {
            return happy8Rate;
        }

        public void setHappy8Rate(String happy8Rate) {
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

        public String getXuanwuRate() {
            return xuanwuRate;
        }

        public void setXuanwuRate(String xuanwuRate) {
            this.xuanwuRate = xuanwuRate;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getFarmRate() {
            return farmRate;
        }

        public void setFarmRate(String farmRate) {
            this.farmRate = farmRate;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getRaceRate() {
            return raceRate;
        }

        public void setRaceRate(String raceRate) {
            this.raceRate = raceRate;
        }

        public String getSixRate() {
            return sixRate;
        }

        public void setSixRate(String sixRate) {
            this.sixRate = sixRate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getDanRate() {
            return danRate;
        }

        public void setDanRate(String danRate) {
            this.danRate = danRate;
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

        public String getChessRate() {
            return chessRate;
        }

        public void setChessRate(String chessRate) {
            this.chessRate = chessRate;
        }

        public int getIslimit() {
            return islimit;
        }

        public void setIslimit(int islimit) {
            this.islimit = islimit;
        }
    }
}
