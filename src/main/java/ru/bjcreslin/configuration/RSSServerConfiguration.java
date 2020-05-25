package ru.bjcreslin.configuration;

public interface RSSServerConfiguration {
    String USER_AGENT="Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0";

    // Запрос капремонт в ТО  Весь
   String QUERY_ALL_ROCEDURE_STRING="https://zakupki.gov.ru/tinyurl/8f04c559-63d1-4629-8e42-03b3d327dd82";

    // Запрос капремонт в ТО  Кроме отмененных и завершенных
    String QUERY_ACTING_STRING="https://zakupki.gov.ru/tinyurl/1788f02a-8987-468c-acfe-3e340d588d4a";

}

