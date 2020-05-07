package ru.bjcreslin.service;

import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.fromXML.ItemFromXML;

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
        var o1 = Arrays.asList(itemFromXML.getDescription().split("<br/>"));
        o1.forEach(System.out::println);
        var newItemDto = ItemDto.builder()
                .author(itemFromXML.getAuthor())
                .lawNumber("gaaa")
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
                .replace("<strong>Этап размещения: </strong>>", "");
    }
}
