package ru.bjcreslin.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.Item;
import ru.bjcreslin.domain.fromXML.ItemDto;
import ru.bjcreslin.domain.repo.ItemRepo;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class ItemDomainService {
    ItemRepo itemRepo;

    @Autowired
    public ItemDomainService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    /**
     * метод сохранения ItemDto в базу Item
     *
     * @param itemDto объект
     * @return сохраненный объект
     */
    public Item saveNeeded(ItemDto itemDto) {

        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        item.setNeeded(true);
        return itemRepo.insert(item);

    }


    /**
     * Метод- есть ли в базе объект с uin таким то
     *
     * @param uin - uin искомого объекта
     * @return true ессли есть, false если нет
     */
    public boolean isItemDtoExistByUin(Long uin) {
        Example<ItemDto> example = getItemDtoExample(uin);
        return itemRepo.exists(example);
    }

    /**
     * Метод получения Итем из базы по uin
     *
     * @param uin искомого объекта
     * @return Optional от itemDto
     */

    public Optional<ItemDto> getItemByUin(Long uin) {
        Example<ItemDto> example = getItemDtoExample(uin);
        return itemRepo.findOne(example);
    }


    /**
     * Метод получения количества элементов
     * @return количество
     */
    public Long count() {
        return itemRepo.count();
    }


    /**
     * Метод получения example из uin
     *
     * @param uin получаемый uin
     * @return Example
     */

    private Example<ItemDto> getItemDtoExample(Long uin) {
        ExampleMatcher uinMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("uin", ignoreCase());
        ItemDto probe = new ItemDto();
        probe.setUin(uin);
        return Example.of(probe, uinMatcher);
    }

}
