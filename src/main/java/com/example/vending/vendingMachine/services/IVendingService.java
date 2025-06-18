package com.example.vending.vendingMachine.services;

import com.example.vending.vendingMachine.dto.CreateVendingMachineDto;
import com.example.vending.vendingMachine.dto.VendingMachineDto;

import java.util.List;

public interface IVendingService {
    VendingMachineDto findById(Long id);

    List<VendingMachineDto> findAll();

    VendingMachineDto createVendingMachine(CreateVendingMachineDto machineDto);

    VendingMachineDto updateVendingMachineAddress(Long id, String newAddress);
}
