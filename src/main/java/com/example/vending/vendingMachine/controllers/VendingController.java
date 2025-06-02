package com.example.vending.vendingMachine.controllers;

import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.services.VendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vending-machine")
public class VendingController {
    private final VendingService vendingMachineService;

    public VendingController(VendingService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping()
    public ResponseEntity<List<VendingMachineDto>> getAllMachines() {
        List<VendingMachineDto> machines = vendingMachineService.findAll();
        return ResponseEntity.ok(machines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VendingMachineDto>> getMachineById(@PathVariable Long id) {
        Optional<VendingMachineDto> machineDto = vendingMachineService.findById(id);
        return ResponseEntity.ok(machineDto);
    }

    @PostMapping()
    public ResponseEntity<VendingMachineDto> createMachine(@RequestBody VendingMachineDto dto){
        VendingMachineDto machine = vendingMachineService.createVendingMachine(dto);
        return ResponseEntity.ok(machine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendingMachineDto> updateVendingMachine(@PathVariable Long id, @RequestBody VendingMachineDto dto){
        try {
            VendingMachineDto updatedMachine = vendingMachineService.updateVendingMachine(id, dto);
            return ResponseEntity.ok(updatedMachine);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody VendingMachineDto dto) {
        if (dto.getAddress() == null) {
            return ResponseEntity.badRequest().body("No address field provided");
        }

        try {
            VendingMachineDto updatedMachine = vendingMachineService.updateVendingMachineAddress(id, dto.getAddress());
            return ResponseEntity.ok(updatedMachine);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vending machine not found with id: " + id);
        }
    }

}