package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	public static int counter = 1;
	private final int number;
	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public Invoice() {
		number = counter;
		counter++;
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		products.put(product, quantity);
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public int getNumber() {
		return number;
	}

	public String preparePrint() {
		String printed = String.valueOf(number);

		for (Product product : products.keySet()) {
			printed += "\n";
			printed += product.getName();
			printed += " " + products.get(product);
		}

		return printed;
	}

	/*
	 * public String preparePrint() { String printed = String.valueOf(number);
	 * 
	 * printed += products.keySet().stream() return printed; }
	 */

}
