package com.example.vending.vendingMachine.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendingCellEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer size;

    @ManyToOne
    @JoinColumn(name = "vending_machine_id")
    private VendingMachineEntity vendingMachine;

}
