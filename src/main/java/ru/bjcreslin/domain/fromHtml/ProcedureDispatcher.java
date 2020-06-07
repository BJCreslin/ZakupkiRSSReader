package ru.bjcreslin.domain.fromHtml;

import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ProcedureDispatcher {
    private Map<String, BiConsumer<ProcedureFromHtmlParser, String>> map;

    public Map<String, BiConsumer<ProcedureFromHtmlParser, String>> getMap() {
        return map;
    }

    public ProcedureDispatcher() {
        map = new HashMap<>();
        map.put("Номер закупки", this::setNumber);
        map.put("Наименование закупки", this::setName);
        map.put("Наименование электронной площадки", this::setPlatform);
        map.put("Наименование организации", this::setSponsorName);
        map.put("Дата и время окончания срока подачи заявок", this::setDeadline);
        map.put("Дата окончания срока рассмотрения заявок", this::setReviewDeadline);
        map.put("Дата проведения электронного аукциона", this::setAuctionDate);
        map.put("Начальная (максимальная) цена договора", this::setMaximumContractPrice);

    }

    private void setMaximumContractPrice(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        var price = new BigDecimal(s.replace(" в российских рублях", "").trim());
        procedureFromHtmlParser.setMaximumContractPrice(price);
    }

    private void setAuctionDate(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var localDate = LocalDate.parse(s.replace("\n" +
                "<p class=\"caption\"><b>Условия договора</b>", "").trim(), formatter);
        procedureFromHtmlParser.setAuctionDate(localDate);
    }

    private void setReviewDeadline(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var localDate = LocalDate.parse(s.trim(), formatter);
        procedureFromHtmlParser.setReviewDeadline(localDate);
    }

    private void setDeadline(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        var localDateTime = LocalDateTime.parse(s.trim(), formatter);
        procedureFromHtmlParser.setDeadline(localDateTime);
    }

    private void setSponsorName(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        procedureFromHtmlParser.setSponsorName(s);
    }

    private void setPlatform(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        procedureFromHtmlParser.setTradingPlatformName(s);
    }

    private void setName(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        procedureFromHtmlParser.setName(s);
    }

    private void setNumber(ProcedureFromHtmlParser procedureFromHtmlParser, String number) {
        procedureFromHtmlParser.setNumber(number);
    }
}
