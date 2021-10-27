package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class ChannelMessageModel {

    String jsonStr;
    boolean iisClearMovies;
    int finalPageNo;
    boolean isLoadmore;
    boolean isRefresh;
    String channelName;
    long channelId;

    public ChannelMessageModel(String jsonStr, boolean iisClearMovies, int finalPageNo, boolean isLoadmore, boolean isRefresh, String channelName, long channelId) {
        this.jsonStr = jsonStr;
        this.iisClearMovies = iisClearMovies;
        this.finalPageNo = finalPageNo;
        this.isLoadmore = isLoadmore;
        this.isRefresh = isRefresh;
        this.channelName = channelName;
        this.channelId = channelId;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public boolean isIisClearMovies() {
        return iisClearMovies;
    }

    public void setIisClearMovies(boolean iisClearMovies) {
        this.iisClearMovies = iisClearMovies;
    }

    public int getFinalPageNo() {
        return finalPageNo;
    }

    public void setFinalPageNo(int finalPageNo) {
        this.finalPageNo = finalPageNo;
    }

    public boolean isLoadmore() {
        return isLoadmore;
    }

    public void setLoadmore(boolean loadmore) {
        isLoadmore = loadmore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
