package com.organization.retail.service;

import com.organization.retail.dao.ProductRepository;
import com.organization.retail.dto.EmployeeDTO;
import com.organization.retail.dto.ProductDTO;
import com.organization.retail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ProductDTO getProductById(Integer productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        Product product = productOptional.get();
        // Fetch employee using Feign client
        EmployeeDTO employee = null;
        if (product.getEmpId() != null) {
            try {
                employee = employeeService.getEmployeeById(product.getEmpId());
            } catch (Exception e) {
                e.getMessage();
            }
        }
        // Map Product to ProductDTO
        ProductDTO productDTO = mapToProductDTO(product, employee);

        return productDTO;
    }

    /**
     * Maps Product entity and EmployeeDTO to ProductDTO
     */
    private ProductDTO mapToProductDTO(Product product, EmployeeDTO employee) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                employee  // This can be null if employee is not found
        );
    }
}
