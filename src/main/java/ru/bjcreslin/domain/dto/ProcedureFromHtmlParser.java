package ru.bjcreslin.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
/**
 * класс для хранения данных, полученных от парсинга данных
 */
public class ProcedureFromHtmlParser {
    private String number;
    private String name;
    private String tradingPlatformName;
    private String sponsorName;
    private LocalDateTime deadline;
    private LocalDate reviewDeadline;
    private LocalDate auctionDate;
    private BigDecimal maximumContractPrice;
}
