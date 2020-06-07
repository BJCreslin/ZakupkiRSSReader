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
    private ProcedureDispatcher dispatcher = new ProcedureDispatcher();

    public ProcedureFromHtmlParser getResult(String uin) {
        String requestedAddress = getRequestedAddressFromUin(uin);
        var tradeProcedure = new ProcedureFromHtmlParser();
        try {
            String[] elements = getLinesFromAddress(requestedAddress);
            tradeProcedure = makeProcedureFromLines(dispatcher, elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tradeProcedure;
    }

    private ProcedureFromHtmlParser makeProcedureFromLines(ProcedureDispatcher dispatcher, String[] elements) {
        var tradeProcedure = new ProcedureFromHtmlParser();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
            //   System.out.println(i+" :"+elements[i]);
            if (dispatcher.getMap().containsKey(elements[i].trim())) {
                dispatcher.getMap().get(elements[i]).
                        accept(tradeProcedure, elements[++i]);
            }
        }
        return tradeProcedure;
    }

    private String[] getLinesFromAddress(String requestedAddress) throws IOException {
        var document = Jsoup.connect(requestedAddress)
                .userAgent(Constants.USER_AGENT).get();
        return document
                .body()
                .getElementsByTag("td")
                .html()
                .replace("</p>", "")
                .split("(<p class=\"parameter\">)|(<p class=\"parameterValue\">)");
    }

    private String getRequestedAddressFromUin(String uin) {
        return RepairsServerConfiguration.ADRESS_OBJECT_FROM_SERVER_BY_UIN + uin;
    }


}
