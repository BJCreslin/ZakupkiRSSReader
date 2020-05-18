package ru.bjcreslin.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bjcreslin.domain.dto.ItemDto;

public interface ItemRepo extends MongoRepository<ItemDto,String> {

}
