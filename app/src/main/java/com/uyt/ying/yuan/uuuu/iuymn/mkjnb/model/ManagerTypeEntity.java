package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class ManagerTypeEntity {

    /**
     * anchorSubscribeEndDate : 
     * isSuperRoomManager : 0
     * anchorSubscribe : 0.0
     * isLiving : 1
     * liveUrl : hO7gWboZUrFxHFL5KLyyXqwiyWJ8qnzbuYl7HeLvnYE1X05U+8z1mFHGuQIBumJYuVVXAl5sAFay
     W3y0xLKbwaORoLwx3Fynx8RXGvzA36gCv1HzxiZDBMNkKftN10X17MK3KDN/Zsj7oF1JWho7r8+q
     j/QcHDP0UO4wutPrXD/XphmCo8Ulp2NTldyuYm89
     * autoAnchorSubscribe : 0
     * inviteNumber : 2
     * message : success
     * status : success
     */

    private String anchorSubscribeEndDate;
    private String isSuperRoomManager; //是不是超级房管(0否；1是)
    private String anchorSubscribe;//该直播间收费标准(>0要收费；=0无需收费)
    private String isLiving;
    private String liveUrl;
    private String autoAnchorSubscribe; //用户是否自动续费
    private String inviteNumber;//用户还需要邀请多少人才可以发言(=0够了;>0还需要邀请)
    private String message;
    private String status;
    private String isRoomManager;
    private String anchorBusinessCard;
    private String giftAmount;
    private int robotCount=0;
    private int isLevel;

    public int getIsLevel() {
        return isLevel;
    }

    public void setIsLevel(int isLevel) {
        this.isLevel = isLevel;
    }

    public int getRobotCount() {
        return robotCount;
    }

    public void setRobotCount(int robotCount) {
        this.robotCount = robotCount;
    }

    public String getAnchorBusinessCard() {
        return anchorBusinessCard;
    }

    public void setAnchorBusinessCard(String anchorBusinessCard) {
        this.anchorBusinessCard = anchorBusinessCard;
    }

    public String getIsRoomManager() {
        return StringMyUtil.isEmptyString(isRoomManager)?"0":isRoomManager;
    }

    public String getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(String giftAmount) {
        this.giftAmount = giftAmount;
    }

    public void setIsRoomManager(String isRoomManager) {
        this.isRoomManager = isRoomManager;
    }

    public String getAnchorSubscribeEndDate() {
        return anchorSubscribeEndDate;
    }

    public void setAnchorSubscribeEndDate(String anchorSubscribeEndDate) {
        this.anchorSubscribeEndDate = anchorSubscribeEndDate;
    }

    public String getIsSuperRoomManager() {
        return isSuperRoomManager;
    }

    public void setIsSuperRoomManager(String isSuperRoomManager) {
        this.isSuperRoomManager = isSuperRoomManager;
    }

    public String getAnchorSubscribe() {
        return anchorSubscribe;
    }

    public void setAnchorSubscribe(String anchorSubscribe) {
        this.anchorSubscribe = anchorSubscribe;
    }

    public String getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(String isLiving) {
        this.isLiving = isLiving;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getAutoAnchorSubscribe() {
        return StringMyUtil.isEmptyString(autoAnchorSubscribe)?"0":autoAnchorSubscribe;
    }

    public void setAutoAnchorSubscribe(String autoAnchorSubscribe) {
        this.autoAnchorSubscribe = autoAnchorSubscribe;
    }

    public String getInviteNumber() {
        return inviteNumber;
    }

    public void setInviteNumber(String inviteNumber) {
        this.inviteNumber = inviteNumber;
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
}
