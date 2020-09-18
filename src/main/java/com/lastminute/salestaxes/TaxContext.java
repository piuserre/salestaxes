package com.lastminute.salestaxes;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.enums.BasicBooksEnum;
import com.lastminute.salestaxes.enums.BasicFoodsEnum;
import com.lastminute.salestaxes.enums.BasicMedicalProductsEnum;
import com.lastminute.salestaxes.model.Purchase;

@Service
public class TaxContext {

	@Autowired
	private TaxStrategy taxStrategy;

	public double executeTaxCalculation(Purchase purchase) {
		return this.taxStrategy.doTaxCalculation(purchase);
	}

	public boolean checkBasicSale(String goodName) {

		if (Arrays.asList(BasicBooksEnum.values()).stream().map(enumName -> enumName.getBookName())
				.filter(bookName -> bookName.equals(goodName)).findFirst().isPresent())
			return true;

		if (Arrays.asList(BasicFoodsEnum.values()).stream().map(enumName -> enumName.getFoodName())
				.filter(foodName -> foodName.equals(goodName)).findFirst().isPresent())
			return true;

		if (Arrays.asList(BasicMedicalProductsEnum.values()).stream().map(enumName -> enumName.getMedicalProductName())
				.filter(medicalProductName -> medicalProductName.equals(goodName)).findFirst().isPresent())
			return true;

		return false;
	}
}
