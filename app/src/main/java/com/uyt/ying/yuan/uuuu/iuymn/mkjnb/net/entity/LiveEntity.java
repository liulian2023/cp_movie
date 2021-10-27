/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity;

import com.uyt.ying.rxhttp.net.model.BaseEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommonModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LiveEntity extends BaseEntity implements Serializable{


    /**
     * totalsize : 353
     * anchorMemberRoomList : [{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200206165127_0b577ba3487dafb25bc90ab238f65692?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.kk蜜桃),"cpId":63,"anchorAccount":"3_3119145","liveRoomId":"xunbobaac3b1277eb410f88cbe9174ddac0b2","anchorMemberId":221,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/3119145_1581751521?txSecret=a223920b175cc0ab75e87be878e03977&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101181356_de751b995957191e0fa44e232882cc73?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.y小老婆),"cpId":88,"anchorAccount":"3_2338737","liveRoomId":"xunbo6157fab350014868b7b20fbb0c39abd7","anchorMemberId":99913,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338737_1581744302?txSecret=3dd14787cdf78700ec32fe7df0ace128&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200129171428_f4cef0c60016447188518d233766ca7a?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.PC丝袜下的诱惑),"cpId":32,"anchorAccount":"3_2965975","liveRoomId":"xunboa4af87f0578f4dacab7dda7b87871cd0","anchorMemberId":253,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2965975_1581743303?txSecret=2a68838048ca7a2498e8f5599a7706c6&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101164648_aa0597e4c429facd2802552314d8eb4d?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.天天开心),"cpId":14,"anchorAccount":"3_2338681","liveRoomId":"xunbod10b17c30fa546d3bf27af04303244eb","anchorMemberId":99957,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338681_1581747370?txSecret=b2ba092d7c605dde2ec7b285fbce7250&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101201926_20329c12156a697d6ef1c0f8d6502c31?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.SM美腿呦呦),"cpId":62,"anchorAccount":"3_2338924","liveRoomId":"xunbo8b5eba4c95774eafab218adc4eeac6a6","anchorMemberId":296,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338924_1581664497?txSecret=2f178feb619322d4cd7b8a649c676a35&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200105235638_76be43309f943a2ace1747ab45e1f61d?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.IQ等你来深入),"cpId":40,"anchorAccount":"3_2399065","liveRoomId":"xunbob922129306c84b19aa045137fc4850f0","anchorMemberId":314,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2399065_1581673842?txSecret=1af09f2390a0256302dd6094c623b498&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200211193100_6460eacc17bc9dd03e0950c43fdeae43?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.JJ柚柚),"cpId":14,"anchorAccount":"3_3204375","liveRoomId":"xunbo3e96844f08064e54a636549d6edbf8e2","anchorMemberId":100321,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/3204375_1581747397?txSecret=ecc7b4bc85312938ce98c69d60cc7d0f&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200102180137_3a7283ceba7b2d5d162549d5747d0906?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.猫猫冰宝),"cpId":62,"anchorAccount":"3_2342143","liveRoomId":"xunbo9724990c08624b0098ca074fa3b6268e","anchorMemberId":100016,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2342143_1581743546?txSecret=6c0d1b527583349149045bc36f1e5cd8&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200128090646_614d8d1ff7778c6142fa570952266222?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.IQ痒的难受),"cpId":111,"anchorAccount":"3_2897296","liveRoomId":"xunbo8c7c5ecc749344f180f59f979182fd7c","anchorMemberId":307,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2897296_1581694152?txSecret=b32723228d35539de6f4d8dd19b47eee&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200109030349_36d9f472f5aa96b146edb4de46f1d7db?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.cs 么么哒),"cpId":8,"anchorAccount":"3_2357514","liveRoomId":"xunboa339480365444a9b8da04f9be536de53","anchorMemberId":100217,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2357514_1581751995?txSecret=505307e42ca8ad0808592f5c94843730&txTime=5E47A04B"}]
     * anchorFollowList : [{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200206165127_0b577ba3487dafb25bc90ab238f65692?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.kk蜜桃),"cpId":63,"anchorAccount":"3_3119145","liveRoomId":"xunbobaac3b1277eb410f88cbe9174ddac0b2","anchorMemberId":221,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/3119145_1581751521?txSecret=a223920b175cc0ab75e87be878e03977&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101181356_de751b995957191e0fa44e232882cc73?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.y小老婆),"cpId":88,"anchorAccount":"3_2338737","liveRoomId":"xunbo6157fab350014868b7b20fbb0c39abd7","anchorMemberId":99913,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338737_1581744302?txSecret=3dd14787cdf78700ec32fe7df0ace128&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200129171428_f4cef0c60016447188518d233766ca7a?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.PC丝袜下的诱惑),"cpId":32,"anchorAccount":"3_2965975","liveRoomId":"xunboa4af87f0578f4dacab7dda7b87871cd0","anchorMemberId":253,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2965975_1581743303?txSecret=2a68838048ca7a2498e8f5599a7706c6&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101164648_aa0597e4c429facd2802552314d8eb4d?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.天天开心),"cpId":14,"anchorAccount":"3_2338681","liveRoomId":"xunbod10b17c30fa546d3bf27af04303244eb","anchorMemberId":99957,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338681_1581747370?txSecret=b2ba092d7c605dde2ec7b285fbce7250&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200101201926_20329c12156a697d6ef1c0f8d6502c31?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.SM美腿呦呦),"cpId":62,"anchorAccount":"3_2338924","liveRoomId":"xunbo8b5eba4c95774eafab218adc4eeac6a6","anchorMemberId":296,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2338924_1581664497?txSecret=2f178feb619322d4cd7b8a649c676a35&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200105235638_76be43309f943a2ace1747ab45e1f61d?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.IQ等你来深入),"cpId":40,"anchorAccount":"3_2399065","liveRoomId":"xunbob922129306c84b19aa045137fc4850f0","anchorMemberId":314,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2399065_1581673842?txSecret=1af09f2390a0256302dd6094c623b498&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200211193100_6460eacc17bc9dd03e0950c43fdeae43?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.JJ柚柚),"cpId":14,"anchorAccount":"3_3204375","liveRoomId":"xunbo3e96844f08064e54a636549d6edbf8e2","anchorMemberId":100321,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/3204375_1581747397?txSecret=ecc7b4bc85312938ce98c69d60cc7d0f&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200102180137_3a7283ceba7b2d5d162549d5747d0906?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.猫猫冰宝),"cpId":62,"anchorAccount":"3_2342143","liveRoomId":"xunbo9724990c08624b0098ca074fa3b6268e","anchorMemberId":100016,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2342143_1581743546?txSecret=6c0d1b527583349149045bc36f1e5cd8&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200128090646_614d8d1ff7778c6142fa570952266222?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.IQ痒的难受),"cpId":111,"anchorAccount":"3_2897296","liveRoomId":"xunbo8c7c5ecc749344f180f59f979182fd7c","anchorMemberId":307,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2897296_1581694152?txSecret=b32723228d35539de6f4d8dd19b47eee&txTime=5E47A04B"},{"giftAmount":0,"headImg":"http://static.j6s0f.cn/20200109030349_36d9f472f5aa96b146edb4de46f1d7db?imageView2/2/w/600/h/600","anchorNickName":Utils.getString(R.string.cs 么么哒),"cpId":8,"anchorAccount":"3_2357514","liveRoomId":"xunboa339480365444a9b8da04f9be536de53","anchorMemberId":100217,"isLevel":1,"isFollow":0,"isForbidden":0,"robotCount":500,"liveUrl":"rtmp://pull.purikura.com.cn/live/2357514_1581751995?txSecret=505307e42ca8ad0808592f5c94843730&txTime=5E47A04B"}]
     */

    private int totalsize;
    private int pageNo;
    private List<AnchorMemberRoomListBean> anchorMemberRoomList;
    private List<AnchorFollowListBean> anchorFollowList;

    public int getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(int totalsize) {
        this.totalsize = totalsize;
    }

    public List<AnchorMemberRoomListBean> getAnchorMemberRoomList() {
        return anchorMemberRoomList;
    }

    public void setAnchorMemberRoomList(List<AnchorMemberRoomListBean> anchorMemberRoomList) {
        this.anchorMemberRoomList = anchorMemberRoomList;
    }

    public List<AnchorFollowListBean> getAnchorFollowList() {
        return anchorFollowList;
    }

    public void setAnchorFollowList(List<AnchorFollowListBean> anchorFollowList) {
        this.anchorFollowList = anchorFollowList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public static class AnchorMemberRoomListBean extends CommonModel implements Serializable {
        /**
         * giftAmount : 0
         * headImg : http://static.j6s0f.cn/20200206165127_0b577ba3487dafb25bc90ab238f65692?imageView2/2/w/600/h/600
         * anchorNickName : kk蜜桃
         * cpId : 63
         * anchorAccount : 3_3119145
         * liveRoomId : xunbobaac3b1277eb410f88cbe9174ddac0b2
         * "isPrivate":0,
         * anchorMemberId : 221
         * cover :xblive/20200608/54de476736634a6cbcebc85ba7a228ae.jpg
         * isLevel : 1
         * isFollow : 0
         * title: 小柳岩来了
         * isForbidden : 0
         * robotCount : 500
         * liveUrl : rtmp://pull.purikura.com.cn/live/3119145_1581751521?txSecret=a223920b175cc0ab75e87be878e03977&txTime=5E47A04B
         */
        private String status="0";//是否在直播 1 正在直播 0已下播
        private String lotteryName; //彩票名
        private String game;
        private String giftAmount;
        private String area;//地区
        private String headImg;
        private String anchorNickName;
        private long cpId;
        private int isPrivate;//是否是主播端
        private String title;
        private String anchorAccount;
        private String liveRoomId;
        private String anchorMemberId;
        private String cover;
        private int isLevel;
        private int isFollow;
        private int isForbidden;
        private int robotCount;
        private int isUseToy;
        private String liveUrl;
        private String anchorSubscribe;//大于0位付费
        private String remoteLiveManagementId;
        private String livePassWord;//直播密码

        private int basePeopleNum=0;
        private int realChangeNum=0;//实时变动真实人数

        public int getIsUseToy() {
            return isUseToy;
        }

        public void setIsUseToy(int isUseToy) {
            this.isUseToy = isUseToy;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getAnchorSubscribe() {
            return anchorSubscribe;
        }

        public void setAnchorSubscribe(String anchorSubscribe) {
            this.anchorSubscribe = anchorSubscribe;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getRealChangeNum() {
            return realChangeNum;
        }

        public void setRealChangeNum(int realChangeNum) {
            this.realChangeNum = realChangeNum;
        }

        public int getBasePeopleNum() {
                if (basePeopleNum == 0) {
                    String anchorViewNumber = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.ANCHOR_VIEW_NUMBER, "1");
                    int baseNum = 0;
                    if (anchorViewNumber.contains("-")) {
                        String[] arr = anchorViewNumber.split("-");
                        if (arr.length == 2) {
                            String form = arr[0];
                            baseNum = robotCount * Integer.parseInt(form);
                            if(baseNum==0){
                                String to = arr[1];
                                baseNum=  new Random().nextInt(Integer.parseInt(to))+Integer.parseInt(form);
                            }
                        }
                    }else {
                        baseNum = robotCount * Integer.parseInt(anchorViewNumber);
                    }
                    basePeopleNum = baseNum;
                }
                return basePeopleNum;
        }

        public void setBasePeopleNum(int basePeopleNum) {
            this.basePeopleNum = basePeopleNum;
        }

        public String getLivePassWord() {
            return StringMyUtil.isEmptyString(livePassWord)?"":livePassWord;
        }

        public void setLivePassWord(String livePassWord) {
            livePassWord = StringMyUtil.isEmptyString(livePassWord)?"":livePassWord;
            try {
                this.livePassWord=  AESUtil.decrypt(livePassWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getRemoteLiveManagementId() {
            return remoteLiveManagementId;
        }

        public void setRemoteLiveManagementId(String remoteLiveManagementId) {
            this.remoteLiveManagementId = remoteLiveManagementId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AnchorMemberRoomListBean)) return false;
            AnchorMemberRoomListBean that = (AnchorMemberRoomListBean) o;
            return Objects.equals(getLiveRoomId(), that.getLiveRoomId()) &&
                    Objects.equals(getLiveUrl(), that.getLiveUrl());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getLiveRoomId(), getLiveUrl());
        }

        @Override
        public String toString() {
            return "AnchorMemberRoomListBean{" +
                    "liveRoomId='" + liveRoomId + '\'' +
                    ", liveUrl='" + liveUrl + '\'' +
                    '}';
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getIsPrivate() {
            return isPrivate;
        }

        public void setIsPrivate(int isPrivate) {
            this.isPrivate = isPrivate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLotteryName() {
            return lotteryName;
        }

        public void setLotteryName(String lotteryName) {
            this.lotteryName = lotteryName;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getAnchorNickName() {
            return anchorNickName;
        }

        public void setAnchorNickName(String anchorNickName) {
            this.anchorNickName = anchorNickName;
        }

        public long getCpId() {
            return cpId;
        }

        public void setCpId(long cpId) {
            this.cpId = cpId;
        }

        public String getAnchorAccount() {
            return anchorAccount;
        }

        public void setAnchorAccount(String anchorAccount) {
            this.anchorAccount = anchorAccount;
        }

        public String getLiveRoomId() {
            return liveRoomId;
        }

        public void setLiveRoomId(String liveRoomId) {
            try {
                this.liveRoomId = AESUtil.decrypt(liveRoomId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public String getAnchorMemberId() {
            return anchorMemberId;
        }

        public void setAnchorMemberId(String anchorMemberId) {
            this.anchorMemberId = anchorMemberId;
        }

        public int getIsLevel() {
            return isLevel;
        }

        public void setIsLevel(int isLevel) {
            this.isLevel = isLevel;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public int getIsForbidden() {
            return isForbidden;
        }

        public void setIsForbidden(int isForbidden) {
            this.isForbidden = isForbidden;
        }

        public int getRobotCount() {
            return robotCount;
        }

        public void setRobotCount(int robotCount) {
            this.robotCount = robotCount;
        }

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            try {
                this.liveUrl = AESUtil.decrypt(liveUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public static class AnchorFollowListBean extends AnchorMemberRoomListBean implements Serializable {
        /**
         * giftAmount : 0
         * headImg : http://static.j6s0f.cn/20200206165127_0b577ba3487dafb25bc90ab238f65692?imageView2/2/w/600/h/600
         * anchorNickName : kk蜜桃
         * cpId : 63
         * anchorAccount : 3_3119145
         * liveRoomId : xunbobaac3b1277eb410f88cbe9174ddac0b2
         * anchorMemberId : 221
         * isLevel : 1
         * isFollow : 0
         * isForbidden : 0
         * robotCount : 500
         * liveUrl : rtmp://pull.purikura.com.cn/live/3119145_1581751521?txSecret=a223920b175cc0ab75e87be878e03977&txTime=5E47A04B
         */
        private String status="0";//是否在直播 1正在直播0 已下播
        private String lotteryName; //彩票名
        private String giftAmount;
        private String headImg;
        private String anchorNickName;
        private long cpId;
        private String anchorAccount;
        private String liveRoomId;
        private String anchorMemberId;
        private int isLevel;
        private int isFollow;
        private int isForbidden;
        private int robotCount;
        private String liveUrl;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLotteryName() {
            return lotteryName;
        }

        public void setLotteryName(String lotteryName) {
            this.lotteryName = lotteryName;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getAnchorNickName() {
            return anchorNickName;
        }

        public void setAnchorNickName(String anchorNickName) {
            this.anchorNickName = anchorNickName;
        }

        public long getCpId() {
            return cpId;
        }

        public void setCpId(long cpId) {
            this.cpId = cpId;
        }

        public String getAnchorAccount() {
            return anchorAccount;
        }

        public void setAnchorAccount(String anchorAccount) {
            this.anchorAccount = anchorAccount;
        }

        public String getLiveRoomId() {
            return liveRoomId;
        }

        public void setLiveRoomId(String liveRoomId) {
            try {
                this.liveRoomId = AESUtil.decrypt(liveRoomId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        public String getAnchorMemberId() {
            return anchorMemberId;
        }

        public void setAnchorMemberId(String anchorMemberId) {
            this.anchorMemberId = anchorMemberId;
        }

        public int getIsLevel() {
            return isLevel;
        }

        public void setIsLevel(int isLevel) {
            this.isLevel = isLevel;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public int getIsForbidden() {
            return isForbidden;
        }

        public void setIsForbidden(int isForbidden) {
            this.isForbidden = isForbidden;
        }

        public int getRobotCount() {
            return robotCount;
        }

        public void setRobotCount(int robotCount) {
            this.robotCount = robotCount;
        }

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            try {
                this.liveUrl = AESUtil.decrypt(liveUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
