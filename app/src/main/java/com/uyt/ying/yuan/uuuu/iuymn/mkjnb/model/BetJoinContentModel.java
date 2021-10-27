package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class BetJoinContentModel {

    private long id;
    private long RuleId;
    private String name;
    private String type;
    private int beishu;
    private int danjia;
    private int chouma;
    String multiple="1";
    String quickAmount="";

    public String getQuickAmount() {
        return quickAmount;
    }

    public void setQuickAmount(String quickAmount) {
        this.quickAmount = quickAmount;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRuleId() {
        return RuleId;
    }

    public void setRuleId(long ruleId) {
        RuleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeishu() {
        return beishu;
    }

    public void setBeishu(int beishu) {
        this.beishu = beishu;
    }

    public int getDanjia() {
        return danjia;
    }

    public void setDanjia(int danjia) {
        this.danjia = danjia;
    }

    public int getChouma() {
        return chouma;
    }

    public void setChouma(int chouma) {
        this.chouma = chouma;
    }
}
