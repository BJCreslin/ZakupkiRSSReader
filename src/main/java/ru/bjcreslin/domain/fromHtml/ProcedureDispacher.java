package ru.bjcreslin.domain.fromHtml;

import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

import java.util.Map;
import java.util.function.BiConsumer;

public interface ProcedureDispacher {
     Map<String, BiConsumer<ProcedureFromHtmlParser, String>> getMap();
}
