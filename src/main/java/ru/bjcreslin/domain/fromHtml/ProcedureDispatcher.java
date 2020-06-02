package ru.bjcreslin.domain.fromHtml;

import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ProcedureDispatcher {
    private Map<String, BiConsumer<ProcedureFromHtmlParser, String>> map;

    public ProcedureDispatcher() {
        map = new HashMap<>();
        map.put("Номер закупки",number(ProcedureFromHtmlParser ,String number));

    }

    private BiConsumer<ProcedureFromHtmlParser, String> number(ProcedureFromHtmlParser procedureFromHtmlParser,String number) {

    }
}
