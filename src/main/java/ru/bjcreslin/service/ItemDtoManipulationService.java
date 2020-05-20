package ru.bjcreslin.service;

import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.fromXML.ItemFromXML;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис манипуляции с объектами ItemDto
 */
@Service
public class ItemDtoManipulationService {

    /**
     * Метод делает список ItemDto из коллекции ItemFromXML
     *
     * @param xmlList коллекция ItemFromXML
     * @return список ItemDto
     */
    public List<ItemDto> createItemDtoCollectionFromItemFromXmlList(List<ItemFromXML> xmlList) {
        return xmlList.stream().map(this::createNewItemDtoFromItemFromXML).collect(Collectors.toList());
    }

    /**
     * Создаётся новый ItemDto из ItemFromXML
     *
     * @param itemFromXML, получен из RSS
     * @return объект
     */
    public ItemDto createNewItemDtoFromItemFromXML(ItemFromXML itemFromXML) {
        itemFromXML.setDescription(removalUnnecessaryInformationFromDescriptionField(itemFromXML.getDescription()));
        return copyFileldsFromItemFromXmlToitemDto(itemFromXML);
    }

    /**
     * метод превращения данных из данных, полученных в XML, в бизнес данные
     *
     * @param itemFromXML исходный "очищенный" объект
     * @return конечный объект
     */
    private ItemDto copyFileldsFromItemFromXmlToitemDto(ItemFromXML itemFromXML) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var tempListForManipulation = Arrays.asList(itemFromXML.getDescription().split("<br/>"));
        var linkTemp = itemFromXML.getLink().replace("<link>", "").replace("</link>", "");

        return ItemDto.builder()
                .author(itemFromXML.getAuthor())
                .lawNumber(tempListForManipulation.get(1))  //1  : ПП РФ 615 (Капитальный ремонт)
                .placementPhase(tempListForManipulation.get(11))
                .initialContractprice(new BigDecimal(tempListForManipulation.get(8)))
                .posted(LocalDate.parse(tempListForManipulation.get(9), dateTimeFormatter))
                .updated(LocalDate.parse(tempListForManipulation.get(10), dateTimeFormatter))
                .name(tempListForManipulation.get(5))
                .placementStages(tempListForManipulation.get(2))
                .link(linkTemp)
                .uin(linkTemp.replace("/epz/order/notice/ea615/view/common-info.html?regNumber=", ""))
                .build();
    }


    /**
     * Метод удаления ненужной информации из поля описания Закупки itemFromXML
     *
     * @param descriptionField поле описания Закупки itemFromXML- String
     * @return "очищенное от мусора" значение String
     */
    private String removalUnnecessaryInformationFromDescriptionField(String descriptionField) {
        return descriptionField
                .replace("<strong>Наименование объекта закупки: </strong>", "")
                .replace("<strong>Закупки по: </strong> ", "")
                .replace("<strong>Этап закупки: </strong>", "")
                .replace("<strong>Размещение выполняется по: </strong>", "")
                .replace("<strong>Наименование Заказчика: </strong>", "")
                .replace("<strong>Начальная цена контракта: </strong>", "")
                .replace("<strong> Валюта: </strong>Российский рубль", "")
                .replace("<strong>Размещено: </strong>", "")
                .replace("<strong>Обновлено: </strong>", "")
                .replace("<strong>Этап размещения: </strong>", "")
                ;
    }
}
