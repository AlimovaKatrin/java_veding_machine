package com.example.vending.vendingMachine.controllers;

import com.example.vending.common.dto.ApiResponse;
import com.example.vending.vendingMachine.dto.CreateVendingMachineDto;
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
    public ResponseEntity<ApiResponse<List<VendingMachineDto>>> getAllMachines() {
        List<VendingMachineDto> machines = vendingMachineService.findAll();
        return ApiResponse.success(machines).toOk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VendingMachineDto>> getMachineById(@PathVariable Long id) {
        Optional<VendingMachineDto> dto = vendingMachineService.findById(id);

        if (dto.isPresent()) {
            return ApiResponse.success(dto.get()).toOk();
        } else {
            return ApiResponse
                    .<VendingMachineDto>error("VendingMachine with id " + id + " not found")
                    .toResponse(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    public ResponseEntity<ApiResponse<VendingMachineDto>> createMachine(@RequestBody CreateVendingMachineDto dto) {
        VendingMachineDto machine = vendingMachineService.createVendingMachine(dto);
        return ApiResponse.success(machine).toOk();
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<ApiResponse<VendingMachineDto>> updateAddress(@PathVariable Long id, @RequestBody VendingMachineDto dto) {
        if (dto.getAddress() == null) {
            return ApiResponse
                    .<VendingMachineDto>error("No address field provided")
                    .toResponse(HttpStatus.BAD_REQUEST);
        }

        try {
            VendingMachineDto updatedMachine = vendingMachineService.updateVendingMachineAddress(id, dto.getAddress());
            return ApiResponse.success(updatedMachine).toOk();
        } catch (RuntimeException e) {
            return ApiResponse
                    .<VendingMachineDto>error("Vending machine not found with id: " + id)
                    .toResponse(HttpStatus.NOT_FOUND);
        }
    }
}
