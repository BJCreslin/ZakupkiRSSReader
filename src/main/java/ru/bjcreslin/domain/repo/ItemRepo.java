package ru.bjcreslin.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bjcreslin.domain.dto.Item;

public interface ItemRepo extends MongoRepository<Item,String> {

}
