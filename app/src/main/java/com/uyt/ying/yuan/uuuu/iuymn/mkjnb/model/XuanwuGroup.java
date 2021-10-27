/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class XuanwuGroup{


    /**
     * RulelistAll : [{"isboth":0,"balled":0,"Rulelist":"xxx","type":0}]
     * message : 操作成功
     * status : success
     */

    private String message;
    private String status;
    private List<RulelistAllBean> RulelistAll;

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

    public List<RulelistAllBean> getRulelistAll() {
        return RulelistAll;
    }

    public void setRulelistAll(List<RulelistAllBean> RulelistAll) {
        this.RulelistAll = RulelistAll;
    }

    public static class RulelistAllBean {
        /**
         * isboth : 0
         * balled : 0
         * Rulelist : xxx
         * type : 0
         */

        private int isboth;
        private int balled;
        private String Rulelist;
        private int type;

        public int getIsboth() {
            return isboth;
        }

        public void setIsboth(int isboth) {
            this.isboth = isboth;
        }

        public int getBalled() {
            return balled;
        }

        public void setBalled(int balled) {
            this.balled = balled;
        }

        public String getRulelist() {
            return Rulelist;
        }

        public void setRulelist(String Rulelist) {
            this.Rulelist = Rulelist;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
