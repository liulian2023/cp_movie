package com.uyt.ying.rxhttp.net.model;


/**
 * created  by xxxx on 2019/11/19.
 */
public class UserEntity extends BaseEntity {


    /**
     * data : {"memberInfo":{"id":21,"nickname":"xxxx14","realname":"null","phone":"****5414","parent_id":1,"email":"null","isagent":0,"isTest":0,"bankCard":"111","bankName":"建行","bankDot":"222","sex":2,"maintainStatus":0,"maintainCount":0,"userNickName":"1629zV18","source":0,"registerIp":"","registerArea":"马来西亚","remark":"测试",CommonStr.GRADE:0},"token":"eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJnYW56aGUxNCIsInVzZXJJZCI6IjIxIiwibmFtZSI6IjE2Mjl6VjE4IiwidXNlckZsYWciOiJhcGlVc2VyIiwiZXhwIjoxNTc1Nzk1ODU4fQ.aggVrAn2RITk-gFdxSjDQkkdEl21HBN84_3Py0HXYnE1qvOHk6Bl4aKWjDTEfaknSJ3aeoerzHQdkiWXxeYKYSvM988A2E8vQAewcmQtezcBtDqDGObAAaVvobW8-qLATMybDHYfoNyxkgB77Ecym0I5pgSLkI_EkyAJU-WVN8E"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * memberInfo : {"id":21,"nickname":"xxxx14","realname":"null","phone":"****5414","parent_id":1,"email":"null","isagent":0,"isTest":0,"bankCard":"111","bankName":"建行","bankDot":"222","sex":2,"maintainStatus":0,"maintainCount":0,"userNickName":"1629zV18","source":0,"registerIp":"","registerArea":"马来西亚","remark":"测试",CommonStr.GRADE:0}
         * token : eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJnYW56aGUxNCIsInVzZXJJZCI6IjIxIiwibmFtZSI6IjE2Mjl6VjE4IiwidXNlckZsYWciOiJhcGlVc2VyIiwiZXhwIjoxNTc1Nzk1ODU4fQ.aggVrAn2RITk-gFdxSjDQkkdEl21HBN84_3Py0HXYnE1qvOHk6Bl4aKWjDTEfaknSJ3aeoerzHQdkiWXxeYKYSvM988A2E8vQAewcmQtezcBtDqDGObAAaVvobW8-qLATMybDHYfoNyxkgB77Ecym0I5pgSLkI_EkyAJU-WVN8E
         */

        private MemberInfoBean memberInfo;
        private String token;

        public MemberInfoBean getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(MemberInfoBean memberInfo) {
            this.memberInfo = memberInfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class MemberInfoBean {
            /**
             * id : 21
             * nickname : xxxx14
             * realname : null
             * phone : ****5414
             * parent_id : 1
             * email : null
             * isagent : 0
             * isTest : 0
             * bankCard : 111
             * bankName : 建行
             * bankDot : 222
             * sex : 2
             * maintainStatus : 0
             * maintainCount : 0
             * userNickName : 1629zV18
             * source : 0
             * registerIp :
             * registerArea : 马来西亚
             * remark : 测试
             * pointGrade : 0
             */

            private long id;
            private String nickname;
            private String realname;
            private String phone;
            private long parent_id;
            private String email;
            private int isagent;
            private int isTest;
            private String bankAccountName;
            private String bankCard;
            private String bankName;
            private String bankDot;
            private int sex;
            private int maintainStatus;
            private int maintainCount;
            private String userNickName;
            private int source;
            private String registerIp;
            private String registerArea;
            private String remark;
            private int pointGrade;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getParent_id() {
                return parent_id;
            }

            public void setParent_id(long parent_id) {
                this.parent_id = parent_id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getIsagent() {
                return isagent;
            }

            public void setIsagent(int isagent) {
                this.isagent = isagent;
            }

            public int getIsTest() {
                return isTest;
            }

            public void setIsTest(int isTest) {
                this.isTest = isTest;
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

            public String getBankAccountName() {
                return bankAccountName;
            }

            public void setBankAccountName(String bankAccountName) {
                this.bankAccountName = bankAccountName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getMaintainStatus() {
                return maintainStatus;
            }

            public void setMaintainStatus(int maintainStatus) {
                this.maintainStatus = maintainStatus;
            }

            public int getMaintainCount() {
                return maintainCount;
            }

            public void setMaintainCount(int maintainCount) {
                this.maintainCount = maintainCount;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public String getRegisterIp() {
                return registerIp;
            }

            public void setRegisterIp(String registerIp) {
                this.registerIp = registerIp;
            }

            public String getRegisterArea() {
                return registerArea;
            }

            public void setRegisterArea(String registerArea) {
                this.registerArea = registerArea;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getPointGrade() {
                return pointGrade;
            }

            public void setPointGrade(int pointGrade) {
                this.pointGrade = pointGrade;
            }
        }
    }
}
