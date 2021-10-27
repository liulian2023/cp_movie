package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.util.List;

public class RechargeModel {

    /**
     * message : 获取汇款支付方式成功
     * bankAllList : [{"image":"upload/images/20190912/1568288206158.png","lastModifiedDate":1583472777000,"isDelete":0,"sort":1,"type":0,"shopInfoVoList":[],"createdDate":1553661788000,"name":Utils.getString(R.string.公司入款),"lastModifiedUser":"pt12345","id":1,"createdUser":"lottery","rechargeBankList":[{"typeName":Utils.getString(R.string.公司入款),"bankName":Utils.getString(R.string.华夏银行),"remark":Utils.getString(R.string.<p>充值赠送优惠2%<\/p>),"type":0,"down":10,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"xbgoogle","id":1,"up":1000000,"thirdPartyFlag":"","createdUser":"kugua","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":1,"lastModifiedDate":1604053384000,"isDelete":0,"url":"","createdDate":1583483176000,"dinpayPublicKey":"","name":Utils.getString(R.string.张三驱),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20200306/1583490734576.png","thirdPartyFlagName":"","account":"620000000000","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"32","remark":"","type":0,"down":32,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":13,"up":32,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":2,"lastModifiedDate":1603942603000,"isDelete":0,"url":"","createdDate":1597641652000,"dinpayPublicKey":"","name":"323","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"323","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"ew","remark":"","type":0,"down":23,"bankAllId":1,"gradeStr":"0","id":15,"up":32,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":2,"isDelete":0,"url":"","createdDate":1597641944000,"dinpayPublicKey":"","name":"ew","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"wew","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"34","remark":"<p>432<\/p>","type":0,"down":34,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":17,"up":34,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":43,"lastModifiedDate":1603942615000,"isDelete":0,"url":"","createdDate":1598406046000,"dinpayPublicKey":"","name":"334","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"43","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":Utils.getString(R.string.农业银行),"remark":Utils.getString(R.string.<p>入款赠送优惠2%<\/p>),"type":0,"down":10,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,20,21,22,23,24,25,26,27,28,29,13","lastModifiedUser":"kugua","id":19,"up":10000,"thirdPartyFlag":"","createdUser":"xbgoogle","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":1,"lastModifiedDate":1604054344000,"isDelete":0,"url":"","createdDate":1604053177000,"dinpayPublicKey":"","name":Utils.getString(R.string.孙悟空),"payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"62284800000000000","status":1}],"status":1},{"image":"upload/images/20191223/1577079133546.png","lastModifiedDate":1577079135000,"isDelete":0,"sort":1,"type":1,"shopInfoVoList":[],"createdDate":1553749191000,"name":Utils.getString(R.string.微信),"lastModifiedUser":"xbgoogle","id":2,"createdUser":"lottery","rechargeBankList":[{"typeName":Utils.getString(R.string.微信),"bankName":Utils.getString(R.string.微信扫描支付),"remark":"","type":1,"down":10,"bankAllId":2,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":2,"up":100000,"thirdPartyFlag":"","createdUser":"kugua","image":"null","merchantPrivateKey":"","merchantCode":"","orderNo":2,"lastModifiedDate":1603942641000,"isDelete":0,"url":"","createdDate":1583483314000,"dinpayPublicKey":"","name":Utils.getString(R.string.小葵),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20200306/1583483311360.png","thirdPartyFlagName":"","account":Utils.getString(R.string.QQ账号:123456),"status":1},{"typeName":Utils.getString(R.string.微信),"bankName":Utils.getString(R.string.微信),"remark":"","type":1,"down":100,"bankAllId":2,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","id":21,"up":500,"thirdPartyFlag":"","createdUser":"kugua","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":88,"isDelete":0,"url":"","createdDate":1604370310000,"dinpayPublicKey":"","name":Utils.getString(R.string.微信),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20201103/1604370307559.png","thirdPartyFlagName":"","account":"1452745","status":1}],"status":1},{"image":"upload/images/20191223/1577079147479.png","lastModifiedDate":1577079149000,"isDelete":0,"sort":2,"type":2,"shopInfoVoList":[],"createdDate":1553749206000,"name":Utils.getString(R.string.支付宝),"lastModifiedUser":"xbgoogle","id":3,"createdUser":"lottery","rechargeBankList":[{"typeName":Utils.getString(R.string.支付宝),"bankName":Utils.getString(R.string.星河支付宝扫码支付),"remark":Utils.getString(R.string.星河支付宝扫码支付),"type":3,"down":300,"bankAllId":3,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":10,"up":500,"thirdPartyFlag":"XINGHE_903","createdUser":"xbgoogle","image":"","merchantPrivateKey":"zcjwgho4m3q4f7aq14ev0x3g8b8yzh4r","merchantCode":"10614","orderNo":0,"lastModifiedDate":1603942574000,"isDelete":0,"url":"","createdDate":1591077484000,"dinpayPublicKey":"zcjwgho4m3q4f7aq14ev0x3g8b8yzh4r","name":"","payNums":0,"md5PrivateKey":"","thirdPartyFlagName":Utils.getString(R.string.星河支付宝扫码支付),"account":"","status":1},{"typeName":Utils.getString(R.string.支付宝),"bankName":Utils.getString(R.string.天下汇小额扫码),"remark":Utils.getString(R.string.天下汇支付宝扫码),"type":3,"down":1000,"bankAllId":3,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"xbgoogle","id":11,"up":10000,"thirdPartyFlag":"TIANXIAHUI_2","createdUser":"xbgoogle","image":"","merchantPrivateKey":"C5B6046209F240586ADA759936109E99","merchantCode":"10318","orderNo":0,"lastModifiedDate":1604054835000,"isDelete":0,"url":"","createdDate":1591183506000,"dinpayPublicKey":"C5B6046209F240586ADA759936109E99","name":"","payNums":0,"md5PrivateKey":"","thirdPartyFlagName":Utils.getString(R.string.天下汇支付宝扫码),"account":"","status":1},{"typeName":Utils.getString(R.string.支付宝),"bankName":Utils.getString(R.string.时代支付宝扫码),"remark":"","type":3,"down":300,"bankAllId":3,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":12,"up":500,"thirdPartyFlag":"SHIDAI_01","createdUser":"xbgoogle","image":"","merchantPrivateKey":"868vFvsSPf2HHkEo","merchantCode":"295306973677617152","orderNo":0,"lastModifiedDate":1603942588000,"isDelete":0,"url":"","createdDate":1591330590000,"dinpayPublicKey":"zKfZFHDR","name":"","payNums":0,"md5PrivateKey":"5ac3a095c142484a9d991b4b39d19c61","thirdPartyFlagName":Utils.getString(R.string.时代支付宝扫码),"account":"","status":1},{"typeName":Utils.getString(R.string.支付宝),"bankName":Utils.getString(R.string.支付宝),"remark":"","type":2,"down":100,"bankAllId":3,"gradeStr":"0,1,2,3,4,5,6,7,8,9,20,21,22,23,24,25,26,27,28,29,10,11,12,13,14,15,16,17,18,19","id":22,"up":600,"thirdPartyFlag":"","createdUser":"kugua","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":99,"isDelete":0,"url":"","createdDate":1604370646000,"dinpayPublicKey":"","name":Utils.getString(R.string.支付宝),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20201103/1604370639101.png","thirdPartyFlagName":"","account":"12345687","status":1}],"status":1},{"image":"upload/images/20191223/1577079158273.png","lastModifiedDate":1581740015000,"isDelete":0,"sort":3,"type":3,"shopInfoVoList":[],"createdDate":1555073399000,"name":Utils.getString(R.string.qq钱包),"lastModifiedUser":"sys","id":4,"createdUser":"lottery","rechargeBankList":[{"typeName":Utils.getString(R.string.qq钱包),"bankName":"QQ","remark":"","type":0,"down":10,"bankAllId":4,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":5,"up":1000,"thirdPartyFlag":"","createdUser":"admin","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":4,"lastModifiedDate":1603942610000,"isDelete":0,"url":"","createdDate":1583489274000,"dinpayPublicKey":"","name":Utils.getString(R.string.QQ收款),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20200306/1583489321009.png","thirdPartyFlagName":"","account":"13800000","status":1},{"typeName":Utils.getString(R.string.qq钱包),"bankName":"QQ","remark":"","type":3,"down":50,"bankAllId":4,"gradeStr":"0,1,2,3,4,5,6,7,8,9,20,21,22,23,24,25,26,27,28,29,10,11,12,13,14,15,16,17,18,19","id":20,"up":500,"thirdPartyFlag":"","createdUser":"kugua","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":5,"isDelete":0,"url":"","createdDate":1604370227000,"dinpayPublicKey":"","name":"QQ","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"123456","status":1}],"status":1},{"isDelete":0,"name":Utils.getString(R.string.人工充值),"sort":0,"type":6,"shopInfoVoList":[{"qq":"43","wx":"34","amount":109.8,"lastModifiedDate":1604146231000,"shopNickName":"222222","isDelete":0,"down":100,"createdDate":1603946026000,"zfb":"34","onlineService":"http://www.sohu.com","lastModifiedUser":"sys","id":2,"up":10000,"createdUser":"pg"},{"qq":"123456","wx":"123456","amount":0,"lastModifiedDate":1604116288000,"shopNickName":"dc001","isDelete":0,"down":100,"createdDate":1604112239000,"zfb":"123456","onlineService":"","lastModifiedUser":"test@qq.com","id":3,"up":10000,"createdUser":"xbgoogle"},{"qq":"dc001","wx":"dc001","amount":0,"lastModifiedDate":1604116273000,"shopNickName":"dc001223","isDelete":0,"down":100,"createdDate":1604112745000,"zfb":"dc001","onlineService":"","lastModifiedUser":"test@qq.com","id":4,"up":10000,"createdUser":"xbgoogle"},{"qq":"","wx":"","amount":1811.1,"lastModifiedDate":1604376799000,"shopNickName":Utils.getString(R.string.七喜),"isDelete":0,"down":100,"createdDate":1604126854000,"zfb":"","onlineService":"http://www.baidu.com","lastModifiedUser":"sys","id":5,"up":10000,"createdUser":"kugua"},{"qq":"1111111111","wx":"qqqq55","amount":1255,"lastModifiedDate":1604314370000,"shopNickName":Utils.getString(R.string.万家灯火24H在线),"isDelete":0,"down":100,"createdDate":1604291071000,"zfb":"33333333","onlineService":"piaov.com","lastModifiedUser":"kugua","id":6,"up":10000,"createdUser":"kugua"},{"qq":"","wx":"","amount":0,"createdDate":1604293181000,"shopNickName":Utils.getString(R.string.新八八八八),"isDelete":0,"zfb":"","onlineService":"","id":7,"up":10000,"down":100,"createdUser":"kugua"},{"qq":"","wx":"","amount":1522,"lastModifiedDate":1604380273000,"shopNickName":"xili","isDelete":0,"down":100,"createdDate":1604377039000,"zfb":"","onlineService":"","lastModifiedUser":"xili001","id":8,"up":10000,"createdUser":"xili001"}],"rechargeBankList":[],"status":1}]
     * status : success
     */

    private String message;
    private String status;
    /**
     * image : upload/images/20190912/1568288206158.png
     * lastModifiedDate : 1583472777000
     * isDelete : 0
     * sort : 1
     * type : 0
     * shopInfoVoList : []
     * createdDate : 1553661788000
     * name : 公司入款
     * lastModifiedUser : pt12345
     * id : 1
     * createdUser : lottery
     * rechargeBankList : [{"typeName":Utils.getString(R.string.公司入款),"bankName":Utils.getString(R.string.华夏银行),"remark":Utils.getString(R.string.<p>充值赠送优惠2%<\/p>),"type":0,"down":10,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"xbgoogle","id":1,"up":1000000,"thirdPartyFlag":"","createdUser":"kugua","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":1,"lastModifiedDate":1604053384000,"isDelete":0,"url":"","createdDate":1583483176000,"dinpayPublicKey":"","name":Utils.getString(R.string.张三驱),"payNums":0,"md5PrivateKey":"","payTypeImage":"upload/images/20200306/1583490734576.png","thirdPartyFlagName":"","account":"620000000000","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"32","remark":"","type":0,"down":32,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":13,"up":32,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":2,"lastModifiedDate":1603942603000,"isDelete":0,"url":"","createdDate":1597641652000,"dinpayPublicKey":"","name":"323","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"323","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"ew","remark":"","type":0,"down":23,"bankAllId":1,"gradeStr":"0","id":15,"up":32,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":2,"isDelete":0,"url":"","createdDate":1597641944000,"dinpayPublicKey":"","name":"ew","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"wew","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":"34","remark":"<p>432<\/p>","type":0,"down":34,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29","lastModifiedUser":"kugua","id":17,"up":34,"thirdPartyFlag":"","createdUser":"pt12345","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":43,"lastModifiedDate":1603942615000,"isDelete":0,"url":"","createdDate":1598406046000,"dinpayPublicKey":"","name":"334","payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"43","status":1},{"typeName":Utils.getString(R.string.公司入款),"bankName":Utils.getString(R.string.农业银行),"remark":Utils.getString(R.string.<p>入款赠送优惠2%<\/p>),"type":0,"down":10,"bankAllId":1,"gradeStr":"0,1,2,3,4,5,6,7,8,9,20,21,22,23,24,25,26,27,28,29,13","lastModifiedUser":"kugua","id":19,"up":10000,"thirdPartyFlag":"","createdUser":"xbgoogle","image":"","merchantPrivateKey":"","merchantCode":"","orderNo":1,"lastModifiedDate":1604054344000,"isDelete":0,"url":"","createdDate":1604053177000,"dinpayPublicKey":"","name":Utils.getString(R.string.孙悟空),"payNums":0,"md5PrivateKey":"","payTypeImage":"","thirdPartyFlagName":"","account":"62284800000000000","status":1}]
     * status : 1
     */

    private List<BankAllListBean> bankAllList;

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

    public List<BankAllListBean> getBankAllList() {
        return bankAllList;
    }

    public void setBankAllList(List<BankAllListBean> bankAllList) {
        this.bankAllList = bankAllList;
    }

    public static class BankAllListBean {
        private String image;
        private long lastModifiedDate;
        private String isDelete;
        private String label;
        private String sort;
        private String type;
        private String createdDate;
        private String name;
        private String lastModifiedUser;
        private String id;
        private String createdUser;
        private String status;
        private List<ManualEntity> shopInfoVoList;
        private boolean isSelector =false;

        /**
         * typeName : 公司入款
         * bankName : 华夏银行
         * remark : <p>充值赠送优惠2%</p>
         * type : 0
         * down : 10.0
         * bankAllId : 1
         * gradeStr : 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29
         * lastModifiedUser : xbgoogle
         * id : 1
         * up : 1000000.0
         * thirdPartyFlag :
         * createdUser : kugua
         * image :
         * merchantPrivateKey :
         * merchantCode :
         * orderNo : 1
         * lastModifiedDate : 1604053384000
         * isDelete : 0
         * url :
         * createdDate : 1583483176000
         * dinpayPublicKey :
         * name : 张三驱
         * payNums : 0
         * md5PrivateKey :
         * payTypeImage : upload/images/20200306/1583490734576.png
         * thirdPartyFlagName :
         * account : 620000000000
         * status : 1
         */

        private List<RechargeBankListBean> rechargeBankList;
        public boolean isSelector() {
            return isSelector;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setSelector(boolean selector) {
            isSelector = selector;
        }
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ManualEntity> getShopInfoVoList() {
            return shopInfoVoList;
        }

        public void setShopInfoVoList(List<ManualEntity> shopInfoVoList) {
            this.shopInfoVoList = shopInfoVoList;
        }

        public List<RechargeBankListBean> getRechargeBankList() {
            return rechargeBankList;
        }

        public void setRechargeBankList(List<RechargeBankListBean> rechargeBankList) {
            this.rechargeBankList = rechargeBankList;
        }

        public static class RechargeBankListBean {
            private String bankDot;
            private String typeName;
            private String bankName;
            private String remark;
            private String type;
            private String down;
            private String bankAllId;
            private String gradeStr;
            private String lastModifiedUser;
            private long id;
            private String up;
            private String czFdRate="";
            private String thirdPartyFlag="";//不为空为第三方支付
            private String createdUser;
            private String image;
            private String merchantPrivateKey;
            private String merchantCode;
            private String orderNo;
            private String usdtRate;
            private String lastModifiedDate;
            private String isDelete;
            private String url;
            private String createdDate;
            private String dinpayPublicKey;
            private String name;
            private String payNums;
            private String md5PrivateKey;
            private String payTypeImage;
            private String thirdPartyFlagName;
            private String useCzPriceManualInput;
            private String czPrices;
            private String account;
            private String title;
            private String status;
            private boolean isCheck=false;
            private List<ManualEntity> shopInfoVoList;

            public String getCzFdRate() {
                return czFdRate;
            }

            public void setCzFdRate(String czFdRate) {
                this.czFdRate = czFdRate;
            }

            public List<ManualEntity> getShopInfoVoList() {
                return shopInfoVoList;
            }

            public void setShopInfoVoList(List<ManualEntity> shopInfoVoList) {
                this.shopInfoVoList = shopInfoVoList;
            }

            public String getBankDot() {
                return bankDot;
            }

            public void setBankDot(String bankDot) {
                this.bankDot = bankDot;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUsdtRate() {
                return usdtRate;
            }

            public void setUsdtRate(String usdtRate) {
                this.usdtRate = usdtRate;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDown() {
                return down;
            }

            public String getUseCzPriceManualInput() {
                return useCzPriceManualInput;
            }

            public void setUseCzPriceManualInput(String useCzPriceManualInput) {
                this.useCzPriceManualInput = useCzPriceManualInput;
            }

            public String getCzPrices() {
                return StringMyUtil.isEmptyString(czPrices)?"0":czPrices;
            }

            public void setCzPrices(String czPrices) {
                this.czPrices = czPrices;
            }

            public void setDown(String down) {
                this.down = down;
            }

            public String getBankAllId() {
                return bankAllId;
            }

            public void setBankAllId(String bankAllId) {
                this.bankAllId = bankAllId;
            }

            public String getGradeStr() {
                return gradeStr;
            }

            public void setGradeStr(String gradeStr) {
                this.gradeStr = gradeStr;
            }

            public String getLastModifiedUser() {
                return lastModifiedUser;
            }

            public void setLastModifiedUser(String lastModifiedUser) {
                this.lastModifiedUser = lastModifiedUser;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getUp() {
                return up;
            }

            public void setUp(String up) {
                this.up = up;
            }

            public String getThirdPartyFlag() {
                return thirdPartyFlag;
            }

            public void setThirdPartyFlag(String thirdPartyFlag) {
                this.thirdPartyFlag = thirdPartyFlag;
            }

            public String getCreatedUser() {
                return createdUser;
            }

            public void setCreatedUser(String createdUser) {
                this.createdUser = createdUser;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMerchantPrivateKey() {
                return merchantPrivateKey;
            }

            public void setMerchantPrivateKey(String merchantPrivateKey) {
                this.merchantPrivateKey = merchantPrivateKey;
            }

            public String getMerchantCode() {
                return merchantCode;
            }

            public void setMerchantCode(String merchantCode) {
                this.merchantCode = merchantCode;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getDinpayPublicKey() {
                return dinpayPublicKey;
            }

            public void setDinpayPublicKey(String dinpayPublicKey) {
                this.dinpayPublicKey = dinpayPublicKey;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPayNums() {
                return payNums;
            }

            public void setPayNums(String payNums) {
                this.payNums = payNums;
            }

            public String getMd5PrivateKey() {
                return md5PrivateKey;
            }

            public void setMd5PrivateKey(String md5PrivateKey) {
                this.md5PrivateKey = md5PrivateKey;
            }

            public String getPayTypeImage() {
                return payTypeImage;
            }

            public void setPayTypeImage(String payTypeImage) {
                this.payTypeImage = payTypeImage;
            }

            public String getThirdPartyFlagName() {
                return thirdPartyFlagName;
            }

            public void setThirdPartyFlagName(String thirdPartyFlagName) {
                this.thirdPartyFlagName = thirdPartyFlagName;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
