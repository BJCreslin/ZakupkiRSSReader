package ru.bjcreslin.domain.fromHtml;

import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ProcedureDispatcher {
    private Map<String, BiConsumer<ProcedureFromHtmlParser, String>> map;

    public ProcedureDispatcher() {
        map = new HashMap<>();
        map.put("Номер закупки", this::setNumber);
        map.put("Наименование закупки", this::setName);
        map.put("Наименование электронной площадки", this::setPlatform);
        map.put("Наименование организации", this::setSponsorName);
        map.put("Дата и время окончания срока подачи заявок", this::setDeadline);

    }

    private void setDeadline(ProcedureFromHtmlParser procedureFromHtmlParser, String s) {
        procedureFromHtmlParser.setDeadline(s);
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
