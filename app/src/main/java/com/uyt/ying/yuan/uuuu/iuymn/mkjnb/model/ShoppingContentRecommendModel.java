package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ShoppingContentRecommendModel extends CommonModel {
    ShoppingContentChessModel.ChessEntity.DataBean chessDataBean;
    NavigateEntity.GameClassListBean lotteryDataBean;
    boolean isHistory=false;//当前model是否显示在浏览记录中

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public ShoppingContentChessModel.ChessEntity.DataBean getChessDataBean() {
        return chessDataBean;
    }

    public void setChessDataBean(ShoppingContentChessModel.ChessEntity.DataBean chessDataBean) {
        this.chessDataBean = chessDataBean;
    }

    public NavigateEntity.GameClassListBean getLotteryDataBean() {
        return lotteryDataBean;
    }

    public void setLotteryDataBean(NavigateEntity.GameClassListBean lotteryDataBean) {
        this.lotteryDataBean = lotteryDataBean;
    }

}
