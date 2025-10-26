package com.organization.retail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
//    private Integer empId;
    @Column(name = "createdBy")
    private Integer createdBy;

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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Product(Integer id, String name, String description, Double price, Integer createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdBy = createdBy;
//        this.empId = empId;
    }

    public Product() {
    }
}
