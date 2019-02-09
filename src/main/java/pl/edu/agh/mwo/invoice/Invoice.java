package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList();


    public void addProduct(Product product) {
        //products.add(product);
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("illegal quantity");
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    public BigDecimal getTotalNetValue() {
        BigDecimal sumNetValue = BigDecimal.ZERO;
        for (Product product : products) {
            sumNetValue = sumNetValue.add(product.getPrice());
        }
        return sumNetValue;
    }

    public BigDecimal getTotalGrossValue() {
        BigDecimal sumGrossValue = BigDecimal.ZERO;
        for (Product product : products) {
            sumGrossValue = sumGrossValue.add(product.getPriceWithTax());
        }
        return sumGrossValue;
        //return getTotalNetValue().add(getTax());
    }

    public BigDecimal getTax() {
//        BigDecimal tax = BigDecimal.ZERO;
//        for (Product product : products) {
//            tax = tax.add(product.getPriceWithTax().subtract(product.getPrice()));
//        }
//        return tax;
        return getTotalGrossValue().subtract(getTotalNetValue());
    }
}
