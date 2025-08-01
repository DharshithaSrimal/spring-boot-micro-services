package com.retail_app.retail.controller;

import com.retail_app.retail.dto.ProductDTO;
import com.retail_app.retail.model.EmployeeDTO;
import com.retail_app.retail.model.Product;
import com.retail_app.retail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

//    @GetMapping
//    public List<Product> fetchProducts(){
//        List<Product> products = new ArrayList<>();
//        Product product1 = new Product(1, "A", "Good condition", 10.0);
//        Product product2 = new Product(2, "B", "Good condition", 20.0);
//        Product product3 = new Product(3, "C", "Good condition", 30.0);
//        products.add(product1);
//        products.add(product2);
//        products.add(product3);
//
//        return products;
//    }

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ProductService productService;

//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> fetchProducts(){
//        List<ProductDTO> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }

//    @GetMapping("/api/employees")
//    public ResponseEntity<List<EmployeeDTO>> getEmployeesFromEmployeeService() {
//        List<EmployeeDTO> employees = retailService.fetchEmployeesFromEmployeeService();
//        return ResponseEntity.ok(employees);
//    }

}
