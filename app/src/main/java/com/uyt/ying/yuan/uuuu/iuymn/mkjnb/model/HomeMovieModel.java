/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class HomeMovieModel extends CommonModel {
    String imageUrl;
    String lotteryName;
    String classfyName;
    String watchNum;
    String movieName;
    long movieId;
    int channelId;

//    String movieUrl;

    long cpId;;
    int isCollection;
    String uploadUrl;
    String networkUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getNetworkUrl() {
        return networkUrl;
    }

    public void setNetworkUrl(String networkUrl) {
        this.networkUrl = networkUrl;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public long getCpId() {
        return cpId;
    }

    public void setCpId(long cpId) {
        this.cpId = cpId;
    }

/*    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }*/

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public HomeMovieModel(String imageUrl, String lotteryName, String classfyName, String watchNum, String movieName, long movieId) {
        this.imageUrl = imageUrl;
        this.lotteryName = lotteryName;
        this.classfyName = classfyName;
        this.watchNum = watchNum;
        this.movieName = movieName;
        this.movieId = movieId;
    }
    //搜索结果调用的构造方法
    public HomeMovieModel(String imageUrl, String lotteryName, String movieName, long movieId) {
        this.imageUrl = imageUrl;
        this.lotteryName = lotteryName;
        this.movieName = movieName;
        this.movieId = movieId;
    }

    public HomeMovieModel(String imageUrl, String lotteryName, String classfyName, String watchNum, String movieName, long movieId, int channelId/*,String movieUrl*/) {
        this.imageUrl = imageUrl;
        this.lotteryName = lotteryName;
        this.classfyName = classfyName;
        this.watchNum = watchNum;
        this.movieName = movieName;
        this.movieId = movieId;
        this.channelId = channelId;
//        this.movieUrl = movieUrl;
    }


    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getClassfyName() {
        return classfyName;
    }

    public void setClassfyName(String classfyName) {
        this.classfyName = classfyName;
    }

    public String getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(String watchNum) {
        this.watchNum = watchNum;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public HomeMovieModel() {
    }



}
