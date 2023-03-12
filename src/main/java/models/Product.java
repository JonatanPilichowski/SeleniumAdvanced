package models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String productName;
    private BigDecimal price;
    private int count = 0;

    public Product(String productName, BigDecimal price, int count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
