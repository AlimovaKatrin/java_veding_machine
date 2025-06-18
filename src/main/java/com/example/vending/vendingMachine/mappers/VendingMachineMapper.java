package com.example.vending.vendingMachine.mappers;

import com.example.vending.vendingMachine.dto.VendingCellDto;
import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.entities.VendingMachineEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineMapper {
    public static VendingMachineDto toDto(VendingMachineEntity entity) {
        List<VendingCellDto> cellDtos = entity.getVendingCells() != null
                ? entity.getVendingCells().stream()
                .map(VendingCellMapper::toDto)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return VendingMachineDto.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .paymentMethods(entity.getPaymentMethods())
                .vendingCells(cellDtos)
                .size(cellDtos.isEmpty() ? 0 : cellDtos.get(0).getSize())
                .build();
    }
}
