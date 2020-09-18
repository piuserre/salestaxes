package com.lastminute.salestaxes.enums;

public enum BasicFoodsEnum {

	CHOCOLATE_BAR("chocolate bar"), BOX_OF_CHOCOLATES("box of chocolates");

	private String foodName;

	BasicFoodsEnum(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodName() {
		return this.foodName;
	}

}
