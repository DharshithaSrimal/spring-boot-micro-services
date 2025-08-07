package com.organization.retail.service;

import com.organization.retail.dao.ProductRepository;
import com.organization.retail.dto.EmployeeDTO;
import com.organization.retail.dto.ProductDTO;
import com.organization.retail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeService employeeService;
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll().stream()
                .toList();
        return products.stream()
                .map(product -> {
                    // Fetch employee data using the empId from product
                    EmployeeDTO employee = null;
                    if (product.getEmpId() != null) {
                        employee = employeeService.getEmployeeById(product.getEmpId());
                    }

                    // Create and return ProductDTO with employee data
                    return new ProductDTO(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            employee
                    );
                })
                .toList();

    }

//    private ProductDTO convertToDTO(Product product) {
//        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.());
//    }
}
