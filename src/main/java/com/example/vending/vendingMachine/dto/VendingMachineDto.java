package com.example.vending.vendingMachine.dto;

import com.example.vending.common.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO для передачи и получения готового торгового автомата.
 */
@Data
@Builder
public class VendingMachineDto {
    private Long id;

    /**
     * Адрес установки автомата.
     */
    private String address;

    /**
     * Id ячеек которые находятся в этом автомате
     */
    private List<VendingCellDto> vendingCells;

    /**
     * Размер вендинговой машины, определяет "глубину" рейла (capacity у VendingCell)
     */
    private Integer size;

    /**
     * Список поддерживаемых методов оплаты.
     */
    private List<PaymentMethod> paymentMethods;
}
