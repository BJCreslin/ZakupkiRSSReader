package ru.bjcreslin.domain.fromHtml;


import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import ru.bjcreslin.configuration.Constants;
import ru.bjcreslin.configuration.RepairsServerConfiguration;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

import java.io.IOException;

/**
 * парсер торговых процедур по капремонту.
 * Сделан из печатных форм объектов
 */
@Log
public class RepaireTradeProcedureParser {

    public ProcedureFromHtmlParser getResult(String uin) {
        String requestedAddress = RepairsServerConfiguration.ADRESS_OBJECT_FROM_SERVER_BY_UIN + uin;
        var tradeProcedure = new ProcedureFromHtmlParser();
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
                    procedure.setNumber(elements[++i]);
                }
                if (elements[i].equals("Наименование закупки")) {
                    procedure.setName(elements[i++]);
                }

                if (elements[i].equals("Наименование электронной площадки")) {
                    procedure.setName(elements[i++]);
                }


            }
            var dispatcher = new ProcedureDispatcher();

            for (int i = 0; i < elements.length; i++) {
                elements[i]=elements[i].trim();
             //   System.out.println(i+" :"+elements[i]);
                if (dispatcher.getMap().containsKey(elements[i].trim())) {
                    dispatcher.getMap().get(elements[i]).
                            accept(tradeProcedure, elements[++i]);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return tradeProcedure;
    }


}
