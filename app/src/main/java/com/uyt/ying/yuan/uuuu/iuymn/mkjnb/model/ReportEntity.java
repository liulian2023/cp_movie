package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.List;

public class ReportEntity {


    /**
     * gameReport : [{"image":"upload/images/20200514/1589461498973.png","game":0,"profitAndLoss":0,"mobileStatus":1,"pcStatus":1,"tzNum":0,"name2":Utils.getString(R.string.彩票游戏),"tzPrice":0,"status":1,"zjPrice":0},{"image":"upload/images/20200515/1589540723821.jpg","game":50,"profitAndLoss":0,"mobileStatus":1,"pcStatus":1,"tzNum":0,"name2":Utils.getString(R.string.开元棋牌),"tzPrice":0,"status":1,"zjPrice":0},{"image":"upload/images/20201026/1603697471804.png","game":51,"profitAndLoss":0,"mobileStatus":1,"pcStatus":1,"tzNum":0,"name2":Utils.getString(R.string.龙城棋牌),"tzPrice":0,"status":1,"zjPrice":0}]
     * message : app游戏报表获取成功
     * status : success
     */

    private String message;
    private String status;
    /**
     * image : upload/images/20200514/1589461498973.png
     * game : 0
     * profitAndLoss : 0.0
     * mobileStatus : 1
     * pcStatus : 1
     * tzNum : 0
     * name2 : 彩票游戏
     * tzPrice : 0.0
     * status : 1
     * zjPrice : 0.0
     */

    private List<GameReportBean> gameReport;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GameReportBean> getGameReport() {
        return gameReport;
    }

    public void setGameReport(List<GameReportBean> gameReport) {
        this.gameReport = gameReport;
    }

    public static class GameReportBean extends CommonModel {
        private String image;
        private int game;
        private String profitAndLoss;
        private String mobileStatus;
        private String pcStatus;
        private String tzNum;
        private String name2;
        private String tzPrice;
        private String status;
        private String zjPrice;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public String getProfitAndLoss() {
            return StringMyUtil.isEmptyString(profitAndLoss)?"0":profitAndLoss;
        }

        public void setProfitAndLoss(String profitAndLoss) {
            this.profitAndLoss = profitAndLoss;
        }

        public String getMobileStatus() {
            return mobileStatus;
        }

        public void setMobileStatus(String mobileStatus) {
            this.mobileStatus = mobileStatus;
        }

        public String getPcStatus() {
            return pcStatus;
        }

        public void setPcStatus(String pcStatus) {
            this.pcStatus = pcStatus;
        }

        public String getTzNum() {
            return tzNum;
        }

        public void setTzNum(String tzNum) {
            this.tzNum = tzNum;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getTzPrice() {
            return tzPrice;
        }

        public void setTzPrice(String tzPrice) {
            this.tzPrice = tzPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getZjPrice() {
            return zjPrice;
        }

        public void setZjPrice(String zjPrice) {
            this.zjPrice = zjPrice;
        }
    }
}
