package ru.bjcreslin.domain.fromXML;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "overhaul")
public class ItemDto {
    @Id
    String id;

    boolean needed;

    // номер закона - ПП РФ 615 (Капитальный ремонт)
    private String lawNumber;
    // Заказчк -
    private String author;
    // Этап размещения:  Работа комиссии
    private String placementPhase;
    //Размещено: 10.04.2020
    private LocalDate posted;
    // Обновлено: 07.05.2020
    private LocalDate updated;
    // Начальная цена контракта: 4115914.02
    private BigDecimal initialContractprice;
    // Объект закупки
    private String name;
    //этапы размещения
    private String placementStages;
    // <link>/epz/order/notice/ea615/view/common-info.html?regNumber=206520000011900119</link>
    private String link;
    // Уникальный номер закупки uin
    private long uin;
}
