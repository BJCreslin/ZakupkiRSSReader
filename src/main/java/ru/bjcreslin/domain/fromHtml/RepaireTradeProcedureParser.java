package ru.bjcreslin.domain.fromHtml;


import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import ru.bjcreslin.configuration.Constants;
import ru.bjcreslin.configuration.RepairsServerConfiguration;

import java.io.IOException;
import java.util.List;

/**
 * парсер торговых процедур по капремонту.
 * Сделан из печатных форм объектов
 */
@Log
public class RepaireTradeProcedureParser {

    public String getResult(String uin) {
        String requestedAddress = RepairsServerConfiguration.ADRESS_OBJECT_FROM_SERVER_BY_UIN + uin;
        String result = "";
        try {
            var document = Jsoup.connect(requestedAddress)
                    .userAgent(Constants.USER_AGENT).get();
            var elements=document
                    .body()
                    .getElementsByTag("td")
                    .html()
                    .split("(<p class=\"parameter\">)|(<p class=\"parameterValue\">)");

            result="";
                    List.of(elements).stream().forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
