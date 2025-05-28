package com.example.vending.vendingMachine.services;

import com.example.vending.common.enums.PaymentMethod;
import com.example.vending.vendingMachine.dto.VendingMachineDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class VendingService implements IVendingService {
    @Override
    public VendingMachineDto findById(Long id) {
        return VendingMachineDto.builder()
                .id(1L)
                .address("Some Address")
                .totalCells(10)
                .paymentMethods(Arrays.asList(PaymentMethod.APP, PaymentMethod.TERMINAL))
                .build();
    }
}