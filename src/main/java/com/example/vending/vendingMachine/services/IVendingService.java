package com.example.vending.vendingMachine.services;

import com.example.vending.vendingMachine.dto.VendingMachineDto;

public interface IVendingService {
    VendingMachineDto findById(Long id);
}
