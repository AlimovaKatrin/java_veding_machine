package com.example.vending.vendingMachine.dto;

import com.example.vending.common.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class VendingMachineDto {
    private Long id;
    private String address;
    private Integer totalCells;
    private List<PaymentMethod> paymentMethods;
}