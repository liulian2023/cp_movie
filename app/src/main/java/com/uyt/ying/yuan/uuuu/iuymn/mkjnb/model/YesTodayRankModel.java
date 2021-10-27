

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class YesTodayRankModel extends CommonModel {
    String rank;
    String nickname;
    String amount;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public YesTodayRankModel(String rank, String nickname, String amount) {
        this.rank = rank;
        this.nickname = nickname;
        this.amount = amount;
    }
}
