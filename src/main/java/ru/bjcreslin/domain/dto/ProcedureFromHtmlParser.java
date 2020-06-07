package ru.bjcreslin.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProcedureFromHtmlParser {
    private String number;
    private String name;
    private String tradingPlatformName;
    private String sponsorName;
    private String deadline;
    private String reviewDeadline;
    private String auctionDate;
    private BigDecimal maximumContractPrice;
}
