package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;


/**
 * 活动参数
 */
public class RongcloudHBParameter extends BaseEntity {

    /**
     * createdDate : 1580471290000
     * createdIp : 127.0.0.1
     * createdUser : sys
     * id : 1
     * isDelete : 0
     * lastModifiedDate : 1605688508000
     * lastModifiedUser : kugua
     * quYueHBCodeAmount : 100
     * quYueHBGrade : 1
     * quYueHBMaxAmount : 88
     * quYueHBMinAmount : 2
     * quYueHBPerson : 1
     * quYueHBScore : 10
     * quYueHBSwitch : 1
     * quYueHBTotalAmount : 2
     * quYueHBTotalAmountEnd : 55
     * scoreByOnePerson : 10
     * tjHBGrade : 1
     * tjHBPersonProperty : {"randomFlag":0,"todayCz":0,"todayDm":0,"todaySl":0,"yesterdayCz":0,"yesterdayDm":0,"yesterdaySl":0}
     * tjHBPersonPropertyJson : {"randomFlag":0,"todayCz":0,"todayDm":0,"todaySl":0,"yesterdayCz":0,"yesterdayDm":0,"yesterdaySl":0}
     * tjHBPlatGrade : 2
     * tjHBPlatScore : 0
     * tjHBScore : 0
     * tjHBSwitch : 1
     * ywFLGrade : 0
     * ywFLScore : 0
     * ywFLSwitch : 0
     * zxHBGameClassIds : 32,121,111,57
     * zxHBGrade : 3
     * zxHBRate : 1.0
     * zxHBScore : 0
     * zxHBSwitch : 1
     */

    private RongcloudHBParameterBean rongcloudHBParameter;

    public RongcloudHBParameterBean getRongcloudHBParameter() {
        return rongcloudHBParameter;
    }

    public void setRongcloudHBParameter(RongcloudHBParameterBean rongcloudHBParameter) {
        this.rongcloudHBParameter = rongcloudHBParameter;
    }

    public static class RongcloudHBParameterBean {
        private long createdDate;
        private String createdIp;
        private String createdUser;
        private long id;
        private int isDelete;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int quYueHBCodeAmount;//趣约红包抽奖后加打码量(0-100)%
        private int quYueHBGrade;//趣约红包所需等级
        private int quYueHBMaxAmount;//趣约红包抽奖显示的最高金额
        private int quYueHBMinAmount;;//趣约红包抽奖显示的最低金额
        private int quYueHBPerson;
        private int quYueHBScore;//趣约红包所需要的积分
        private int quYueHBSwitch;//趣约红包开关(1开启0关闭)
        private int quYueHBTotalAmount;//趣约红包抽奖总金额(一个用户)
        private int quYueHBTotalAmountEnd;
        private int scoreByOnePerson;//推广一人换算的积分
        private int tjHBGrade;//天降红包玩家抢玩家发的包所需等级
        /**
         * randomFlag : 0
         * todayCz : 0
         * todayDm : 0
         * todaySl : 0
         * yesterdayCz : 0
         * yesterdayDm : 0
         * yesterdaySl : 0
         */

        private TjHBPersonPropertyBean tjHBPersonProperty;
        private String tjHBPersonPropertyJson;
        private int tjHBPlatGrade;
        private int tjHBPlatScore;
        private int tjHBScore;
        private int tjHBSwitch;
        private int ywFLGrade;
        private int ywFLScore;
        private int ywFLSwitch;
        private String zxHBGameClassIds;
        private int zxHBGrade;
        private double zxHBRate;
        private int zxHBScore;
        private int zxHBSwitch;

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

        public int getQuYueHBCodeAmount() {
            return quYueHBCodeAmount;
        }

        public void setQuYueHBCodeAmount(int quYueHBCodeAmount) {
            this.quYueHBCodeAmount = quYueHBCodeAmount;
        }

        public int getQuYueHBGrade() {
            return quYueHBGrade;
        }

        public void setQuYueHBGrade(int quYueHBGrade) {
            this.quYueHBGrade = quYueHBGrade;
        }

        public int getQuYueHBMaxAmount() {
            return quYueHBMaxAmount;
        }

        public void setQuYueHBMaxAmount(int quYueHBMaxAmount) {
            this.quYueHBMaxAmount = quYueHBMaxAmount;
        }

        public int getQuYueHBMinAmount() {
            return quYueHBMinAmount;
        }

        public void setQuYueHBMinAmount(int quYueHBMinAmount) {
            this.quYueHBMinAmount = quYueHBMinAmount;
        }

        public int getQuYueHBPerson() {
            return quYueHBPerson;
        }

        public void setQuYueHBPerson(int quYueHBPerson) {
            this.quYueHBPerson = quYueHBPerson;
        }

        public int getQuYueHBScore() {
            return quYueHBScore;
        }

        public void setQuYueHBScore(int quYueHBScore) {
            this.quYueHBScore = quYueHBScore;
        }

        public int getQuYueHBSwitch() {
            return quYueHBSwitch;
        }

        public void setQuYueHBSwitch(int quYueHBSwitch) {
            this.quYueHBSwitch = quYueHBSwitch;
        }

        public int getQuYueHBTotalAmount() {
            return quYueHBTotalAmount;
        }

        public void setQuYueHBTotalAmount(int quYueHBTotalAmount) {
            this.quYueHBTotalAmount = quYueHBTotalAmount;
        }

        public int getQuYueHBTotalAmountEnd() {
            return quYueHBTotalAmountEnd;
        }

        public void setQuYueHBTotalAmountEnd(int quYueHBTotalAmountEnd) {
            this.quYueHBTotalAmountEnd = quYueHBTotalAmountEnd;
        }

        public int getScoreByOnePerson() {
            return scoreByOnePerson;
        }

        public void setScoreByOnePerson(int scoreByOnePerson) {
            this.scoreByOnePerson = scoreByOnePerson;
        }

        public int getTjHBGrade() {
            return tjHBGrade;
        }

        public void setTjHBGrade(int tjHBGrade) {
            this.tjHBGrade = tjHBGrade;
        }

        public TjHBPersonPropertyBean getTjHBPersonProperty() {
            return tjHBPersonProperty;
        }

        public void setTjHBPersonProperty(TjHBPersonPropertyBean tjHBPersonProperty) {
            this.tjHBPersonProperty = tjHBPersonProperty;
        }

        public String getTjHBPersonPropertyJson() {
            return tjHBPersonPropertyJson;
        }

        public void setTjHBPersonPropertyJson(String tjHBPersonPropertyJson) {
            this.tjHBPersonPropertyJson = tjHBPersonPropertyJson;
        }

        public int getTjHBPlatGrade() {
            return tjHBPlatGrade;
        }

        public void setTjHBPlatGrade(int tjHBPlatGrade) {
            this.tjHBPlatGrade = tjHBPlatGrade;
        }

        public int getTjHBPlatScore() {
            return tjHBPlatScore;
        }

        public void setTjHBPlatScore(int tjHBPlatScore) {
            this.tjHBPlatScore = tjHBPlatScore;
        }

        public int getTjHBScore() {
            return tjHBScore;
        }

        public void setTjHBScore(int tjHBScore) {
            this.tjHBScore = tjHBScore;
        }

        public int getTjHBSwitch() {
            return tjHBSwitch;
        }

        public void setTjHBSwitch(int tjHBSwitch) {
            this.tjHBSwitch = tjHBSwitch;
        }

        public int getYwFLGrade() {
            return ywFLGrade;
        }

        public void setYwFLGrade(int ywFLGrade) {
            this.ywFLGrade = ywFLGrade;
        }

        public int getYwFLScore() {
            return ywFLScore;
        }

        public void setYwFLScore(int ywFLScore) {
            this.ywFLScore = ywFLScore;
        }

        public int getYwFLSwitch() {
            return ywFLSwitch;
        }

        public void setYwFLSwitch(int ywFLSwitch) {
            this.ywFLSwitch = ywFLSwitch;
        }

        public String getZxHBGameClassIds() {
            return zxHBGameClassIds;
        }

        public void setZxHBGameClassIds(String zxHBGameClassIds) {
            this.zxHBGameClassIds = zxHBGameClassIds;
        }

        public int getZxHBGrade() {
            return zxHBGrade;
        }

        public void setZxHBGrade(int zxHBGrade) {
            this.zxHBGrade = zxHBGrade;
        }

        public double getZxHBRate() {
            return zxHBRate;
        }

        public void setZxHBRate(double zxHBRate) {
            this.zxHBRate = zxHBRate;
        }

        public int getZxHBScore() {
            return zxHBScore;
        }

        public void setZxHBScore(int zxHBScore) {
            this.zxHBScore = zxHBScore;
        }

        public int getZxHBSwitch() {
            return zxHBSwitch;
        }

        public void setZxHBSwitch(int zxHBSwitch) {
            this.zxHBSwitch = zxHBSwitch;
        }

        public static class TjHBPersonPropertyBean {
            private int randomFlag;
            private int todayCz;
            private int todayDm;
            private int todaySl;
            private int yesterdayCz;
            private int yesterdayDm;
            private int yesterdaySl;

            public int getRandomFlag() {
                return randomFlag;
            }

            public void setRandomFlag(int randomFlag) {
                this.randomFlag = randomFlag;
            }

            public int getTodayCz() {
                return todayCz;
            }

            public void setTodayCz(int todayCz) {
                this.todayCz = todayCz;
            }

            public int getTodayDm() {
                return todayDm;
            }

            public void setTodayDm(int todayDm) {
                this.todayDm = todayDm;
            }

            public int getTodaySl() {
                return todaySl;
            }

            public void setTodaySl(int todaySl) {
                this.todaySl = todaySl;
            }

            public int getYesterdayCz() {
                return yesterdayCz;
            }

            public void setYesterdayCz(int yesterdayCz) {
                this.yesterdayCz = yesterdayCz;
            }

            public int getYesterdayDm() {
                return yesterdayDm;
            }

            public void setYesterdayDm(int yesterdayDm) {
                this.yesterdayDm = yesterdayDm;
            }

            public int getYesterdaySl() {
                return yesterdaySl;
            }

            public void setYesterdaySl(int yesterdaySl) {
                this.yesterdaySl = yesterdaySl;
            }
        }
    }
}
