package com.retail_app.retail.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Product {
    private int productId;
    private String productName;
    private String description;
    private double price;

    public Product(int productId, String productName, String description, double price){
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
}
