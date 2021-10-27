/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;


import java.io.Serializable;
import java.util.List;

public class GetGroup implements Serializable {
    private String  message;
    private String status;
    private List<GameruleBean> gameRulelist;
        public class GameruleBean implements Serializable{
            /**
             * code : da
             * createdDate : 1554391999000
             * createdUser : sys
             * group_id : 1
             * groupname : 三军、大小
             * id : 2053
             * img_url :
             * isDelete : 0
             * lastModifiedDate : 1557391567000
             * model_id : 1090
             * name : 大
             * odds : 1.97
             * play : 0
             * type_id : 1
             */
            private String code;
            private long createdDate;
            private String createdUser;
            private int group_id;
            private String groupname;
            private String id;
            private String img_url;
            private int isDelete;
            private long lastModifiedDate;
            private int model_id;
            private String name;
            private double odds;
            private int play;
            private int type_id;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public long getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(long createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedUser() {
                return createdUser;
            }

            public void setCreatedUser(String createdUser) {
                this.createdUser = createdUser;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getOdds() {
                return odds;
            }

            public void setOdds(double odds) {
                this.odds = odds;
            }

            public int getPlay() {
                return play;
            }

            public void setPlay(int play) {
                this.play = play;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

        }

    private List<GameruleTwoBean> gameRulelisttwo;
        public static class GameruleTwoBean implements Serializable{


            /**
             * code : 3
             * createdDate : 1554391999000
             * createdUser : sys
             * group_id : 10
             * groupname : 三不同号
             * id : 2127
             * isDelete : 0
             * model_id : 1251
             * name : 三不同号
             * odds : 35.46
             * play : 1
             * remark : 1,2,3,4,5,6
             * type_id : 1
             */

            private String code;
            private long createdDate;
            private String createdUser;
            private int group_id;
            private String groupname;
            private long id;
            private int isDelete;
            private int model_id;
            private String name;
            private double odds;
            private int play;
            private String remark;
            private int type_id;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public long getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(long createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedUser() {
                return createdUser;
            }

            public void setCreatedUser(String createdUser) {
                this.createdUser = createdUser;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
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

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getOdds() {
                return odds;
            }

            public void setOdds(double odds) {
                this.odds = odds;
            }

            public int getPlay() {
                return play;
            }

            public void setPlay(int play) {
                this.play = play;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }
        }

    private List<GroupTwoBean> grouplisttwo;
        public class GroupTwoBean implements Serializable{

            /**
             * oddsMin : 1.97
             * group_id : 6
             * groupname : 和值
             * odds : 212.76
             */

            private double oddsMin;
            private int group_id;
            private String groupname;
            private double odds;

            public double getOddsMin() {
                return oddsMin;
            }

            public void setOddsMin(double oddsMin) {
                this.oddsMin = oddsMin;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public double getOdds() {
                return odds;
            }

            public void setOdds(double odds) {
                this.odds = odds;
            }
        }

    private List<GroupBean>   grouplist;
        public class GroupBean implements Serializable{


            /**
             * group_id : 1
             * groupname : 三军、大小
             */

            private int group_id;
            private String groupname;

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
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

    public List<GameruleBean> getGameRulelist() {
        return gameRulelist;
    }

    public void setGameRulelist(List<GameruleBean> gameRulelist) {
        this.gameRulelist = gameRulelist;
    }

    public List<GameruleTwoBean> getGameRulelisttwo() {
        return gameRulelisttwo;
    }

    public void setGameRulelisttwo(List<GameruleTwoBean> gameRulelisttwo) {
        this.gameRulelisttwo = gameRulelisttwo;
    }

    public List<GroupTwoBean> getGrouplisttwo() {
        return grouplisttwo;
    }

    public void setGrouplisttwo(List<GroupTwoBean> grouplisttwo) {
        this.grouplisttwo = grouplisttwo;
    }

    public List<GroupBean> getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(List<GroupBean> grouplist) {
        this.grouplist = grouplist;
    }
}
