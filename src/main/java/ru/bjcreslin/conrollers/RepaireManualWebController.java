package ru.bjcreslin.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.domain.service.ItemDomainService;
import ru.bjcreslin.service.ItemDtoManipulationService;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
@RequestMapping("/goszakupki/repair")
public class RepaireManualWebController {

    private final RSSService rssService;
    private final XMLService xmlService;
    private final ItemDtoManipulationService itemDtoManipulationService;
    private final ItemDomainService itemDomainService;

    @Autowired
    public RepaireManualWebController(RSSService rssService, XMLService xmlService, ItemDtoManipulationService itemDtoManipulationService,
                                      ItemDomainService itemDomainService) {
        this.rssService = rssService;
        this.xmlService = xmlService;
        this.itemDtoManipulationService = itemDtoManipulationService;
        this.itemDomainService = itemDomainService;
    }

    @GetMapping("/")
    @ResponseBody
    public String getAll() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        StringBuilder result = new StringBuilder();
        var response = rssService.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
        var resultXml = xmlService.getItemCollection(response);
        var resultItem = itemDtoManipulationService.createItemDtoCollectionFromItemFromXmlList(resultXml);
        resultItem.forEach((x) -> result.append(x.toString()));

        return result.toString();
    }

    @GetMapping("/count")
    @ResponseBody
    public long count() {
        return itemDomainService.count();
    }
}