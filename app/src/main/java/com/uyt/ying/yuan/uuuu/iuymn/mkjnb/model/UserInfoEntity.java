package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class UserInfoEntity {


    /**
     * accountName : 都会觉得
     * allMaintainStatusFlag : 0
     * bankCard : ****7010
     * bankDot : 北京市北京市东城区hdhsh
     * bankName : 兴业银行
     * createdDate : 1607335275000
     * id : 1335887082782068736
     * image : upload/images/20190911/1568202451775.jpg
     * isTest : 0
     * isagent : 1
     * lastModifiedDate : 1608958004000
     * maintainCount : 0
     * maintainStatus : 0
     * nickname : xx1111
     * parent_id : 1
     * password : 1
     * paypassword : 1
     * phone : ****5124
     * pointGrade : 2
     * realname : 都会觉得
     * registerArea : 未分配或者内网IP
     * registerIp : 10.6.46.27
     * remark : 
     * sex : 2
     * source : 2
     * usdtAccount : xx123456789
     * userNickName : 萌新m40l734C
     */

    private MemberInfoBean memberInfo;
    /**
     * memberInfo : {"accountName":Utils.getString(R.string.都会觉得),"allMaintainStatusFlag":0,"bankCard":"****7010","bankDot":Utils.getString(R.string.北京市北京市东城区hdhsh),"bankName":Utils.getString(R.string.兴业银行),"createdDate":1607335275000,"id":"1335887082782068736","image":"upload/images/20190911/1568202451775.jpg","isTest":0,"isagent":1,"lastModifiedDate":1608958004000,"maintainCount":0,"maintainStatus":0,"nickname":"xx1111","parent_id":1,"password":"1","paypassword":"1","phone":"****5124",CommonStr.GRADE:2,"realname":Utils.getString(R.string.都会觉得),"registerArea":Utils.getString(R.string.未分配或者内网IP),"registerIp":"10.6.46.27","remark":"","sex":2,"source":2,"usdtAccount":"xx123456789","userNickName":Utils.getString(R.string.萌新m40l734C)}
     * flag : 0
     * money : 285.19
     * memberAgent : {"happytenRate":8.5,"sscaiRate":8.5,"remark":Utils.getString(R.string.公司自身最高返点8.5,六合返点5.5,赔率1.97),"registerLink":"","xuanwuRate":8.5,"min":0,"farmRate":8.5,"lastModifiedUser":"xbgoogle","id":1,"createdUser":"sss","islimit":1,"k3Rate":8.5,"lastModifiedDate":1608082106000,"max":9,"isDelete":0,"isagent":1,"happy8Rate":8.5,"isNotDelete":1,"createdDate":1536039956000,"user_id":"","inviteCode":"","raceRate":8.5,"sixRate":5.5,"danRate":8.5,"maintainStatus":0,"chessRate":0}
     * commission : 0.0
     * message : 操作成功!
     * tokenInfo : 48768674948447bfa6cec9aa2742f590
     * status : success
     */

    private String flag;
    private String money;
    private String totalZhuanShi;
    /**
     * happytenRate : 8.5
     * sscaiRate : 8.5
     * remark : 公司自身最高返点8.5,六合返点5.5,赔率1.97
     * registerLink : 
     * xuanwuRate : 8.5
     * min : 0
     * farmRate : 8.5
     * lastModifiedUser : xbgoogle
     * id : 1
     * createdUser : sss
     * islimit : 1
     * k3Rate : 8.5
     * lastModifiedDate : 1608082106000
     * max : 9
     * isDelete : 0
     * isagent : 1
     * happy8Rate : 8.5
     * isNotDelete : 1
     * createdDate : 1536039956000
     * user_id : 
     * inviteCode : 
     * raceRate : 8.5
     * sixRate : 5.5
     * danRate : 8.5
     * maintainStatus : 0
     * chessRate : 0
     */

    private MemberAgentBean memberAgent;
    private BigDecimal commission;
    private String message;
    private String tokenInfo;
    private String status;

    public String getTotalZhuanShi() {
        return totalZhuanShi;
    }

    public void setTotalZhuanShi(String totalZhuanShi) {
        this.totalZhuanShi = totalZhuanShi;
    }

    public MemberInfoBean getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfoBean memberInfo) {
        this.memberInfo = memberInfo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public MemberAgentBean getMemberAgent() {
        return memberAgent;
    }

    public void setMemberAgent(MemberAgentBean memberAgent) {
        this.memberAgent = memberAgent;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MemberInfoBean {
        private String accountName;
        private String allMaintainStatusFlag;
        private String bankCard;
        private String bankDot;
        private String bankName;
        private List<memberBankInfoVoListBean> memberBankInfoVoList;
        private String createdDate;
        private long id;
        private String image;
        private String isTest;
        private String isagent;
        private String lastModifiedDate;
        private String maintainCount;
        private String maintainStatus;
        private String nickname;
        private String parent_id;
        private String password;
        private String paypassword;
        private String phone;
        private String pointGrade;
        private String realname;
        private String registerArea;
        private String registerIp;
        private String remark;
        private String sex;
        private String source;
        private String usdtAccount;
        private String userNickName;

        public List<memberBankInfoVoListBean> getMemberBankInfoVoList() {
            return memberBankInfoVoList;
        }

        public void setMemberBankInfoVoList(List<memberBankInfoVoListBean> memberBankInfoVoList) {
            this.memberBankInfoVoList = memberBankInfoVoList;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAllMaintainStatusFlag() {
            return allMaintainStatusFlag;
        }

        public void setAllMaintainStatusFlag(String allMaintainStatusFlag) {
            this.allMaintainStatusFlag = allMaintainStatusFlag;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public String getBankDot() {
            return bankDot;
        }

        public void setBankDot(String bankDot) {
            this.bankDot = bankDot;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIsTest() {
            return isTest;
        }

        public void setIsTest(String isTest) {
            this.isTest = isTest;
        }

        public String getIsagent() {
            return isagent;
        }

        public void setIsagent(String isagent) {
            this.isagent = isagent;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getMaintainCount() {
            return maintainCount;
        }

        public void setMaintainCount(String maintainCount) {
            this.maintainCount = maintainCount;
        }

        public String getMaintainStatus() {
            return maintainStatus;
        }

        public void setMaintainStatus(String maintainStatus) {
            this.maintainStatus = maintainStatus;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(String paypassword) {
            this.paypassword = paypassword;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPointGrade() {
            return pointGrade;
        }

        public void setPointGrade(String pointGrade) {
            this.pointGrade = pointGrade;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getRegisterArea() {
            return registerArea;
        }

        public void setRegisterArea(String registerArea) {
            this.registerArea = registerArea;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUsdtAccount() {
            return usdtAccount;
        }

        public void setUsdtAccount(String usdtAccount) {
            this.usdtAccount = usdtAccount;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public static class memberBankInfoVoListBean {
            private Long id =0l;
            private int version;//版本号（并发控管）
            private Long user_id;//用户id
            private String user_name;//用户名
            private String accountName;//开户姓名
            private String bankCard; //银行卡号
            private String bankName; //银行名称
            private String bankDot; //开户网点(省市+用户输入的具体分行)
            private int isDefault;


            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public Long getUser_id() {
                return user_id;
            }

            public void setUser_id(Long user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getBankCard() {
                return bankCard;
            }

            public void setBankCard(String bankCard) {
                this.bankCard = bankCard;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankDot() {
                return bankDot;
            }

            public void setBankDot(String bankDot) {
                this.bankDot = bankDot;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }
        }
    }

    public static class MemberAgentBean {
        private String happytenRate;
        private String sscaiRate;
        private String remark;
        private String registerLink;
        private String xuanwuRate;
        private String min;
        private String farmRate;
        private String lastModifiedUser;
        private String id;
        private String createdUser;
        private String islimit;
        private String k3Rate;
        private String lastModifiedDate;
        private String max;
        private String isDelete;
        private String isagent;
        private String happy8Rate;
        private String isNotDelete;
        private String createdDate;
        private String user_id;
        private String inviteCode;
        private String raceRate;
        private String sixRate;
        private String danRate;
        private String maintainStatus;
        private String chessRate;

        public String getHappytenRate() {
            return happytenRate;
        }

        public void setHappytenRate(String happytenRate) {
            this.happytenRate = happytenRate;
        }

        public String getSscaiRate() {
            return sscaiRate;
        }

        public void setSscaiRate(String sscaiRate) {
            this.sscaiRate = sscaiRate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRegisterLink() {
            return registerLink;
        }

        public void setRegisterLink(String registerLink) {
            this.registerLink = registerLink;
        }

        public String getXuanwuRate() {
            return xuanwuRate;
        }

        public void setXuanwuRate(String xuanwuRate) {
            this.xuanwuRate = xuanwuRate;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getFarmRate() {
            return farmRate;
        }

        public void setFarmRate(String farmRate) {
            this.farmRate = farmRate;
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

        public String getIslimit() {
            return islimit;
        }

        public void setIslimit(String islimit) {
            this.islimit = islimit;
        }

        public String getK3Rate() {
            return k3Rate;
        }

        public void setK3Rate(String k3Rate) {
            this.k3Rate = k3Rate;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsagent() {
            return isagent;
        }

        public void setIsagent(String isagent) {
            this.isagent = isagent;
        }

        public String getHappy8Rate() {
            return happy8Rate;
        }

        public void setHappy8Rate(String happy8Rate) {
            this.happy8Rate = happy8Rate;
        }

        public String getIsNotDelete() {
            return isNotDelete;
        }

        public void setIsNotDelete(String isNotDelete) {
            this.isNotDelete = isNotDelete;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getRaceRate() {
            return raceRate;
        }

        public void setRaceRate(String raceRate) {
            this.raceRate = raceRate;
        }

        public String getSixRate() {
            return sixRate;
        }

        public void setSixRate(String sixRate) {
            this.sixRate = sixRate;
        }

        public String getDanRate() {
            return danRate;
        }

        public void setDanRate(String danRate) {
            this.danRate = danRate;
        }

        public String getMaintainStatus() {
            return maintainStatus;
        }

        public void setMaintainStatus(String maintainStatus) {
            this.maintainStatus = maintainStatus;
        }

        public String getChessRate() {
            return chessRate;
        }

        public void setChessRate(String chessRate) {
            this.chessRate = chessRate;
        }
    }
}
