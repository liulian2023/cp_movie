package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class BetPopAllModel {

    List<BetPopTabModel> betPopTabModelList;
    List<BetPopChildModel> betPopChildModelList;

    public List<BetPopTabModel> getBetPopTabModelList() {
        return betPopTabModelList;
    }

    public void setBetPopTabModelList(List<BetPopTabModel> betPopTabModelList) {
        this.betPopTabModelList = betPopTabModelList;
    }

    public List<BetPopChildModel> getBetPopChildModelList() {
        return betPopChildModelList;
    }

    public void setBetPopChildModelList(List<BetPopChildModel> betPopChildModelList) {
        this.betPopChildModelList = betPopChildModelList;
    }
    public static class BetPopTabModel {

        private long id;
        private String name;
        private boolean isSelect;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class BetPopChildModel {

        private long id;  // 记录位置
        private long parentId;  //关联的groupId
        private long ruleId;  //投注id
        private String groupname;
        private String name;
        private BigDecimal odds;  //赔率
        private boolean isSelect;

        private int danjia;
        private int beishu;

        private String rubbish;

        public String getRubbish() {
            return rubbish;
        }

        public void setRubbish(String rubbish) {
            this.rubbish = rubbish;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public long getRuleId() {
            return ruleId;
        }

        public void setRuleId(long ruleId) {
            this.ruleId = ruleId;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
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

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getDanjia() {
            return danjia;
        }

        public void setDanjia(int danjia) {
            this.danjia = danjia;
        }

        public int getBeishu() {
            return beishu;
        }

        public void setBeishu(int beishu) {
            this.beishu = beishu;
        }
    }

}
