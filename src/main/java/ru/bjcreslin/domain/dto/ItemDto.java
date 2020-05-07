package ru.bjcreslin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    // номер закона - ПП РФ 615 (Капитальный ремонт)
    String lawNumber;
    // Заказчк -
    String author;
    // Этап размещения:  Работа комиссии
    String placementPhase;
    //Размещено: 10.04.2020
    LocalDate posted;
    // Обновлено: 07.05.2020
    LocalDate updated;
    // Начальная цена контракта: 4115914.02
    BigDecimal initialContractprice;
    // Объект закупки
    String name;
    //этапы размещения
    String placementStages;
}
