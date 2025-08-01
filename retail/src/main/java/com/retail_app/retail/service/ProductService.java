package com.retail_app.retail.service;

import com.retail_app.retail.client.EmployeeClient;
import com.retail_app.retail.dao.ProductRepository;
import com.retail_app.retail.dto.ProductDTO;
import com.retail_app.retail.model.EmployeeDTO;
import com.retail_app.retail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final EmployeeClient employeeClient;
    @Autowired
    private ProductRepository productRepository;

    public ProductService(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public List<EmployeeDTO> fetchEmployeesFromEmployeeService() {
        return employeeClient.getAllEmployees();
    }

//    public List<ProductDTO> getAllProducts() {
//        List<ProductDTO> products = new ArrayList<ProductDTO>();
//        ProductDTO product = new ProductDTO();
//        Integer p1id = 1;
//        String des = "Des";
//        Double price1 = 10.0;
//        product.setId(p1id);
//        product.setName(des);
//        product.setDescription(des);
//        product.setPrice(price1);
//        products.add(product);
//
//        return products;
//    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getEmpId());
    }
}
