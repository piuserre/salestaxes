package com.lastminute.salestaxes.model;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PurchaseFactory {

	private final static Logger LOGGER = Logger.getLogger(PurchaseFactory.class.getName());

	public Purchase getPurchase(String purchaseString) {

		LOGGER.info("Going to create Purchase for " + purchaseString);

		Purchase purchase = new Purchase();

		try {
			boolean imported = purchaseString.indexOf("imported") > -1;
			purchaseString = purchaseString.replaceAll("imported ", "");

			Pattern pattern = Pattern.compile("(\\d+)\\s+(.*)\\s+at\\s+(.*)");

			Matcher matcher = pattern.matcher(purchaseString);
			matcher.find();

			purchase.setQuantity(Integer.valueOf(matcher.group(1)));
			purchase.setImported(imported);
			purchase.setGoodName(matcher.group(2));
			purchase.setPrice(Double.valueOf(matcher.group(3)));

		} catch (Exception e) {
			LOGGER.severe("Error during Purchase creation");
			throw e;
		}

		LOGGER.info("Created Purchase: " + purchase);

		return purchase;
	}

}
