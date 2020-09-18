package com.lastminute.salestaxes.model;

public class Purchase {

	private String goodName;
	private int quantity;
	private double price;
	private boolean imported;
	private boolean basicSale;

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public boolean isBasicSale() {
		return basicSale;
	}

	public void setBasicSale(boolean basicSale) {
		this.basicSale = basicSale;
	}

	@Override
	public String toString() {
		return "Purchase [goodName=" + goodName + ", quantity=" + quantity + ", price=" + price + ", imported="
				+ imported + ", basicSale=" + basicSale + "]";
	}

}
