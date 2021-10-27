
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ChangLongBetModel extends CommonModel {
    int game;
    String type_id;
    String ruleId_Left;
    String ruleId_Right;
    String imgUrl;
    String typaname;
    String qishu;
    String time;
    String playType;
    String daxiao;
    String qishu2;
    String betTypeLeft;
    String rebateLeft;
    String betTypeRight;
    String rebateRight;
    String shijaincha;
    String lastLotteryQiShu;
    String dragonId;
    boolean isLeft =false;
    int statusLeft=0;  //左侧投注linear的选中状态
    int statusRight=0;//右侧投注linear的选中状态
    long id;
    boolean isXian;

    public boolean isXian() {
        return isXian;
    }

    public void setXian(boolean xian) {
        isXian = xian;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public String getDragonId() {
        return dragonId;
    }

    public void setDragonId(String dragonId) {
        this.dragonId = dragonId;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTypaname() {
        return typaname;
    }

    public void setTypaname(String typaname) {
        this.typaname = typaname;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public String getDaxiao() {
        return daxiao;
    }

    public void setDaxiao(String daxiao) {
        this.daxiao = daxiao;
    }

    public String getQishu2() {
        return qishu2;
    }

    public void setQishu2(String qishu2) {
        this.qishu2 = qishu2;
    }

    public String getBetTypeLeft() {
        return betTypeLeft;
    }

    public void setBetTypeLeft(String betTypeLeft) {
        this.betTypeLeft = betTypeLeft;
    }

    public String getRebateLeft() {
        return rebateLeft;
    }

    public void setRebateLeft(String rebateLeft) {
        this.rebateLeft = rebateLeft;
    }

    public String getBetTypeRight() {
        return betTypeRight;
    }

    public void setBetTypeRight(String betTypeRight) {
        this.betTypeRight = betTypeRight;
    }

    public String getRebateRight() {
        return rebateRight;
    }

    public void setRebateRight(String rebateRight) {
        this.rebateRight = rebateRight;
    }

    public int getStatusLeft() {
        return statusLeft;
    }

    public void setStatusLeft(int statusLeft) {
        this.statusLeft = statusLeft;
    }

    public int getStatusRight() {
        return statusRight;
    }

    public void setStatusRight(int statusRight) {
        this.statusRight = statusRight;
    }


    public String getShijaincha() {
        return shijaincha;
    }

    public void setShijaincha(String shijaincha) {
        this.shijaincha = shijaincha;
    }

    public String getLastLotteryQiShu() {
        return lastLotteryQiShu;
    }

    public void setLastLotteryQiShu(String lastLotteryQiShu) {
        this.lastLotteryQiShu = lastLotteryQiShu;
    }

    public ChangLongBetModel(int game, String type_id, String ruleId_Left, String ruleId_Right,String imgUrl, String typaname, String qishu, String time, String playType, String daxiao, String qishu2, String betTypeLeft, String rebateLeft, String betTypeRight, String rebateRight, String shijaincha, String lastLotteryQiShu,String dragonId) {
        this.game = game;
        this.type_id = type_id;
        this.ruleId_Left = ruleId_Left;
        this.ruleId_Right = ruleId_Right;
        this.imgUrl = imgUrl;
        this.typaname = typaname;
        this.qishu = qishu;
        this.time = time;
        this.playType = playType;
        this.daxiao = daxiao;
        this.qishu2 = qishu2;
        this.betTypeLeft = betTypeLeft;
        this.rebateLeft = rebateLeft;
        this.betTypeRight = betTypeRight;
        this.rebateRight = rebateRight;
        this.shijaincha = shijaincha;
        this.lastLotteryQiShu = lastLotteryQiShu;
        this.dragonId=dragonId;
    }

    public String getRuleId_Left() {
        return ruleId_Left;
    }

    public void setRuleId_Left(String ruleId_Left) {
        this.ruleId_Left = ruleId_Left;
    }

    public String getRuleId_Right() {
        return ruleId_Right;
    }

    public void setRuleId_Right(String ruleId_Right) {
        this.ruleId_Right = ruleId_Right;
    }
}
