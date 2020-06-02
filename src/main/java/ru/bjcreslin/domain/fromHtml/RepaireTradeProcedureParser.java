package ru.bjcreslin.domain.fromHtml;


import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import ru.bjcreslin.configuration.Constants;
import ru.bjcreslin.configuration.RepairsServerConfiguration;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

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
            var elements = document
                    .body()
                    .getElementsByTag("td")
                    .html()
                    .replace("</p>", "")
                    .split("(<p class=\"parameter\">)|(<p class=\"parameterValue\">)");

            ProcedureFromHtmlParser procedure = new ProcedureFromHtmlParser();
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].equals("Номер закупки")) {
                    procedure.setNumber(elements[i++]);
                }
                if (elements[i].equals("Наименование закупки")) {
                    procedure.setName(elements[i++]);
                }

 if (elements[i].equals("Наименование электронной площадки")) {
                    procedure.setName(elements[i++]);
                }


            }


            result = "";
            List.of(elements).stream().forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
