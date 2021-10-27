package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
* @ClassName: RechargeBank 
* @Description: 充值汇款银行表
* @author administrator
*
 */

public class RechargeBank extends AbstractEntity implements Serializable {

	public RechargeBank(){
		this.up = BigDecimal.ZERO;
		this.down = BigDecimal.ZERO;
	}
	private Long bankAllId;//支付大类id
	private String bankName;//银行名称/通道名称
	
	private String payTypeImage;//支付方式图片
	
	private String image;//银行图标
	
	private String url;//汇款银行官网路径
	
	private int status;//状态0隐藏1显示
	
	private int orderNo;//排序
	
	private String gradeStr;//显示需要的等级0.VIP1,1.VIP2,2.VIP3,3.VIP4,4.VIP5,5.VIP6,6.VIP7,7.VIP8,8.VIP9
	
	private String account;//账号
	
	private String name;//开户姓名
	
	private int type;//类型类型0银行卡1微信2支付宝3qq钱包4京东支付5银联(bankAll.type)
	private String typeName;//类型0银行卡1微信2支付宝3qq钱包4京东支付5银联(bankAll.name)
	private String thirdPartyFlag="";//rechargeBankThirdType.netwayCode(英文如:XINFA_WX) 技术控制的唯一
	private String thirdPartyFlagName="";//rechargeBankThirdType.name(中文如:鑫发微信支付) 技术控制的唯一

	private BigDecimal up;//充值金额上限
	
	private BigDecimal down;//充值金额下限
	
	private String merchantCode;//商家号

	
	private String dinpayPublicKey;//第三方公钥(或appId)
	
	private String merchantPrivateKey;//第三方私钥
	private String md5PrivateKey;//MD5秘钥(鑫发才有)

	private String remark;//提示信息
	
	private String netwayCodeStr;
	
	private String netwayCodePcStr;


	public Long getBankAllId() {
		return bankAllId;
	}

	public void setBankAllId(Long bankAllId) {
		this.bankAllId = bankAllId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPayTypeImage() {
		return payTypeImage;
	}

	public void setPayTypeImage(String payTypeImage) {
		this.payTypeImage = payTypeImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 *
	 * @return (rechargeBankThirdType.netwayCode)
	 */
	public String getThirdPartyFlag() {
		return thirdPartyFlag;
	}

	/**
	 *
	 * @param thirdPartyFlag (rechargeBankThirdType.netwayCode)
	 */
	public void setThirdPartyFlag(String thirdPartyFlag) {
		this.thirdPartyFlag = thirdPartyFlag;
	}

	/**
	 *
	 * @return   (rechargeBankThirdType.name)
	 */
	public String getThirdPartyFlagName() {
		return thirdPartyFlagName;
	}

	/**
	 *
	 * @param thirdPartyFlagName   (rechargeBankThirdType.name)
	 */
	public void setThirdPartyFlagName(String thirdPartyFlagName) {
		this.thirdPartyFlagName = thirdPartyFlagName;
	}

	public BigDecimal getUp() {
		return up;
	}

	public void setUp(BigDecimal up) {
		this.up = up;
	}

	public BigDecimal getDown() {
		return down;
	}

	public void setDown(BigDecimal down) {
		this.down = down;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getDinpayPublicKey() {
		return dinpayPublicKey;
	}

	public void setDinpayPublicKey(String dinpayPublicKey) {
		this.dinpayPublicKey = dinpayPublicKey;
	}

	public String getMerchantPrivateKey() {
		return merchantPrivateKey;
	}

	public void setMerchantPrivateKey(String merchantPrivateKey) {
		this.merchantPrivateKey = merchantPrivateKey;
	}

	public String getMd5PrivateKey() {
		return md5PrivateKey;
	}

	public void setMd5PrivateKey(String md5PrivateKey) {
		this.md5PrivateKey = md5PrivateKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNetwayCodeStr() {
		return netwayCodeStr;
	}

	public void setNetwayCodeStr(String netwayCodeStr) {
		this.netwayCodeStr = netwayCodeStr;
	}

	public String getNetwayCodePcStr() {
		return netwayCodePcStr;
	}

	public void setNetwayCodePcStr(String netwayCodePcStr) {
		this.netwayCodePcStr = netwayCodePcStr;
	}

	@Override
	public String toString() {
		return "RechargeBank{" +
				"bankAllId=" + bankAllId +
				", bankName='" + bankName + '\'' +
				", payTypeImage='" + payTypeImage + '\'' +
				", image='" + image + '\'' +
				", url='" + url + '\'' +
				", status=" + status +
				", orderNo=" + orderNo +
				", gradeStr='" + gradeStr + '\'' +
				", account='" + account + '\'' +
				", name='" + name + '\'' +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", thirdPartyFlag='" + thirdPartyFlag + '\'' +
				", thirdPartyFlagName='" + thirdPartyFlagName + '\'' +
				", up=" + up +
				", down=" + down +
				", merchantCode='" + merchantCode + '\'' +
				", dinpayPublicKey='" + dinpayPublicKey + '\'' +
				", merchantPrivateKey='" + merchantPrivateKey + '\'' +
				", md5PrivateKey='" + md5PrivateKey + '\'' +
				", remark='" + remark + '\'' +
				", netwayCodeStr='" + netwayCodeStr + '\'' +
				", netwayCodePcStr='" + netwayCodePcStr + '\'' +
				'}';
	}


}
