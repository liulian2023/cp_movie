package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class
RebateModel  extends  CommonModel  {
    String text;
    String gameType;//彩票类型id,根据这个加载下拉刷新的数据
    String odds;
//    ArrayList<Odds> oddsArrayList =new ArrayList<>();

//    public RebateModel(String text, String gameType,String odds) {
//        this.text = text;
//        this.gameType = gameType;
//        this.odds = odds;
//    }

//
//    public ArrayList<Odds> getOddsArrayList() {
//        return oddsArrayList;
//    }
//
//    public void setOddsArrayList(ArrayList<Odds> oddsArrayList) {
//        this.oddsArrayList = oddsArrayList;
//    }

    public RebateModel(String text, String gameType, String odds/*, ArrayList<Odds> oddsArrayList*/) {
        this.text = text;
        this.gameType = gameType;
        this.odds = odds;
//        this.oddsArrayList = oddsArrayList;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public RebateModel(String text) {
        this.text = text;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public RebateModel(String text, String gameType) {

        this.text = text;
        this.gameType = gameType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
