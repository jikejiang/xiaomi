package com.mycls.ssm.entity;

public class Cartlist {
		private Integer goodId;

	    private String goodName;

	    private Float goodPrice;

	    private String goodType;

	    private String goodColor;

	    private Integer goodCount;

	    private String goodImg;

	    private String goodDesc;
	    private Float price;
	    private Integer preId;
	    
	    
		public Cartlist() {
			super();
		}
		public Cartlist(Integer goodId, String goodName, Float goodPrice, String goodType, String goodColor,
				Integer goodCount, String goodImg, String goodDesc, Float price, Integer preId) {
			super();
			this.goodId = goodId;
			this.goodName = goodName;
			this.goodPrice = goodPrice;
			this.goodType = goodType;
			this.goodColor = goodColor;
			this.goodCount = goodCount;
			this.goodImg = goodImg;
			this.goodDesc = goodDesc;
			this.price = price;
			this.preId = preId;
		}
		public Integer getGoodId() {
			return goodId;
		}
		public void setGoodId(Integer goodId) {
			this.goodId = goodId;
		}
		public String getGoodName() {
			return goodName;
		}
		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}
		public Float getGoodPrice() {
			return goodPrice;
		}
		public void setGoodPrice(Float goodPrice) {
			this.goodPrice = goodPrice;
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
		public Integer getGoodCount() {
			return goodCount;
		}
		public void setGoodCount(Integer goodCount) {
			this.goodCount = goodCount;
		}
		public String getGoodImg() {
			return goodImg;
		}
		public void setGoodImg(String goodImg) {
			this.goodImg = goodImg;
		}
		public String getGoodDesc() {
			return goodDesc;
		}
		public void setGoodDesc(String goodDesc) {
			this.goodDesc = goodDesc;
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public Integer getPreId() {
			return preId;
		}
		public void setPreId(Integer preId) {
			this.preId = preId;
		}
		@Override
		public String toString() {
			return "Cartlist [goodId=" + goodId + ", goodName=" + goodName + ", goodPrice=" + goodPrice + ", goodType="
					+ goodType + ", goodColor=" + goodColor + ", goodCount=" + goodCount + ", goodImg=" + goodImg
					+ ", goodDesc=" + goodDesc + ", price=" + price + ", preId=" + preId + "]";
		}
	    
	    
}
