package ru.bjcreslin.domain.dto;

import java.util.Map;
import java.util.function.BiConsumer;

public interface ProcedureDispacher {
     Map<String, BiConsumer<ProcedureFromHtmlParser, String>> getMap();
}
