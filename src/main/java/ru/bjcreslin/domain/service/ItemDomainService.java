package ru.bjcreslin.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.Item;
import ru.bjcreslin.domain.fromXML.ItemDto;
import ru.bjcreslin.domain.repo.ItemRepo;

@Service
public class ItemDomainService {
    ItemRepo itemRepo;

    @Autowired
    public ItemDomainService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Item saveNeeded(ItemDto itemDto) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        item.setNeeded(true);
        return itemRepo.insert(item);
    }
}
