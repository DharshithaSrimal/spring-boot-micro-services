package com.retail_app.retail.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer empId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getEmpId() {
        return empId;
    }

    public Product(Integer id, String name, String description, Double price, Integer empId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.empId = empId;
    }
}
