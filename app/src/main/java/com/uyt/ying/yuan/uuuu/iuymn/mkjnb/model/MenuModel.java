package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class MenuModel extends CommonModel implements Serializable {

    /**
     * chessGameList : [{"image":"upload/images/20200504/1588579330583.png","game":0,"name2":Utils.getString(R.string.彩票游戏),"status":1},{"image":"upload/images/20200504/1588579319213.png","game":50,"name2":Utils.getString(R.string.开元棋牌),"status":1}]
     * message : 购彩大厅左侧菜单栏获取成功
     * status : success
     */

    private String message;
    private String status;
    private List<ChessGameListBean> chessGameList;

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

    public static class ChessGameListBean extends CommonModel implements Serializable{
        /**
         * image : upload/images/20200504/1588579330583.png
         * game : 0
         * name2 : 彩票游戏
         * status : 1
         */
        private boolean selector = false;
        private String image;
        private int game;
        private String name2;
        private int status;

        public boolean isSelector() {
            return selector;
        }

        public void setSelector(boolean selector) {
            this.selector = selector;
        }

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

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
