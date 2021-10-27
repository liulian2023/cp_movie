/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class SysParameterEntity {


    /**
     * name : 斯嘎视频
     * logo : upload/images/20191025/1571985759542.jpg
     * isClose : 0
     * closeDesc : 您已关闭!
     * isCloseBackCode : 1
     * isCloseCommentReview : 1
     * imgDomain : http://103.253.13.162:8877/
     * frontDomain : http://103.253.13.162:8855/
     * registerSetting : {"isCloseTgTask":"1","maxNumOneIp":"55","userNameIgnore":"select,delete","type":0,"isCloseSmsCode":"0"}
     * advertisementSetting : {"videoAdSettings":"2","type":1}
     * searchSetting : {"searchReg":"select.delete,name,special,expand,label","recommendHot":"a,b,c,d,e,f,g","searchHot":Utils.getString(R.string.动作,恐怖,犯罪,爱情,喜剧,战争,悬疑,科幻,剧情,灾难),"type":2}
     * videoSetting : {"likeNumEnd":"999","performer":Utils.getString(R.string.周星驰),"scoreEnd":"9.9","playNumEnd":"1000","type":3,"playNumStart":"500","movieName":Utils.getString(R.string.2019中英电视台联欢晚会),"introduction":"123!","moviePhoto":"upload/images/20191031/1572492210171.jpg","scoreStart":"5","likeNumStart":"500"}
     * recommendSetting : {"recommendUrl":"http://103.253.13.162:8855","recommendUrl2":Utils.getString(R.string.发现一个免费电影网站http://103.253.13.162:8855，最新电影美剧韩剧应有尽有，分享给大家！),"appUrl":"http://www.baidu.com","type":4,"tipsUp":Utils.getString(R.string.扫码下载APP),"tips":Utils.getString(R.string.使用前请保存官方网址，可在官网下载最新APP，官网：http://www.baidu.comm)}
     * id : 1
     */

    private String name;
    private String logo;
    private int isClose;
    private String closeDesc;
    private int isCloseBackCode;
    private int isCloseCommentReview;
    private String imgDomain;
    private String frontDomain;
    private String registerSetting;
    private String advertisementSetting;
    private String searchSetting;
    private String videoSetting;
    private String recommendSetting;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    public String getCloseDesc() {
        return closeDesc;
    }

    public void setCloseDesc(String closeDesc) {
        this.closeDesc = closeDesc;
    }

    public int getIsCloseBackCode() {
        return isCloseBackCode;
    }

    public void setIsCloseBackCode(int isCloseBackCode) {
        this.isCloseBackCode = isCloseBackCode;
    }

    public int getIsCloseCommentReview() {
        return isCloseCommentReview;
    }

    public void setIsCloseCommentReview(int isCloseCommentReview) {
        this.isCloseCommentReview = isCloseCommentReview;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public String getFrontDomain() {
        return frontDomain;
    }

    public void setFrontDomain(String frontDomain) {
        this.frontDomain = frontDomain;
    }

    public String getRegisterSetting() {
        return registerSetting;
    }

    public void setRegisterSetting(String registerSetting) {
        this.registerSetting = registerSetting;
    }

    public String getAdvertisementSetting() {
        return advertisementSetting;
    }

    public void setAdvertisementSetting(String advertisementSetting) {
        this.advertisementSetting = advertisementSetting;
    }

    public String getSearchSetting() {
        return searchSetting;
    }

    public void setSearchSetting(String searchSetting) {
        this.searchSetting = searchSetting;
    }

    public String getVideoSetting() {
        return videoSetting;
    }

    public void setVideoSetting(String videoSetting) {
        this.videoSetting = videoSetting;
    }

    public String getRecommendSetting() {
        return recommendSetting;
    }

    public void setRecommendSetting(String recommendSetting) {
        this.recommendSetting = recommendSetting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
