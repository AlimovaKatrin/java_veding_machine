package com.example.vending.product.controllers;

import com.example.vending.product.dto.CreateProductDto;
import com.example.vending.product.dto.ProductDto;
import com.example.vending.product.dto.UpdateProductDto;
import com.example.vending.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ProductDto createProduct(@RequestBody CreateProductDto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public String searchProduct() {
        return productService.searchProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PatchMapping("/{id}")
    public Optional<ProductDto> updateProductById(@PathVariable Long id, @RequestBody UpdateProductDto dto) {
        return productService.updateProductById(id, dto);
    }

    @DeleteMapping("/{id}")
    public Void deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
