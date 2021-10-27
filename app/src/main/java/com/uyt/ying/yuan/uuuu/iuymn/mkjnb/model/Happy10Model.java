package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.Objects;

public class Happy10Model extends CommonModel implements Serializable,Comparable<Happy10Model> {
    String betType; //下注类型
    String rebate; //赔率
    String rule_id;//玩法id
    String groupname;//玩法类型(用于下注清单中筛选每种类型的下注信息)
    int status=0;//是否选中
    String lianmaType;//快乐十分中 用于判断是 连码 页面下的哪种类型(跟recycleview上方的radioButton比较)
    String lianmaId="";//快乐十分中 连码 数据需要自己生成,导致点击每个radioButton拿到的数据id都是一样的,点击当前radioButoon后,切换回来,由于是按id判断是否选中,会导致所有的item都选中,所以再加一个字段


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Happy10Model)) return false;
        Happy10Model that = (Happy10Model) o;
        return Objects.equals(betType, that.betType) &&
                Objects.equals(rebate, that.rebate) &&
                Objects.equals(rule_id, that.rule_id) &&
                Objects.equals(groupname, that.groupname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betType, rebate, rule_id, groupname);
    }

    public String getLianmaType() {
        return lianmaType;
    }

    public String getLianmaId() {
        return lianmaId;
    }

    public void setLianmaId(String lianmaId) {
        this.lianmaId = lianmaId;
    }

    public void setLianmaType(String lianmaType) {
        this.lianmaType = lianmaType;
    }

    public Happy10Model() {
    }

    public Happy10Model(String betType, String rebate, String rule_id, String groupname) {
        this.betType = betType;
        this.rebate = rebate;
        this.rule_id = rule_id;
        this.groupname = groupname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }



    public String getBetType() {
        return betType;
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

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    @Override
    public String toString() {
        return "Happy10Model{" +
                "betType='" + betType + '\'' +
                ", rebate='" + rebate + '\'' +
                ", rule_id='" + rule_id + '\'' +
                ", groupname='" + groupname + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public int compareTo(Happy10Model o) {
        try {
            if (Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id())==0){
                if (Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id())==0){
                    return Integer.parseInt(this.getBetType())-Integer.parseInt(o.getBetType());
                }else {
                    return Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id());
                }
            }else {
                return Integer.parseInt(this.rule_id)-Integer.parseInt(o.getRule_id());
            }

        }catch (Exception e){
            return 0;
        }

    }
}
