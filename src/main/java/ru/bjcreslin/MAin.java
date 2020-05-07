package ru.bjcreslin;

import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class MAin {
    public static void main(String[] args) {
        var Rss = new RSSService();
        var XML = new XMLService();

        try {
            var response = Rss.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
            var result = XML.getItemCollection(response);
           // result.forEach(System.out::println);
            var num=0;
//            System.out.println(result.get(num).getTitle());
//            System.out.println(result.get(num).getLink());
//            System.out.println(result.get(num).getAuthor());
//            System.out.println(result.get(num).getPubDate());
//            System.out.println(result.get(num).getDescription());
//            System.out.println("------------------------------------------------------------------------------------------");
           var transResult=result.get(num).getDescription().replace("<strong>Наименование объекта закупки: </strong>","");
            transResult=transResult.replace("<strong>Закупки по: </strong> ","")
                    .replace("<strong>Этап закупки: </strong>","")
                    .replace("<strong>Размещение выполняется по: </strong>","")
                    .replace("<strong>Наименование Заказчика: </strong>","")
                    .replace("<strong>Начальная цена контракта: </strong>","")
                    .replace("<strong> Валюта: </strong>Российский рубль","")
                    .replace("<strong>Размещено: </strong>","")
                    .replace("<strong>Обновлено: </strong>","")
                    .replace("<strong>Этап размещения: </strong>>","");

            var o1=Arrays.asList(transResult.split("<br/>"));

            o1.forEach(System.out::println);

       //     System.out.println(Arrays.toString(result.get(num).getDescription().split("<br/>")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}
