package com.lastminute.salestaxes.calculation;

import com.lastminute.salestaxes.model.Purchase;

public interface TaxStrategy {

	public double doTaxCalculation(Purchase purchase);

}
