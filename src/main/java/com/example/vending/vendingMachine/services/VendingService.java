package com.example.vending.vendingMachine.services;

import com.example.vending.vendingMachine.dto.CreateVendingMachineDto;
import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.entities.VendingCellEntity;
import com.example.vending.vendingMachine.entities.VendingMachineEntity;
import com.example.vending.vendingMachine.mappers.VendingMachineMapper;
import com.example.vending.vendingMachine.repositories.VendingRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class VendingService implements IVendingService {
    private final VendingRepository vendingRepository;

    public VendingService(VendingRepository vendingRepository) {
        this.vendingRepository = vendingRepository;
    }

    @Override
    public Optional<VendingMachineDto> findById(Long id) {
        return vendingRepository.findById(id)
                .map(VendingMachineMapper::toDto);
    }

    @Override
    public List<VendingMachineDto> findAll() {
        return vendingRepository.findAll().stream()
                .map(VendingMachineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendingMachineDto createVendingMachine(CreateVendingMachineDto machineDto) {
        VendingMachineEntity machine = VendingMachineEntity.builder()
                .address(machineDto.getAddress())
                .paymentMethods(machineDto.getPaymentMethods())
                .build();

        List<VendingCellEntity> cells = IntStream.range(0, machineDto.getTotalCells())
                .mapToObj(i -> VendingCellEntity.builder()
                        .size(machineDto.getSize())
                        .vendingMachine(machine)
                        .build())
                .collect(Collectors.toList());

        machine.setVendingCells(cells);
        VendingMachineEntity saved = vendingRepository.save(machine);

        return VendingMachineMapper.toDto(saved);
    }

    @Override
    public VendingMachineDto updateVendingMachineAddress(Long id, String newAddress) {
        Optional<VendingMachineEntity> existingEntity = vendingRepository.findById(id);
        if (existingEntity.isPresent()) {
            VendingMachineEntity entityToUpdate = existingEntity.get();
            entityToUpdate.setAddress(newAddress);
            VendingMachineEntity savedEntity = vendingRepository.save(entityToUpdate);
            return VendingMachineMapper.toDto(savedEntity);
        } else {
            throw new RuntimeException("Vending machine not found with id: " + id);
        }
    }

}