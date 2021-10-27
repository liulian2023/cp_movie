package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.util.List;

public class ChessListBean {
    private static ChessListBean chessListBean;
    List<ShoppingContentChessModel.ChessEntity.DataBean> data;
    public static ChessListBean getInstance(){
        if(chessListBean==null){
            chessListBean = new ChessListBean();
        }
        return chessListBean;
    }

    private ChessListBean() {
    }

    public List<ShoppingContentChessModel.ChessEntity.DataBean> getData() {
        return data;
    }

    public void setData(List<ShoppingContentChessModel.ChessEntity.DataBean> data) {
        this.data = data;
    }
}
