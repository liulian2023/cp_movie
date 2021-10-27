package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatAllMessageMedol implements Serializable {
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String toString() {
        return "ChatAllMessageMedol{" +
                "objectName='" + objectName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pointGrade='" + pointGrade + '\'' +
                ", content='" + content + '\'' +
                ", redId='" + redId + '\'' +
                ", type_id='" + type_id + '\'' +
                ", rule_id='" + rule_id + '\'' +
                ", lotteryqishu='" + lotteryqishu + '\'' +
                ", game='" + game + '\'' +
                ", typename='" + typename + '\'' +
                ", lotmoney='" + lotmoney + '\'' +
                ", name='" + name + '\'' +
                ", groupname='" + groupname + '\'' +
                ", nameList='" + nameList + '\'' +
                ", isOfficial='" + isOfficial + '\'' +
                ", bettingPrice='" + bettingPrice + '\'' +
                ", lotteryTotalPrice='" + lotteryTotalPrice + '\'' +
                ", profitAndLoss='" + profitAndLoss + '\'' +
                '}';
    }

    String objectName;
    private String nickname; //昵称
    private String pointGrade;//会员等级
    private String content;//红包备注
    private String redId;//红包id

    private String type_id;//彩票type_id
    private String rule_id;//投注id
    private String lotteryqishu;//彩票期数
    private String game;//彩票game
    private String typename;//彩票typename
    private String lotmoney;//下注金额
    private String name;//投注时点击的item name
    private String groupname;//未点击的item name
    private String nameList;
    private String isOfficial;

    private String bettingPrice;//投注金额
    private String lotteryTotalPrice;//中奖金额
    private String profitAndLoss;//盈亏

    private String image;//头像

     private String imageUrl;//图片路径
     private String userId;//图片路径
     private String date;
     private String videoUrl;
     private int status=0;
     private boolean isBack=false;

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
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

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getBettingPrice() {
        return bettingPrice;
    }

    public void setBettingPrice(String bettingPrice) {
        this.bettingPrice = bettingPrice;
    }

    public String getLotteryTotalPrice() {
        return lotteryTotalPrice;
    }

    public void setLotteryTotalPrice(String lotteryTotalPrice) {
        this.lotteryTotalPrice = lotteryTotalPrice;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }
}
