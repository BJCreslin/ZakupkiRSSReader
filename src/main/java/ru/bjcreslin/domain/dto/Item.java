package ru.bjcreslin.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.bjcreslin.domain.fromXML.ItemDto;


@Data
public class Item extends ItemDto {

}
