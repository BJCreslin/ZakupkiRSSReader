package ru.bjcreslin.conrollers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;
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
import ru.bjcreslin.conrollers.Exception.ServiceUnavailable;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.service.ItemDomainService;
import ru.bjcreslin.service.ItemDtoManipulationService;
import ru.bjcreslin.service.RSSService;
import ru.bjcreslin.service.XMLService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static ru.bjcreslin.configuration.Constants.REPAIR_CONTROLLER;

@CrossOrigin
@Log
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

    @ApiOperation(value = "выдает все данные с сервера zakupki.gov.ru")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Переданы данные"),
            @ApiResponse(code = 503, message = "ОШибка запроса к серверу zakupki или нечитаемый объект ответа"),
    })

    @GetMapping("")
    @ResponseBody
    public List<ItemDto> getAllFromServerZakupki() {
        try {
            var response = rssService.getXMLFromServer(RSSServerConfiguration.QUERY_ACTING_STRING);
            var resultXml = xmlService.getItemCollection(response);
            var resultItem = itemDtoManipulationService.createItemDtoCollectionFromItemFromXmlList(resultXml);
            log.info("Data to controller: " + resultItem.size() + " numbers.");
            return resultItem;
        } catch (IOException | InterruptedException | SAXException | ParserConfigurationException e) {
            log.severe("Error in getting data from zakupki" + e.getMessage());
        }
        throw new ServiceUnavailable();
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
