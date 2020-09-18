package com.lastminute.salestaxes.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.model.Purchase;

@Service
public class TaxContext {

	@Autowired
	private TaxStrategy taxStrategy;

	public double executeTaxCalculation(Purchase purchase) {
		return this.taxStrategy.doTaxCalculation(purchase);
	}

}
