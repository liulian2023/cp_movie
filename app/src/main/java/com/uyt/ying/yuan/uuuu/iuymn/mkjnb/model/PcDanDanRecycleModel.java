

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class PcDanDanRecycleModel extends CommonModel implements Comparable<PcDanDanRecycleModel>{
    String betType; //下注类型
    String rebate; //赔率
    String rule_id;//玩法id
    String groupname;//玩法类型(用于下注清单中筛选每种类型的下注信息)
    int status=0;//是否选中  0否 1是

    public String getBetType() {
        return betType;
    }

    public PcDanDanRecycleModel(String betType, String rebate, String rule_id, String groupname) {
        this.betType = betType;
        this.rebate = rebate;
        this.rule_id = rule_id;
        this.groupname = groupname;
    }


    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int compareTo(PcDanDanRecycleModel o) {
        try {
            if (Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id())==0){
                return Integer.parseInt(this.getBetType())-Integer.parseInt(o.getBetType());
            }else {
                return Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id());
            }
        }catch (Exception e){
            return 0;
        }

    }
}
