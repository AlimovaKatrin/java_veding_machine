package com.example.vending.product.services;

import com.example.vending.product.dto.CreateProductDto;
import com.example.vending.product.dto.ProductDto;
import com.example.vending.product.dto.UpdateProductDto;
import com.example.vending.product.mappers.ProductMapper;
import com.example.vending.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductDto createProduct(CreateProductDto dto) {
        var product = ProductMapper.toProductEntity(dto);
        var entity = productRepository.save(product);
        return ProductMapper.toDto(entity);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        var entities = productRepository.findAll();
        return entities.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public String searchProducts() {
        return "searchProducts";
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDto);
    }

    @Override
    public Optional<ProductDto> updateProductById(Long id, UpdateProductDto dto) {
        return productRepository.findById(id)
                .map(entity -> {
                    if (dto.getName() != null) {
                        entity.setName(dto.getName());
                    }
                    if (dto.getPriceInCents() != null) {
                        entity.setPriceInCents(dto.getPriceInCents());
                    }
                    if (dto.getDescription() != null) {
                        entity.setDescription(dto.getDescription());
                    }

                    var saved = productRepository.save(entity);
                    return ProductMapper.toDto(saved);
                });
    }

    @Override
    public Void deleteProductById(Long id) {
        productRepository.deleteById(id);
        return null;
    }

}
