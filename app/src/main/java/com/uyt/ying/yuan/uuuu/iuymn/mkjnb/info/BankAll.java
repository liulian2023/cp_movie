package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.info;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManualEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: BankInfo 
* @Description: 银行信息
* @author administrator
*
 */

public class BankAll extends AbstractEntity {

	
	private String name;//支付类型名称
	private int sort;//排序
	private int status;//状态0隐藏1显示
	private String image;//图片路径
	private String remark;//备注
	private int type;//类型0银行卡1微信2支付宝3qq钱包4京东支付5银联

	List<RechargeBank> rechargeBankList=new ArrayList<>();
	List<ManualEntity> shopInfoVoList=new ArrayList<>();

	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<RechargeBank> getRechargeBankList() {
		return rechargeBankList;
	}

	public void setRechargeBankList(List<RechargeBank> rechargeBankList) {
		this.rechargeBankList = rechargeBankList;
	}

	public List<ManualEntity> getShopInfoVoList() {
		return shopInfoVoList;
	}

	public void setShopInfoVoList(List<ManualEntity> shopInfoVoList) {
		this.shopInfoVoList = shopInfoVoList;
	}

	@Override
	public String toString() {
		return "BankAll{" +
				"name='" + name + '\'' +
				", sort=" + sort +
				", status=" + status +
				", image='" + image + '\'' +
				", remark='" + remark + '\'' +
				", type=" + type +
				", rechargeBankList=" + rechargeBankList +
				", id=" + id +
				", createdUser='" + createdUser + '\'' +
				", createdDate=" + createdDate +
				", createdIp='" + createdIp + '\'' +
				", lastModifiedUser='" + lastModifiedUser + '\'' +
				", lastModifiedDate=" + lastModifiedDate +
				", lastModifiedIp='" + lastModifiedIp + '\'' +
				'}';
	}
}
