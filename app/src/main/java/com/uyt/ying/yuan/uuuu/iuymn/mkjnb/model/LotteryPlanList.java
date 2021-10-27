/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class LotteryPlanList implements Serializable {


    /**
     * result : [{"groupNameItem":Utils.getString(R.string.小,大,小),"islottery":0,"lotteryqishu3":"20603709","name":Utils.getString(R.string.20603707-20603709期),"result":0,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,小),"islottery":0,"lotteryqishu3":"20603706","name":Utils.getString(R.string.20603704-20603706期),"result":0,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,小,大),"islottery":1,"lotteryqishu3":"20603703","name":Utils.getString(R.string.20603701-20603703期),"qishu":"20603701","result":2,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,小,小),"islottery":1,"lotteryqishu3":"20603700","name":Utils.getString(R.string.20603698-20603700期),"qishu":"20603698","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,小,小),"islottery":1,"lotteryqishu3":"20603697","name":Utils.getString(R.string.20603695-20603697期),"qishu":"20603695","result":3,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,大),"islottery":1,"lotteryqishu3":"20603694","name":Utils.getString(R.string.20603692-20603694期),"qishu":"20603692","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,小),"islottery":1,"lotteryqishu3":"20603691","name":Utils.getString(R.string.20603689-20603691期),"qishu":"20603689","result":3,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,小,小),"islottery":1,"lotteryqishu3":"20603688","name":Utils.getString(R.string.20603686-20603688期),"qishu":"20603686","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,大),"islottery":1,"lotteryqishu3":"20603685","name":Utils.getString(R.string.20603683-20603685期),"qishu":"20603683","result":3,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,小),"islottery":1,"lotteryqishu3":"20603682","name":Utils.getString(R.string.20603680-20603682期),"qishu":"20603680","result":2,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,大,小),"islottery":1,"lotteryqishu3":"20603679","name":Utils.getString(R.string.20603677-20603679期),"qishu":"20603677","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,小),"islottery":1,"lotteryqishu3":"20603676","name":Utils.getString(R.string.20603674-20603676期),"qishu":"20603676","result":0,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,大,大),"islottery":1,"lotteryqishu3":"20603673","name":Utils.getString(R.string.20603671-20603673期),"qishu":"20603671","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,大,大),"islottery":1,"lotteryqishu3":"20603670","name":Utils.getString(R.string.20603668-20603670期),"qishu":"20603668","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,小,小),"islottery":1,"lotteryqishu3":"20603667","name":Utils.getString(R.string.20603665-20603667期),"qishu":"20603667","result":0,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,小,大),"islottery":1,"lotteryqishu3":"20603664","name":Utils.getString(R.string.20603662-20603664期),"qishu":"20603662","result":1,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,小,小),"islottery":1,"lotteryqishu3":"20603661","name":Utils.getString(R.string.20603659-20603661期),"qishu":"20603661","result":0,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,大,小),"islottery":1,"lotteryqishu3":"20603658","name":Utils.getString(R.string.20603656-20603658期),"qishu":"20603656","result":3,"type_id":10},{"groupNameItem":Utils.getString(R.string.小,大,小),"islottery":1,"lotteryqishu3":"20603655","name":Utils.getString(R.string.20603653-20603655期),"qishu":"20603653","result":2,"type_id":10},{"groupNameItem":Utils.getString(R.string.大,小,小),"islottery":1,"lotteryqishu3":"20603652","name":Utils.getString(R.string.20603650-20603652期),"qishu":"20603650","result":2,"type_id":10}]
     * groupNameItems : [{"gameRules":[{"code":"da","isboth":1,"group_id":11,"type_id":10,"odds":2.25,"name":Utils.getString(R.string.冠亚大),"id":1023,"groupname":Utils.getString(R.string.冠亚和),"isGY":1},{"code":"xiao","isboth":1,"group_id":11,"type_id":10,"odds":1.8,"name":Utils.getString(R.string.冠亚小),"id":1024,"groupname":Utils.getString(R.string.冠亚和),"isGY":1}],"title":Utils.getString(R.string.冠亚大小)},{"gameRules":[{"code":"dan","isboth":1,"group_id":11,"type_id":10,"odds":1.8,"name":Utils.getString(R.string.冠亚单),"id":1025,"groupname":Utils.getString(R.string.冠亚和),"isGY":1},{"code":"sh","isboth":1,"group_id":11,"type_id":10,"odds":2.25,"name":Utils.getString(R.string.冠亚双),"id":1026,"groupname":Utils.getString(R.string.冠亚和),"isGY":1}],"title":Utils.getString(R.string.冠亚单双)}]
     * message : success
     * status : success
     */

    private String message;
    private String status;
    private List<ResultBean> result;
    private List<GroupNameItemsBean> groupNameItems;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<GroupNameItemsBean> getGroupNameItems() {
        return groupNameItems;
    }

    public void setGroupNameItems(List<GroupNameItemsBean> groupNameItems) {
        this.groupNameItems = groupNameItems;
    }

    public static class ResultBean implements Serializable{
        /**
         * groupNameItem : 小,大,小
         * islottery : 0
         * lotteryqishu3 : 20603709
         * name : 20603707-20603709期
         * result : 0
         * type_id : 10
         * qishu : 20603701
         */

        private String groupNameItem;
        private int islottery;
        private String lotteryqishu3;
        private String name;
        private int result;
        private int type_id;
        private String qishu;

        public String getGroupNameItem() {
            return groupNameItem;
        }

        public void setGroupNameItem(String groupNameItem) {
            this.groupNameItem = groupNameItem;
        }

        public int getIslottery() {
            return islottery;
        }

        public void setIslottery(int islottery) {
            this.islottery = islottery;
        }

        public String getLotteryqishu3() {
            return lotteryqishu3;
        }

        public void setLotteryqishu3(String lotteryqishu3) {
            this.lotteryqishu3 = lotteryqishu3;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getQishu() {
            return qishu;
        }

        public void setQishu(String qishu) {
            this.qishu = qishu;
        }
    }

    public static class GroupNameItemsBean implements Serializable{
        /**
         * gameRules : [{"code":"da","isboth":1,"group_id":11,"type_id":10,"odds":2.25,"name":Utils.getString(R.string.冠亚大),"id":1023,"groupname":Utils.getString(R.string.冠亚和),"isGY":1},{"code":"xiao","isboth":1,"group_id":11,"type_id":10,"odds":1.8,"name":Utils.getString(R.string.冠亚小),"id":1024,"groupname":Utils.getString(R.string.冠亚和),"isGY":1}]
         * title : 冠亚大小
         */

        private String title;
        private List<GameRulesBean> gameRules;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<GameRulesBean> getGameRules() {
            return gameRules;
        }

        public void setGameRules(List<GameRulesBean> gameRules) {
            this.gameRules = gameRules;
        }

        public static class GameRulesBean {
            /**
             * code : da
             * isboth : 1
             * group_id : 11
             * type_id : 10
             * odds : 2.25
             * name : 冠亚大
             * id : 1023
             * groupname : 冠亚和
             * isGY : 1
             */

            private String code;
            private int isboth;
            private int group_id;
            private int type_id;
            private double odds;
            private String name;
            private long id;
            private String groupname;
            private int isGY;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getIsboth() {
                return isboth;
            }

            public void setIsboth(int isboth) {
                this.isboth = isboth;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public double getOdds() {
                return odds;
            }

            public void setOdds(double odds) {
                this.odds = odds;
            }

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

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public int getIsGY() {
                return isGY;
            }

            public void setIsGY(int isGY) {
                this.isGY = isGY;
            }
        }
    }
}
