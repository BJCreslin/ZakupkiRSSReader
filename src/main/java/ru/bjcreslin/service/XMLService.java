package ru.bjcreslin.service;

import org.springframework.stereotype.Service;
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


@Service
public class XMLService {

    /**
     * Метод делаает из String RSS ленты коллекцию объектов Item
     *
     * @param xmlString строка - результат запроса RSS ленты
     * @return List Item
     * @throws ParserConfigurationException Ошибочки
     * @throws IOException                  Ошибочки
     * @throws SAXException                 Ошибочки
     */
    public List<ItemFromXML> getItemCollection(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        List<ItemFromXML> resultList = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
        Document document = dBuilder.parse(input);
        document.getDocumentElement().normalize();
        //Раскладываем документ по элементам (нодам) item
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                ItemFromXML itemFromXML = getItemFromXML((Element) node);
                resultList.add(itemFromXML);
            }
        }
        return resultList;
    }

    /**
     * Если нода является Element, то рачленяем её на куски, для объекта ItemFromXML
     *
     * @param node нода=Элемент
     * @return готовый объект
     */
    private ItemFromXML getItemFromXML(Element node) {
        return ItemFromXML.builder()
                .author(node.getElementsByTagName("author").item(0).getTextContent())
                .description(node.getElementsByTagName("description").item(0).getTextContent())
                .link(node.getElementsByTagName("link").item(0).getTextContent())
                .title(node.getElementsByTagName("title").item(0).getTextContent())
                .pubDate(node.getElementsByTagName("pubDate").item(0).getTextContent())
                .build();
    }
}
