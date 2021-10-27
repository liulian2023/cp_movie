package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;

public class HbGameClassModel {
    String gameIdListStr="";
    RongcloudHBParameter hbParameter;
    String typtIdList;
    String gamelist;
    private static HbGameClassModel hbGameClassModel;
    public static HbGameClassModel getInstance( ){
        if(null==hbGameClassModel){
            hbGameClassModel = new HbGameClassModel();
        }
        return hbGameClassModel;
    }
    public String getTyptIdList() {
        return typtIdList;
    }

    public void setTyptIdList(String typtIdList) {
        this.typtIdList = typtIdList;
    }

    public String getGamelist() {
        return gamelist;
    }

    public void setGamelist(String gamelist) {
        this.gamelist = gamelist;
    }

    public static HbGameClassModel getHbGameClassModel() {
        return hbGameClassModel;
    }

    public static void setHbGameClassModel(HbGameClassModel hbGameClassModel) {
        HbGameClassModel.hbGameClassModel = hbGameClassModel;
    }

    private HbGameClassModel( ) {

    }

    public String getGameIdListStr() {
        return gameIdListStr;
    }

    public void setGameIdListStr(String gameIdListStr) {
        this.gameIdListStr = gameIdListStr;
    }
    public RongcloudHBParameter getHbParameter() {
        return hbParameter;
    }

    public void setHbParameter(RongcloudHBParameter hbParameter) {
        this.hbParameter = hbParameter;
    }
}
