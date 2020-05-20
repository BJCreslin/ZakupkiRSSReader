package ru.bjcreslin.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.ItemDto;
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
    public ItemDto saveNeeded(ItemDto itemDto) {

        ItemDto item = new ItemDto();
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
    public boolean isItemDtoExistByUin(String uin) {
        Example<ItemDto> example = getItemDtoExample(uin);
        return itemRepo.exists(example);
    }

    /**
     * Метод получения Итем из базы по uin
     *
     * @param uin искомого объекта
     * @return Optional от itemDto
     */

    public Optional<ItemDto> getItemByUin(String uin) {
        Example<ItemDto> example = getItemDtoExample(uin);
        return itemRepo.findOne(example);
    }


    /**
     * Метод получения количества элементов
     *
     * @return количество
     */
    public Long count() {
        return itemRepo.count();
    }


    /**
     * Получение данных из БД с пагинацией
     *
     * @param page страница
     * @param size размер выборки на одной странице
     * @return Page
     */
    public Page<ItemDto> getPage(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return itemRepo.findAll(pageable);
    }


    /**
     * Метод получения example из uin
     *
     * @param uin получаемый uin
     * @return Example
     */

    private Example<ItemDto> getItemDtoExample(String uin) {
        ExampleMatcher uinMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("uin", ignoreCase());
        ItemDto probe = new ItemDto();
        probe.setUin(uin);
        return Example.of(probe, uinMatcher);
    }


}
