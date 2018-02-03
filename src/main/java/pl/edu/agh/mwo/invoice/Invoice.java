package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products;
	private BigDecimal netValue;
	private BigDecimal total;
	private BigDecimal tax;

	// konstruktor
	Invoice() {
		netValue = new BigDecimal("0");
		total = new BigDecimal("0");
		tax = new BigDecimal("0");
		products = new ArrayList<Product>();

	}

	public void addProduct(Product product) {
		products.add(product);
		update();
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < quantity; i++) {
			products.add(product);
		}
		update();
	}

	public void setNetValue() {
		netValue = BigDecimal.ZERO;
		for (Product product : products) {
			netValue = netValue.add(product.getPrice());
		}
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setTax() {
		tax = total.subtract(netValue);
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTotal() {
		total = BigDecimal.ZERO;
		for (Product product : products) {
			total = total.add(product.getPriceWithTax());
		}
	}

	public BigDecimal getTotal() {
		return total;
	}

	public static Invoice create() {
		return new Invoice();
	}

	public void update() {
		setNetValue();
		setTotal();
		setTax();
	}

}
