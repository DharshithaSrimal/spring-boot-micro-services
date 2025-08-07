package com.organization.retail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private EmployeeDTO employee;

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

    public EmployeeDTO getEmployee() {
        return employee;
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

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public ProductDTO(Integer id, String name, String description, Double price, EmployeeDTO employee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.employee = employee;
    }

    public ProductDTO() {
    }
}
