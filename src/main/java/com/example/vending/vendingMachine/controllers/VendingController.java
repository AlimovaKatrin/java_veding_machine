package com.example.vending.vendingMachine.controllers;

import com.example.vending.vendingMachine.dto.VendingMachineDto;
import com.example.vending.vendingMachine.services.VendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String createMachine(@RequestBody VendingMachineDto dto){
        return "создали одну вендинг машину";
    }
}