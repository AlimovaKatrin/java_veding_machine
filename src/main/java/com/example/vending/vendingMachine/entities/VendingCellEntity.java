package com.example.vending.vendingMachine.entities;

import com.example.vending.product.entities.ProductEntity;
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
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "vending_machine_id")
    private VendingMachineEntity vendingMachine;

    @ManyToOne
    private ProductEntity product;
}
