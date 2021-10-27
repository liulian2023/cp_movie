package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class ChessBalanceEntity {


    /**
     * chessMemberMoneyFreeMoney : 0
     * chessGameList : [{"freeMoney":0,"game":50,"id":2,"image":"upload/images/20200502/1588416434278.jpg","listsort":1,"name2":Utils.getString(R.string.开元棋牌)}]
     * chessMemberMoneyFreeMoneyStatus : 1
     * memberMoney : 216713.77
     * autoConvert : 1
     * message : 获取用户棋牌资金成功
     * status : success
     */

    private String chessMemberMoneyFreeMoney;
    private String chessMemberMoneyFreeMoneyStatus;
    private String memberMoney;
    private int autoConvert;
    private String message;
    private String status;
    private List<ChessGameListBean> chessGameList;

    public String getChessMemberMoneyFreeMoney() {
        return chessMemberMoneyFreeMoney;
    }

    public void setChessMemberMoneyFreeMoney(String chessMemberMoneyFreeMoney) {
        this.chessMemberMoneyFreeMoney = chessMemberMoneyFreeMoney;
    }

    public String getChessMemberMoneyFreeMoneyStatus() {
        return chessMemberMoneyFreeMoneyStatus;
    }

    public void setChessMemberMoneyFreeMoneyStatus(String chessMemberMoneyFreeMoneyStatus) {
        this.chessMemberMoneyFreeMoneyStatus = chessMemberMoneyFreeMoneyStatus;
    }

    public String getMemberMoney() {
        return memberMoney;
    }

    public void setMemberMoney(String memberMoney) {
        this.memberMoney = memberMoney;
    }

    public int getAutoConvert() {
        return autoConvert;
    }

    public void setAutoConvert(int autoConvert) {
        this.autoConvert = autoConvert;
    }

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

    public List<ChessGameListBean> getChessGameList() {
        return chessGameList;
    }

    public void setChessGameList(List<ChessGameListBean> chessGameList) {
        this.chessGameList = chessGameList;
    }

    public static class ChessGameListBean extends CommonModel{
        /**
         * freeMoney : 0
         * game : 50
         * id : 2
         * image : upload/images/20200502/1588416434278.jpg
         * listsort : 1
         * name2 : 开元棋牌
         */

        private String freeMoney;
        private String game;
        private long id;
        private String image;
        private int listsort;
        private String name2;

        public String getFreeMoney() {
            return freeMoney;
        }

        public void setFreeMoney(String freeMoney) {
            this.freeMoney = freeMoney;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }
    }
}
