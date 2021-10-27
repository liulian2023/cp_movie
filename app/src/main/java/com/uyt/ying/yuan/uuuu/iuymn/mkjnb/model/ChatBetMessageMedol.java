package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatBetMessageMedol implements Serializable {
    private String nickname; //昵称
    private String pointGrade;//会员等级
    private String type_id;//彩票type_id
    private String rule_id;//投注id
    private String lotteryqishu;//彩票期数
    private String game;//彩票game
    private String typename;//彩票typename
    private String lotmoney;//下注金额
    private String name;//投注时点击的item name
    private String groupname;//未点击的item name
//    private String nameList;
    private String isOfficial;

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public ChatBetMessageMedol(String nickname, String pointGrade, String type_id, String rule_id, String lotteryqishu, String game, String typename, String lotmoney, String name, String groupname, /*String nameList,*/ String isOfficial) {
        this.nickname = nickname;
        this.pointGrade = pointGrade;
        this.type_id = type_id;
        this.rule_id = rule_id;
        this.lotteryqishu = lotteryqishu;
        this.game = game;
        this.typename = typename;
        this.lotmoney = lotmoney;
        this.name = name;
        this.groupname = groupname;
//        this.nameList = nameList;
        this.isOfficial = isOfficial;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPointGrade() {
        return pointGrade;
    }

    public void setPointGrade(String pointGrade) {
        this.pointGrade = pointGrade;
    }



    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    public String getLotteryqishu() {
        return lotteryqishu;
    }

    public void setLotteryqishu(String lotteryqishu) {
        this.lotteryqishu = lotteryqishu;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getLotmoney() {
        return lotmoney;
    }

    public void setLotmoney(String lotmoney) {
        this.lotmoney = lotmoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

//    public String getNameList() {
//        return nameList;
//    }
//
//    public void setNameList(String nameList) {
//        this.nameList = nameList;
//    }

}
