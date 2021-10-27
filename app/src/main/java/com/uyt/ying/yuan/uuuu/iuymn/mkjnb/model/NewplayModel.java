/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class NewplayModel implements Serializable {


    /**
     * playRuleListTwo : [{"name":Utils.getString(R.string.直选),"id":1803,"per":0,"parentId":1802,"odds":0},{"name":Utils.getString(R.string.标准),"id":1800,"per":0,"parentId":1799,"odds":0},{"name":Utils.getString(R.string.直选),"id":1806,"per":0,"parentId":1805,"odds":0},{"name":Utils.getString(R.string.直选),"id":1809,"per":0,"parentId":1808,"odds":0},{"name":Utils.getString(R.string.直选),"id":1812,"per":0,"parentId":1811,"odds":0},{"name":Utils.getString(R.string.直选),"id":1815,"per":0,"parentId":1814,"odds":0}]
     * playRuleListThree : [{"name":Utils.getString(R.string.复式),"remark":Utils.getString(R.string.从前五名中各选择1个不重复的号码组成一注，选号与相同名次的号码一致;1 2 3 4 5;1 2 3 4 5*),"id":1804,"per":5,"parentId":1803,"odds":59572.8},{"name":Utils.getString(R.string.定位胆),"remark":Utils.getString(R.string.从任意名次上选择一个号码组成一注，选号与相同名次的号码一致;冠选择01;01 *),"id":1801,"per":1,"parentId":1800,"odds":19.7},{"name":Utils.getString(R.string.复式),"remark":Utils.getString(R.string.从前四名中各选择1个不重复的号码组成一注，选号与相同名次的号码一致;1 2 3 4;1 2 3 4*),"id":1807,"per":4,"parentId":1806,"odds":9928.8},{"name":Utils.getString(R.string.复式),"remark":Utils.getString(R.string.从冠军、亚军、季军中各选择1个不重复的号码组成一注，选号与相同名次的号码一致;1 2 3;1 2 3*),"id":1810,"per":3,"parentId":1809,"odds":1418.4},{"name":Utils.getString(R.string.复式),"remark":Utils.getString(R.string.从冠军、亚军中各选择1个不重复的号码组成一注，选号与相同名次的号码一致;1 2;1 2*),"id":1813,"per":2,"parentId":1812,"odds":177.3},{"name":Utils.getString(R.string.复式),"remark":Utils.getString(R.string.选择1个号码组成一注，选号与冠军号码一致;1;1 *),"id":1816,"per":1,"parentId":1815,"odds":19.7}]
     * playRuleListOne : [{"name":Utils.getString(R.string.定位胆),"remark":Utils.getString(R.string.从前四名中各选择1个不重复的号码组成一注，选号与相同名次的号码一致),"id":1799,"per":0,"parentId":0,"odds":19.7},{"name":Utils.getString(R.string.猜前五),"id":1802,"per":0,"parentId":0,"odds":59572.8},{"name":Utils.getString(R.string.猜前四),"id":1805,"per":0,"parentId":0,"odds":9928.8},{"name":Utils.getString(R.string.猜前三),"id":1808,"per":0,"parentId":0,"odds":1418.4},{"name":Utils.getString(R.string.猜前二),"id":1811,"per":0,"parentId":0,"odds":177.3},{"name":Utils.getString(R.string.猜冠军),"id":1814,"per":0,"parentId":0,"odds":19.7}]
     * message : 获取成功
     * playRuleListFour : [{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1982,"isDelete":0,"isQuick":1,"model_id":1,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1801,"sort":1},{"balled":2,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1983,"isDelete":0,"isQuick":1,"model_id":2,"name":Utils.getString(R.string.亚军),"odds":0,"playRuleId":1801,"sort":2},{"balled":3,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1984,"isDelete":0,"isQuick":1,"model_id":3,"name":Utils.getString(R.string.季军),"odds":0,"playRuleId":1801,"sort":3},{"balled":4,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1985,"isDelete":0,"isQuick":1,"model_id":4,"name":Utils.getString(R.string.第四),"odds":0,"playRuleId":1801,"sort":4},{"balled":5,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1986,"isDelete":0,"isQuick":1,"model_id":5,"name":Utils.getString(R.string.第五),"odds":0,"playRuleId":1801,"sort":5},{"balled":6,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1987,"isDelete":0,"isQuick":1,"model_id":6,"name":Utils.getString(R.string.第六),"odds":0,"playRuleId":1801,"sort":6},{"balled":7,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1988,"isDelete":0,"isQuick":1,"model_id":7,"name":Utils.getString(R.string.第七),"odds":0,"playRuleId":1801,"sort":7},{"balled":8,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1989,"isDelete":0,"isQuick":1,"model_id":8,"name":Utils.getString(R.string.第八),"odds":0,"playRuleId":1801,"sort":8},{"balled":9,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1990,"isDelete":0,"isQuick":1,"model_id":9,"name":Utils.getString(R.string.第九),"odds":0,"playRuleId":1801,"sort":9},{"balled":10,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1991,"isDelete":0,"isQuick":1,"model_id":10,"name":Utils.getString(R.string.第十),"odds":0,"playRuleId":1801,"sort":10},{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1992,"isDelete":0,"isQuick":1,"model_id":11,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1804,"sort":11},{"balled":2,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1993,"isDelete":0,"isQuick":1,"model_id":12,"name":Utils.getString(R.string.亚军),"odds":0,"playRuleId":1804,"sort":12},{"balled":3,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1994,"isDelete":0,"isQuick":1,"model_id":13,"name":Utils.getString(R.string.季军),"odds":0,"playRuleId":1804,"sort":13},{"balled":4,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1995,"isDelete":0,"isQuick":1,"model_id":14,"name":Utils.getString(R.string.第四),"odds":0,"playRuleId":1804,"sort":14},{"balled":5,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1996,"isDelete":0,"isQuick":1,"model_id":15,"name":Utils.getString(R.string.第五),"odds":0,"playRuleId":1804,"sort":15},{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1997,"isDelete":0,"isQuick":1,"model_id":16,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1807,"sort":16},{"balled":2,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1998,"isDelete":0,"isQuick":1,"model_id":17,"name":Utils.getString(R.string.亚军),"odds":0,"playRuleId":1807,"sort":17},{"balled":3,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":1999,"isDelete":0,"isQuick":1,"model_id":18,"name":Utils.getString(R.string.季军),"odds":0,"playRuleId":1807,"sort":18},{"balled":4,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2000,"isDelete":0,"isQuick":1,"model_id":19,"name":Utils.getString(R.string.第四),"odds":0,"playRuleId":1807,"sort":19},{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2001,"isDelete":0,"isQuick":1,"model_id":20,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1810,"sort":20},{"balled":2,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2002,"isDelete":0,"isQuick":1,"model_id":21,"name":Utils.getString(R.string.亚军),"odds":0,"playRuleId":1810,"sort":21},{"balled":3,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2003,"isDelete":0,"isQuick":1,"model_id":22,"name":Utils.getString(R.string.季军),"odds":0,"playRuleId":1810,"sort":22},{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2004,"isDelete":0,"isQuick":1,"model_id":23,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1813,"sort":23},{"balled":2,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2005,"isDelete":0,"isQuick":1,"model_id":24,"name":Utils.getString(R.string.亚军),"odds":0,"playRuleId":1813,"sort":24},{"balled":1,"codes":"1,2,3,4,5,6,7,8,9,10","createdDate":1536828661000,"createdUser":"admin","game":0,"id":2006,"isDelete":0,"isQuick":1,"model_id":25,"name":Utils.getString(R.string.冠军),"odds":0,"playRuleId":1816,"sort":25}]
     * status : success
     */

    private String message;
    private String status;
    private List<PlayRuleListTwoBean> playRuleListTwo;
    private List<PlayRuleListThreeBean> playRuleListThree;
    private List<PlayRuleListOneBean> playRuleListOne;
    private List<PlayRuleListFourBean> playRuleListFour;

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

    public List<PlayRuleListTwoBean> getPlayRuleListTwo() {
        return playRuleListTwo;
    }

    public void setPlayRuleListTwo(List<PlayRuleListTwoBean> playRuleListTwo) {
        this.playRuleListTwo = playRuleListTwo;
    }

    public List<PlayRuleListThreeBean> getPlayRuleListThree() {
        return playRuleListThree;
    }

    public void setPlayRuleListThree(List<PlayRuleListThreeBean> playRuleListThree) {
        this.playRuleListThree = playRuleListThree;
    }

    public List<PlayRuleListOneBean> getPlayRuleListOne() {
        return playRuleListOne;
    }

    public void setPlayRuleListOne(List<PlayRuleListOneBean> playRuleListOne) {
        this.playRuleListOne = playRuleListOne;
    }

    public List<PlayRuleListFourBean> getPlayRuleListFour() {
        return playRuleListFour;
    }

    public void setPlayRuleListFour(List<PlayRuleListFourBean> playRuleListFour) {
        this.playRuleListFour = playRuleListFour;
    }

    public static class PlayRuleListTwoBean implements Serializable{
        /**
         * name : 直选
         * id : 1803
         * per : 0
         * parentId : 1802
         * odds : 0
         */

        private String name;
        private long id;
        private int per;
        private long parentId;
        private double odds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }
    }

    public static class PlayRuleListThreeBean implements Serializable{
        /**
         * name : 复式
         * remark : 从前五名中各选择1个不重复的号码组成一注，选号与相同名次的号码一致;1 2 3 4 5;1 2 3 4 5*
         * id : 1804
         * per : 5
         * parentId : 1803
         * odds : 59572.8
         */

        private String name;
        private String remark;
        private long id;
        private int per;
        private long parentId;
        private String odds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }
    }

    public static class PlayRuleListOneBean implements Serializable{
        /**
         * name : 定位胆
         * remark : 从前四名中各选择1个不重复的号码组成一注，选号与相同名次的号码一致
         * id : 1799
         * per : 0
         * parentId : 0
         * odds : 19.7
         */

        private String name;
        private String remark;
        private long id;
        private int per;
        private long parentId;
        private double odds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }
    }

    public static class PlayRuleListFourBean implements Serializable{
        /**
         * balled : 1
         * codes : 1,2,3,4,5,6,7,8,9,10
         * createdDate : 1536828661000
         * createdUser : admin
         * game : 0
         * id : 1982
         * isDelete : 0
         * isQuick : 1
         * model_id : 1
         * name : 冠军
         * odds : 0
         * playRuleId : 1801
         * sort : 1
         */

        private int balled;
        private String codes;
        private long createdDate;
        private String createdUser;
        private int game;
        private long id;
        private int isDelete;
        private int isQuick;
        private int model_id;
        private String name;
        private double odds;
        private int playRuleId;
        private int sort;

        public int getBalled() {
            return balled;
        }

        public void setBalled(int balled) {
            this.balled = balled;
        }

        public String getCodes() {
            return codes;
        }

        public void setCodes(String codes) {
            this.codes = codes;
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

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
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

        public int getIsQuick() {
            return isQuick;
        }

        public void setIsQuick(int isQuick) {
            this.isQuick = isQuick;
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

        public int getPlayRuleId() {
            return playRuleId;
        }

        public void setPlayRuleId(int playRuleId) {
            this.playRuleId = playRuleId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
