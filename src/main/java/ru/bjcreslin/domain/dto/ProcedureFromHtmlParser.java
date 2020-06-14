package ru.bjcreslin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
/**
 * класс для хранения данных, полученных от парсинга данных
 */
@Document(collection = "saveoverhaul")
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFromHtmlParser {
    @Id
    private String id;
    // Номер
    private String uin;
    //Название процедуры
    private String name;
    //Назване торговой платформы
    private String tradingPlatformName;
    //Название организатора
    private String sponsorName;
    //Дата окончания приема документов
    private LocalDateTime deadline;
    //Дата окончания рассмотрения
    private LocalDate reviewDeadline;
    //Дата проведения аукциона
    private LocalDate auctionDate;
    //Максимальная цена контракта
    private BigDecimal maximumContractPrice;
}
