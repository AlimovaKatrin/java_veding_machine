package com.example.vending.vendingMachine.mappers;

import com.example.vending.vendingMachine.dto.VendingCellDto;
import com.example.vending.vendingMachine.entities.VendingCellEntity;

public class VendingCellMapper {
    public static VendingCellDto toDto(VendingCellEntity entity) {
        return VendingCellDto.builder()
                .id(entity.getId())
                .size(entity.getSize())
                .build();
    }
}
