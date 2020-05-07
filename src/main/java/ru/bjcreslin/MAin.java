package ru.bjcreslin;

import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.service.ItemDtoManipulationService;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MAin {
    public static void main(String[] args) {
        var rss = new RSSService();
        var xmlService = new XMLService();
        var itemDtoManipulationService = new ItemDtoManipulationService();
        try {
            var response = rss.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
            var result = xmlService.getItemCollection(response);
            var resultItem = itemDtoManipulationService.createItemDtoCollectionFromItemFromXmlList(result);
            resultItem.forEach(System.out::println);

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
