package com.example.vending.vendingMachine.services;

import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.entities.VendingMachineEntity;
import com.example.vending.vendingMachine.repositories.VendingRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendingService implements IVendingService {
    private final VendingRepository vendingRepository;

    public VendingService(VendingRepository vendingRepository) {
        this.vendingRepository = vendingRepository;
    }

    @Override
    public Optional<VendingMachineDto> findById(Long id) {
        Optional<VendingMachineEntity> optionalEntity = vendingRepository.findById(id);
        return optionalEntity.map(this::mapEntityToDto);
    }

    @Override
    public List<VendingMachineDto> findAll() {
        return vendingRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendingMachineDto createVendingMachine(VendingMachineDto machineDto) {
        VendingMachineEntity machineEntity = mapDtoToEntity(machineDto);
        VendingMachineEntity savedEntity = vendingRepository.save(machineEntity);
        return mapEntityToDto(savedEntity);
    }

    @Override
    public VendingMachineDto updateVendingMachine(Long id, VendingMachineDto machineDto) {
        Optional<VendingMachineEntity> existingEntity = vendingRepository.findById(id);
        if (existingEntity.isPresent()) {
            VendingMachineEntity updatedEntity = existingEntity.get();
            updatedEntity.setAddress(machineDto.getAddress());
            updatedEntity.setPaymentMethods(machineDto.getPaymentMethods());
            updatedEntity.setTotalCells(machineDto.getTotalCells());
            VendingMachineEntity savedEntity = vendingRepository.save(updatedEntity);
            return mapEntityToDto(savedEntity);
        } else {
            throw new RuntimeException("Vending machine not found with id: " + id);
        }
    }

    @Override
    public VendingMachineDto updateVendingMachineAddress(Long id, String newAddress) {
        Optional<VendingMachineEntity> existingEntity = vendingRepository.findById(id);
        if (existingEntity.isPresent()) {
            VendingMachineEntity entityToUpdate = existingEntity.get();
            entityToUpdate.setAddress(newAddress);
            VendingMachineEntity savedEntity = vendingRepository.save(entityToUpdate);
            return mapEntityToDto(savedEntity);
        } else {
            throw new RuntimeException("Vending machine not found with id: " + id);
        }
    }

        private VendingMachineDto mapEntityToDto(VendingMachineEntity machine) {
        return VendingMachineDto.builder()
                .id(machine.getId())
                .address(machine.getAddress())
                .paymentMethods(machine.getPaymentMethods())
                .totalCells(machine.getTotalCells())
                .build();
    }

        private VendingMachineEntity mapDtoToEntity(VendingMachineDto machine) {
        return VendingMachineEntity.builder()
                .id(machine.getId())
                .address(machine.getAddress())
                .paymentMethods(machine.getPaymentMethods())
                .totalCells(machine.getTotalCells())
                .build();
    }
}