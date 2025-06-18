package com.example.vending.vendingMachine.dto;

import com.example.vending.common.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO для создания торгового автомата.
 */
@Data
@Builder
public class CreateVendingMachineDto {
    private Long id;

    /** Адрес установки автомата. */
    private String address;

    /** Количество ячеек которое создастся и привяжется к этому автомату */
    private Integer totalCells;

    /** Размер вендинговой машины, определяет "глубину" рейла (capacity у VendingCell)*/
    private Integer size;

    /** Список поддерживаемых методов оплаты. */
    private List<PaymentMethod> paymentMethods;
}