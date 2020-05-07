package ru.bjcreslin.service;

import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.fromXML.ItemFromXML;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Сервис манипуляции с объектами ItemDto
 */
public class ItemDtoManipulationService {


    /**
     * Создаётся новый ItemDto из ItemFromXML
     *
     * @param itemFromXML, получен из RSS
     * @return объект
     */
    public ItemDto createNewItemDtoFromItemFromXML(ItemFromXML itemFromXML) {
        itemFromXML.setDescription(removalUnnecessaryInformationFromDescriptionField(itemFromXML.getDescription()));
        var newItemDto = copyFileldsFromItemFromXmlToitemDto(itemFromXML);

        return newItemDto;
    }

    /**
     * метод превращения данных из данных, полученных в XML, в бизнес данные
     *
     * @param itemFromXML исходный "очищенный" объект
     * @return конечный объект
     */
    private ItemDto copyFileldsFromItemFromXmlToitemDto(ItemFromXML itemFromXML) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var o1 = Arrays.asList(itemFromXML.getDescription().split("<br/>"));

        for (int i = 0; i < o1.size(); i++) {
            System.out.println(i + "  : " + o1.get(i));
        }
        var linkTemp = itemFromXML.getLink().replace("<link>", "").replace("</link>", "");
        var newItemDto = ItemDto.builder()
                .author(itemFromXML.getAuthor())
                .lawNumber(o1.get(1))  //1  : ПП РФ 615 (Капитальный ремонт)
                .placementPhase(o1.get(11))
                .initialContractprice(new BigDecimal(o1.get(8)))
                .posted(LocalDate.parse(o1.get(9), dateTimeFormatter))
                .updated(LocalDate.parse(o1.get(10), dateTimeFormatter))
                .name(o1.get(5))
                .placementStages(o1.get(2))
                .link(linkTemp)
                .uin(Long.parseUnsignedLong(linkTemp.replace("/epz/order/notice/ea615/view/common-info.html?regNumber=", "")))
                .build();

        return newItemDto;
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
