package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;

public class TodayWinLoseModel extends CommonModel  {
    String qishu;//期数
    BigDecimal betAmount;//下注金额
    BigDecimal winAmount;//中奖金额
    BigDecimal winLoseAmount;//输赢金额

    public TodayWinLoseModel(String qishu, BigDecimal betAmount, BigDecimal winAmount, BigDecimal winLoseAmount) {
        this.qishu = qishu;
        this.betAmount = betAmount;
        this.winAmount = winAmount;
        this.winLoseAmount = winLoseAmount;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }

    public BigDecimal getWinLoseAmount() {
        return winLoseAmount;
    }

    public void setWinLoseAmount(BigDecimal winLoseAmount) {
        this.winLoseAmount = winLoseAmount;
    }
}
