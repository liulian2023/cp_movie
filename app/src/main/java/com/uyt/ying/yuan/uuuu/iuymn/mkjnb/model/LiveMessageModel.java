package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedStatus;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import io.rong.imlib.model.Message;

public class LiveMessageModel {

    String objectName="";
    String userName="";
    String level="1";
    String textContent="";
    int redType;//1 趣约  2 专享 3 普通
    String redPrice="1"; //趣约 专享 用到
    long redId;
    String status="0";//进入/退出直播间 0进入 1退出
    String giftId="";
    String type="";
    String platUserId="";

    String giftName="";
    String giftNum="1";
    RedStatus redStatus =RedStatus.NORMAL;

    String qbPrice="1";
    String failInfo="";

    String portrait="";
    String giftPrice="1";

    String typeName="";

    String betName="";//彩票投注列表(三级列表)
    String betAmount="1";//投注金额
    String betQiShu="";//期数
    String betGroupName="";
    String game="1";
    String type_id="0";
    String reluId="";
    String userInfoJson="";

    //后台中奖信息
    private String isBack;
    private int user_id;
    private String price="1";
    private String anchorAccount="1";
    private String liveRoomId="1";
    private String userNickName="";
    private String rcUsId="1";
    private String isForbidden="";//禁言状态  0 解禁 1 禁言
    private String targetNickName=""; //被禁言的nickName
    private String zkCode="";
    private String managerType="";//1 主播 2超管 3房管
    private String isRoomManager="";//是否是管理员 0 否 1是

    private boolean rongConnect;//融云连接状态

    private String systemStatus="";
    private String field="";


    private String medalIcon="";
    private String levelIcon="";
    private String titleIcon="";
    private String levelSVGA="";
    private String mountSVGA="";
    private String mountName="";
    private String userInfoJsonUserId;//userInfo中的userId

    private String rewardPrice;


    Message.MessageDirection messageDirection;
    public String getType() {
        return StringMyUtil.isEmptyString(type)?"":type;
    }

    public String getRewardPrice() {
        return rewardPrice;
    }

    public void setRewardPrice(String rewardPrice) {
        this.rewardPrice = rewardPrice;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMountName() {
        return StringMyUtil.isEmptyString(mountName)?"":mountName;
    }

    public void setMountName(String mountName) {
        this.mountName = mountName;
    }

    public String getSystemStatus() {
        return StringMyUtil.isEmptyString(systemStatus)?"":systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getField() {
        return StringMyUtil.isEmptyString(field)?"":field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMedalIcon() {
        return StringMyUtil.isEmptyString(medalIcon)?"":medalIcon;
    }

    public void setMedalIcon(String medalIcon) {
        this.medalIcon = medalIcon;
    }

    public String getLevelIcon() {
        return StringMyUtil.isEmptyString(levelIcon)?"":levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    public String getTitleIcon() {

        return StringMyUtil.isEmptyString(titleIcon)?"":titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
    }

    public String getLevelSVGA() {
        return StringMyUtil.isEmptyString(levelSVGA)?"":levelSVGA;
    }

    public void setLevelSVGA(String levelSVGA) {
        this.levelSVGA = levelSVGA;
    }

    public String getMountSVGA() {
        return StringMyUtil.isEmptyString(mountSVGA)?"":mountSVGA;
    }

    public void setMountSVGA(String mountSVGA) {
        this.mountSVGA = mountSVGA;
    }

    public boolean isRongConnect() {
        return rongConnect;
    }

    public void setRongConnect(boolean rongConnect) {
        this.rongConnect = rongConnect;
    }



    public Message.MessageDirection getMessageDirection() {
        return messageDirection;
    }

    public void setMessageDirection(Message.MessageDirection messageDirection) {
        this.messageDirection = messageDirection;
    }

    public String getUserInfoJsonUserId() {
        return StringMyUtil.isEmptyString(userInfoJsonUserId)?"":userInfoJsonUserId;
    }

    public void setUserInfoJsonUserId(String userInfoJsonUserId) {
        this.userInfoJsonUserId = userInfoJsonUserId;
    }

    public String getPlatUserId() {
        return platUserId;
    }

    public void setPlatUserId(String platUserId) {
        this.platUserId = platUserId;
    }

    public String getIsRoomManager() {
        return StringMyUtil.isEmptyString(isRoomManager)?"":isRoomManager;
    }

    public void setIsRoomManager(String isRoomManager) {
        this.isRoomManager = isRoomManager;
    }

    public String getManagerType() {
        return StringMyUtil.isEmptyString(managerType)?"":managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }

    public String getZkCode() {
        return StringMyUtil.isEmptyString(zkCode)?"":zkCode;
    }

    public void setZkCode(String zkCode) {
        this.zkCode = zkCode;
    }

    public String getIsForbidden() {
        return StringMyUtil.isEmptyString(isForbidden)?"":isForbidden;
    }

    public void setIsForbidden(String isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getTargetNickName() {
        return StringMyUtil.isEmptyString(targetNickName)?"":targetNickName;
    }

    public void setTargetNickName(String targetNickName) {
        this.targetNickName = targetNickName;
    }

    public String getRcUsId() {
        return rcUsId;
    }

    public void setRcUsId(String rcUsId) {
        this.rcUsId = rcUsId;
    }

    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPrice() {
        return StringMyUtil.isEmptyString(price)?"":price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAnchorAccount() {
        return StringMyUtil.isEmptyString(anchorAccount)?"":anchorAccount;
    }

    public void setAnchorAccount(String anchorAccount) {
        this.anchorAccount = anchorAccount;
    }

    public String getLiveRoomId() {

        return StringMyUtil.isEmptyString(liveRoomId)?"":liveRoomId;
    }

    public void setLiveRoomId(String liveRoomId) {
        this.liveRoomId = liveRoomId;
    }

    public String getUserNickName() {

        return StringMyUtil.isEmptyString(userNickName)?"":userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }


    public String getUserInfoJson() {

        return StringMyUtil.isEmptyString(userInfoJson)?"":userInfoJson;
    }

    public void setUserInfoJson(String userInfoJson) {
        this.userInfoJson = userInfoJson;
    }

    public String getBetName() {

        return StringMyUtil.isEmptyString(betName)?"":betName;
    }

    public void setBetName(String betName) {
        this.betName = betName;
    }

    public String getGame() {

        return StringMyUtil.isEmptyString(game)?"1":game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getType_id() {
        return StringMyUtil.isEmptyString(type_id)?"1":type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getReluId() {
        return reluId;
    }

    public void setReluId(String reluId) {
        this.reluId = reluId;
    }

    public String getBetGroupName() {
        return betGroupName;
    }

    public void setBetGroupName(String betGroupName) {
        this.betGroupName = betGroupName;
    }

    public String getBetQiShu() {
        return betQiShu;
    }

    public void setBetQiShu(String betQiShu) {
        this.betQiShu = betQiShu;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getFailInfo() {
        return failInfo;
    }

    public void setFailInfo(String failInfo) {
        this.failInfo = failInfo;
    }

    public String getQbPrice() {
        return qbPrice;
    }

    public void setQbPrice(String qbPrice) {
        this.qbPrice = qbPrice;
    }

    public RedStatus getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(RedStatus redStatus) {
        this.redStatus = redStatus;
    }
    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftNum() {
        return Utils.isInt(giftNum)?giftNum:"1";
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public long getRedId() {
        return redId;
    }

    public void setRedId(long redId) {
        this.redId = redId;
    }

    public String getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(String redPrice) {
        this.redPrice = redPrice;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    public int getRedType() {
        return redType;
    }

    public void setRedType(int redType) {
        this.redType = redType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return Utils.isInt(level)?level:"1";
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

