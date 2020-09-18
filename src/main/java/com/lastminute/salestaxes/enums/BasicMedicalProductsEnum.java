package com.lastminute.salestaxes.enums;

public enum BasicMedicalProductsEnum {

	PACKET_OF_HEADACHE_PILLS("packet of headache pills");

	private String medicalProductName;

	BasicMedicalProductsEnum(String medicalProductName) {
		this.medicalProductName = medicalProductName;
	}

	public String getMedicalProductName() {
		return this.medicalProductName;
	}

}
