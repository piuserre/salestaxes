package com.lastminute.salestaxes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lastminute.salestaxes.calculation.TaxContext;

@Component
@Scope("prototype")
public class Receipt {
	private final static Logger LOGGER = Logger.getLogger(Receipt.class.getName());

	@Autowired
	private TaxContext taxContext;

	private List<Purchase> listPurchase;
	private double totalTaxes;
	private double totalPrice;

	public Receipt() {
		this.listPurchase = new ArrayList<>();
	}

	public List<Purchase> getListPurchase() {
		return listPurchase;
	}

	public void setListPurchase(List<Purchase> listPurchase) {
		this.listPurchase = listPurchase;
	}

	public void addPurchase(Purchase purchase) {
		this.listPurchase.add(purchase);
	}

	public double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String print() {
		StringBuffer buffer = new StringBuffer();

		double[] totalTaxes = { 0 };
		double[] totalPrices = { 0 };

		this.listPurchase.stream().forEach(purchase -> {

			double priceTaxed = taxContext.executeTaxCalculation(purchase);
			totalTaxes[0] += priceTaxed - purchase.getPrice();
			totalPrices[0] += priceTaxed;

			buffer.append(purchase.getQuantity()).append(" ");

			if (purchase.isImported())
				buffer.append("imported ");
			buffer.append(purchase.getGoodName()).append(": ").append(String.format(Locale.US, "%.2f", priceTaxed))
					.append("\n");
		});

		buffer.append("Sales Taxes: ").append(String.format(Locale.US, "%.2f", totalTaxes[0])).append("\n")
				.append("Total: ").append(String.format(Locale.US, "%.2f", totalPrices[0]));

		this.totalPrice = Double.valueOf(String.format(Locale.US, "%.2f", totalPrices[0]));
		this.totalTaxes = Double.valueOf(String.format(Locale.US, "%.2f", totalTaxes[0]));

		LOGGER.info("\n--------------------------------\n" + buffer.toString() + "\n--------------------------------");

		return buffer.toString();
	}

}
