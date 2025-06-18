package com.example.vending.vendingMachine.controllers;

import com.example.vending.vendingMachine.dto.CreateVendingMachineDto;
import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.services.VendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<VendingMachineDto> getMachineById(@PathVariable Long id) {
        VendingMachineDto machineDto = vendingMachineService.findById(id);
        return ResponseEntity.ok(machineDto);
    }

    @PostMapping()
    public ResponseEntity<VendingMachineDto> createMachine(@RequestBody CreateVendingMachineDto dto){
        VendingMachineDto machine = vendingMachineService.createVendingMachine(dto);
        return ResponseEntity.ok(machine);
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody VendingMachineDto dto) {
        if (dto.getAddress() == null) {
            return ResponseEntity.badRequest().body("No address field provided");
        }

        try {
            VendingMachineDto updatedMachine = vendingMachineService.updateVendingMachineAddress(id, dto.getAddress());
            return ResponseEntity.ok(updatedMachine);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "code", HttpStatus.NOT_FOUND,
                            "message", "Vending machine not found with id: " + id
                    ));
        }
    }

}