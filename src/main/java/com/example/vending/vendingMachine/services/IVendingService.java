package com.example.vending.vendingMachine.services;

import com.example.vending.vendingMachine.dto.CreateVendingMachineDto;
import com.example.vending.vendingMachine.dto.VendingMachineDto;

import java.util.List;
import java.util.Optional;

public interface IVendingService {
    Optional<VendingMachineDto> findById(Long id);

    List<VendingMachineDto> findAll();

    VendingMachineDto createVendingMachine(CreateVendingMachineDto machineDto);

    VendingMachineDto updateVendingMachineAddress(Long id, String newAddress);
}
