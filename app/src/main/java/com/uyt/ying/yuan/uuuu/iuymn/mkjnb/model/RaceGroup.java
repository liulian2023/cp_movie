/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class RaceGroup implements Serializable {

    private String message;
    private String status;
    private List<RulelistAllBean> RulelistAll;
    public class RulelistAllBean implements Serializable{

        /**
         * isboth : 0
         * Rulelist : sad
         * type : 0
         * isGY : 0
         */

        private int isboth;
        private String Rulelist;
        private int type;
        private int isGY;

        public int getIsboth() {
            return isboth;
        }

        public void setIsboth(int isboth) {
            this.isboth = isboth;
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

        public int getIsGY() {
            return isGY;
        }

        public void setIsGY(int isGY) {
            this.isGY = isGY;
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

    public List<RulelistAllBean> getRulelistAll() {
        return RulelistAll;
    }

    public void setRulelistAll(List<RulelistAllBean> rulelistAll) {
        RulelistAll = rulelistAll;
    }
}
