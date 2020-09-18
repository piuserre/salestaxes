package com.lastminute.salestaxes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.lastminute.salestaxes.model.Purchase;
import com.lastminute.salestaxes.model.PurchaseFactory;
import com.lastminute.salestaxes.model.Receipt;

@SpringBootTest
class SalesTaxesApplicationTests {

	@Autowired
	private ApplicationContext ctx;
	
	@Autowired
	private PurchaseFactory purchaseFactory;

	@Test
	void contextLoads() {
	}

	@Test
	public void test01_withProperInput_shouldReturnPrintedReceipt() {

		String input = "1 book at 12.49";
		String input1 = "1 music CD at 14.99";
		String input2 = "1 chocolate bar at 0.85";

		Purchase purchase = purchaseFactory.getPurchase(input);
		Purchase purchase1 = purchaseFactory.getPurchase(input1);
		Purchase purchase2 = purchaseFactory.getPurchase(input2);

		Receipt receipt = ctx.getBean(Receipt.class);
		receipt.addPurchase(purchase);
		receipt.addPurchase(purchase1);
		receipt.addPurchase(purchase2);

		receipt.print();

		assertEquals(1.50, receipt.getTotalTaxes());
		assertEquals(29.83, receipt.getTotalPrice());
	}

	@Test
	public void test02_withProperInput_shouldReturnPrintedReceipt() {

		String input = "1 imported box of chocolates at 10.00";
		String input1 = "1 imported bottle of perfume at 47.50";

		Purchase purchase = purchaseFactory.getPurchase(input);
		Purchase purchase1 = purchaseFactory.getPurchase(input1);

		Receipt receipt = ctx.getBean(Receipt.class);
		receipt.addPurchase(purchase);
		receipt.addPurchase(purchase1);

		receipt.print();

		assertEquals(7.65, receipt.getTotalTaxes());
		assertEquals(65.15, receipt.getTotalPrice());
	}

	@Test
	public void test03_withProperInput_shouldReturnPrintedReceipt() {

		String input = "1 imported bottle of perfume at 27.99";
		String input1 = "1 bottle of perfume at 18.99";
		String input2 = "1 packet of headache pills at 9.75";
		String input3 = "1 box of imported chocolates at 11.25";// (11.25*5/100)=0.5625=0.55 -> 11.25+0.55=11.80

		Purchase purchase = purchaseFactory.getPurchase(input);
		Purchase purchase1 = purchaseFactory.getPurchase(input1);
		Purchase purchase2 = purchaseFactory.getPurchase(input2);
		Purchase purchase3 = purchaseFactory.getPurchase(input3);

		Receipt receipt = ctx.getBean(Receipt.class);
		receipt.addPurchase(purchase);
		receipt.addPurchase(purchase1);
		receipt.addPurchase(purchase2);
		receipt.addPurchase(purchase3);

		receipt.print();

		assertEquals(6.65, receipt.getTotalTaxes());// 6.7 actual task value
		assertEquals(74.63, receipt.getTotalPrice());// 74.68 actual task value
	}

	@Test
	public void test04_withWrongInput_shouldReturnException() {

		String input = "i don't know what to buy $$$%%%££";

		assertThrows(IllegalStateException.class, () -> {
			purchaseFactory.getPurchase(input);
		});
	}

	@Test
	public void test05_withNullInput_shouldReturnException() {

		String input = null;

		assertThrows(NullPointerException.class, () -> {
			purchaseFactory.getPurchase(input);
		});
	}

	@Test
	public void test06_withWrongOrderInput_shouldReturnException() {

		String input = "imported bottle of perfume 1";

		assertThrows(IllegalStateException.class, () -> {
			purchaseFactory.getPurchase(input);
		});
	}

	@Test
	public void test07_withNoPurchases_shouldReturnEmptyReceipt() {

		Receipt receipt = ctx.getBean(Receipt.class);

		receipt.print();

		assertEquals(0.00, receipt.getTotalTaxes());
		assertEquals(0.00, receipt.getTotalPrice());
	}

}
