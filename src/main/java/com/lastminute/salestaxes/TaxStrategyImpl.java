package com.lastminute.salestaxes;

import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.model.Purchase;

@Service
public class TaxStrategyImpl implements TaxStrategy {

	private static final long basicSaleTax = 10;
	private static final long importDutyTax = 5;

	@Override
	public double doTaxCalculation(Purchase purchase) {

		long totalTax = 0;

		if (!purchase.isBasicSale())
			totalTax = basicSaleTax;
		if (purchase.isImported())
			totalTax += importDutyTax;

		return purchase.getPrice() + Math.round((purchase.getPrice() * totalTax) / 100 * 20.0) / 20.0;
	}

}
