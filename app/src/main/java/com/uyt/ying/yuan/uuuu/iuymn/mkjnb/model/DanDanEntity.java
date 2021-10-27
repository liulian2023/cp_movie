package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class DanDanEntity {


    /**
     * danRulelist : [{"code":"da","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.hunhe),"id":497,"isDelete":0,"model_id":1,"name":Utils.getString(R.string.大),"odds":1.98,"type_id":6},{"code":"xiao","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":498,"isDelete":0,"model_id":2,"name":Utils.getString(R.string.小),"odds":1.98,"type_id":6},{"code":"dan","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":499,"isDelete":0,"model_id":3,"name":Utils.getString(R.string.单),"odds":1.98,"type_id":6},{"code":"sh","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":500,"isDelete":0,"model_id":4,"name":Utils.getString(R.string.双),"odds":1.98,"type_id":6},{"code":"ddan","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":501,"isDelete":0,"model_id":5,"name":Utils.getString(R.string.大单),"odds":4.287,"type_id":6},{"code":"dshuan","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":502,"isDelete":0,"model_id":6,"name":Utils.getString(R.string.大双),"odds":3.683,"type_id":6},{"code":"xdan","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":503,"isDelete":0,"model_id":7,"name":Utils.getString(R.string.小单),"odds":3.683,"type_id":6},{"code":"xshaun","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":504,"isDelete":0,"model_id":8,"name":Utils.getString(R.string.小双),"odds":4.287,"type_id":6},{"code":"jda","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":505,"isDelete":0,"model_id":9,"name":Utils.getString(R.string.极大),"odds":9.801,"type_id":6},{"code":"jxiao","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":506,"isDelete":0,"model_id":10,"name":Utils.getString(R.string.极小),"odds":9.801,"type_id":6},{"code":"baozi","createdDate":1557319000000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.混合),"id":507,"isDelete":0,"model_id":11,"name":Utils.getString(R.string.豹子),"odds":89.1,"type_id":6},{"code":"red","createdDate":1557319000000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.波色),"id":508,"isDelete":0,"model_id":12,"name":Utils.getString(R.string.红波),"odds":2.95,"type_id":6},{"code":"green","createdDate":1557319000000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.波色),"id":509,"isDelete":0,"model_id":13,"name":Utils.getString(R.string.绿波),"odds":3.802,"type_id":6},{"code":"blue","createdDate":1557319000000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.波色),"id":510,"isDelete":0,"model_id":14,"name":Utils.getString(R.string.蓝波),"odds":3.802,"type_id":6},{"code":"0","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":511,"isDelete":0,"model_id":15,"name":"0","odds":980.1,"type_id":6},{"code":"1","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":512,"isDelete":0,"model_id":16,"name":"1","odds":326.7,"type_id":6},{"code":"2","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":513,"isDelete":0,"model_id":17,"name":"2","odds":163.35,"type_id":6},{"code":"3","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":514,"isDelete":0,"model_id":18,"name":"3","odds":98.01,"type_id":6},{"code":"4","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":515,"isDelete":0,"model_id":19,"name":"4","odds":65.34,"type_id":6},{"code":"5","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":516,"isDelete":0,"model_id":20,"name":"5","odds":46.669,"type_id":6},{"code":"6","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":517,"isDelete":0,"model_id":21,"name":"6","odds":35.006,"type_id":6},{"code":"7","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":518,"isDelete":0,"model_id":22,"name":"7","odds":27.225,"type_id":6},{"code":"8","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":519,"isDelete":0,"model_id":23,"name":"8","odds":21.78,"type_id":6},{"code":"9","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":520,"isDelete":0,"model_id":24,"name":"9","odds":17.82,"type_id":6},{"code":"10","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":521,"isDelete":0,"model_id":25,"name":"10","odds":15.553,"type_id":6},{"code":"11","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":522,"isDelete":0,"model_id":26,"name":"11","odds":14.206,"type_id":6},{"code":"12","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":523,"isDelete":0,"model_id":27,"name":"12","odds":13.424,"type_id":6},{"code":"13","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":524,"isDelete":0,"model_id":28,"name":"13","odds":13.068,"type_id":6},{"code":"14","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":525,"isDelete":0,"model_id":29,"name":"14","odds":13.068,"type_id":6},{"code":"15","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":526,"isDelete":0,"model_id":30,"name":"15","odds":13.424,"type_id":6},{"code":"16","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":527,"isDelete":0,"model_id":31,"name":"16","odds":14.206,"type_id":6},{"code":"17","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":528,"isDelete":0,"model_id":32,"name":"17","odds":15.553,"type_id":6},{"code":"18","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":529,"isDelete":0,"model_id":33,"name":"18","odds":17.82,"type_id":6},{"code":"19","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":530,"isDelete":0,"model_id":34,"name":"19","odds":21.78,"type_id":6},{"code":"20","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":531,"isDelete":0,"model_id":35,"name":"20","odds":27.225,"type_id":6},{"code":"21","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":532,"isDelete":0,"model_id":36,"name":"21","odds":35.006,"type_id":6},{"code":"22","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":533,"isDelete":0,"model_id":37,"name":"22","odds":46.669,"type_id":6},{"code":"23","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":534,"isDelete":0,"model_id":38,"name":"23","odds":65.34,"type_id":6},{"code":"24","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":535,"isDelete":0,"model_id":39,"name":"24","odds":98.01,"type_id":6},{"code":"25","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":536,"isDelete":0,"model_id":40,"name":"25","odds":163.35,"type_id":6},{"code":"26","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":537,"isDelete":0,"model_id":41,"name":"26","odds":326.7,"type_id":6},{"code":"27","createdDate":1557319000000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.特码),"id":538,"isDelete":0,"model_id":42,"name":"27","odds":980.1,"type_id":6}]
     * grouplist : [{"group_id":1,"groupname":Utils.getString(R.string.混合)},{"group_id":2,"groupname":Utils.getString(R.string.波色)},{"group_id":3,"groupname":Utils.getString(R.string.特码)}]
     * message : 操作成功
     * status : success
     */

    private String message;
    private String status;
    private List<DanRulelistBean> danRulelist;
    private List<GrouplistBean> grouplist;

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

    public List<DanRulelistBean> getDanRulelist() {
        return danRulelist;
    }

    public void setDanRulelist(List<DanRulelistBean> danRulelist) {
        this.danRulelist = danRulelist;
    }

    public List<GrouplistBean> getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(List<GrouplistBean> grouplist) {
        this.grouplist = grouplist;
    }

    public static class DanRulelistBean {
        /**
         * code : da
         * createdDate : 1557319000000
         * createdUser : sys
         * group_id : 1
         * groupname : 混合
         * id : 497
         * isDelete : 0
         * model_id : 1
         * name : 大
         * odds : 1.98
         * type_id : 6
         */

        private String code;
        private long createdDate;
        private String createdUser;
        private int group_id;
        private String groupname;
        private long id;
        private int isDelete;
        private long model_id;
        private String name;
        private BigDecimal odds;
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

        public long getModel_id() {
            return model_id;
        }

        public void setModel_id(long model_id) {
            this.model_id = model_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getOdds() {
            return odds;
        }

        public void setOdds(BigDecimal odds) {
            this.odds = odds;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }
    }

    public static class GrouplistBean {
        /**
         * group_id : 1
         * groupname : 混合
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
}
