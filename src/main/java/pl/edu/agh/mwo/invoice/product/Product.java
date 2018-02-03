package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) {
		this.name = name;
		this.price = price;
		this.taxPercent = tax;

		if (name == null || name.isEmpty() || price == null || tax == null) {
			throw new IllegalArgumentException();
		}
		
		if (price.signum() == -1 || tax.signum() == -1) {
			throw new IllegalArgumentException();
		}
		

	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		BigDecimal sumPriceAndTax= price.add(price.multiply(taxPercent));
		return sumPriceAndTax;
	}
}
