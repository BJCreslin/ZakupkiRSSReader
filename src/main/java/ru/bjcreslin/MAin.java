package ru.bjcreslin;

import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.service.ItemDtoManipulationService;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class MAin {
    public static void main(String[] args) {
        var rss = new RSSService();
        var xmlService = new XMLService();
        var itemDtoManipulationService = new ItemDtoManipulationService();
        try {
            var response = rss.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
            var result = xmlService.getItemCollection(response);
            // result.forEach(System.out::println);
            var num = 0;
//            System.out.println(result.get(num).getTitle());
//            System.out.println(result.get(num).getLink());
//            System.out.println(result.get(num).getAuthor());
//            System.out.println(result.get(num).getPubDate());
//            System.out.println(result.get(num).getDescription());
//            System.out.println("------------------------------------------------------------------------------------------");
//           var transResult=result.get(num).getDescription().replace("<strong>Наименование объекта закупки: </strong>","");
//            transResult=transResult.replace("<strong>Закупки по: </strong> ","")
//                    .replace("<strong>Этап закупки: </strong>","")
//                    .replace("<strong>Размещение выполняется по: </strong>","")
//                    .replace("<strong>Наименование Заказчика: </strong>","")
//                    .replace("<strong>Начальная цена контракта: </strong>","")
//                    .replace("<strong> Валюта: </strong>Российский рубль","")
//                    .replace("<strong>Размещено: </strong>","")
//                    .replace("<strong>Обновлено: </strong>","")
//                    .replace("<strong>Этап размещения: </strong>>","");
//
//            var o1=Arrays.asList(transResult.split("<br/>"));
//
//            o1.forEach(System.out::println);

            ItemDto itemDto = itemDtoManipulationService.createNewItemDtoFromItemFromXML(result.get(num));
            System.out.println("----------------------------------------");
            System.out.println(itemDto);
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
