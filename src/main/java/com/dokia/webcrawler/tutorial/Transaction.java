package com.dokia.webcrawler.tutorial;

public class Transaction {
	private String district;
	private String town;
	private String community;
	private String estateInfo;
	private String area;
	private String date;
	private String totalPrice;
	private String unitPrice;
	
	public Transaction(){}
	
	public Transaction loadInfo(String[] str) throws Exception {
		if (str.length < 8) {
			throw new Exception("Loaded string array is not valid: 8 elements is required");
		}
		setDistrict(str[0]);
		setTown(str[1]);
		setCommunity(str[2]);
		setEstateInfo(str[3]);
		setArea(str[4]);
		setDate(str[5]);
		setTotalPrice(str[6]);
		setUnitPrice(str[7]);
		return this;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getEstateInfo() {
		return estateInfo;
	}
	public void setEstateInfo(String estateInfo) {
		this.estateInfo = estateInfo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Override
	public String toString() {
		return String.format("区县：%s, 乡镇：%s, 小区名称：%s, 房源信息：%s, 面积：%s, 成交时间：%s, 总价：%s, 单价：%s", 
				getDistrict(),
				getTown(),
				getCommunity(),
				getEstateInfo(),
				getArea(),
				getDate(),
				getTotalPrice(),
				getUnitPrice());
	}
}
