package com.organization.retail.controller;

import com.organization.retail.dao.ProductRepository;
import com.organization.retail.dto.ProductDTO;
import com.organization.retail.model.Product;
import com.organization.retail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductWebController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    // View all products
    @GetMapping
    public String listProducts(Model model) {
        System.out.println("=== listProducts() called ===");
        try {
            List<ProductDTO> products = productService.getAllProducts();
            System.out.println("Products found: " + products.size());
            model.addAttribute("products", products);
            return "products/list";  // Will look for /WEB-INF/views/products/list.jsp
        } catch (Exception e) {
            System.err.println("Error in listProducts: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // Show add product form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        System.out.println("=== showAddForm() called ===");
        model.addAttribute("product", new Product());
        return "products/add";
    }

    // Handle add product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        System.out.println("=== addProduct() called ===");
//        product.set(LocalDateTime.now());
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Product added successfully!");
        return "redirect:/products";
    }

    // Show edit product form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("=== showEditForm() called for id: " + id + " ===");
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/products";
        }
        model.addAttribute("product", productOptional.get());
        return "products/edit";
    }

    // Handle update product
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product product,
                                RedirectAttributes redirectAttributes) {
        System.out.println("=== updateProduct() called for id: " + id + " ===");
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/products";
        }

        Product productToUpdate = existingProduct.get();
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCreatedBy(product.getCreatedBy());
//        productToUpdate.setUpdatedAt(LocalDateTime.now());

        productRepository.save(productToUpdate);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/products";
    }

    // Show view product details
    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("=== viewProduct() called for id: " + id + " ===");
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "products/view";
    }

    // Handle delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        System.out.println("=== deleteProduct() called for id: " + id + " ===");
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
            return "redirect:/products";
        }

        productRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        return "redirect:/products";
    }

    // Test endpoint to verify controller is working
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "ProductWebController is working! JSP path: /WEB-INF/views/";
    }
}
