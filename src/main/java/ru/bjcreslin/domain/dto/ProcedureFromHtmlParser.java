package ru.bjcreslin.domain.dto;

import lombok.Data;

@Data
public class ProcedureFromHtmlParser {
    private String number;
    private String name;
    private String tradingPlatformName;
    private String sponsorName;
    private String deadline;
}
