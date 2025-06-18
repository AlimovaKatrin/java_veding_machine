package com.example.vending.vendingMachine.entities;

import com.example.vending.common.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendingMachineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vending_machine_payment_methods", joinColumns = @JoinColumn(name = "vending_machine_id"))
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "vendingMachine", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<VendingCellEntity> vendingCells = new ArrayList<>();
}
