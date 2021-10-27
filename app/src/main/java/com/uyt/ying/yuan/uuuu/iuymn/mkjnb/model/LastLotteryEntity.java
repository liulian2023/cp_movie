package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class LastLotteryEntity {
    int game;
    private String lotteryNo;//当期开奖号码
    private String lotteryqishu; //当前期数
    private List<String> NoList;
    private List<String> AnimalList ;
    private List<String> ColorList;


    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getLotteryqishu() {
        return lotteryqishu;
    }

    public void setLotteryqishu(String lotteryqishu) {
        this.lotteryqishu = lotteryqishu;
    }

    public List<String> getNoList() {
        return NoList;
    }

    public void setNoList(List<String> noList) {
        NoList = noList;
    }

    public List<String> getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(List<String> animalList) {
        AnimalList = animalList;
    }

    public List<String> getColorList() {
        return ColorList;
    }

    public void setColorList(List<String> colorList) {
        ColorList = colorList;
    }
}
