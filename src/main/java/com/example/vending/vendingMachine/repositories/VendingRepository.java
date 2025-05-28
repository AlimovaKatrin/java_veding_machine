package com.example.vending.vendingMachine.repositories;

import com.example.vending.vendingMachine.entities.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingRepository extends JpaRepository<VendingMachine, Long> {
}