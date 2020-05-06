package ru.bjcreslin;

import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MAin {
    public static void main(String[] args) {
        var Rss = new RSSService();
        var XML = new XMLService();

        try {
            var response = Rss.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
            var result = XML.getItemCollection(response);
            result.forEach(System.out::println);
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
