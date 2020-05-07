package ru.bjcreslin.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.bjcreslin.domain.fromXML.ItemFromXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class XMLService {

    /**
     * Метод делаает из String RSS ленты коллекцию объектов Item
     *
     * @param xmlString строка - результат запроса RSS ленты
     * @return List Item
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public List<ItemFromXML> getItemCollection(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        List<ItemFromXML> resultList = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
        Document document = dBuilder.parse(input);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                ItemFromXML itemFromXML = ItemFromXML.builder()
                        .author(eElement.getElementsByTagName("author").item(0).getTextContent())
                        .description(eElement.getElementsByTagName("description").item(0).getTextContent())
                        .link(eElement.getElementsByTagName("link").item(0).getTextContent())
                        .title(eElement.getElementsByTagName("title").item(0).getTextContent())
                        .pubDate(eElement.getElementsByTagName("pubDate").item(0).getTextContent())
                        .build();
                resultList.add(itemFromXML);
            }

        }
        return resultList;
    }
}
