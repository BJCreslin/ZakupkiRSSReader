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
        var newItemDto = new ItemDto();

        var transResult = removalUnnecessaryInformationFromDescriptionField(itemFromXML.getDescription());

        var o1 = Arrays.asList(transResult.split("<br/>"));

        return newItemDto;
    }


    /**
     * Метод удаления ненужной информации из поля описания Закупки itemFromXML
     * @param descriptionField  поле описания Закупки itemFromXML- String
     * @return  "очищенное от мусора" значение String
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
