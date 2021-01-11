package com.mycls.ssm.entity;

public class CartandGood {
	
	private int preId;
	private String goodImg;
	private String goodName;
	private String goodType;
	private String goodColor;
	private Float goodPrice;
	private int goodNum;
	private Float price;
	
	
	public CartandGood() {
		super();
	}
	public CartandGood(Cart cart,Good good) {
		super();
		this.preId = cart.getPreId();
		this.goodImg = good.getGoodImg();
		this.goodName = good.getGoodImg();
		this.goodType = good.getGoodType();
		this.goodColor = good.getGoodColor();
		this.goodPrice = good.getGoodPrice();
		this.goodNum = cart.getGoodNum();
		this.price = cart.getPrice();
	}
	public int getPreId() {
		return preId;
	}
	public void setPreId(int preId) {
		this.preId = preId;
	}
	public String getGoodImg() {
		return goodImg;
	}
	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodType() {
		return goodType;
	}
	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}
	public String getGoodColor() {
		return goodColor;
	}
	public void setGoodColor(String goodColor) {
		this.goodColor = goodColor;
	}
	public Float getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(Float goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	
}
