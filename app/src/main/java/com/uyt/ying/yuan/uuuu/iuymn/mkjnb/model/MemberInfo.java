package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
* @ClassName: MemberInfo 
* @Description: 成员信息表
* @author administrator
*
 */

public class MemberInfo extends AbstractEntity{
	public MemberInfo() {
		this.rebate = BigDecimal.ZERO;
		this.multiple = BigDecimal.ONE;
		this.multiplePrice = BigDecimal.ZERO;
		this.memberagentId = 1l;
	}

	private int version;//版本号（并发控管）
	private String nickname;//用户名
	private String realname;//用户真实姓名
	private String password;//登入密码
	private String paypassword;//支付密码
	private String phone;//手机号
	private Long parent_id;//父级id
	private BigDecimal rebate;//反水
	private String email;//email
	private int isagent; //代理:0不是1是
	private int isTest; //是否是测试账号:0否1是
	private String accountName;//开户姓名
	private String bankCard; //银行卡号
	private String bankName; //银行名称
	private String bankDot; //开户网点(省市+用户输入的具体分行)
	private int sex;//性别0女1男2保密
	private int maintainStatus;//维护状态(0正常 1代维护或被拉黑)
	private int maintainCount;//代维护次数
	private String userNickName;//用户昵称
	

	private Date birthday;//生日
	private String image;//头像
	private BigDecimal multiple;//提现的打码量倍数
	private BigDecimal multiplePrice;//打码量倍数额外增加金额
	private int source;//来源(SourceEnum)
	private String domainName;//代理的>前端邀请访问域名
	private String registerIp;
	private String registerArea;
	private String invitationCode;//邀请码
	private int passErrorNumber;//密码输错次数
	private long memberagentId;//邀请人id

	private String parentIds;//父级id

	private String childIds;//子级id

	

	private String parent_name;//用户名

	private BigDecimal amount;//用户余额

	private BigDecimal commission;//用户佣金

	private int pointGrade;//用户积分等级
	
	
	
	
	
	public long getMemberagentId() {
		return memberagentId;
	}
	public void setMemberagentId(long memberagentId) {
		this.memberagentId = memberagentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getMultiple() {
		return multiple;
	}
	public void setMultiple(BigDecimal multiple) {
		this.multiple = multiple;
	}
	public BigDecimal getMultiplePrice() {
		return multiplePrice;
	}
	public void setMultiplePrice(BigDecimal multiplePrice) {
		this.multiplePrice = multiplePrice;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getIsTest() {
		return isTest;
	}
	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getPointGrade() {
		return pointGrade;
	}
	public void setPointGrade(int pointGrade) {
		this.pointGrade = pointGrade;
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
	public BigDecimal getRebate() {
		return rebate;
	}
	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getChildIds() {
		return childIds;
	}

	public void setChildIds(String childIds) {
		this.childIds = childIds;
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

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public int getMaintainStatus() {
		if(maintainStatus==1 || getIsDelete()==1 ){
			return 1;
		}
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

	public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

	public int getPassErrorNumber() {
		return passErrorNumber;
	}

	public void setPassErrorNumber(int passErrorNumber) {
		this.passErrorNumber = passErrorNumber;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
}
