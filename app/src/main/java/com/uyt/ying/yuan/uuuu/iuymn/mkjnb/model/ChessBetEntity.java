package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class ChessBetEntity {

    /**
     * touZhuList : [{"account":"ll1111","betAmount":102050,"endTime":1588328851000,"game":50,"gameId":"50-1588328388-112623960-3","id":663,"profitAmount":-18450,"revenueAmount":0,"startTime":1588328388000,"tableId":"102060001","type_id":"510","typename":Utils.getString(R.string.红包捕鱼),"user_id":72,"user_name":"ll1111","validBetAmount":102050},{"account":"ll1111","betAmount":114400,"endTime":1588328388000,"game":50,"gameId":"50-1588327816-112623960-3","id":662,"profitAmount":-20850,"revenueAmount":0,"startTime":1588327816000,"tableId":"102060001","type_id":"510","typename":Utils.getString(R.string.红包捕鱼),"user_id":72,"user_name":"ll1111","validBetAmount":114400}]
     * message : 获取棋牌投注记录成功!!!!!
     * status : success
     */

    private String message;
    private String status;
    private List<TouZhuListBean> touZhuList;

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

    public List<TouZhuListBean> getTouZhuList() {
        return touZhuList;
    }

    public void setTouZhuList(List<TouZhuListBean> touZhuList) {
        this.touZhuList = touZhuList;
    }

    public static class TouZhuListBean extends CommonModel implements Serializable {
        /**
         * account : ll1111
         * betAmount : 102050.0
         * endTime : 1588328851000
         * game : 50
         * gameId : 50-1588328388-112623960-3
         * id : 663
         * profitAmount : -18450.0
         * revenueAmount : 0.0
         * startTime : 1588328388000
         * tableId : 102060001
         * type_id : 510
         * typename : 红包捕鱼
         * user_id : 72
         * user_name : ll1111
         * validBetAmount : 102050.0
         */

        private String account;
        private String betAmount;
        private long endTime;
        private int game;
        private String gameId;
        private long id;
        private String profitAmount;
        private String revenueAmount;
        private long startTime;
        private String tableId;
        private String type_id;
        private String typename;
        private long user_id;
        private String user_name;
        private String validBetAmount;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(String betAmount) {
            this.betAmount = betAmount;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProfitAmount() {
            return profitAmount;
        }

        public void setProfitAmount(String profitAmount) {
            this.profitAmount = profitAmount;
        }

        public String getRevenueAmount() {
            return revenueAmount;
        }

        public void setRevenueAmount(String revenueAmount) {
            this.revenueAmount = revenueAmount;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getTableId() {
            return tableId;
        }

        public void setTableId(String tableId) {
            this.tableId = tableId;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getValidBetAmount() {
            return validBetAmount;
        }

        public void setValidBetAmount(String validBetAmount) {
            this.validBetAmount = validBetAmount;
        }
    }
}
