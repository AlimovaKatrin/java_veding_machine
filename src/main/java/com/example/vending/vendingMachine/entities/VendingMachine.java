package com.example.vending.vendingMachine.entities;

import com.example.vending.common.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vending_machine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendingMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Integer totalCells;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vending_machine_payments", joinColumns = @JoinColumn(name = "vending_machine_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private List<PaymentMethod> paymentMethods;
}