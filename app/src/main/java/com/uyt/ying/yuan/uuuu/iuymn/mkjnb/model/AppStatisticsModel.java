package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class AppStatisticsModel {
    public static AppStatisticsModel appStatisticsModel;
    boolean isAppStatistics=false;
    public static AppStatisticsModel getInstance(){
        if(appStatisticsModel==null){
            appStatisticsModel = new AppStatisticsModel();
        }
        return appStatisticsModel;
    }

    public boolean isAppStatistics() {
        return isAppStatistics;
    }

    public void setAppStatistics(boolean appStatistics) {
        isAppStatistics = appStatistics;
    }
}
