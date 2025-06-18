package com.example.vending.vendingMachine.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendingCellDto {
    private Long id;
    private Integer size;
}
