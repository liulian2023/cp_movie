package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.Objects;

public class SscModel implements Serializable,Comparable<SscModel> {
    String betType; //下注类型
    String rebate; //赔率
    String rule_id;//玩法id
    String groupname;//玩法类型(用于下注清单中筛选每种类型的下注信息)
    int status=0;//是否选中
    String lianmaType;//快乐十分中 用于判断是 连码 页面下的哪种类型(跟recycleview上方的radioButton比较)
    String lianmaId="";//快乐十分中 连码 数据需要自己生成,导致点击每个radioButton拿到的数据id都是一样的,点击当前radioButoon后,切换回来,由于是按id判断是否选中,会导致所有的item都选中,所以再加一个字段

    public String getLianmaType() {
        return lianmaType;
    }


    public SscModel(String betType, String rebate, String rule_id, String groupname) {
        this.betType = betType;
        this.rebate = rebate;
        this.rule_id = rule_id;
        this.groupname = groupname;
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

    public SscModel() {
    }

//    public SscModel(String betType, String rebate, String rule_id, String groupname) {
//        this.betType = betType;
//        this.rebate = rebate;
//        this.rule_id = rule_id;
//        this.groupname = groupname;
//    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SscModel that = (SscModel) o;
        return rule_id.equals(that.rule_id) &&
                lianmaId.equals(that.lianmaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule_id, lianmaId);
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

    //根据id 排序
    @Override
    public int compareTo(SscModel o) {
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
