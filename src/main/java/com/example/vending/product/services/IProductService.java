package com.example.vending.product.services;

import com.example.vending.product.dto.CreateProductDto;
import com.example.vending.product.dto.ProductDto;
import com.example.vending.product.dto.UpdateProductDto;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductDto createProduct(CreateProductDto dto);

    List<ProductDto> getAllProducts();

    String searchProducts();

    Optional<ProductDto> getProductById(Long id);

    Optional<ProductDto> updateProductById(Long id, UpdateProductDto dto);

    Void deleteProductById(Long id);
}
