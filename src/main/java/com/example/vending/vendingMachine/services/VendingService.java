package com.example.vending.vendingMachine.services;

import com.example.vending.common.enums.PaymentMethod;
import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.entities.VendingMachine;
import com.example.vending.vendingMachine.repositories.VendingRepository;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendingService implements IVendingService {
    private final VendingRepository vendingRepository;

    public VendingService(VendingRepository vendingRepository) {
        this.vendingRepository = vendingRepository;
    }

    @Override
    public VendingMachineDto findById(Long id) {
        return VendingMachineDto.builder()
                .id(1L)
                .address("Some Address")
                .totalCells(10)
                .paymentMethods(Arrays.asList(PaymentMethod.APP, PaymentMethod.TERMINAL))
                .build();
    }

    public List<VendingMachineDto> findAll() {
        return vendingRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private VendingMachineDto mapEntityToDto(VendingMachine machine) {
        return VendingMachineDto.builder()
                .id(machine.getId())
                .address(machine.getAddress())
                .paymentMethods(machine.getPaymentMethods())
                .totalCells(machine.getTotalCells())
                .build();
    }
}