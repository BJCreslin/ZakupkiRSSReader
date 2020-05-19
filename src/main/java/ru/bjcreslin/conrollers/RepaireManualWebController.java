package ru.bjcreslin.conrollers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import ru.bjcreslin.configuration.Constants;
import ru.bjcreslin.configuration.RSSServerConfiguration;
import ru.bjcreslin.conrollers.Exception.BadRequestException;
import ru.bjcreslin.conrollers.Exception.ConflictException;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.service.ItemDomainService;
import ru.bjcreslin.service.ItemDtoManipulationService;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static ru.bjcreslin.configuration.Constants.REPAIR_CONTROLLER;

@Api
@Controller
@RequestMapping(REPAIR_CONTROLLER)
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

    @GetMapping("")
    @ResponseBody
    public List<ItemDto> getAllFromServerZakupki() throws IOException, InterruptedException, ParserConfigurationException, SAXException {

        var response = rssService.getXMLFromServer(RSSServerConfiguration.QUERY_STRING);
        var resultXml = xmlService.getItemCollection(response);
        var resultItem = itemDtoManipulationService.createItemDtoCollectionFromItemFromXmlList(resultXml);

        //StringBuilder result = new StringBuilder();
        //resultItem.forEach((x) -> result.append(x.toString()));

        return resultItem;
    }


    @GetMapping(path = Constants.PAGE_PREFIX + "{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Page<ItemDto> page(@PathVariable int page, @PathVariable int size) {
        //Проверяем номер страницы и размер
        if (page < 1 || size < 1) {
            throw new BadRequestException();
        }
        Page<ItemDto> result = itemDomainService.getPage(page, size);
        if (result == null) {
            throw new ConflictException();
        }
        return result;
    }

    @GetMapping("/count")
    @ResponseBody
    public long count() {
        return itemDomainService.count();
    }
}
