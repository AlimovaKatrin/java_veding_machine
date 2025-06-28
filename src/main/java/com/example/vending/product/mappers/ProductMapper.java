package com.example.vending.product.mappers;

import com.example.vending.product.dto.CreateProductDto;
import com.example.vending.product.dto.ProductDto;
import com.example.vending.product.entities.ProductEntity;

public class ProductMapper {
    public static ProductDto toDto(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .priceInCents(entity.getPriceInCents())
                .build();
    }

    public static ProductEntity toProductEntity(CreateProductDto createDto) {
        return ProductEntity.builder()
                .name(createDto.getName())
                .description(createDto.getDescription())
                .priceInCents(createDto.getPriceInCents())
                .build();
    }
}
