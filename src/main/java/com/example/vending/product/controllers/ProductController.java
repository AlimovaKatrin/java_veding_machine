package com.example.vending.product.controllers;

import com.example.vending.common.dto.ApiResponse;
import com.example.vending.product.dto.CreateProductDto;
import com.example.vending.product.dto.ProductDto;
import com.example.vending.product.dto.UpdateProductDto;
import com.example.vending.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ApiResponse<ProductDto>> createProduct(@RequestBody CreateProductDto createDto) {
        ProductDto productDto = productService.createProduct(createDto);
        return ApiResponse.success(productDto).toOk();
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        List<ProductDto> list = productService.getAllProducts();
        return ApiResponse.success(list).toOk();
    }

    @GetMapping("/search")
    public String searchProduct() {
        return productService.searchProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable Long id) {
        Optional<ProductDto> dto = productService.getProductById(id);

        if (dto.isPresent()) {
            return ApiResponse.success(dto.get()).toOk();
        } else {
            return ApiResponse
                    .<ProductDto>error("Product with id " + id + " not found")
                    .toResponse(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProductById(@PathVariable Long id, @RequestBody UpdateProductDto dto) {
        Optional<ProductDto> updatedProduct = productService.updateProductById(id, dto);

        if (updatedProduct.isPresent()) {
            return ApiResponse.success(updatedProduct.get()).toOk();
        } else {
            return ApiResponse
                    .<ProductDto>error("Product with id " + id + " not found")
                    .toResponse(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> deleteProductById(@PathVariable Long id) {
        Optional<ProductDto> product = productService.getProductById(id);

        if (product.isPresent()) {
            productService.deleteProductById(id);
            return ApiResponse.success(product.get()).toOk();
        } else {
            return ApiResponse.<ProductDto>error("Product with id " + id + " not found")
                    .toResponse(HttpStatus.NOT_FOUND);
        }
    }
}
